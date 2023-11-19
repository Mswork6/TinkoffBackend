package edu.project3;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "C:\\XboxGames\\**\\datapath";

        List<LogData> list = LogParser.parseLogData(url);
        LogAnalyzer analyzer = new LogAnalyzer(list);
        LogStats stats = analyzer.collectStats("data.txt");
        LogReport report = new LogReport(stats);
        String table = report.generateReportMd();
        report.createMarkdownFile();
        System.out.println(table);

    }


}
