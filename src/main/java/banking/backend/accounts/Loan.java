package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

abstract class Loan extends Account {
    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     */
    public Loan(Customer holder) {
        super(holder);
        throw new NotYetImplementedException();
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
}
