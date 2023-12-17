package edu.hw10.task1.generators;

import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public final class RandomStringGenerator implements RandomSimpleObjectGenerator<String> {
    private static final int MAX_LENGTH = 1000;

    private final Random random;

    public RandomStringGenerator() {
        random = new Random();
    }

    public RandomStringGenerator(Random random) {
        this.random = new Random();
    }


    @Override
    public String generate(Parameter parameter) {
        int randomLength = random.nextInt(MAX_LENGTH);
        byte[] array = new byte[randomLength];
        random.nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
