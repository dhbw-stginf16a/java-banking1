package banking.backend.transactions;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.accounts.Account;

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
        throw new NotYetImplementedException();
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
}
