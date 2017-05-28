package banking.backend;

import banking.NotYetImplementedException;

/**
 * Represents a point in time classified by date and time.
 */
public class DateTime implements Comparable<DateTime> {
    /**
     * Constructs a date time with the current time.
     */
    public DateTime() {
        throw new NotYetImplementedException();
    }

    /**
     * Constructs a date time with the specified value parsed with format.
     *
     * @param value  the value
     * @param format the format for parsing the value
     */
    public DateTime(String value, String format) {
        throw new NotYetImplementedException();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     * <p>
     * this return -1 if this date is before dt.
     *
     * @param   dt the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     *
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException if the specified object's type prevents it
     *         from being compared to this object.
     */
    @Override
    public int compareTo(DateTime dt) {
        throw new NotYetImplementedException();
    }

    /**
     * Get the number of years this lies in the past.
     *
     * @return the age of this
     */
    int getAge() {
        throw new NotYetImplementedException();
    }

    /**
     * Format the datetime as a nice String for printing.
     *
     * this is in the format 2014-12-03_01:09:12
     * yyyy-MM-dd_HH:mm:ss
     *
     * @return nice format of the datetime
     */
    @Override
    public String toString() {
        throw new NotYetImplementedException();
    }
}
