package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockExchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

        // then
        StockExchange answer = new StockExchange();
        answer.add(new Stock("MIT", 300));
        answer.add(new Stock("XLE", 236));
        answer.add(new Stock("LIJ", 438));

        assertEquals(result, answer);
        /*Я здесь сравниваю экземпляры класса, потому что немного не разобрался как сравнивать
        * сами очереди. У них всё время разные хеши и из-за этого проверка на равенство всегда
        * оказывалась ложной :-(
        * Я был бы рад, если бы вы мне смогли как то этот момент прояснить че я не так сделал*/

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

        // then
        StockExchange answer = new StockExchange();
        answer.add(new Stock("MIT", 300));
        answer.add(new Stock("LIJ", 438));

        assertEquals(result, answer);
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

}
