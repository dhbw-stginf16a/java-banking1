package banking.backend;

import banking.NotYetImplementedException;

/**
 * A class for handling amounts of currency.
 */
public class Money implements Comparable<Money> {

    /**
     * Constructs a money object holding the given amount.
     *
     * To add cents please use {@link Money#Money(int, int)}
     *
     * @param amount the integer representation of the amount
     */
    public Money(int amount) {
        throw new NotYetImplementedException();
    }

    /**
     * Constructs a money object holding the given amount.
     *
     * @param amount The int value of the non-decimal places
     * @param cents  The int value of the decimal places
     * @throws IllegalArgumentException if cents ist not in the interval [0,99]
     */
    public Money(int amount, int cents) {
        throw new NotYetImplementedException();
    }

    /**
     * Constructs a money object from the given double value and cuts off after the the second decimal place
     *
     * @param amount the double value to store
     */
    public Money(double amount) {
        throw new NotYetImplementedException();
    }

    /**
     * Converts the String if possible to a money amount. The String needs to pass the following regex:
     * ^-?\d{1,3}?(,\d{3})*((\.\d\d)|(\.\d))?€$
     *
     * In words:
     * - The decimal separator is '.'
     * - The thousands separator is needed and is ','
     * - The number can either have no decimal separator and no decimals or exactly one or two decimal places
     * - The euro after the number is needed
     *
     * This means that the following formats should be handled:
     * - XXX,XXX.XX€ (english format)
     * - X,XXX.X€
     * - XXX,XXX,XXX€
     * This isn't supported:
     * - X.XX (No euro sign in it)
     * - X,XX€ (wrong decimal separator)
     * - XXXXXX.XX€ (No thousands separators)
     *
     * @param amount The String to parse
     * @throws NumberFormatException if the String isn't of the format specified above
     */
    public Money(String amount) {
        throw new NotYetImplementedException();
    }

    /**
     * Returns a new instance of Money with the added value of this and amount.
     *
     * @param amount the summand
     * @return a new instance after calculation
     */
    public Money add(Money amount) {
        throw new NotYetImplementedException();
    }

    /**
     * Returns a new instance of Money with the value of this minus amount.
     *
     * @param amount the subtrahend
     * @return a new instance after calculation
     */
    public Money subtract(Money amount) {
        throw new NotYetImplementedException();
    }

    /**
     * Returns a new instance with the negative value of this.
     * @return a new instance after calculation
     */
    public Money negate() {
        throw new NotYetImplementedException();
    }

    /**
     * Checks whether this is equal to another monetary amount.
     *
     * @param o the object maybe
     * @return false if other object or different monetary amount - true otherwise
     */
    @Override
    public boolean equals(Object o) {
        throw new NotYetImplementedException();
    }

    /**
     * Adds the needed percentage to the value and generates a new instance containing the result
     *
     * @param percentage the percentage to be calculated
     * @return a new instance after calculation
     */
    public Money applyPercentage(Percentage percentage) {
        throw new NotYetImplementedException();
    }

    /**
     * String representation of the amount.
     *
     * @return a string representation of this
     */
    @Override
    public String toString() {
        throw new NotYetImplementedException();
    }

    /**
     * Compares this money object with the specified money object for order.  Returns a
     * negative integer, zero, or a positive integer as this money object is less
     * than, equal to, or greater than the specified money object.
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
     *
     * This returns -1 if this object is less than money
     *
     * @param money the money object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Money money) {
        throw new NotYetImplementedException();
    }
}
