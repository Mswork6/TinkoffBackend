package edu.hw7.Task3Test;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.SynchronizedPersonDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SynchronizedPersonDatabaseTest {
    @Test
    @DisplayName("Проверка корректного добавления, удаления и поиска человека, который есть в списке")
    public void testDataBaseWork() throws InterruptedException {
        //given
        SynchronizedPersonDatabase synchronizedPersonDatabase = new SynchronizedPersonDatabase();
        AtomicBoolean phoneFound = new AtomicBoolean(false);
        AtomicBoolean nameFound = new AtomicBoolean(false);
        AtomicBoolean addressFound = new AtomicBoolean(false);

        Thread addThread1 = new Thread(() -> {
            synchronizedPersonDatabase.add(new Person(1, "John", "123 Main St", "555-1234"));
            synchronizedPersonDatabase.add(new Person(2, "Bob", "235 Baker St", "224-7865"));
        });

        Thread addThread2 = new Thread(() -> {
            synchronizedPersonDatabase.add(new Person(3, "Alice", "456 Oak St", "555-5678"));
            synchronizedPersonDatabase.add(new Person(4, "John", "325 Avenue St", "498-6623"));
        });

        Thread deleteThread = new Thread(() -> {
            synchronizedPersonDatabase.delete(2);
        });

        Thread searchThread2 = new Thread(() -> {
            List<Person> adressList;
            adressList = synchronizedPersonDatabase.findByAddress("325 Avenue St");
            addressFound.set(!adressList.isEmpty());
        });

        Thread searchThread = new Thread(() -> {
            List<Person> phoneList;
            phoneList = synchronizedPersonDatabase.findByPhone("498-6623");
            phoneFound.set(!phoneList.isEmpty());

        });

        Thread searchThread3 = new Thread(() -> {
            List<Person> nameList;
            nameList = synchronizedPersonDatabase.findByName("John");
            nameFound.set(!nameList.isEmpty());
        });

        //when
        addThread1.start();
        addThread2.start();
        sleep(500);
        /* делаю здесь sleep, чтобы потоки добавления успели отработать
        до того как начнут работу потоки поиска.
         */
        deleteThread.start();
        searchThread.start();
        searchThread2.start();
        searchThread3.start();

        //then
        try {
            addThread1.join();
            addThread2.join();
            deleteThread.join();
            searchThread.join();
            searchThread2.join();
            searchThread3.join();

            assertEquals(synchronizedPersonDatabase.getDatabase().size(), 3);
            assertTrue(phoneFound.get());
            assertTrue(nameFound.get());
            assertTrue(addressFound.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Проверка поиска человека, которого нет в списке")
    public void testDataBaseFindNonExistingPerson() throws InterruptedException {
        //given
        SynchronizedPersonDatabase synchronizedPersonDatabase = new SynchronizedPersonDatabase();
        AtomicBoolean phoneFound = new AtomicBoolean(false);
        AtomicBoolean nameFound = new AtomicBoolean(false);
        AtomicBoolean addressFound = new AtomicBoolean(false);

        Thread addThread1 = new Thread(() -> {
            synchronizedPersonDatabase.add(new Person(1, "John", "123 Main St", "555-1234"));
            synchronizedPersonDatabase.add(new Person(2, "Bob", "235 Baker St", "224-7865"));
        });


        Thread searchThread2 = new Thread(() -> {
            List<Person> adressList;
            adressList = synchronizedPersonDatabase.findByAddress("456 Oak St");
            addressFound.set(!adressList.isEmpty());
        });

        Thread searchThread = new Thread(() -> {
            List<Person> phoneList;
            phoneList = synchronizedPersonDatabase.findByPhone("555-5678");
            phoneFound.set(!phoneList.isEmpty());

        });

        Thread searchThread3 = new Thread(() -> {
            List<Person> nameList;
            nameList = synchronizedPersonDatabase.findByName("Alice");
            nameFound.set(!nameList.isEmpty());
        });

        //when
        addThread1.start();
        sleep(500);
        searchThread.start();
        searchThread2.start();
        searchThread3.start();

        //then
        try {
            addThread1.join();
            searchThread.join();
            searchThread2.join();
            searchThread3.join();

            assertFalse(phoneFound.get());
            assertFalse(nameFound.get());
            assertFalse(addressFound.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
