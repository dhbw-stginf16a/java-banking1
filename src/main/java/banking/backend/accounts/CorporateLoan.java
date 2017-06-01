package banking.backend.accounts;

import banking.backend.Percentage;
import banking.backend.persons.Customer;

public class CorporateLoan extends Loan {

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer is younger than 18 or no business Customer
     */
    public CorporateLoan(Customer holder) {
        super(holder);
        if (holder.getAge() < 18 || !holder.isBusinessCustomer()) {
            throw new IllegalArgumentException("You need to be at least 18 or a business customer.");
        }
    }

    @Override
    protected Percentage getBorrowingInterest() {
        return new Percentage(2);
    }
}
