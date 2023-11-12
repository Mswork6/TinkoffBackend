package edu.hw5;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task4 {
    private static final String PASSWORD_REGEX = ".*[~!@#$%^&*|].*";

    public static boolean checkPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }
}
