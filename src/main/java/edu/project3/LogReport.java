package edu.project3;

import java.util.List;
import java.util.Map;

import java.util.Map;
import java.util.stream.Collectors;

public class LogReport {
    private static final String[] TITLE_NAMES = {"Метрика",  "Значение", "Ресурс", "Количество",
    "Имя", "Код"};

    private LogStats stats;

    public LogReport(LogStats stats){
        this.stats = stats;
    }
    private int countSizeOfColumn(List<String> list){
        return list.stream()
            .map(String::length)
            .max(Integer::compare)
            .orElse(0);
    }

    private int countSizeOfColumn(Map<String, Long> map){
        List<String> list = map.keySet().stream().toList();
        return countSizeOfColumn(list);
    }

}
