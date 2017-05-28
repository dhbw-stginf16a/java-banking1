package banking.backend;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 * Represents a percentage of something. May be above 100.00 or below 0.00.
 * Accuracy of two decimals XX.XX%
 */
public class Percentage {
    private static final int ACCURACY = 2;
    private static final int FACTOR_ACCURACY = (int) Math.pow(10, ACCURACY);

    private static final DecimalFormat format = new DecimalFormat("0.00%", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

    /**
     * Int value of this.
     * Accuracy of two decimals XX.XX%
     */
    private int percentage;

    /**
     * Construct a percentage by a given integer
     *
     * @param percentage the percentage to be stored
     */
    public Percentage(int percentage) {
        this.percentage = percentage * FACTOR_ACCURACY;
    }

    /**
     * Construct a percentage by a given double
     *
     * @param percentage the percentage to be stored
     */
    public Percentage(double percentage) {
        this.percentage = (int) Math.floor(percentage * FACTOR_ACCURACY);
    }

    /**
     * Construct a percentage by parsing a string
     * <p>
     * This string consist of a number that fits in this criteria
     * - english number formatting
     * - '.' as decimal separator
     * - max 4 decimal places if no % is uses else max. 2 decimal places
     * - if no % is uses the number is interpreted as * 100%
     *
     * @param string the percentage to be stored
     * @throws IllegalArgumentException if the string is of the wrong format
     */
    public Percentage(String string) {
        Number number = null;

        if (string.endsWith("%")) {
            String pattern = "#.";
            for (int i = 0; i < ACCURACY; i++) {
                pattern = pattern + "#";
            }
            pattern += "%";

            DecimalFormat formatWithPercent = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));

            formatWithPercent.setGroupingSize(3);
            formatWithPercent.setGroupingUsed(true);

            try {
                number = formatWithPercent.parse(string);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Couldn't parse: \"" + string + "\" to Percentage", e);
            }
        } else {
            String pattern = "#.";
            for (int i = 0; i < ACCURACY + 2; i++) {
                pattern = pattern + "#";
            }

            DecimalFormat formatWithoutPercent = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));

            formatWithoutPercent.setGroupingSize(3);
            formatWithoutPercent.setGroupingUsed(true);

            try {
                number = formatWithoutPercent.parse(string);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Couldn't parse: \"" + string + "\" to Percentage", e);
            }
        }

        percentage = (int) Math.round(number.doubleValue() * 100 * FACTOR_ACCURACY);
    }

    /**
     * Get the int value of this.
     * Accuracy of two decimals XX.XX%
     *
     * @return the int value with two decimals precision
     */
    int getPercentage() {
        return percentage;
    }

    /**
     * Gets the Percentage as String of format XX.XX% including grouping separators
     *
     * @return The String representation of this Percentage
     */
    @Override
    public String toString() {
        format.setGroupingUsed(true);
        format.setGroupingSize(3);
        return format.format(((double) percentage) / FACTOR_ACCURACY / 100);
    }

    /**
     * Test if the given object is a percentage with the same value
     *
     * @param o the object to compare to
     * @return true if the object is of type Percentage and has the same integer value stored.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Percentage && ((Percentage) o).percentage == percentage;
    }
}
