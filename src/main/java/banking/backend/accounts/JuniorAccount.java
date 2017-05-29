package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;
import banking.backend.persons.Person;

public class JuniorAccount extends CurrentAccount {
    private Person legalGuardian;

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer is older than 16 or a business person
     */
    public JuniorAccount(Customer holder) {
        super(holder);
        if (holder.getAge() < 16 || !holder.isBusinessCustomer()) {
            throw new IllegalArgumentException("You need to be at least 16 or a business person.");
        }
    }

    /**
     * Get the overdraft - which is zero for a junior account
     *
     * @return zero
     */
    @Override
    protected Money getOverdraft() {
        return new Money(0);
    }

    /**
     * Get the borrowing interest - which is zero for a junior account
     *
     * @return zero
     */
    @Override
    protected Percentage getBorrowingInterest() {
        return new Percentage(0);
    }
}
