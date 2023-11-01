package edu.hw2.task3tests;

import java.util.Random;

public class LuckyRandom extends Random {
    @Override
    public boolean nextBoolean() {
        return true;
    }
}
