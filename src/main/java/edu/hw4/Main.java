package edu.hw4;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import static edu.hw4.Animal.Type;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@UtilityClass
public final class Main {
    /**
     * Отсортировать животных по росту от самого маленького к самому большому
     */
    public static List<Animal> task1(List<Animal> list) {
        return list.stream()
                .sorted(Comparator.comparing(Animal::height))
                .toList();
    }

    /**
     * Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых
     */
    public static List<Animal> task2(List<Animal> list, int number) {
        return list.stream()
                .sorted(Comparator.comparing(Animal::weight, Collections.reverseOrder()))
                .limit(number)
                .toList();
    }

    /**
     * Сколько животных каждого вида
     */
    public static Map<Animal.Type, Integer> task3(List<Animal> list) {
        return list.stream()
                .collect(groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    /**
     * У какого животного самое длинное имя
     */
    public static Animal task4(List<Animal> list) {
        return list.stream()
                .max(Comparator.comparing(animal -> animal.name().length()))
                .orElseThrow();
    }

    /**
     * Каких животных больше: самцов или самок
     */
    public static Animal.Sex task5(List<Animal> list) {
        return list.stream()
                .collect(groupingBy(Animal::sex, counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).orElseThrow().getKey();
    }

    /**
     * Самое тяжелое животное каждого вида
     */
    public static Map<Animal.Type, Animal> task6(List<Animal> list) {
        return list.stream()
                .collect(groupingBy(Animal::type, Collectors.maxBy(Comparator.comparing(Animal::weight))))
                .entrySet()
                .stream()
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().orElseThrow()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * K-е самое старое животное
     */
    public static Animal task7(List<Animal> list, int number) {
        return list.stream()
                .sorted(Comparator.comparing(Animal::age, Collections.reverseOrder()))
                .toList()
                .get(number - 1);
    }

    /**
     * Самое тяжелое животное среди животных ниже k см
     */
    public static Optional<Animal> task8(List<Animal> list, int number) {
        return list.stream()
                .filter(animal -> animal.height() < number)
                .max(Comparator.comparing(Animal::weight));
    }

    /**
     * Сколько в сумме лап у животных в списке
     */
    public static Integer task9(List<Animal> list) {
        return list.stream()
                .mapToInt(Animal::paws)
                .sum();
    }

    /**
     * Список животных, возраст у которых не совпадает с количеством лап
     */
    public static List<Animal> task10(List<Animal> list) {
        return list.stream()
                .filter(animal -> animal.age() != animal.paws())
                .toList();
    }

    /**
     * Список животных, которые могут укусить (bites == null или true) и рост которых превышает 100 см
     */
    @SuppressWarnings("MagicNumber")
    public static List<Animal> task11(List<Animal> list) {
        return list.stream()
                .filter(animal -> (animal.bites() == null || animal.bites()) && animal.height() > 100)
                .toList();
    }

    /**
     * Сколько в списке животных, вес которых превышает рост
     */
    public static Integer task12(List<Animal> list) {
        return Math.toIntExact(list.stream()
                .filter(animal -> animal.weight() > animal.height())
                .count());
    }

    /**
     * Список животных, имена которых состоят из более чем двух слов
     */
    public static List<Animal> task13(List<Animal> list) {
        return list.stream()
                .filter(animal -> animal.name().split(" ").length > 2)
                .toList();
    }

    /**
     * Есть ли в списке собака ростом более k см
     */
    public static Boolean task14(List<Animal> list, int height) {
        return list.stream()
                .anyMatch(animal -> animal.type() == Type.DOG && animal.height() > height);
    }

    /**
     * Найти суммарный вес животных каждого вида, которым от k до l лет
     */
    public static Map<Animal.Type, Integer> task15(List<Animal> list, int min, int max) {
        return list.stream()
                .filter(animal -> animal.age() > Math.min(min, max) && animal.age() < Math.max(min, max))
                .collect(groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    /**
     * Список животных, отсортированный по виду, затем по полу, затем по имени
     */
    public static List<Animal> task16(List<Animal> list) {
        return list.stream()
                .sorted((o1, o2) -> {
                    if (o1.type() == o2.type()) {
                        if (o1.sex() == o2.sex()) {
                            return o1.name().compareTo(o2.name());
                        } else {
                            return o1.sex().toString().compareTo(o2.sex().toString());
                        }
                    }
                    return o1.type().toString().compareTo(o2.type().toString());
                })
                .toList();
    }

    /**
     * Правда ли, что пауки кусаются чаще, чем собаки
     */
    public static Boolean task17(List<Animal> list) {
        long spiderBites = list.stream()
                .filter(animal -> animal.type() == Type.SPIDER && animal.bites())
                .count();

        long spiderAll = list.stream()
                .filter(animal -> animal.type() == Type.SPIDER)
                .count();

        long dogBites = list.stream()
                .filter(animal -> animal.type() == Type.DOG && animal.bites())
                .count();

        long dogAll = list.stream()
                .filter(animal -> animal.type() == Type.DOG)
                .count();

        if (spiderAll == 0 || dogAll == 0) {
            return false;
        }


        return spiderBites / spiderAll > dogBites / dogAll;
    }

    /**
     * Найти самую тяжелую рыбку в 2-х или более списках
     */
    public static Animal task18(List<List<Animal>> list) {
        return list.stream()
                .flatMap(Collection::stream)
                .filter(animal -> animal.type() == Type.FISH)
                .max(Comparator.comparing(Animal::weight))
                .orElseThrow();
    }

    /**
     * Животные, в записях о которых есть ошибки: вернуть имя и список ошибок
     */
    public static Map<String, Set<ValidationError>> task19(List<Animal> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        Animal::name,
                        ValidationError::validateAnimal19
                ))
                .entrySet()
                .stream()
                .filter(stringSetEntry -> !stringSetEntry.getValue().isEmpty())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    /**
     * Животные, в записях о которых есть ошибки: вернуть имя и
     * названия полей с ошибками, объединенные в строку
     */
    public static Map<String, String> task20(List<Animal> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        Animal::name,
                        ValidationError::validateAnimal20
                ))
                .entrySet()
                .stream()
                .filter(stringSetEntry -> !stringSetEntry.getValue().isEmpty())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }
}
