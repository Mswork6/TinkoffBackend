package edu.hw3;

import java.util.Arrays;
import java.util.Comparator;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

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

    @ToString
    @EqualsAndHashCode
    public static final class Contact implements Comparable<Contact> {
        private final String name;
        private final String surname;

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
    }
}
