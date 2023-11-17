package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HackerNewsTest {
    @Test
    @DisplayName("Проверка получения наиболее обсуждаемых статей")
    void checkGetTopStories() {
        // Given
        HackerNews hackerNews = new HackerNews();

        // When
        long[] answer = hackerNews.hackerNewsTopStories();

        // Then
        assert(answer.length != 0);
    }

    @Test
    @DisplayName("Проверка некорректного получения наиболее обсуждаемых статей")
    void checkIncorrectGetTopStories() {
        // Given
        HackerNews hackerNews = new HackerNews();

        // When
        long[] answer = hackerNews.hackerNewsTopStories("https://vk.com/feed");

        // Then
        assert(answer.length == 0);
    }

    @Test
    @DisplayName("Проверка корректного названия новости")
    void checkCorrectNewsTitle() {
        // Given
        HackerNews hackerNews = new HackerNews();
        long id = 37570037;
        String answer = "JDK 21 Release Notes";

        // When
        String result = hackerNews.news(id);

        // Then
        assertEquals(answer, result);
    }

    @Test
    @DisplayName("Проверка некорректного названия новости")
    void checkIncorrectNewsTitle() {
        // Given
        HackerNews hackerNews = new HackerNews();
        long id = 0;
        String answer = "";

        // When
        String result = hackerNews.news(id);

        // Then
        assertEquals(answer, result);
    }
}
