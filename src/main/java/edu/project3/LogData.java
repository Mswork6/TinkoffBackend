package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MagicNumber")
public class LogData {
    private static final String LOG_PATTERN = "^(\\S+) - (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" "
        + "(\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"$";

    private final String request;
    private final int status;
    private final long bodyBytesSent;
    private final OffsetDateTime timestamp;

    public LogData(String logLine) {
        Pattern pattern = Pattern.compile(LOG_PATTERN);
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.find()) {
            this.request = matcher.group(4);
            this.status = Integer.parseInt(matcher.group(5));
            this.bodyBytesSent = Long.parseLong(matcher.group(6));
            this.timestamp = parseTimestamp(matcher.group(3));
        } else {
            throw new IllegalArgumentException("Invalid log record format: " + logLine);
        }
    }

    public String getRequest() {
        return request;
    }

    public int getStatus() {
        return status;
    }

    public long getBodyBytesSent() {
        return bodyBytesSent;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    private OffsetDateTime parseTimestamp(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        return OffsetDateTime.parse(timestamp, formatter);
    }

}

