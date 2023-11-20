package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task6 {
    public static boolean isSubsequence(String sequenceString, String workingString) {
        StringBuilder pattern = new StringBuilder(".*");
        for (int i = 0; i < sequenceString.length(); i++) {
            pattern.append("\\Q").append(sequenceString.charAt(i)).append("\\E.*");
        }
        Pattern regexPattern = Pattern.compile(pattern.toString());
        Matcher matcher = regexPattern.matcher(workingString);
        return matcher.matches();
    }
}
