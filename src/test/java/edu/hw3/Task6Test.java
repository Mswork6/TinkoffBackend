package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockExchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class Task6Test {
    @Test
    @DisplayName("Проверка добавления элементов в очередь")
    void addTest() {
        // given
        StockExchange result = new StockExchange();

        // when
        result.add(new Stock("MIT", 300));
        result.add(new Stock("XLE", 236));
        result.add(new Stock("LIJ", 438));
        List<Stock> resultList = result.getStocks();

        // then
        Queue<Stock> answer = new PriorityQueue<>(Collections.reverseOrder());
        answer.add(new Stock("MIT", 300));
        answer.add(new Stock("XLE", 236));
        answer.add(new Stock("LIJ", 438));
        List<Stock> answerList = new ArrayList<>(answer);



        assertEquals(resultList, answerList);
    }

    @Test
    @DisplayName("Проверка удаления элементов из очереди")
    void removeTest() {
        // given
        StockExchange result = new StockExchange();

        // when
        result.add(new Stock("MIT", 300));
        result.add(new Stock("XLE", 236));
        result.add(new Stock("LIJ", 438));
        result.remove(new Stock("XLE", 236));
        List<Stock> resultList = result.getStocks();

        // then
        Queue<Stock> answer = new PriorityQueue<>(Collections.reverseOrder());
        answer.add(new Stock("MIT", 300));
        answer.add(new Stock("LIJ", 438));
        List<Stock> answerList = new ArrayList<>(answer);

        assertEquals(resultList, answerList);
    }

    @Test
    @DisplayName("Проверка нахождения самой дорогой акции")
    void mostValuableStockTest() {
        // given
        StockExchange result = new StockExchange();

        // when
        result.add(new Stock("MIT", 300));
        result.add(new Stock("XLE", 236));
        result.add(new Stock("LIJ", 438));
        result.remove(new Stock("XLE", 236));
        result.add(new Stock("TFI", 976));

        Stock stockResult = result.mostValuableStock();

        // then
        Stock answer = new Stock("TFI", 976);

        assertEquals(stockResult, answer);
    }

    @Test
    @DisplayName("Проверка нахождения самой дорогой акции, добавленной в конце")
    void mostValuableStockTest2() {
        // given
        StockExchange result = new StockExchange();

        // when
        result.add(new Stock("MIT", 300));
        result.add(new Stock("XLE", 236));
        result.add(new Stock("LIJ", 438));
        result.remove(new Stock("XLE", 236));
        result.remove(new Stock("LIJ", 438));
        result.remove(new Stock("MIT", 300));
        result.add(new Stock("RUR", 777));
        result.add(new Stock("TFI", 976));

        Stock stockResult = result.mostValuableStock();

        // then
        Stock answer = new Stock("TFI", 976);

        assertEquals(stockResult, answer);
    }

    @Test
    @DisplayName("Проверка нахождения самой дорогой акции, добавленной в начале")
    void mostValuableStockTest3() {
        // given
        StockExchange result = new StockExchange();

        // when
        result.add(new Stock("DEL", 1300));
        result.add(new Stock("MIT", 300));
        result.add(new Stock("XLE", 236));
        result.add(new Stock("LIJ", 438));
        result.add(new Stock("RUR", 777));
        result.add(new Stock("TFI", 976));

        Stock stockResult = result.mostValuableStock();

        // then
        Stock answer = new Stock("DEL", 1300);

        assertEquals(stockResult, answer);
    }

    @Test
    @DisplayName("Проверка нахождения самой дорогой акции среди нескольких"
        + "с одинаковой ценой")
    void mostValuableStockFromEquals() {
        // given
        StockExchange result = new StockExchange();

        // when
        result.add(new Stock("DRM", 430));
        result.add(new Stock("MLP", 1300));
        result.add(new Stock("LPS", 977));
        result.add(new Stock("LXS", 1300));

        Stock stockResult = result.mostValuableStock();

        // then
        Stock answer = new Stock("MLP", 1300);

        assertEquals(stockResult, answer);
    }

}
