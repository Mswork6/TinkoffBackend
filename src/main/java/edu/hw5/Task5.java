package edu.hw5;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task5 {
    private static final String PASSWORD_REGEX = "^[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$";

    public static boolean checkPlateNumber(String number) {
        if (number == null) {
            return false;
        }
        return number.matches(PASSWORD_REGEX);
    }
}
