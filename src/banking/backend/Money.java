package banking.backend;

import banking.NotYetImplementedException;

/**
 * A class for handling amounts of currency.
 * TODO: Add support for different currencies.
 */
public class Money {
    /**
     * Add the value of another money object to this.
     *
     * @param money the summand
     */
    void add(Money money) {
        throw new NotYetImplementedException();
    }

    /**
     * Subtract the value of another money object from this.
     *
     * @param money the subtrahend
     */
    void subtract(Money money) {
        throw new NotYetImplementedException();
    }

    /**
     * Turn the value negative.
     */
    void negate() {
        throw new NotYetImplementedException();
    }

    /**
     * Set the value to some percentage of the current value.
     *
     * @param percentage the percentage to be calculated
     */
    void applyPercentage(Percentage percentage) {
        throw new NotYetImplementedException();
    }
}
