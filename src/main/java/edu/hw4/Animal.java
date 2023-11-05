package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {

    public static final int NUMBER_OF_PAWS_CATS_AND_DOGS = 4;
    public static final int NUMBER_OF_PAWS_BIRDS = 2;
    public static final int NUMBER_OF_PAWS_FISHES = 0;
    public static final int NUMBER_OF_PAWS_SPIDERS = 8;

    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> NUMBER_OF_PAWS_CATS_AND_DOGS;
            case BIRD -> NUMBER_OF_PAWS_BIRDS;
            case FISH -> NUMBER_OF_PAWS_FISHES;
            case SPIDER -> NUMBER_OF_PAWS_SPIDERS;
        };
    }
}
