package banking.backend;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by guserav on 28.05.2017.
 */
class DateTimeTest {
    private static final String PATTERN = "yyyy-MM-dd_HH:mm:ss";

    /**
     * Returns a DateTime that references the Day that is the specified years away
     *
     * @param age the years to go back
     * @return DateTime that is age years in the past
     */
    public static DateTime getDateTimeFromAge(int age) {
        return new DateTime(LocalDate.now().atTime(0, 1).minusYears(age).format(DateTimeFormatter.ofPattern(PATTERN)), PATTERN);
    }

    public static DateTime getDateTimeFromDate(String s) {
        return new DateTime(s, "dd.MM.yyyy");
    }

    @Test
    void compareTo() {
        DateTime timeNow = new DateTime();
        assertAll(
                () -> assertEquals(-1, getDateTimeFromDate("18.04.2017").compareTo(getDateTimeFromDate("18.05.2017")), "18.04.2017 should be before 18.05.2017"),
                () -> assertEquals(1, getDateTimeFromDate("01.02.2018").compareTo(getDateTimeFromDate("18.05.2017")), "01.02.2018 should be after 18.05.2017"),
                () -> assertEquals(1, getDateTimeFromDate("18.04.1993").compareTo(getDateTimeFromDate("18.05.1992")), "18.04.1993 should be after 18.04.1993"),
                () -> assertEquals(-1, getDateTimeFromDate("01.04.2017").compareTo(getDateTimeFromDate("02.05.2017")), "01.04.2017 should be before 02.05.2017"),
                () -> assertEquals(0, getDateTimeFromDate("18.04.2017").compareTo(getDateTimeFromDate("18.04.2017")), "18.04.2017 should be equal to 18.04.2017"),
                () -> assertEquals(0, timeNow.compareTo(timeNow), timeNow + " should be equal to" + timeNow)
        );
    }

    @RepeatedTest(5)
    void getAge() {
        int age = (int) (Math.random() * 100);

        final DateTime birthday = getDateTimeFromAge(age);
        assertEquals(age, birthday.getAge(), "The birthday is at " + birthday + " now it is " + LocalDate.now());
    }

    @Test
    void toStringTest() {
        assertAll(
                () -> assertEquals("2017-03-08_00:00:00", getDateTimeFromDate("08.03.2017").toString()),
                () -> assertEquals("0023-01-15_00:00:00", getDateTimeFromDate("15.01.0023").toString()),
                () -> assertEquals("1998-03-16_00:00:00", getDateTimeFromDate("16.03.1998").toString()),
                () -> assertEquals("1854-05-13_23:59:59", new DateTime("1854 05 13 23 59 59", "yyyy MM dd HH mm ss").toString())
        );
    }

}