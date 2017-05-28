package banking.backend;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the functions of
 * <p>
 * Created by guserav on 28.05.2017.
 */
class PercentageTest {
    private static DecimalFormat decimalFormatNoPercent;
    private static DecimalFormat decimalFormatPercent;


    @BeforeAll
    public static void beforeAll() {
        decimalFormatNoPercent = new DecimalFormat("#.####", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        decimalFormatPercent = new DecimalFormat("#.##%", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    }

    /**
     * This test the {@link Percentage#Percentage(int)} constructor.
     */
    @RepeatedTest(50)
    void constructorInt() {
        int toTest = (int) (Math.random() * 1000);
        assertEquals(toTest * 100, new Percentage(toTest).getPercentage());
    }

    /**
     * This test the {@link Percentage#Percentage(double)} constructor.
     */
    @RepeatedTest(50)
    void constructorDouble() {
        double toTest = Math.floor(Math.random() * 1000 * 100) / 100;
        assertEquals((int) (toTest * 100), new Percentage(toTest).getPercentage());
    }

    /**
     * This test the {@link Percentage#Percentage(String)} constructor with strings without % at the end
     */
    @RepeatedTest(value = 50, name = "Percentage(String) Test {currentRepetition} of {totalRepetitions}")
    @DisplayName("Tests Percentage(String) with String without % at the end")
    void constructorString1() {
        double toTest = Math.floor(Math.random() * 1000 * 10000) / 10000;
        final String s = decimalFormatNoPercent.format(toTest);
        assertEquals((int) (toTest * 10000), new Percentage(s).getPercentage(), "Tested \"" + s + "\"");
    }

    /**
     * This test the {@link Percentage#Percentage(String)} constructor with strings with % at the end
     */
    @RepeatedTest(value = 50, name = "Percentage(String) Test {currentRepetition} of {totalRepetitions}")
    @DisplayName("Tests Percentage(String) with String with % at the end")
    void constructorString2() {
        double toTest = Math.floor(Math.random() * 1000 * 100) / 100;
        final String s = decimalFormatPercent.format(toTest);
        assertEquals((int) (toTest * 10000), new Percentage(s).getPercentage(), "Tested \"" + s + "\"");
    }

    @Test
    void toStringTest() {
        assertAll(
                () -> assertEquals("1.00%", new Percentage(1.0).toString()),
                () -> assertEquals("0.01%", new Percentage(1).toString()),
                () -> assertEquals("1.00%", new Percentage(1).toString()),
                () -> assertEquals("1,000.00%", new Percentage(1000).toString()),
                () -> assertEquals("1,234.56", new Percentage(1234.56).toString()),
                () -> assertEquals("6,543.21%", new Percentage(6543.21).toString())
        );
    }

    @Test
    void equalsTestAndConstructor() {
        assertAll(
                () -> assertEquals(new Percentage(1), new Percentage(1.0)),
                () -> assertEquals(new Percentage(1), new Percentage(1)),
                () -> assertEquals(new Percentage("1%"), new Percentage(1.0)),
                () -> assertEquals(new Percentage("1"), new Percentage(100)),
                () -> assertEquals(new Percentage("0.01%"), new Percentage(0.01)),
                () -> assertEquals(new Percentage("0.01"), new Percentage(1)),
                () -> assertEquals(new Percentage("0.0001"), new Percentage(0.01))
        );
    }

}