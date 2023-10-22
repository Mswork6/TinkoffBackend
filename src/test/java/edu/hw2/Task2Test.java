package edu.hw2;

import edu.hw2.Task2.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Расчёт площади квадрата с разными сторонами")
    void calculateIncorrectSquare() {
        // given
        var square = new Square(50).setWidth(20).setHeight(40);

        // when
        double result = square.area();

        // then
        assertThat(result).isEqualTo(800.0);
    }

    @Test
    @DisplayName("Расчёт площади квадрата с одинаковыми сторонами")
    void calculateCorrectSquare() {
        // given
        var square = new Square(20).setWidth(40).setHeight(40);

        // when
        double result = square.area();

        // then
        assertThat(result).isEqualTo(1600.0);
    }

    @Test
    @DisplayName("Расчёт площади квадрата используя собственный" +
        "метод квадрата")
    void calculateCorrectOwnSquare() {
        // given
        Square square = new Square(10);
        var newSquare = square.setSide(60);

        // when
        double result = newSquare.area();

        // then
        assertThat(result).isEqualTo(3600.0);
    }

    @Test
    @DisplayName("Расчёт площади прямоугольника с разными сторонами")
    void calculateRectangle() {
        // given
        Rectangle rectangle = new Rectangle(20, 50);

        // when
        double result = rectangle.area();

        // then
        assertThat(result).isEqualTo(1000.0);
    }

    @Test
    @DisplayName("Расчёт площади прямоугольника с одинаковыми сторонами")
    void calculateEqualRectangle() {
        // given
        Rectangle rectangle = new Rectangle(70, 70);

        // when
        double result = rectangle.area();

        // then
        assertThat(result).isEqualTo(4900.0);
    }

}
