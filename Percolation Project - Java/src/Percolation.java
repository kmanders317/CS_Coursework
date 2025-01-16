import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.ArrayList;
import java.util.List;


public class Percolation {
    WeightedQuickUnionUF gridUnion;
    WeightedQuickUnionUF gridVirtual;
    int totalElements;
    int N;
    boolean[][] grid;
    int numOpen;
    int virtualTop;
    int virtualBottom;


    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        gridUnion = new WeightedQuickUnionUF(N * N + 1);
        gridVirtual = new WeightedQuickUnionUF(N * N + 2);
        totalElements = N * N;
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        this.N = N;
        numOpen = 0;
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        //        for (int j = 0; j < N; j++) {
        //            gridUnion.union(j, virtualTop);
        //            gridVirtual.union(j, virtualTop);
        //            gridVirtual.union(N * N - 1 - j, virtualBottom);
        //        }

    }

    private int coordToNum(int row, int col) {
        return row * N + col;
    }

    private List<Integer> numToCoor(int num) {
        int row = Math.floorDiv(num, N);
        int col = num % N;
        List<Integer> returnList = new ArrayList<Integer>();
        returnList.add(row);
        returnList.add(col);
        return returnList;

    }

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        int currNum = coordToNum(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpen++;
            if (row == 0) {
                gridVirtual.union(currNum, virtualTop);
                gridUnion.union(currNum, virtualTop);
            }
            if (row == N - 1) {
                gridVirtual.union(currNum, virtualBottom);
            }
            if ((row != 0) && grid[row - 1][col]) {
                int aboveNum = coordToNum(row - 1, col);
                gridUnion.union(currNum, aboveNum);
                gridVirtual.union(currNum, aboveNum);
            }
            if ((col != 0) && grid[row][col - 1]) {
                int leftNum = coordToNum(row, col - 1);
                gridUnion.union(currNum, leftNum);
                gridVirtual.union(currNum, leftNum);
            }
            if ((row != N - 1) && grid[row + 1][col]) {
                int bottomNum = coordToNum(row + 1, col);
                gridUnion.union(bottomNum, currNum);
                gridVirtual.union(bottomNum, currNum);
            }
            if ((col != N - 1) && grid[row][col + 1]) {
                int rightNum = coordToNum(row, col + 1);
                gridUnion.union(rightNum, currNum);
                gridVirtual.union(rightNum, currNum);
            }

        }

    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        //        if (row == N - 1) {
        //            this.isOpen(row, col) && gridUnion.connected(coordToNum(row, col), virtualTop)
        //        }
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        return this.isOpen(row, col) && gridUnion.connected(coordToNum(row, col), virtualTop);
        // if (this.isOpen(row, col)) {
        //            if(row == 0) {
        //                return true;
        //            }
        //            int num = coordToNum(row, col);
        //            for(int i = 0; i < N; i++) {
        //                if (gridUnion.connected(num, i)) {
        //                    return true;
        //                }
        //            }

        //            int root = gridUnion.find(num);
        //            System.out.println(numToCoor(root).get(0) == 0);
        //            return numToCoor(root).get(0) == 0;
        //}
        // return false;

    }

    public int numberOfOpenSites() {
        return numOpen;
    }

    public boolean percolates() {
        return gridVirtual.connected(virtualBottom, virtualTop);
        //        for (int i = 0; i < N; i++) {
        //            if (isFull(N-1, i)) {
        //                return true;
        //            }
        //        }
        //        return false;
    }

}
