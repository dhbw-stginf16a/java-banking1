package banking.backend.transactions;

import banking.backend.accounts.Account;

class Withdrawal extends Transaction {
    private Account debitor;

    public Withdrawal() {

    }

    @Override
    void apply() throws TransactionFailedException {

    }
}
