package banking.backend.persons;

import banking.backend.DateTime;
import banking.backend.DateTimeTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests all features of {@link Person}
 * <p>
 * Created by guserav on 29.05.2017.
 */
class PersonTest {
    @RepeatedTest(10)
    void getAge() {
        int age = (int) Math.random() * 100;
        assertEquals(
                age,
                new Person("Testi Testdummy", "Teststraße 77a\n77777 Testingen", DateTimeTest.getDateTimeFromAge(age), "+49 827 8362783").getAge(),
                "If this isn't equal the age wasn't calculated properly"
        );
    }

    @Test
    void allGetters() {
        String name = "Papa Schlump";
        String address = "Hüpfdudelstraße 31, Schlumphausen 12345";
        DateTime birthday = new DateTime();
        String telephone = "+491637349842";

        Person testPerson = new Person(name, address, birthday, telephone);

        assertAll(
                () -> assertEquals(name, testPerson.getName(), "If this isn't equal the name wasn't stored properly"),
                () -> assertEquals(address, testPerson.getAddress(), "If this isn't equal the address wasn't stored properly"),
                () -> assertEquals(birthday, testPerson.getBirthdate(), "If this isn't equal the birthday wasn't stored properly"),
                () -> assertEquals(telephone, testPerson.getTelephoneNumber(), "If this isn't equal the telephone number wasn't stored properly")
        );
    }

    @Test
    void constructor() {
        String name = "Papa Schlump";
        String address = "Hüpfdudelstraße 31, Schlumphausen 12345";
        DateTime birthday = new DateTime();
        String telephone = "+491637349842";

        assertAll("Checks if the constructor throws a IllegalArgumentException if either name, address or birthday is null",
                () -> assertThrows(IllegalArgumentException.class, () -> new Person(null, address, birthday, telephone)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Person(name, null, birthday, telephone)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Person(name, address, null, telephone)),
                () -> assertNull(new Person(name, address, birthday, telephone), "Telephone could be null")
        );
    }

}