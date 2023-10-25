package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import edu.hw3.Task5.Contact;
public class Task5Test {
    @Test
    @DisplayName("Сортировка в порядке возрастания")
    void ascendingSort() {
        // given
        String[] contacts = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        Contact[] answer = new Contact[] {new Contact("Thomas Aquinas"),
            new Contact("Rene Descartes"), new Contact("David Hume"),
            new Contact("John Locke")};

        // when
        Contact[] result = Task5.parseContacts(contacts, "ASC");

        // then
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Сортировка в порядке убывания")
    void descendingSort() {
        // given
        String[] contacts = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        Contact[] answer = new Contact[] {new Contact("Carl Gauss"),
            new Contact("Leonhard Euler"), new Contact("Paul Erdos")};

        // when
        Contact[] result = Task5.parseContacts(contacts, "DESC");

        // then
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Сортировка пустого массива")
    void emptySort() {
        // given
        String[] contacts = new String[] {};
        Contact[] answer = new Contact[] {};

        // when
        Contact[] result = Task5.parseContacts(contacts, "ASC");

        // then
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Сортировка null массива")
    void nullSort() {
        // given
        String[] contacts = null;
        Contact[] answer = new Contact[] {};

        // when
        Contact[] result = Task5.parseContacts(contacts, "ASC");

        // then
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Сортировка с контактом без фамилии")
    void noSurnameSort() {
        // given
        String[] contacts = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes", "Abigail"};;
        Contact[] answer = new Contact[] {new Contact("Abigail"), new Contact("Thomas Aquinas"),
            new Contact("Rene Descartes"), new Contact("David Hume"),
            new Contact("John Locke")};

        // when
        Contact[] result = Task5.parseContacts(contacts, "ASC");

        // then
        assertThat(result).isEqualTo(answer);
    }
}
