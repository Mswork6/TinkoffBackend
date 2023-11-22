package edu.hw4;

public record Animal(
        String name,
        Type type,
        Sex sex,
        Integer age,
        Integer height,
        Integer weight,
        Boolean bites
) {
    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        M, F
    }

    @SuppressWarnings("MagicNumber")
    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }
}
