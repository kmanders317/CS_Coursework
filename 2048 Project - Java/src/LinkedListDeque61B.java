//package deque;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList; // import the ArrayList class

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        //first = new Node(sentinel, null, sentinel);
        size = 0;
    }

    public class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }

    }


    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;

    }


    @Override
    public void addLast(T x) {
        Node p = sentinel.prev;
        p.next = new Node(p, x, sentinel);
        sentinel.prev = p.next;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.item != null) {
                returnList.add(p.item);
            }
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next.item == null;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public T removeFirst() {
        //Node first = sentinel.next;
        if (this.isEmpty()) {
            return null;
        }
        T returnVal = sentinel.next.item;
        //Node a = first.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return returnVal;
    }


    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T returnVal = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return returnVal;
    }


    @Override
    public T get(int index) {
        T target = null;
        Node current = sentinel.next;
        if (index >= this.size()) {
            return null;
        }
        if (index == 0) {
            return current.item;
        }
        for (int i = 0; i < this.size(); i++) {
            if (i == index) {
                return current.item;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= this.size()) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next, index - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
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
        if (o instanceof LinkedListDeque61B oas) {
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
        } else if (o instanceof ArrayDeque61B oas) {
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

