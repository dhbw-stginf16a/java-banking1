package banking.backend.transactions;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.accounts.Account;

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
        throw new NotYetImplementedException();
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
}
