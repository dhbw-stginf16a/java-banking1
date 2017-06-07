package banking.backend.transactions;

import banking.backend.Money;
import banking.backend.accounts.Account;
import banking.backend.accounts.InsufficientFundsException;

import java.util.Arrays;
import java.util.List;

public class Withdrawal extends Transaction {
    /**
     * The account from which the money is drawn.
     */
    private Account debtor;

    /**
     * Constructs a withdrawal transaction issued now with specified amount.
     *
     * @param amount the monetary amount
     * @param debtor the account from which the money is withdrawn
     */
    public Withdrawal(Money amount, Account debtor) {
        super(amount);
        if (debtor == null) {
            throw new IllegalArgumentException("Debtor cannot be null.");
        }
        this.debtor = debtor;
    }

    @Override
    public void apply() throws TransactionFailedException {
        try {
            debtor.withdraw(getAmount());
        } catch (UnsupportedOperationException | InsufficientFundsException e) {
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
                getAmount(), debtor.getAccountId(), getStatus());
    }

    /**
     * Get all accounts involved in this transaction.
     *
     * @return the involved accounts
     */
    @Override
    public List<Account> getInvolvedAccounts() {
        return Arrays.asList(debtor);
    }
}
