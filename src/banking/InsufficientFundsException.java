package banking;

/**
 * This exception is thrown if an attempt is made to decrease the balance
 * of an account below the overdraft limit.
 */
public class InsufficientFundsException extends TransactionFailedException {
}
