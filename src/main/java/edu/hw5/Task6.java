package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task6 {
    public static boolean isSubsequence(String s, String t) {
        StringBuilder pattern = new StringBuilder(".*");
        for (int i = 0; i < s.length(); i++) {
            pattern.append(s.charAt(i));
            pattern.append(".*");
        }
        Pattern regexPattern = Pattern.compile(pattern.toString());
        Matcher matcher = regexPattern.matcher(t);
        return matcher.matches();
    }
}
