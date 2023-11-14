package edu.hw4;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AnimalValidator {
    public record ValidationError(String fieldName, String error){
        @Override
        public String toString(){
            return fieldName + " : " + error;
        }
    }

    public static Set<ValidationError> validate(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (Objects.equals(animal.name(), "")) {
            errors.add(new ValidationError("name", "Имя задано некорректно"));
        }

        if (animal.age() <= 0) {
            errors.add(new ValidationError("age", "Возраст задан некорректно"));
        }

        if (animal.height() <= 0) {
            errors.add(new ValidationError("height", "Рост задан некорректно"));
        }

        if (animal.weight() <= 0) {
            errors.add(new ValidationError("weight", "Вес задан некорректно"));
        }

        return errors;
    }




}
