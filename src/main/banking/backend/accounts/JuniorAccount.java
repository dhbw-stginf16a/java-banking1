package banking.backend.accounts;

import banking.NotYetImplementedException;
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
     */
    public JuniorAccount(Customer holder) {
        super(holder);
        throw new NotYetImplementedException();
    }

    /**
     * Get the overdraft - which is zero for a junior account
     *
     * @return zero
     */
    @Override
    protected Money getOverdraft() {
        throw new NotYetImplementedException();
    }

    /**
     * Get the borrowing interest - which is zero for a junior account
     *
     * @return zero
     */
    @Override
    protected Percentage getBorrowingInterest() {
        return super.getBorrowingInterest();
    }
}
