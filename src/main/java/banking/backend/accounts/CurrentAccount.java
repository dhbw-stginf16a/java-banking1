package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

/**
 * A current account is the most versatile since it allow all kinds of operations
 */
public class CurrentAccount extends Account {
    /**
     * The interest applied... TODO
     */
    private static final Percentage SAVING_INTEREST = new Percentage("1%");
    private static final Money OVERDRAFT = new Money("1,000€");
    private static final Percentage BORROWING_INTEREST = new Percentage("10%");
    private static final int HOLDER_AGE = 16;

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer is younger than 16 or a business person
     */
    public CurrentAccount(Customer holder) {
        super(holder);
        if (holder.isBusinessCustomer() || holder.getAge() < HOLDER_AGE) {
            throw new IllegalArgumentException("A current account is only allowed for no business customers that are at least 16.");
        }
    }

    protected CurrentAccount(Customer holder, boolean isJuniorAccount) {
        super(holder);
        if (!isJuniorAccount) {
            throw new IllegalAccessError("This constructor could only be called from a valid subclass");
        }
    }

    @Override
    protected Money getOverdraft() {
        return OVERDRAFT;
    }

    @Override
    protected Percentage getBorrowingInterest() {
        return BORROWING_INTEREST;
    }

    @Override
    protected Percentage getSavingInterest() {
        return SAVING_INTEREST;
    }
}
