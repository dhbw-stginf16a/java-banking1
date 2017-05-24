package banking.backend.transactions;

import banking.backend.accounts.Account;

class Deposit extends Transaction {
    public Account creditor;

    public Deposit() {

    }

    @Override
    void apply() {

    }
}
