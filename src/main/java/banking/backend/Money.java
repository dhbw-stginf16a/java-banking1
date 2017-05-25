package banking.backend;

import banking.NotYetImplementedException;

/**
 * A class for handling amounts of currency.
 */
public class Money implements Comparable<Money> {
    /**
     * Add the value of another money object to this.
     *
     * @param amount the summand
     */
    public void add(Money amount) {
        throw new NotYetImplementedException();
    }

    /**
     * Subtract the value of another money object from this.
     *
     * @param amount the subtrahend
     * @return this after calculation
     */
    public Money subtract(Money amount) {
        throw new NotYetImplementedException();
    }

    /**
     * Turn the value negative.
     * @return this after calculation
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
     * Set the value to some percentage of the current value.
     *
     * @param percentage the percentage to be calculated
     */
    void applyPercentage(Percentage percentage) {
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
