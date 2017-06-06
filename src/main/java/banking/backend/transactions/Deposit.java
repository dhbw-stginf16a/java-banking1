package banking.backend.transactions;

import banking.backend.Money;
import banking.backend.accounts.Account;

import java.util.Arrays;
import java.util.List;

public class Deposit extends Transaction {
    /**
     * The account to which the money is credited.
     */
    private Account creditor;

    /**
     * Constructs a deposit transaction issued now with specified amount deposited to an account.
     *
     * @param amount   the monetary amount
     * @param creditor the account to which the money is credited
     */
    public Deposit(Money amount, Account creditor) {
        super(amount);
        if (creditor == null) {
            throw new IllegalArgumentException("Creditor cannot be null.");
        }
        this.creditor = creditor;
    }

    /**
     * Get the account to which the money was deposited.
     *
     * @return the creditor
     */
    public Account getCreditor() {
        return creditor;
    }

    @Override
    public void apply() throws TransactionFailedException {
        try {
            creditor.deposit(getAmount());
        } catch (UnsupportedOperationException e) {
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
        return String.format("Status of deposit of %s into %s is %s",
                getAmount(), creditor.getAccountId(), getStatus());
    }

    /**
     * Get all accounts involved in this transaction.
     *
     * @return the involved accounts
     */
    @Override
    public List<Account> getInvolvedAccounts() {
        return Arrays.asList(creditor);
    }
}
