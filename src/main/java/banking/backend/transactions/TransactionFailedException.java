package banking.backend.transactions;

/**
 * This exception is thrown after the application of a transaction has failed.
 * Most like due to insufficient funds.
 * <p>
 * This is supposed to be caught by the UI and an error message be displayed to the user.
 */
public class TransactionFailedException extends Exception {
    public TransactionFailedException(String msg) {
        super(msg);
    }
}
