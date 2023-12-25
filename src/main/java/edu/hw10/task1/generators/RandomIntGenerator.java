package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.reflect.Parameter;
import java.util.Random;

public final class RandomIntGenerator implements RandomSimpleObjectGenerator<Integer> {
    private final Random random;

    public RandomIntGenerator() {
        random = new Random();
    }

    public RandomIntGenerator(Random random) {
        this.random = new Random();
    }

    @Override
    public Integer generate(Parameter parameter) {
        Min minAnnotation = parameter.getAnnotation(Min.class);
        Max maxAnnotation = parameter.getAnnotation(Max.class);
        int minValue;
        int maxValue;
        if (minAnnotation == null) {
            minValue = Integer.MIN_VALUE;
        } else {
            minValue = minAnnotation.value();
        }

        if (maxAnnotation == null) {
            maxValue = Integer.MAX_VALUE;
        } else {
            maxValue = maxAnnotation.value();
        }

        return random.nextInt(minValue, maxValue);
    }
}
