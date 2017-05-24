package banking;

/**
 * This exception is thrown after the application of a transaction has failed.
 * Most like due to insufficient funds.
 *
 * This is supposed to be caught and an error message displayed to the user.
 */
public class TransactionFailedException extends Exception {
}
