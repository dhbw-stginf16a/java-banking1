package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

/**
 * Parent class for all kinds of bank accounts.
 * The subclasses should grant public access to available methods of transaction:
 * {@link #receiveInvoice(Money)}, {@link #sendInvoice(Money)},
 * {@link #withdraw(Money)}, {@link #deposit(Money)}
 */
abstract public class Account {
    /**
     * The amount of money available on this account.
     */
    protected Money balance;

    /**
     * A unique identifier for this account.
     */
    protected AccountId accountId = null;

    /**
     * The holder/owner of this account.
     * They are currently the only one who can send invoices from this account and withdraw money.
     */
    private Customer holder;

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * Subclasses should override this and check if the holder is eligible for an account of this type.
     *
     * @param holder the holder of this account
     */
    public Account(Customer holder) {
        throw new NotYetImplementedException();
    }

    /**
     * Get the unique account id.
     * If it has not been set a {@link IllegalStateException} is thrown.
     *
     * @throws IllegalStateException if the AccountId isn't set
     */
    public AccountId getAccountId() {
        throw new NotYetImplementedException();
    }

    /**
     * Set the unique account id for the first and only time.
     * Any attempt to overwrite the id throws a {@link IllegalStateException}.
     *
     * @param accountId the id of the account
     * @throws IllegalStateException if the AccountId was already set
     */
    public void setAccountId(AccountId accountId) {
        throw new NotYetImplementedException();
    }

    /**
     * Overdraft limit of the account.
     */
    abstract protected Money getOverdraft();

    /**
     * Get the interest applied to a negative balance.
     *
     * @return the borrowing interest
     */
    abstract protected Percentage getBorrowingInterest();

    /**
     * Get the interest applied to a positive balance.
     *
     * @return the saving interest
     */
    abstract protected Percentage getSavingInterest();

    /**
     * Apply the borrowing interest and therefore decrease the balance even further.
     *
     * @throws IllegalStateException if the borrowing interest is negative
     */
    public void applyBorrowingInterest() {
        throw new UnsupportedOperationException("This account does not suppport borrowing interest.");
    }

    /**
     * Apply the saving interest and therefore increase the balance even further.
     *
     * @throws IllegalStateException if the saving interest is negative
     */
    public void applySavingInterest() {
        throw new UnsupportedOperationException("This account does not suppport saving interest.");
    }

    /**
     * Handle an incoming invoice to this account with a specific amount of money.
     *
     * @param amount of money the amount to be added
     * @throws IllegalArgumentException if the amount is negative or zero
     * @throws UnsupportedOperationException if the Account doesn't support receiving a invoice
     */
    public void receiveInvoice(Money amount) {
        throw new NotYetImplementedException();
    }


    /**
     * Handle an outgoing invoice from this account with a specific amount of money.
     * Should check if the transaction is valid i.e enough funds available.
     *
     * @param amount of money to be transferred
     * @throws InsufficientFundsException if there are not sufficient funds available
     * @throws IllegalArgumentException if the amount is negative or zero
     * @throws UnsupportedOperationException if the Account doesn't support sending invoices
     */
    public void sendInvoice(Money amount) throws InsufficientFundsException {
        throw new NotYetImplementedException();
    }

    /**
     * Handle withdrawal from this account with a specific amount of money.
     * Should check if the transaction is valid i.e enough funds available.
     *
     * @param amount of money to be withdrawn
     * @throws InsufficientFundsException if there are not sufficient funds available
     * @throws IllegalArgumentException if the amount is negative or zero
     * @throws UnsupportedOperationException if the Account doesn't support a withdraw
     */
    protected void withdraw(Money amount) throws InsufficientFundsException {
        throw new NotYetImplementedException();
    }

    /**
     * Handle deposit onto this account with a specific amount of money.
     *
     * @param amount the amount of money to be deposited
     * @throws IllegalArgumentException if the amount is negative or zero
     * @throws UnsupportedOperationException if the Account doesn't support depositing
     */
    protected void deposit(Money amount) {
        throw new NotYetImplementedException();
    }

    public Customer getHolder() {
        return holder;
    }

    public Money getBalance() {
        return balance;
    }
}
