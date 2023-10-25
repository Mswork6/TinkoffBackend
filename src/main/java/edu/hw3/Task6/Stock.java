package edu.hw3.Task6;

import edu.hw3.Task5;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.PriorityQueue;

public class Stock implements Comparable<Stock> {
    private final String name;
    private final Integer cost;
    public Stock(String name, int cost){
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Stock[" +
            "name=" + name + ", " +
            "cost=" + cost + ']';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }

    @Override
    public int compareTo(@NotNull Stock stock) {
        return cost.compareTo(stock.cost);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Stock) obj;
        return Objects.equals(this.name, that.name) &&
            Objects.equals(this.cost, that.cost);
    }

//    public static void main(String[] args) {
//        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
//        pq.offer(20);
//        pq.offer(30);
//        pq.offer(3);
//        pq.offer(25);
//
//
//        System.out.println(pq.poll());
//        System.out.println(pq);
//    }
}
