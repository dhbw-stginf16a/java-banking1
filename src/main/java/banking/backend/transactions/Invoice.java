package banking.backend.transactions;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.accounts.Account;

class Invoice extends Transaction {
    private Account creditor;
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
        throw new NotYetImplementedException();
    }

    @Override
    void apply() throws TransactionFailedException {
        throw new NotYetImplementedException();
    }

    @Override
    public String toString() {
        throw new NotYetImplementedException();
    }
}
