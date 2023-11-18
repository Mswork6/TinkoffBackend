package edu.project3;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        List<LogData> list = LogParser.parseLogData(url);
        LogAnalyzer analyzer = new LogAnalyzer(list);
        String from = "2015-05-17";
        String to = "2015-06-04";
        LogStats stats = analyzer.collectStats(from, to);
        System.out.println(stats.toString());

    }


}
