package edu.hw3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


public class Task8 {
    static class BackwardIterator<T> implements Iterator<T> {
        private final Iterator<T> iterator;

        public BackwardIterator(Collection<T> collection) {
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

    public static void main(String[] args) {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("1", "1");
        hash.put("2", "2");
        hash.put("3", "3");

        BackwardIterator<Map.Entry<String, String>> iterator = new BackwardIterator<>(hash.entrySet());
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
