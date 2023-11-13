package edu.hw4;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public record ValidationError(String message) {

    public static Set<ValidationError> validate19(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (Objects.equals(animal.name(), "")) {
            errors.add(new ValidationError("Имя задано некорректно"));
        }

        if (animal.age() <= 0) {
            errors.add(new ValidationError("Возраст задан некорректно"));
        }

        if (animal.height() <= 0) {
            errors.add(new ValidationError("Рост задан некорректно"));
        }

        if (animal.weight() <= 0) {
            errors.add(new ValidationError("Вес задан некорректно"));
        }

        return errors;
    }

    public static String validate20(Animal animal) {
        String errors = "";

        if (Objects.equals(animal.name(), "")) {
            errors += "name : Имя задано некорректно; ";
        }

        if (animal.age() <= 0) {
            errors += "age: Возраст задан некорректно; ";
        }

        if (animal.height() <= 0) {
            errors += "height: Рост задан некорректно; ";
        }

        if (animal.weight() <= 0) {
            errors += "weight: Вес задан некорректно; ";
        }

        return errors;
    }
}
