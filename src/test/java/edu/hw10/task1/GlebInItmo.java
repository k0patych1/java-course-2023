package edu.hw10.task1;

import edu.hw10.task1.annotations.Min;

public record GlebInItmo(String laptop, @Min(500_000) int numOfDops) {}
