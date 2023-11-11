package edu.hw3.Task6;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@ToString
@EqualsAndHashCode
public class Stock implements Comparable<Stock> {
    private final String name;
    private final Integer cost;

    public Stock(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public int compareTo(@NotNull Stock stock) {
        return cost.compareTo(stock.cost);
    }

}
