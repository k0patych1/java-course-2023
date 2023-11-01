package edu.hw3.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.Nullable;

public final class PersonParser {
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";
    public static final String INCORRECT_FORMAT_EXCEPTION_DESCRIPTION = "The string is not in the correct format";

    private PersonParser() {}

    public static List<Person> parseContacts(@Nullable String[] parsingContacts, String order) {
        List<Person> people = new ArrayList<>();

        if (parsingContacts == null) {
            return people;
        }

        for (var contact : parsingContacts) {
            if (contact == null) {
                continue;
            }

            String[] parsedContacts = contact.split(" ");

            if (parsedContacts.length == 1) {
                String name = parsedContacts[0];
                people.add(new Person(name, null));
            } else if (parsedContacts.length == 2) {
                String name = parsedContacts[0];
                String surname = parsedContacts[1];
                people.add(new Person(name, surname));
            } else {
                throw new IllegalArgumentException(INCORRECT_FORMAT_EXCEPTION_DESCRIPTION);
            }
        }

        if (Objects.equals(order, ASC)) {
            people.sort(new PersonComparatorAsc());
        } else if (Objects.equals(order, DESC)) {
            people.sort(new PersonComparatorDesc());
        } else {
            throw new IllegalArgumentException(INCORRECT_FORMAT_EXCEPTION_DESCRIPTION);
        }

        return people;
    }
}
