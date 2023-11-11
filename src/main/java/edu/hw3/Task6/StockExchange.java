package edu.hw3.Task6;

import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@EqualsAndHashCode
public class StockExchange implements StockMarket {
    private final Queue<Stock> stockQueue = new PriorityQueue<>(Collections.reverseOrder());

    @Override
    public void add(Stock stock) {
        stockQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockQueue.peek();
    }

    public List<Stock> getStocks() {
        return new ArrayList<>(stockQueue);
    }
}
