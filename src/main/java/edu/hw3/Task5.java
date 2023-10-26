package edu.hw3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class Task5 {
    public static Contact[] parseContacts(String[] contacts, String type) {
        if (contacts == null || type == null) {
            return new Contact[0];
        }

        Contact[] result = new Contact[contacts.length];

        for (int i = 0; i < contacts.length; i++) {
            String contact = contacts[i];
            result[i] = new Contact(contact);
        }

        if (type.equals("ASC")) {
            Arrays.sort(result);
        } else if (type.equals("DESC")) {
            Arrays.sort(result, Comparator.reverseOrder());
        }
        return result;
    }

    public static final class Contact implements Comparable<Contact> {
        private final String name;
        private final String surname;

        public Contact(@NotNull String name, @Nullable String surname) {
            this.name = name;
            this.surname = surname;
        }

        public Contact(@NotNull String parseableContact) {
            var tokens = parseableContact.split(" ");
            this.name = tokens[0];
            if (tokens.length < 2) {
                this.surname = null;
            } else {
                this.surname = tokens[1];
            }
        }

        @Override
        public int compareTo(@NotNull Task5.Contact contact) {
            if (surname == null || contact.surname == null) {
                return name.compareTo(contact.name);
            } else {
                var surnameComparison = surname.compareTo(contact.surname);
                if (surnameComparison == 0) {
                    return name.compareTo(contact.name);
                }
                return surnameComparison;
            }
        }

        public String name() {
            return name;
        }

        public String surname() {
            return surname;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            var that = (Contact) obj;
            return Objects.equals(this.name, that.name)
                && Objects.equals(this.surname, that.surname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, surname);
        }

        @Override
        public String toString() {
            return "Contact["
                + "name=" + name + ", "
                + "surname="
                + surname
                + ']';
        }
    }
}
