package edu.project3;

import java.time.LocalDateTime;

public record LogRecord(
    String ipAddress,
    LocalDateTime timestamp,
    String method, String url,
    String protocol, int statusCode,
    int responseSize) {}
