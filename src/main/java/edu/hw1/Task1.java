package edu.hw1;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task1 {
    private final static int SECONDS_IN_MINUTE = 60;
    private final static String TIME_REGEX = "^(\\d+):([0-5][0-9])$";
    private final static Pattern TIME_PATTERN = Pattern.compile(TIME_REGEX);

    private Task1() {
    }

    private static int validationCheck(String time) {
        if (time == null) {
            return -1;
        }

        if (!Pattern.matches(TIME_REGEX, time)) {
            return -1;
        }
        return 0;
    }

    public static BigInteger minutesToSeconds(String time) {
        int validationValue = validationCheck(time);

        if (validationValue == -1) {
            return BigInteger.valueOf(-1);
        }

        Matcher matcher = TIME_PATTERN.matcher(time);
        matcher.matches();
        BigInteger seconds = new BigInteger(matcher.group(2));
        BigInteger minutes = new BigInteger(matcher.group(1));

        return minutes.multiply(BigInteger.valueOf(SECONDS_IN_MINUTE)).add(seconds);
    }
}
