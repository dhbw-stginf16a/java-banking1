package banking.backend;

import banking.NotYetImplementedException;

/**
 * Represents a point in time classified by date and time.
 */
public class DateTime implements Comparable<DateTime> {
    /**
     * Compare the exact point in time to another datetime.
     *
     * @param dt the other datetime to compare this to
     * @return a negative integer, zero, or a positive integer as this event is older than,
     *         equal to, or newer than the specified datetime.
     */
    @Override
    public int compareTo(DateTime dt) {
        throw new NotYetImplementedException();
    }

    /**
     * Get the nubmer of years this lies in the past.
     *
     * @return the age of this
     */
    int getAge() {
        throw new NotYetImplementedException();
    }

    /**
     * Format the datetime as a nice String for printing.
     * @return nice format of the datetime
     */
    @Override
    public String toString() {
        throw new NotYetImplementedException();
    }
}
