package banking.backend.transactions;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.accounts.Account;

public class Withdrawal extends Transaction {
    /**
     * Constructs a withdrawal transaction issued now with specified amount.
     *
     * @param amount the monetary amount
     * @param debtor the account from which the money is withdrawn
     */
    public Withdrawal(Money amount, Account debtor) {
        super(amount);
        throw new NotYetImplementedException();
    }

    @Override
    public void apply() throws TransactionFailedException {
        throw new NotYetImplementedException();
    }

    @Override
    public String toString() {
        throw new NotYetImplementedException();
    }
}
