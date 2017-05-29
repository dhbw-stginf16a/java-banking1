package banking.backend.persons;

import banking.backend.DateTime;
import banking.backend.DateTimeTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static banking.backend.persons.CustomerTest.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests all features of {@link Person}
 * <p>
 * Created by guserav on 29.05.2017.
 */
class PersonTest {
    @RepeatedTest(10)
    void getAge() {
        int age = (int) (Math.random() * 100);
        assertEquals(
                age,
                new Person(DUMMY_NAME, DUMMY_ADDRESS, DateTimeTest.getDateTimeFromAge(age), DUMMY_TELEPHONE_NUMBER)
                        .getAge(),
                "If this isn't equal the age wasn't calculated properly"
        );
    }

    @Test
    void constructor() {
        Person dummyPerson = new Person(DUMMY_NAME, DUMMY_ADDRESS, DUMMY_BIRTH_DATE, DUMMY_TELEPHONE_NUMBER);
        // Check if all attributes are correctly set
        assertAll(
                () -> assertEquals(DUMMY_NAME, dummyPerson.getName()),
                () -> assertEquals(DUMMY_ADDRESS, dummyPerson.getAddress()),
                () -> assertEquals(DUMMY_BIRTH_DATE, dummyPerson.getBirthdate()),
                () -> assertEquals(DUMMY_TELEPHONE_NUMBER, dummyPerson.getTelephoneNumber())
        );
        // No parameter of the constructor can be null except for telephone number
        assertThrows(IllegalArgumentException.class, () -> new Person(null, "address", new DateTime(), "tel"));
        assertThrows(IllegalArgumentException.class, () -> new Person("name", null, new DateTime(), "tel"));
        assertThrows(IllegalArgumentException.class, () -> new Person("name", "address", null, "tel"));
        assertThrows(IllegalArgumentException.class, () -> new Person("name", "address", new DateTime(), null));
        assertNotNull(new Person("name", "address", DUMMY_BIRTH_DATE, null), "Telephone number can be null");

        // The date of birth cannot lie in the future
        assertThrows(IllegalArgumentException.class, () -> new Person(
                "name", "address", DateTimeTest.getDateTimeFromAge(-1), "tel"));
    }

}