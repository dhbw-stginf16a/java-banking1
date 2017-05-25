package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

class CorporateLoan extends Loan {

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     */
    public CorporateLoan(Customer holder) {
        super(holder);
        throw new NotYetImplementedException();
    }

    @Override
    protected Percentage getBorrowingInterest() {
        throw new NotYetImplementedException();
    }
}
