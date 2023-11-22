package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw5.Task5.checkPlateNumber;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {

    @ParameterizedTest
    @CsvSource({"А123ВЕ777", "А620ЕВ174", "В320РР174"})
    @DisplayName("Обработка корректного номерного знака")
    void checkCorrectPlate(String plateNumber) {
        // given

        // when
        boolean answer = checkPlateNumber(plateNumber);

        // then
        assertTrue(answer);
    }

    @ParameterizedTest
    @CsvSource({"Б123ВГ111", "какой то текст", "123АВЕ777"})
    @DisplayName("Обработка некорректного номерного знака")
    void checkIncorrectPlate(String plateNumber) {
        // given

        // when
        boolean answer = checkPlateNumber(plateNumber);

        // then
        assertFalse(answer);
    }

    @Test
    @DisplayName("Обработка пустого номерного знака")
    void checkEmptyPlate() {
        // given
        String plateNumber = "";

        // when
        boolean answer = checkPlateNumber(plateNumber);

        // then
        assertFalse(answer);
    }
}
