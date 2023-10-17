package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Обработка пустой строки")
    void calculateEmptyValue() {
        // given
        String time = "";

        // when
        BigInteger result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(BigInteger.valueOf(-1));
    }

    @Test
    @DisplayName("Обработка некорректной строки")
    void calculateIncorrectValue() {
        // given
        String time = "sdahfgsdfh";

        // when
        BigInteger result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(BigInteger.valueOf(-1));
    }

    @Test
    @DisplayName("Обработка строки с отрицательным числом")
    void calculateNegativeValue() {
        // given
        String time = "-27:32";

        // when
        BigInteger result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(BigInteger.valueOf(-1));
    }

    @Test
    @DisplayName("Обработка строки с вещественным числом")
    void calculateFloatValue() {
        // given
        String time = "15.20:43";

        // when
        BigInteger result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(BigInteger.valueOf(-1));
    }

    @Test
    @DisplayName("Обработка строки с количеством секунд равным 60")
    void calculate60SecondsValue() {
        // given
        String time = "40:60";

        // when
        BigInteger result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(BigInteger.valueOf(-1));
    }

    @Test
    @DisplayName("Обработка строки с количеством секунд более 60")
    void calculateBiggerThan60SecondsValue() {
        // given
        String time = "40:75";

        // when
        BigInteger result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(BigInteger.valueOf(-1));
    }

    @Test
    @DisplayName("Обработка строки с большими числами")
    void calculateBigValue() {
        // given
        String time = "9223372036854775807:20";

        // when
        BigInteger result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(new BigInteger("553402322211286548440"));
    }

    @Test
    @DisplayName("Обработка null строки")
    void calculateNullValue() {
        // given
        String time = null;

        // when
        BigInteger result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(BigInteger.valueOf(-1));
    }

}
