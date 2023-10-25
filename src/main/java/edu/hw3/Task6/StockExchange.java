package edu.hw3.Task6;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.PriorityQueue;

public class StockExchange implements StockMarket{
    private final PriorityQueue<Stock> queue = new PriorityQueue<>(Collections.reverseOrder());
    @Override
    public void add(Stock stock) {
        queue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        queue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return queue.peek();
    }

    public PriorityQueue<Stock> queue(){
        return queue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(queue.toArray());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (StockExchange) obj;
        return Arrays.equals(this.queue.toArray(), that.queue.toArray());
    }

    public static void main(String[] args) {
        StockExchange stock = new StockExchange();
        stock.add(new Stock("RZB", 100));
        stock.add(new Stock("GLB", 250));
        System.out.println(stock.queue);
        System.out.println(stock.mostValuableStock());

        StockExchange stock2 = new StockExchange();

        stock2.add(new Stock("RZB", 100));
        stock2.add(new Stock("GLB", 250));

        System.out.println(stock.queue);
        System.out.println(stock2.queue);

    }
}
