package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;
import banking.backend.persons.Person;

public class JuniorAccount extends CurrentAccount {

    private static final Percentage BORROWING_INTEREST = new Percentage(0);
    private static final Money OVERDRAFT = new Money(0);
    private static final int HOLDER_MAX_AGE = 16;

    private Person legalGuardian;

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer is older than 16 or a business person
     */
    public JuniorAccount(Customer holder, Person legalGuardian) {
        super(holder, true);
        if (holder.getAge() >= HOLDER_MAX_AGE || holder.isBusinessCustomer()) {
            throw new IllegalArgumentException("A junior account is only possible for non business teenagers below the age of 16.");
        }
        if (legalGuardian == null || legalGuardian.getAge() >= 18) {
            throw new IllegalArgumentException("Please enter a valid guardian of the galaxy");
        }
        this.legalGuardian = legalGuardian;
    }

    /**
     * Get the overdraft - which is zero for a junior account
     *
     * @return zero
     */
    @Override
    protected Money getOverdraft() {
        return OVERDRAFT;
    }

    /**
     * The Borrowing interest which is zero for a junior account.
     * @return zero
     */
    @Override
    protected Percentage getBorrowingInterest() {
        return BORROWING_INTEREST;
    }
}
