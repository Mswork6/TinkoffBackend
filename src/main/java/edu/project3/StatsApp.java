package edu.project3;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StatsApp {
    public static void generateLogs(String logPath) {
        List<LogData> list = LogParser.parseLogData(logPath);
        LogAnalyzer analyzer = new LogAnalyzer(list);
        LogStats stats = analyzer.collectStats("data.logs");
        LogReport report = new LogReport(stats);
        report.createMarkdownFile();
    }
}
