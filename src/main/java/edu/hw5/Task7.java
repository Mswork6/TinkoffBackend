package edu.hw5;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task7 {
    private static final String FIRST_REGEX = "^[01]{2}0[01]*$";
    private static final String SECOND_REGEX = "^([01]).*(?=\\1$)[01]*$";
    private static final String THIRD_REGEX = "^[01]{1,3}$";

    public static boolean checkFirstRegex(String string) {
        return string.matches(FIRST_REGEX);
    }

    public static boolean checkSecondRegex(String string) {
        return string.matches(SECOND_REGEX);
    }

    public static boolean checkThirdRegex(String string) {
        return string.matches(THIRD_REGEX);
    }

    public static boolean checkAllRegexes(String string) {
        return string.matches(FIRST_REGEX) && string.matches(SECOND_REGEX)
            && string.matches(THIRD_REGEX);
    }

}

