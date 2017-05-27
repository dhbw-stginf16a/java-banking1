package banking.backend.accounts;

import banking.NotYetImplementedException;
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
    private static final Percentage SAVING_INTEREST = null;
    private static final Money OVERDRAFT = null;
    private static final Percentage BORROWING_INTEREST = null;

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     */
    public CurrentAccount(Customer holder) {
        super(holder);
    }

    @Override
    protected Money getOverdraft() {
        throw new NotYetImplementedException();
    }

    @Override
    protected Percentage getBorrowingInterest() {
        throw new NotYetImplementedException();
    }

    @Override
    protected Percentage getSavingInterest() {
        throw new NotYetImplementedException();
    }

    @Override
    public void receiveInvoice(Money amount) {
        throw new NotYetImplementedException();
    }

    @Override
    public void sendInvoice(Money amount) throws InsufficientFundsException {
        throw new NotYetImplementedException();
    }

    @Override
    public void withdraw(Money amount) throws InsufficientFundsException {
        throw new NotYetImplementedException();
    }

    @Override
    public void deposit(Money amount) {
        throw new NotYetImplementedException();
    }
}
