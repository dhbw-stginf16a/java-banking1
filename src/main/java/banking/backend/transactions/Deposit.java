package banking.backend.transactions;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.accounts.Account;

public class Deposit extends Transaction {
    public Account creditor;

    /**
     * Constructs a deposit transaction issued now with specified amount deposited to an account.
     *
     * @param amount   the monetary amount
     * @param creditor the account to which the money is credited
     */
    public Deposit(Money amount, Account creditor) {
        super(amount);
        throw new NotYetImplementedException();
    }

    /**
     * Get the account to which the money was deposited.
     *
     * @return the creditor
     */
    public Account getCreditor() {
        throw new NotYetImplementedException();
    }

    @Override
    public void apply() {
        throw new NotYetImplementedException();
    }

    @Override
    public String toString() {
        throw new NotYetImplementedException();
    }
}
