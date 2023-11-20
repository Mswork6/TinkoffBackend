package edu.hw4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import edu.hw4.Animal.Type;
import edu.hw4.Animal.Sex;

public class MainTest {
    private static List<Animal> animals;

    @BeforeAll
    public static void setUp() {
        animals = Arrays.asList(
                new Animal("Whiskers", Type.CAT, Sex.M, 5, 40, 8, false),
                new Animal("Rex", Type.DOG, Sex.M, 4, 100, 20, true),
                new Animal("Fluffy", Type.CAT, Sex.F, 2, 20, 7, false),
                new Animal("Goldie", Type.FISH, Sex.F, 1, 2, 0, false),
                new Animal("Charlotte", Type.SPIDER, Sex.F, 3, 1, 2, false),
                new Animal("Tweetie", Type.BIRD, Sex.F, 1, 3, 1, false),
                new Animal("Spiderman", Type.SPIDER, Sex.M, -1, -1, 0, true),
                new Animal("Max", Type.DOG, Sex.M, 3, 90, 18, true),
                new Animal("Luna", Type.CAT, Sex.F, 4, 25, 7, false),
                new Animal("Nemo", Type.FISH, Sex.M, 1, 1, 0, false),
                new Animal("Tweety", Type.BIRD, Sex.F, 2, 4, 2, false),
                new Animal("Buddy", Type.DOG, Sex.M, 6, 120, 24, true),
                new Animal("Misty", Type.CAT, Sex.F, 3, 70, 6, false),
                new Animal("Sharky", Type.FISH, Sex.M, 2, 3, 2, true),
                new Animal("Hawk", Type.BIRD, Sex.M, 4, 8, 3, false),
                new Animal("Arachnid", Type.SPIDER, Sex.F, 1, 1, 0, false),
                new Animal("Bella", Type.DOG, Sex.F, 2, 75, 18, false),
                new Animal("Tiger", Type.CAT, Sex.M, 8, 86, 10, true),
                new Animal("Dory Tina Lina", Type.FISH, Sex.F, 1, 1, 0, false),
                new Animal("Simba", Type.CAT, Sex.M, 7, 80, 9, false),
                new Animal("Rusty", Type.DOG, Sex.M, 5, 50, 25, true),
                new Animal("Princess", Type.CAT, Sex.F, 6, 60, 8, false),
                new Animal("Spike", Type.DOG, Sex.M, 4, 56, 22, true),
                new Animal("Rainbow", Type.FISH, Sex.F, 2, 4, 3, false),
                new Animal("Robin", Type.BIRD, Sex.M, 3, 5, 2, false),
                new Animal("Spinner", Type.SPIDER, Sex.M, 1, 1, 0, true),
                new Animal("Vic", Type.FISH, Sex.M, 9, 150, 40, true),
                new Animal("Lensi", Type.FISH, Sex.F, 7, 110, 25, false)

        );
    }

