package edu.project3;

import java.util.Map;

public record LogStats(
    long totalRequests,
    String startDate,
    String endDate,
    String averageResponseSize,
    Map<String, Long> topResources,
    Map<Integer, Long> responseCodes
) {
}
