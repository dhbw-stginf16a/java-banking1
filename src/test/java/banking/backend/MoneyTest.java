package banking.backend;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contains every test for {@link Money}
 *
 * Created by guserav on 25.05.2017.
 */
class MoneyTest {

    /**
     * Tests {@link Money#add(Money)} by testing several additions.
     */
    @Test
    void add() {
        Money instance = new Money(1);
        assertAll(
                () -> assertNotSame( // Test if the money after the addition is actually an other instance
                        instance,
                        instance.add(instance)),
                () -> assertEquals( // 0.11 + 0.90 = 1.01
                        new Money(1.01),
                        new Money(0.11).add(new Money(0.90))),
                () -> assertEquals( // 5 + 5 = 10
                        new Money(10),
                        new Money(5).add(new Money(5))),
                () -> assertEquals( // 2 + (-3) = -1
                        new Money(-1),
                        new Money(2).add(new Money(-3)))
        );
    }

    /**
     * Tests {@link Money#subtract(Money)} by testing several subtractions.
     */
    @Test
    void subtract() {
        Money instance = new Money(1);
        assertAll(
                () -> assertNotSame( // Test if the money after the addition is actually a other instance
                        instance,
                        instance.subtract(instance)),
                () -> assertEquals( // 1.01 - 0.90 = 0.11
                        new Money(0.11),
                        new Money(1.01).subtract(new Money(0.90))),
                () -> assertEquals( // 10 - 5 = 5
                        new Money(5),
                        new Money(10).subtract(new Money(5))),
                () -> assertEquals( // -1 - (-3) = 2
                        new Money(2),
                        new Money(-1).subtract(new Money(-3))),
                () -> assertNotEquals(
                        new Money(100.01),
                        new Money(100).add(new Money("0.1€")))
        );
    }

    /**
     * Tests {@link Money#negate()} by testing several negations.
     */
    @Test
    void negate() {
        Money one = new Money(1);
        assertAll(
                () -> assertNotSame( // Test if the money after the addition is actually a other instance
                        one,
                        one.negate()),
                () -> assertEquals(
                        new Money(-1),
                        new Money(1).negate()),
                () -> assertNotEquals(
                        one,
                        one.negate()),
                () -> assertEquals(
                        new Money(0),
                        new Money(0).negate()),
                () -> assertEquals(
                        new Money(0.11),
                        new Money(-0.11).negate()),
                () -> assertEquals(
                        new Money(1000),
                        new Money(-1000).negate()),
                () -> assertEquals(
                        new Money(10),
                        new Money(10).negate().negate())
        );
    }

    /**
     * Tests all Constructors and the equals method.
     */
    @Test
    void equalsAndConstructor() {
        Money minusOneString = new Money("-1€");
        Money oneInt = new Money(1);
        Money oneIntInt = new Money(1, 0);
        Money oneCentInt = new Money(0, 1);
        Money oneCentString = new Money("0.01€");
        Money oneCentDouble = new Money(0.01);
        Money twoInt = new Money(2);
        assertFalse(oneInt.equals(null));
        assertFalse(oneInt.equals(new Object()));
        assertFalse(oneInt.equals("1"));
        assertFalse(oneInt.equals(1));
        assertNotEquals(oneInt, twoInt);
        assertEquals(oneCentInt, oneCentDouble);
        assertEquals(oneCentInt, oneCentString);
        assertEquals(oneCentDouble, oneCentString);
        assertNotEquals(oneCentInt, oneIntInt);
        assertNotEquals(oneInt, oneCentInt);
        assertEquals(new Money("0.00€"), new Money("0€"));
        assertEquals(new Money("-0.00€"), new Money("0€"));
        assertThrows(NumberFormatException.class, () -> new Money(""));
        assertThrows(NumberFormatException.class, () -> new Money("100.111€"));
        assertThrows(NumberFormatException.class, () -> new Money("10,0€"));
        assertThrows(NumberFormatException.class, () -> new Money("10.€"));
        assertThrows(NumberFormatException.class, () -> new Money("100.100,0€"));
        assertThrows(NumberFormatException.class, () -> new Money("100.100,01€"));
        assertThrows(NumberFormatException.class, () -> new Money("10"));
        assertThrows(NumberFormatException.class, () -> new Money("0"));
        assertThrows(NumberFormatException.class, () -> new Money("0.00"));
        assertThrows(NumberFormatException.class, () -> new Money("10,0€"));
        assertThrows(IllegalArgumentException.class, () -> new Money(10, -1));
        assertThrows(IllegalArgumentException.class, () -> new Money(-10, 1));
        assertThrows(IllegalArgumentException.class, () -> new Money(10, 100));
    }

    /**
     * Tests several adds of Percentages.
     */
    @Test
    void applyPercentage() {
        assertEquals(
                new Money(1, 5),
                new Money(1).applyPercentage(new Percentage(105)));
        assertNotEquals(
                new Money(1, 5),
                new Money(1, 5).applyPercentage(new Percentage(105)));
        assertEquals(
                new Money(5, 0),
                new Money(100).applyPercentage(new Percentage(5)));
        assertNotEquals(
                new Money(1, 5),
                new Money(1).applyPercentage(new Percentage(-105)));
        assertEquals(
                new Money(0, 55),
                new Money(0, 50).applyPercentage(new Percentage(110)));


    }
    /**
     * Tests several to String Conversions
     *
     * @return A List of DynamicTest
     */
    @TestFactory
    List<DynamicTest> toStringTest() {
        String[] strings = {
                "10.00€",
                "100,999.45€",
                "1,483.01€",
                "0.01€",
                "-1.00€"
        };
        List<DynamicTest> tests = new LinkedList<>();

        for (String s : strings) {
            tests.add(
                    DynamicTest.dynamicTest(
                            "Test: " + s,
                            () -> assertEquals(s, new Money(s).toString())
                    )
            );
        }

        return tests;
    }

    /**
     * Checks the return value of {@link Money#compareTo(Money)}
     */
    @Test
    void compareTo() {
        assertAll(
                () -> assertEquals(
                        -1,
                        new Money(1).compareTo(new Money(20))),
                () -> assertEquals(
                        1,
                        new Money(22).compareTo(new Money(1))),
                () -> assertEquals(
                        0,
                        new Money(1).compareTo(new Money(1, 0))),
                () -> assertEquals(
                        -1,
                        new Money(-3).compareTo(new Money(-1))),
                () -> assertEquals(
                        -1,
                        new Money(1).compareTo(new Money(2))),
                () -> assertThrows(
                        NullPointerException.class,
                        () -> new Money(0).compareTo(null))
        );
    }

}