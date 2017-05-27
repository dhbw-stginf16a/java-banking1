package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

class StudentSavings extends Savings {

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer is older than 18 or a business customer
     */
    public StudentSavings(Customer holder) {
        super(holder);
        throw new NotYetImplementedException();
    }

    @Override
    protected Percentage getSavingInterest() {
        throw new NotYetImplementedException();
    }
}
