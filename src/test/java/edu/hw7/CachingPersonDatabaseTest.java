package edu.hw7;

import edu.hw7.task3.CachingPersonDatabase;
import edu.hw7.task3.Person;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CachingPersonDatabaseTest {
    @Test
    public void oneThreadTest() {
        CachingPersonDatabase db = new CachingPersonDatabase();

        Person person1 = new Person(1, "Ruslan", "vyzama", "1234");
        Person person2 = new Person(2, "Senya", "vyzama", "56789");

        db.add(person1);
        db.add(person2);

        List<Person> result = db.findByAddress("vyzama");

        assertThat(result.size()).isEqualTo(2);
        assertTrue(result.contains(person1));
        assertTrue(result.contains(person2));

        db.delete(1);

        List<Person> newResult = db.findByAddress("vyzama");

        assertThat(newResult.size()).isEqualTo(1);
        assertTrue(newResult.contains(person2));
    }

    @Test
    public void severalThreads() {
        CachingPersonDatabase db = new CachingPersonDatabase();

        Person person1 = new Person(1, "Ruslan", "vyzama", "1234");
        Person person2 = new Person(2, "Senya", "vyzama", "56789");
        Person person3 = new Person(3, "Anonim", "xz", "0000");

        Thread thread1 = new Thread(() -> db.add(person1));
        Thread thread2 = new Thread(() -> {
            db.add(person2);
        });
        Thread thread3 = new Thread(() -> {
            db.add(person3);
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(db.findByAddress("vyzama").size()).isEqualTo(2);
        assertTrue(db.findByPhone("0000").contains(person3));
        assertTrue(db.findByPhone("56789").contains(person2));
        assertTrue(db.findByPhone("1234").contains(person1));
        assertTrue(db.findByName("Ruslan").contains(person1));
        assertTrue(db.findByName("Senya").contains(person2));
        assertTrue(db.findByName("Anonim").contains(person3));
    }
}
