package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;


public class Task8 {
    static class BackwardIterator<T> implements Iterator<T> {
        private final Iterator<T> iterator;

        BackwardIterator(Collection<T> collection) {
            LinkedList<T> reversedList = new LinkedList<>(collection);
            LinkedList<T> temp = new LinkedList<>();
            for (T item : reversedList) {
                temp.addFirst(item);
            }
            reversedList = temp;
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
