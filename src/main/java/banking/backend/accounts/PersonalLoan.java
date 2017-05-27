package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

class PersonalLoan extends Loan {

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer is younger than 18 or a business customer
     */
    public PersonalLoan(Customer holder) {
        super(holder);
        throw new NotYetImplementedException();
    }

    @Override
    protected Percentage getBorrowingInterest() {
        throw new NotYetImplementedException();
    }
}
