package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Task8 {
    static class BackwardIterator<T> implements Iterator<T> {
        private final Iterator<T> iterator;

        BackwardIterator(Collection<T> collection) {
            LinkedList<T> reversedList = new LinkedList<>();
            for (T item : collection) {
                reversedList.addFirst(item);
            }
            iterator = reversedList.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return iterator.next();
        }
    }
}
