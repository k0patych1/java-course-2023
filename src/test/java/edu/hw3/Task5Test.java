package edu.hw3;

import edu.hw3.task5.Person;
import edu.hw3.task5.PersonParser;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @Test
    public void parseZeroContactsTest() {
        String[] contacts = new String[0];

        assertTrue(PersonParser.parseContacts(contacts, "ASC").isEmpty());
    }

    @Test
    public void parseIncorrectContactsTest() {
        String[] contacts = new String[] {"a b c"};

        assertThrows(IllegalArgumentException.class, () -> PersonParser.parseContacts(contacts, "ASC"));
    }

    @Test
    public void parseAscContactsWithSurnameTest() {
        String[] contacts = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};

        List<Person> parsedContacts = new ArrayList<>();
        parsedContacts.add(new Person("Thomas", "Aquinas"));
        parsedContacts.add(new Person("Rene", "Descartes"));
        parsedContacts.add(new Person("David", "Hume"));
        parsedContacts.add(new Person("John", "Locke"));

        assertThat(PersonParser.parseContacts(contacts, "ASC")).isEqualTo(parsedContacts);
    }

    @Test
    public void parseDescContactsWithSurnameTest() {
        String[] contacts = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};

        List<Person> parsedContacts = new ArrayList<>();
        parsedContacts.add(new Person("Carl", "Gauss"));
        parsedContacts.add(new Person("Leonhard", "Euler"));
        parsedContacts.add(new Person("Paul", "Erdos"));

        assertThat(PersonParser.parseContacts(contacts, "DESC")).isEqualTo(parsedContacts);
    }

    @Test
    public void parseContactsWithoutSurnameTest() {
        String[] contacts = new String[] {"Ruslan", "Vanya Chizh", "Ayaz"};

        List<Person> parsedContacts = new ArrayList<>(); // A < C < R
        parsedContacts.add(new Person("Ayaz", null));
        parsedContacts.add(new Person("Vanya", "Chizh"));
        parsedContacts.add(new Person("Ruslan", null));

        assertThat(PersonParser.parseContacts(contacts, "ASC")).isEqualTo(parsedContacts);
    }

    @Test
    public void parseNullContactsTest() {
        assertTrue(PersonParser.parseContacts(null, "ASC").isEmpty());
    }
}
