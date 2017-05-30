package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

/**
 * A savings account only for business users with different conditions.
 */
class CorporateSavings extends Savings {

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer is younger than 18 or not a business customer
     */
    public CorporateSavings(Customer holder) {
        super(holder);
        if (holder.getAge() < 18 || !holder.isBusinessCustomer()) {
            throw new IllegalArgumentException("You need to be at least 18 or a business customer.");
        }
        throw new NotYetImplementedException();
    }

    @Override
    protected Percentage getSavingInterest() {
        return (new Percentage(2));
    }
}
