package edu.project3;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "C:\\**\\wgs";
        List<LogData> list = LogParser.parseLogData(url);
        LogAnalyzer analyzer = new LogAnalyzer(list);
        String from = "2015-05-17";
        String to = "2015-06-04";
        LogStats stats = analyzer.collectStats();
        System.out.println(stats.toString());

    }


}
