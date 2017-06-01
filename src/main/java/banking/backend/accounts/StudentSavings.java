package banking.backend.accounts;

import banking.backend.Percentage;
import banking.backend.persons.Customer;

public class StudentSavings extends Savings {

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer is older than 18 or a business customer
     */
    public StudentSavings(Customer holder) {
        super(holder);
        if (holder.getAge() < 18 || !holder.isBusinessCustomer()) {
            throw new IllegalArgumentException("You need to be at least 18 or a business customer.");
        }
    }

    @Override
    protected Percentage getSavingInterest() {
        return new Percentage(2); // not sure which number
    }
}
