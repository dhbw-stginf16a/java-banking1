package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

abstract class Loan extends Account {
    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer is younger than 18
     */
    public Loan(Customer holder) {
        super(holder);
        throw new NotYetImplementedException();
    }

    /**
     * Initialises the value of the Loan
     *
     * @param amount The positive amount to store in the Loan
     * @throws IllegalArgumentException if the amount is negative or zero
     * @throws IllegalStateException    if the value is set already
     */
    void initAmount(Money amount) {
        if (balance != null) {
            throw new IllegalStateException("The Loan is already initialised.");
        }
        if (new Money(0).compareTo(amount) >= 0) {
            throw new IllegalArgumentException("Please enter a positive amount to initialise the Loan");
        }
        balance = amount.negate();
    }

    /**
     * Get the overdraft - which is zero for a loan
     *
     * @return zero
     */
    @Override
    protected Money getOverdraft() {
        throw new NotYetImplementedException();
    }

    /**
     * Get the saving interest - which is zero for a loan
     *
     * @return zero
     */
    @Override
    protected Percentage getSavingInterest() {
        throw new NotYetImplementedException();
    }

    /**
     * Getter for the balance field
     *
     * @return the current balance of the loan
     * @throws IllegalStateException if the loan wasn't initialized correctly
     */
    @Override
    public Money getBalance() {
        throw new NotImplementedException();
    }
}
