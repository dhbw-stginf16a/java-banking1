package banking.backend.transactions;

import banking.backend.Money;
import banking.backend.accounts.Account;
import banking.backend.accounts.InsufficientFundsException;

import java.util.Arrays;
import java.util.List;

public class Invoice extends Transaction {
    /**
     * The account to which the money is credited.
     */
    private Account creditor;

    /**
     * The account from which the money is drawn.
     */
    private Account debtor;

    /**
     * Constructs a deposit transaction issued now with specified amount deposited to an account.
     *
     * @param amount   the monetary amount
     * @param creditor the account to which the money is credited
     * @param debtor   the account from which the money is drawn
     */
    public Invoice(Money amount, Account creditor, Account debtor) {
        super(amount);
        if (creditor == null || debtor == null) {
            throw new IllegalArgumentException("Neither creditor nor debtor can be null.");
        }
        this.creditor = creditor;
        this.debtor = debtor;
    }

    @Override
    public void apply() throws TransactionFailedException {
        try {
            try {
                creditor.sendInvoice(new Money(0));
            } catch (IllegalArgumentException e) {
            }
            try {
                debtor.receiveInvoice(new Money(0));
            } catch (IllegalArgumentException e) {
            }
        } catch (UnsupportedOperationException e) {
            this.status = Status.FAILED;
            throw new TransactionFailedException(e);
        }

        try {
            debtor.sendInvoice(getAmount());
            creditor.receiveInvoice(getAmount());
        } catch (InsufficientFundsException e) {
            this.status = Status.FAILED;
            throw new TransactionFailedException(e);
        }
        this.status = Status.SUCCESS;
    }

    /**
     * Get a string representation for logging purposes or displaying to the user.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return String.format("Status of invoice of %s from %s to %s is %s",
                getAmount(), debtor.getAccountId(), creditor.getAccountId(), getStatus());
    }

    /**
     * Get all accounts involved in this transaction.
     *
     * @return the involved accounts
     */
    @Override
    public List<Account> getInvolvedAccounts() {
        return Arrays.asList(creditor, debtor);
    }
}