    @Test
    @DisplayName("Проверка сортировки по росту")
    public void testTask1() {
        // given

        // when
        List<Animal> result = Main.animalHeightSort(animals);

        // then
        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(result.get(i).height() <= result.get(i + 1).height());
        }
    }

    @Test
    @DisplayName("Проверка сортировки по весу  с выбором k первых")
    public void testTask2() {
        // given
        int k = 3;

        // when
        List<Animal> result = Main.animalWeightSort(animals, k);

        // then
        assertEquals(result.size(), k);
        assertEquals(result.get(0).weight(), 40);
        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(result.get(i).weight() >= result.get(i + 1).weight());
        }
    }

    @Test
    @DisplayName("Проверка подсчёта количества животных")
    public void testTask3() {
        // given

        // when
        Map<Animal.Type, Integer> result = Main.animalCount(animals);

        // then
        assertEquals(result.get(Type.CAT), 7);
        assertEquals(result.get(Type.DOG), 6);
        assertEquals(result.get(Type.FISH), 7);
        assertEquals(result.get(Type.BIRD), 4);
        assertEquals(result.get(Type.SPIDER), 4);
    }

    @Test
    @DisplayName("Проверка самого длинного имени")
    public void testTask4() {
        // given

        // when
        Animal result = Main.animalLongestName(animals);

        // then
        assertEquals(result.name(), "Dory Tina Lina");
    }

    @Test
    @DisplayName("Проверка на сравнение количества животных")
    public void testTask5() {
        // given
        List<Animal> list1 = new ArrayList<>();
        List<Animal> list2 = new ArrayList<>(List.of(
            new Animal("Linda", Type.CAT, Sex.F, 5, 40, 8, false),
            new Animal("Lola", Type.DOG, Sex.F, 5, 40, 8, false),
            new Animal("Wolfgang", Type.DOG, Sex.M, 5, 40, 8, false),
            new Animal("Amadeus", Type.SPIDER, Sex.M, 5, 40, 8, false),
            new Animal("Leyla", Type.CAT, Sex.F, 5, 40, 8, false)
        ));

        // when
        Animal.Sex result = Main.animalSexCount(animals);
        Animal.Sex result1 = Main.animalSexCount(list1);
        Animal.Sex result2 = Main.animalSexCount(list2);

        // then
        assertEquals(result, Animal.Sex.M);
        assertNull(result1);
        assertEquals(result2, Animal.Sex.F);
    }

    @Test
    @DisplayName("Проверка самого тяжелого животного каждого вида")
    public void testTask6() {
        // given

        // when
        Map<Animal.Type, Animal> result = Main.heaviestEveryTypeAnimal(animals);

        // then
        assertEquals(result.get(Animal.Type.CAT).name(), "Tiger");
        assertEquals(result.get(Animal.Type.DOG).name(), "Rusty");
        assertEquals(result.get(Animal.Type.FISH).name(), "Vic");
        assertEquals(result.get(Animal.Type.BIRD).name(), "Hawk");
        assertEquals(result.get(Animal.Type.SPIDER).name(), "Charlotte");
    }

    @Test
    @DisplayName("Проверка K-ого самого старого животного")
    public void testTask7() {
        // given
        int k = 2;

        // when
        Animal result = Main.oldestAnimal(animals, k);

        // then
        assertEquals(result.name(), "Tiger");
    }


    @Test
    @DisplayName("Проверка самого тяжелого животного среди животных ниже k см")
    public void testTask8() {
        // given
        int k = 50;

        // when
        Optional<Animal> result = Main.heaviestAnimalLowerThan(animals, k);

        // then
        assertTrue(result.isPresent());
        assertEquals(result.get().name(), "Whiskers");
    }

    @Test
    @DisplayName("Проверка суммы лап животных")
    public void testTask9() {
        // given

        // when
        int result = Main.pawCount(animals);

        // then
        assertEquals(result, 92);
    }

    @Test
    @DisplayName("Проверка списка животных, возраст у которых не совпадает с количеством лап")
    public void testTask10() {
        // given

        // when
        List<Animal> result = Main.animalsAgeNotEqualPaws(animals);

        // then
        for (Animal animal : result) {
            assertNotEquals(animal.age(), animal.paws());
        }
    }

    @Test
    @DisplayName("Проверка списка животных, которые могут укусить и рост которых превышает 100 см")
    public void testTask11() {
        // given

        // when
        List<Animal> result = Main.animalsThatCanBite(animals);

        // then
        assertEquals(result.size(), 2);
        for (Animal animal : result) {
            assertTrue((animal.bites() == null || animal.bites()) && animal.height() > 100);
        }
    }

    @Test
    @DisplayName("Проверка количества животных, вес которых превышает рост")
    public void testTask12() {
        // given

        // when
        int result = Main.animalsWeightMoreThanHeight(animals);

        // then
        assertEquals(result, 1);
    }

    @Test
    @DisplayName("Проверка списка животных, имена которых состоят из более чем двух слов")
    public void testTask13() {
        // given

        // when
        List<Animal> result = Main.twoNameOrMoreAnimal(animals);

        // then
        assertEquals(result.size(), 1);
        for (Animal animal : result) {
            assertTrue(animal.name().split(" ").length > 2);
        }
    }

    @Test
    @DisplayName("Проверка наличия собаки ростом более k см")
    public void testTask14() {
        // given
        int trueHeight = 15;
        int falseHeight = 200;

        // when
        boolean result = Main.hasDogHeightMoreThan(animals, trueHeight);
        boolean result1 = Main.hasDogHeightMoreThan(animals, falseHeight);

        // then
        assertTrue(result);
        assertFalse(result1);
    }

    @Test
    @DisplayName("Проверка нахождения суммарного веса животных каждого вида "
        + "от k до l лет")
    public void testTask15() {
        // given
        int minAge = 2;
        int maxAge = 5;
        List<Animal> emptyList = new ArrayList<>();

        // when
        Map<Animal.Type, Integer> result = Main.animalEveryTypeWeightSum(animals, minAge, maxAge);
        Map<Animal.Type, Integer> emptyResult = Main.animalEveryTypeWeightSum(emptyList, minAge, maxAge);

        // then
        assertEquals(result.get(Type.CAT), 13);
        assertEquals(result.get(Type.DOG), 60);
        assertEquals(result.get(Type.BIRD), 5);
        assertEquals(result.get(Type.SPIDER), 2);
        assertNull(result.get(Type.FISH));

        assertNull(emptyResult.get(Type.CAT));
        assertNull(emptyResult.get(Type.DOG));
        assertNull(emptyResult.get(Type.BIRD));
        assertNull(emptyResult.get(Type.SPIDER));
        assertNull(emptyResult.get(Type.FISH));
    }

    @Test
    @DisplayName("Проверка сортировки списка")
    public void testTask16() {
        // given

        // when
        List<Animal> result = Main.animalSortedListByTypeSexName(animals);

        // then
        for (int i = 0; i < result.size() - 1; i++) {
            int typeComparison = result.get(i).type()
                    .compareTo(result.get(i + 1).type());
            if (typeComparison == 0) {
                int sexComparison = result.get(i).sex()
                        .compareTo(result.get(i + 1).sex());
                if (sexComparison == 0) {
                    assertTrue(result.get(i).name().compareTo(result.get(i + 1).name()) <= 0);
                } else {
                    assertTrue(sexComparison <= 0);
                }
            } else {
                assertTrue(typeComparison <= 0);
            }
        }
    }


    @Test
    @DisplayName("Проверка, что пауки кусаются чаще, чем собаки")
    public void testTask17() {
        // given
        List<Animal> trueList = new ArrayList<>(List.of(
            new Animal("Wolfgang", Type.DOG, Sex.M, 5, 40, 8, false),
            new Animal("Hans", Type.DOG, Sex.F, 5, 40, 8, true),
            new Animal("Jurgen", Type.DOG, Sex.M, 5, 40, 8, false),
            new Animal("Mathias", Type.SPIDER, Sex.M, 5, 2, 8, true),
            new Animal("Jorg", Type.SPIDER, Sex.F, 5, 2, 8, true),
            new Animal("Jens", Type.SPIDER, Sex.F, 5, 2, 8, false)
        ));

        List<Animal> noDogsList = new ArrayList<>(List.of(
            new Animal("Mathias", Type.SPIDER, Sex.M, 5, 2, 8, true),
            new Animal("Jorg", Type.SPIDER, Sex.F, 5, 2, 8, false),
            new Animal("Jens", Type.SPIDER, Sex.F, 5, 2, 8, false)
        ));

        List<Animal> noSpidersList = new ArrayList<>(List.of(
            new Animal("Wolfgang", Type.DOG, Sex.M, 5, 40, 8, true),
            new Animal("Hans", Type.DOG, Sex.F, 5, 40, 8, true),
            new Animal("Jurgen", Type.DOG, Sex.M, 5, 40, 8, false)
        ));

        // when
        boolean result = Main.spiderBitesMoreThanDogs(animals);
        boolean result1 = Main.spiderBitesMoreThanDogs(trueList);
        boolean result2 = Main.spiderBitesMoreThanDogs(noDogsList);
        boolean result3 = Main.spiderBitesMoreThanDogs(noSpidersList);

        // then

        assertFalse(result);
        assertTrue(result1);
        assertFalse(result2);
        assertFalse(result3);

    }

    @Test
    @DisplayName("Проверка нахождения самой тяжелой рыбки в 2-х или более списках")
    public void testTask18() {
        // given
        List<List<Animal>> animalLists = new ArrayList<>();
        animalLists.add(animals.subList(0, 5));
        animalLists.add(animals.subList(5, 10));
        animalLists.add(animals.subList(10, 27));

        // when
        Animal result = Main.heaviestFish(animalLists);

        // then
        assertEquals(result.name(), "Vic");
    }

    @Test
    @DisplayName("Проверка наличия животных с некорректными данными")
    public void testTask19() {
        // given

        // when
        Map<String, Set<AnimalValidator.ValidationError>> result = Main.animalWithErrors(animals);
        System.out.println(result.toString());

        // then
        assertEquals(result.size(), 6);
        assertEquals(result.get("Spinner").toString(), "[weight : Вес задан некорректно]");
        assertEquals(result.get("Arachnid").toString(), "[weight : Вес задан некорректно]");
        assertEquals(result.get("Goldie").toString(), "[weight : Вес задан некорректно]");
        assertEquals(result.get("Dory Tina Lina").toString(), "[weight : Вес задан некорректно]");
        assertEquals(result.get("Nemo").toString(), "[weight : Вес задан некорректно]");
        assertEquals(result.get("Spiderman").toString(), "[weight : Вес задан некорректно, "
            + "height : Рост задан некорректно, age : Возраст задан некорректно]");

    }

    @Test
    @DisplayName("Проверка наличия животных с некорректными данными")
    public void testTask20() {
        // given

        // when
        Map<String, String> result = Main.animalWithErrorsAsString(animals);
        System.out.println(result.toString());

        // then
        assertEquals(result.size(), 6);
        assertEquals(result.get("Spinner"), "[weight : Вес задан некорректно]");
        assertEquals(result.get("Arachnid"), "[weight : Вес задан некорректно]");
        assertEquals(result.get("Goldie"), "[weight : Вес задан некорректно]");
        assertEquals(result.get("Dory Tina Lina"), "[weight : Вес задан некорректно]");
        assertEquals(result.get("Nemo"), "[weight : Вес задан некорректно]");
        assertEquals(result.get("Spiderman"), "[weight : Вес задан некорректно, "
            + "height : Рост задан некорректно, age : Возраст задан некорректно]");
    }
}
