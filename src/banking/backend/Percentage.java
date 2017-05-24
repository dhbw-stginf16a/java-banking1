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
}
