package banking.backend;

import banking.NotYetImplementedException;

/**
 * Represents a percentage of something. May be above 100.00 or below 0.00.
 * Accuracy of two decimals XX.XX%
 */
public class Percentage {
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
        throw new NotYetImplementedException();
    }

    /**
     * Construct a percentage by a given double
     *
     * @param percentage the percentage to be stored
     */
    public Percentage(double percentage) {
        throw new NotYetImplementedException();
    }

    /**
     * Construct a percentage by parsing a string
     *
     * This string consist of a number that fits in this criteria
     * - english number formatting
     * - '.' as decimal separator
     * - max 4 decimal places if no % is uses else max. 2 decimal places
     * - if no % is uses the number is interpreted as * 100%
     *
     * @param percentage the percentage to be stored
     */
    public Percentage(String percentage) {
        throw new NotYetImplementedException();
    }

    /**
     * Get the int value of this.
     * Accuracy of two decimals XX.XX%
     *
     * @return the int value with two decimals precision
     */
    int getPercentage() {
        throw new NotYetImplementedException();
    }

    /**
     * Gets the Percentage as String of format XX.XX% including grouping separators
     *
     * @return The String representation of this Percentage
     */
    @Override
    public String toString() {
        throw new NotYetImplementedException();
    }

    /**
     * Test if the given object is a percentage with the same value
     *
     * @param o the object to compare to
     * @return true if the object is of type Percentage and has the same integer value stored.
     */
    @Override
    public boolean equals(Object o) {
        throw new NotYetImplementedException();
    }
}
