package banking.backend;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 * A class for handling amounts of currency.
 */
public class Money implements Comparable<Money> {
    /**
     * Amount in cents
     */
    private int cents;

    /**
     * Constructs a money object holding the given amount in euro.
     * <p>
     * To add cents please use {@link Money#Money(int, int)}
     *
     * @param amount the integer representation of the amount
     */
    public Money(int amount) {
        cents = amount * 100;
    }

    /**
     * Constructs a money object holding the given amount.
     *
     * @param amount The int value of the non-decimal places
     * @param cents  The int value of the decimal places
     * @throws IllegalArgumentException if cents ist not in the interval [0,99]
     */
    public Money(int amount, int cents) {

        if (cents < 0 || cents > 99) {
            throw new IllegalArgumentException("cent is a wrong value has to be between 0 and 99");
        } else {
            if (amount < 0) {
                throw new IllegalArgumentException("amount cant be a negative number");
            } else this.cents = amount * 100 + cents;
        }


    }

    /**
     * Constructs a money object from the given double value and cuts off after the second decimal place
     *
     * @param amount the double value to store
     */
    public Money(double amount) {
        cents = (int) (amount * 100);
    }

    /**
     * Converts the String if possible to a money amount. The String needs to pass the following regex:
     * ^-?\d{1,3}?(,\d{3})*((\.\d\d)|(\.\d))?€$
     * <p>
     * In words:
     * - The decimal separator is '.'
     * - The thousands separator is needed and is ','
     * - The number can either have no decimal separator and no decimals or exactly one or two decimal places
     * - The euro after the number is needed
     * <p>
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
        double a;
        if (amount.matches("^-?\\d{1,3}?(,\\d{3})*((\\.\\d\\d)|(\\.\\d))?€$")) {
            DecimalFormat df = new DecimalFormat("###,###.##€", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
            try {
                Double number = df.parse(amount).doubleValue();
                cents = (int) (number * 100);
            } catch (ParseException e) {
                throw new NumberFormatException("Number is not in the format ###,###.##");
            }
        } else throw new NumberFormatException("Number is not in the format ###,###.##");
    }


    /**
     * adds one instance of money to an already existing instance.
     *
     * @param money the summand
     * @return a new instance after calculation
     */

    public Money add(Money money) {

        Money result = new Money(0, 0);
        result.cents = money.cents + this.cents;
        return result;
    }

    /**
     * Returns a new instance of Money with the value of this minus money.
     *
     * @param money the subtrahend
     * @return a new instance after calculation
     */
    public Money subtract(Money money) {
        Money result = new Money(0, 0);
        result.cents = this.cents - money.cents;
        return result;
    }

    /**
     * Returns a new instance with the negative value of this.
     *
     * @return a new instance after calculation
     */
    public Money negate() {
        return new Money(this.cents * -0.01);
    }

    /**
     * Checks whether this is equal to another monetary amount.
     *
     * @param o the object maybe
     * @return true if same object or same monetary amount - false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else {
            return o instanceof Money && (this.cents == ((Money) o).cents);
        }
    }

    /**
     * Adds the needed percentage to the value and generates a new instance containing the result
     *
     * @param percentage the percentage to be calculated
     * @return a new instance after calculation
     * @deprecated
     */
    @Deprecated
    Money applyPercentage(Percentage percentage) {
        return addPercentage(percentage);
    }

    /**
     * Adds the needed percentage to the value and generates a new instance containing the result
     *
     * @param percentage the percentage to be calculated
     * @return a new instance after calculation
     */
    public Money addPercentage(Percentage percentage) {
        Money money = new Money(0);
        Percentage hundred = new Percentage(100);
        money.cents = this.cents * ((hundred.getPercentage()) / 100 + (percentage.getPercentage() / 100)) / 100;
        return money;
    }

    /**
     * String representation of the amount.
     *
     * @return a string representation of this
     */
    @Override
    public String toString() {
        double total;
        DecimalFormat df = new DecimalFormat("###,##0.00€", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        total = this.cents / 100.0;
        return df.format(total);
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
     * <p>
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
        if (this.cents < money.cents) {
            return -1;
        } else if (this.cents == money.cents) {
            return 0;
        } else return 1;
    }
}
