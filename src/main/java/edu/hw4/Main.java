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
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@UtilityClass
public final class Main {
    /**
     * Отсортировать животных по росту от самого маленького к самому большому
     */
    public static List<Animal> animalHeightSort(List<Animal> list) {
        return list.stream()
                .sorted(Comparator.comparing(Animal::height))
                .toList();
    }

    /**
     * Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых
     */
    public static List<Animal> animalWeightSort(List<Animal> list, int number) {
        return list.stream()
                .sorted(Comparator.comparing(Animal::weight, Collections.reverseOrder()))
                .limit(number)
                .toList();
    }

    /**
     * Сколько животных каждого вида
     */
    public static Map<Animal.Type, Integer> animalCount(List<Animal> list) {
        return list.stream()
                .collect(groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    /**
     * У какого животного самое длинное имя
     */
    public static Animal animalLongestName(List<Animal> list) {
        return list.stream()
                .max(Comparator.comparing(animal -> animal.name().length()))
                .orElseThrow();
    }

    /**
     * Каких животных больше: самцов или самок
     */
    public static Animal.Sex animalSexCount(List<Animal> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.stream()
                .collect(groupingBy(Animal::sex, counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).orElseThrow().getKey();
    }

    /**
     * Самое тяжелое животное каждого вида
     */
    public static Map<Animal.Type, Animal> heaviestEveryTypeAnimal(List<Animal> list) {
        return list.stream()
                .collect(groupingBy(Animal::type,
                        collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Animal::weight)),
                                Optional::orElseThrow
                        )));
    }
    /* Не совсем понял как реализовать это именно через .collect(Collectors.toMap())
    Но данная моя реализация по факту делает то же самое, она так же возвращает мапу
     */


    /**
     * K-е самое старое животное
     */
    public static Animal oldestAnimal(List<Animal> list, int number) {
        return list.stream()
                .sorted(Comparator.comparing(Animal::age, Collections.reverseOrder()))
                .skip(number - 1)
                .findFirst()
                .orElseThrow();
    }

    /**
     * Самое тяжелое животное среди животных ниже k см
     */
    public static Optional<Animal> heaviestAnimalLowerThan(List<Animal> list, int number) {
        return list.stream()
                .filter(animal -> animal.height() < number)
                .max(Comparator.comparing(Animal::weight));
    }

    /**
     * Сколько в сумме лап у животных в списке
     */
    public static Integer pawCount(List<Animal> list) {
        return list.stream()
                .mapToInt(Animal::paws)
                .sum();
    }

    /**
     * Список животных, возраст у которых не совпадает с количеством лап
     */
    public static List<Animal> animalsAgeNotEqualPaws(List<Animal> list) {
        return list.stream()
                .filter(animal -> animal.age() != animal.paws())
                .toList();
    }

    /**
     * Список животных, которые могут укусить (bites == null или true) и рост которых превышает 100 см
     */
    @SuppressWarnings("MagicNumber")
    public static List<Animal> animalsThatCanBite(List<Animal> list) {
        return list.stream()
                .filter(animal -> (animal.bites() == null || animal.bites()) && animal.height() > 100)
                .toList();
    }

    /**
     * Сколько в списке животных, вес которых превышает рост
     */
    public static Integer animalsWeightMoreThanHeight(List<Animal> list) {
        return Math.toIntExact(list.stream()
                .filter(animal -> animal.weight() > animal.height())
                .count());
    }

    /**
     * Список животных, имена которых состоят из более чем двух слов
     */
    public static List<Animal> twoNameOrMoreAnimal(List<Animal> list) {
        return list.stream()
                .filter(animal -> animal.name().contains(" "))
                .toList();
    }

    /**
     * Есть ли в списке собака ростом более k см
     */
    public static Boolean hasDogHeightMoreThan(List<Animal> list, int height) {
        return list.stream()
                .anyMatch(animal -> animal.type() == Type.DOG && animal.height() > height);
    }

    /**
     * Найти суммарный вес животных каждого вида, которым от k до l лет
     */
    public static Map<Animal.Type, Integer> animalEveryTypeWeightSum(List<Animal> list, int min, int max) {
        return list.stream()
                .filter(animal -> animal.age() > Math.min(min, max) && animal.age() < Math.max(min, max))
                .collect(groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    /**
     * Список животных, отсортированный по виду, затем по полу, затем по имени
     */
    public static List<Animal> animalSortedListByTypeSexName(List<Animal> list) {
        return list.stream()
                .sorted(
                        Comparator.comparing(Animal::type)
                                .thenComparing(Animal::sex)
                                .thenComparing(Animal::name)
                )
                .toList();
    }

    /**
     * Правда ли, что пауки кусаются чаще, чем собаки
     */
    public static Boolean spiderBitesMoreThanDogs(List<Animal> list) {
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

        double spiderTotal = (double) spiderBites / spiderAll;
        double dogTotal = (double) dogBites / dogAll;

        return spiderTotal > dogTotal;
    }

    /**
     * Найти самую тяжелую рыбку в 2-х или более списках
     */
    public static Animal heaviestFish(List<List<Animal>> list) {
        return list.stream()
                .flatMap(Collection::stream)
                .filter(animal -> animal.type() == Type.FISH)
                .max(Comparator.comparing(Animal::weight))
                .orElseThrow();
    }

    /**
     * Животные, в записях о которых есть ошибки: вернуть имя и список ошибок
     */
    public static Map<String, Set<AnimalValidator.ValidationError>> animalWithErrors(List<Animal> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        Animal::name,
                        AnimalValidator::validate
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
    public static Map<String, String> animalWithErrorsAsString(List<Animal> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        Animal::name,
                        AnimalValidator::validate
                ))
                .entrySet()
                .stream()
                .filter(stringSetEntry -> !stringSetEntry.getValue().isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, value -> value.getValue().toString()
                ));
    }
}
