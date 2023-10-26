package edu.hw3.Task6;


import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final String name;
    private final Integer cost;

    public Stock(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Stock["
            + "name=" + name + ", "
            + "cost=" + cost + ']';
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
        return Objects.equals(this.name, that.name)
            && Objects.equals(this.cost, that.cost);
    }
}
