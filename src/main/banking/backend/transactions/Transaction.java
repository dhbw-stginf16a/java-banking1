package banking.backend.transactions;

import banking.NotYetImplementedException;
import banking.backend.DateTime;
import banking.backend.Money;

/**
 * A financial transaction which either transfers money between accounts
 * or increments/decrements the balance in a single account.
 */
public abstract class Transaction {
    /**
     * The monetary value of the transaction.
     */
    private Money amount;
    /**
     * The point in time when the transaction was issued.
     * Should be applied shortly after, therefore another `applied` attribute is not needed.
     */
    private DateTime issued;
    /**
     * The status of applying the transaction.
     * Has to be set by {@link #apply()} before throwing an exception to indicate success.
     */
    private Status status = Status.PENDING;

    /**
     * Constructs a transaction issued now with specified amount.
     *
     * @param amount the positive monetary amount
     */
    public Transaction(Money amount) {
        throw new NotYetImplementedException();
    }

    /**
     * Apply changes to all affected accounts in the transaction.
     * All changed are rolled back on if transaction was unsuccessful.
     *
     * @throws TransactionFailedException if it was not possible to complete the transaction on any
     *                                    one of the accounts
     */
    abstract void apply() throws TransactionFailedException;

    @Override
    public abstract String toString();

    enum Status {PENDING, FAILED, SUCCESS}
}
