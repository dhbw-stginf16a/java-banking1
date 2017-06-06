package banking.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a point in time classified by date and time.
 */
public class DateTime implements Comparable<DateTime> {
    private static final String PATTERN = "yyyy-MM-dd_HH:mm:ss";

    private final LocalDateTime value;

    /**
     * Constructs a date time with the current time.
     */
    public DateTime() {
        value = LocalDateTime.now();
    }

    /**
     * Constructs a date time with the specified value parsed with format.
     *
     * @param value  the value
     * @param format the format for parsing the value
     * @throws DateTimeParseException if the format is invalid.
     */
    public DateTime(String value, String format) {
        LocalDateTime dummy = null;

        try {
            dummy = LocalDateTime.parse(value, DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            dummy = LocalDate.parse(value, DateTimeFormatter.ofPattern(format)).atStartOfDay();
        }

        this.value = dummy;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     * <p>
     * this return -1 if this date is before dt.
     *
     * @param dt the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(DateTime dt) {
        if (value.isAfter(dt.value)) {
            return 1;
        } else if (value.isBefore(dt.value)) {
            return -1;
        }
        return 0;
    }

    /**
     * Get the number of years this lies in the past.
     *
     * @return the age of this
     */

    public int getAge() {
        LocalDateTime now = LocalDateTime.now();
        int differenceYears = now.getYear() - value.getYear();

        LocalDateTime inPast = now.minusYears(differenceYears);

        return inPast.isBefore(value) ? differenceYears -1 : differenceYears;
    }

    /**
     * Format the datetime as a nice String for printing.
     * <p>
     * this is in the format 2014-12-03_01:09:12
     * yyyy-MM-dd_HH:mm:ss
     *
     * @return nice format of the datetime
     */
    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern(PATTERN));
    }

    /**
     * Test if the date and time of this is equal to another instance.
     *
     * @param obj other object to compare to
     * @return equivalence
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof DateTime && compareTo((DateTime) obj) == 0;
    }
}
