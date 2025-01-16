import java.util.Iterator;
import java.util.List;
//import java.lang.Math;
import java.util.ArrayList;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int nextFirst;
    private int nextLast;
    private int size;
    private T[] items;
    private final int NUMMIN = 15;
    public ArrayDeque61B() {
        size = 0;
        nextFirst = 7;
        nextLast = 0;
        items = (T[]) new Object[8];
    }

    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            a[i] = this.get(i);
        }

        //        for (int i = nextFirst + 1; i < items.length; i++) {
        //            a[i - nextFirst - 1] = (items[i]);
        //        }
        //        for (int j = 0; j < nextLast; j++) {
        //            a[j + items.length - nextFirst - 1] = (items[j]);
        //        }
        items = a;
        nextFirst = a.length - 1;
        nextLast = size;
    }

    private void resizeDown(int cap) {
        T[] a = (T[]) new Object[cap];
        for (int i = nextFirst + 1; i < items.length; i++) {
            a[i - nextFirst - 1] = (items[i]);
        }
        for (int j = 0; j < nextLast; j++) {
            a[j + items.length - nextFirst - 1] = (items[j]);
        }
        items = a;
        nextFirst = a.length - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size++;
        //nextFirst = Math.floorMod(nextFirst - 1, items.length);
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size++;
        //nextLast = Math.floorMod(nextLast + 1, items.length);
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (size == 0) {
            return returnList;
        }
        for (int i = 0; i < size; i++) {
            returnList.add(this.get(i));
        }
        //        for (int i = nextFirst + 1; i < items.length; i++) {
        //            returnList.add(items[i]);
        //        }
        //        for (int j = 0; j < nextLast; j++) {
        //            returnList.add(items[j]);
        //        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T val;
        if (size == 0) {
            return null;
        }
        if (size <= (items.length * 0.25) && items.length > NUMMIN) {
            resize(items.length / 2);
        }
        size--;
        nextFirst = Math.floorMod(nextFirst + 1, items.length);
        val = items[nextFirst];
        items[nextFirst] = null;
        return val;
    }

    @Override
    public T removeLast() {
        T val;
        if (size == 0) {
            return null;
        }
        if (size <= (items.length * 0.25) && items.length > NUMMIN) {
            resize(size * 2);
        }
        //        if (nextLast == 0) {
        //            val = items[items.length - 1];
        //        }
        //        else {
        //            val = items[nextLast - 1];
        //        }
        size--;
        nextLast = Math.floorMod(nextLast - 1, items.length);
        val = items[nextLast];
        items[nextLast] = null;
        return val;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int adjustedIndex = Math.floorMod(nextFirst + 1 + index, items.length);
        return items[adjustedIndex];
        //        if (index < (items.length - nextFirst)) {
        //            return items[nextFirst + 1 + index];
        //        } else if (index < (items.length - nextFirst + nextLast)) {
        //            return items[index - (size - nextFirst + 1)];
        //        }

    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61B.ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;
        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = (T) get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque61B oas) {
            // check sets are of the same size
            if (oas.size != this.size) {
                return false;
            }

            // check that all of MY items are in the other array set
            Iterator<T> seer = this.iterator();
            Iterator<T> seer2 = oas.iterator();
            while (seer.hasNext()) {
                T i = seer.next();
                T j = seer2.next();
                if (!j.equals(i)) {
                    return false;
                }

            }

            return true;
        } else if (o instanceof LinkedListDeque61B oas) {
            // check sets are of the same size
            if (oas.size() != this.size) {
                return false;
            }

            // check that all of MY items are in the other array set
            Iterator<T> seer = this.iterator();
            Iterator<T> seer2 = oas.iterator();
            while (seer.hasNext()) {
                T i = seer.next();
                T j = seer2.next();
                if (!j.equals(i)) {
                    return false;
                }

            }
            return true;

        }
        // o is not an arrayset, so return false
        return false;
    }

    @Override
    public String toString() {
        return toList().toString();
    }

}
