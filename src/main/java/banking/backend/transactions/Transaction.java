package banking.backend.transactions;

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
    protected Status status = Status.PENDING;

    /**
     * Constructs a transaction issued now with specified amount.
     *
     * @param amount the positive monetary amount
     * @throws IllegalArgumentException if amount is null or zero
     */
    public Transaction(Money amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null.");
        }
        if (amount.equals(new Money(0))) {
            throw new IllegalArgumentException("Amount cannot be zero.");
        }
        this.amount = amount;
        issued = new DateTime();
    }

    /**
     * Apply changes to all affected accounts in the transaction.
     * All changed are rolled back on if transaction was unsuccessful.
     *
     * @throws TransactionFailedException if it was not possible to complete the transaction on any
     *                                    one of the accounts
     */
    public abstract void apply() throws TransactionFailedException;

    /**
     * Get the current status of this transaction.
     *
     * @return the current status of this
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Return the monetary amount of this.
     *
     * @return the amount
     */
    public Money getAmount() {
        return amount;
    }

    /**
     * Get a string representation for logging purposes or displaying to the user.
     *
     * @return string representation
     */
    @Override
    public abstract String toString();

    public DateTime getIssueDate() {
        return issued;
    }

    /**
     * The current status of this transaction.
     * Default is pending and changes to either FAILED or SUCCESS on applying.
     */
    public enum Status {PENDING, FAILED, SUCCESS}
}
