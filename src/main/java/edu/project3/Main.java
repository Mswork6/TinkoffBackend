package edu.project3;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "C:\\XboxGames\\**\\datapath";

        List<LogData> list = LogParser.parseLogData(url);
        LogAnalyzer analyzer = new LogAnalyzer(list);
        String from = "2015-05-17";
        String to = "2015-06-04";
        LogStats stats = analyzer.collectStats();
        System.out.println(stats.toString());

    }


}
