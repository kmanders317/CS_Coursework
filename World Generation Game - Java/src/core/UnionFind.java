package core;
import java.util.ArrayList;


public class UnionFind {
    private ArrayList<Integer> unionList = new ArrayList<>();
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        for (int i = 0; i < N; i++) {
            unionList.add(-1);
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        int i = find(v);
        return Math.abs(unionList.get(i));
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return unionList.get(v);
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v >= unionList.size()) {
            throw new IllegalArgumentException();
        }
        if (unionList.get(v) < 0) {
            return v;
        }
        if (unionList.get(unionList.get(v)) < 0) {
            return unionList.get(v);
        }
        pathCompressIteration1(v);
        return unionList.get(v);
    }

    public int findRoot(int v) {
        if (unionList.get(v) < 0) {
            return v;
        } else {
            return findRoot(unionList.get(v));
        }
    }

    private void pathCompressIteration1(int v) {
        //int root = findRoot(v);
        int root = findRoot(v);
        int index = v;
        //unionList.set(v, root);
        ArrayList<Integer> temp = new ArrayList<>();
        while (unionList.get(index) >= 0) {
            temp.add(index);
            index = unionList.get(index);
        }
        for (int i : temp) {
            unionList.set(i, root);
        }
    }

    private void pathCompressIteration(int v) {
        int root = findRoot(v);
        int index = v;
        unionList.set(v, root);
        while (unionList.get(index) >= 0) {
            index = unionList.get(index);
            unionList.set(index, root);

        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        if (v1 != v2 && !connected(v1, v2)) {
            int bigger = v2;
            int smaller = v1;
            if (this.sizeOf(v1) > this.sizeOf(v2)) {
                bigger = v1;
                smaller = v2;
            }
            int smallerSize = unionList.get(find(smaller));
            int biggerSize = unionList.get(find(bigger));
            unionList.set(find(smaller), find(bigger));
            unionList.set(find(bigger), biggerSize + smallerSize);
        }

    }

}
