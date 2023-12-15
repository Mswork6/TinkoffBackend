package edu.project3;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Main {
    public static void main(String[] args) {
        String url = "https://raw.githubusercontent.com/elastic/examples/master"
            + "/Common%20Data%20Formats/nginx_logs/nginx_logs";
        StatsApp.generateLogs(url);
    }


}
