package edu.hw5;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task8 {
    private static final String REGEX1 = "^([01]{2})*[01]$";
    private static final String REGEX2 = "^0([01]{2})*$|^1[01]([01]{2})*$";
    private static final String REGEX3 = "^((1*)(0)(1*)(0)(1*)(0)(1*))*$";
    private static final String REGEX4 = "^(?!11$|111$)[01]*$";
    private static final String REGEX5 = "^1([01]1)*([01])?$";
    private static final String REGEX7 = "^(?!.*11)[01]*$";

    public static boolean checkRegex1(String string) {
        return string.matches(REGEX1);
    }

    public static boolean checkRegex2(String string) {
        return string.matches(REGEX2);
    }

    public static boolean checkRegex3(String string) {
        return string.matches(REGEX3);
    }

    public static boolean checkRegex4(String string) {
        return string.matches(REGEX4);
    }

    public static boolean checkRegex5(String string) {
        return string.matches(REGEX5);
    }

    public static boolean checkRegex7(String string) {
        return string.matches(REGEX7);
    }

}
