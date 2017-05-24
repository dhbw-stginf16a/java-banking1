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
    protected AccountId accountId;

    /**
     * The holder/owner of this account.
     */
    private Customer holder;

    /**
     * Overdraft limit of the account.
     */
    public static Money getOverdraft() {
        throw new NotYetImplementedException();
    }

    /**
     * Get the interest applied to a negative balance.
     *
     * @return the borrowing interest
     */
    protected Percentage getBorrowingInterest() {
        throw new NotYetImplementedException();
    }

    /**
     * Get the interest applied to a positive balance.
     *
     * @return the saving interest
     */
    protected Percentage getSavingInterest() {
        throw new NotYetImplementedException();
    }

    /**
     * Apply the borrowing interest and therefore decrease the balance even further.
     */
    private void applyBorrowingInterest() {
        throw new UnsupportedOperationException("This account does not suppport borrowing interest.");
    }

    /**
     * Apply the saving interest and therefore increase the balance even further.
     */
    public void applySavingInterest() {
        throw new UnsupportedOperationException("This account does not suppport saving interest.");
    }

    /**
     * Handle an incoming invoice to this account with a specific amount of money.
     *
     * @param amount of money the amount to be added
     */
    protected void receiveInvoice(Money amount) {
        throw new UnsupportedOperationException("This account does not suppport receiving invoices.");
    }


    /**
     * Handle an outgoing invoice from this account with a specific amount of money.
     * Should check if the transaction is valid i.e enough funds available.
     *
     * @param amount of money to be transferred
     * @throws InsufficientFundsException if there are not sufficient funds available
     */
    protected void sendInvoice(Money amount) throws InsufficientFundsException {
        throw new UnsupportedOperationException("This account does not suppport sending invoices.");
    }

    /**
     * Handle withdrawal from this account with a specific amount of money.
     * Should check if the transaction is valid i.e enough funds available.
     *
     * @param amount of money to be withdrawn
     * @throws InsufficientFundsException if there are not sufficient funds available
     */
    protected void withdraw(Money amount) throws InsufficientFundsException {
        throw new UnsupportedOperationException("This account does not suppport sending money.");
    }

    /**
     * Handle deposit onto this account with a specific amount of money.
     *
     * @param amount the amount of money to be deposited
     */
    protected void deposit(Money amount) {
        throw new UnsupportedOperationException("This account does not suppport depositing money.");
    }

}
