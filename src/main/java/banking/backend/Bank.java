package banking.backend;

import banking.NotYetImplementedException;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.persons.Customer;
import banking.backend.persons.CustomerId;
import banking.backend.transactions.Transaction;

import java.util.List;
import java.util.Map;

/**
 * The bank class holds general information about what the bank knows.
 * It stores all the customers, their accounts, all employees and the transactions between accounts.
 * It is the main class with which the GUI handles the business logic.
 * <p>
 * Bank is not instantiable, an instance can instead be obtained through the static {@link #getInstance} method.
 */
public class Bank {
    private static Bank instance = null;
    /**
     * The map of all customers indexed by their {@link CustomerId}
     */
    protected Map<CustomerId, Customer> customers;
    /**
     * The map of all accounts index by their {@link AccountId}
     */
    protected Map<AccountId, Account> accounts;
    /**
     * The list of all past transactions. Failed and successful.
     */
    private List<Transaction> transactions;

    /**
     * Not visible to avoid unwanted instantiation.
     * Use {@link #getInstance()}
     * Throws {@link UnsupportedOperationException} if instance is already set.
     */
    private Bank() {
        throw new NotYetImplementedException();
    }

    /**
     * Get the singleton instance of bank.
     */
    public static Bank getInstance() {
        throw new NotYetImplementedException();
    }

    /**
     * Add a new customer to the database of the bank.
     *
     * @param customer the new customer
     */
    public void addCustomer(Customer customer) {
        throw new NotYetImplementedException();
    }

    /**
     * Get the customer with the requested id.
     *
     * @param customerId the customerId to look up the customer
     * @return the customer if found or null if not
     */
    public Customer getCustomer(CustomerId customerId) {
        throw new NotYetImplementedException();
    }

    /**
     * Get all accounts belonging to a specific user.
     *
     * @param customer the customer whose accounts are to be returned
     * @return all accounts of the customer
     */
    public List<Account> getAccounts(Customer customer) {
        throw new NotYetImplementedException();
    }

    /**
     * Get all accounts belonging to a specific user.
     *
     * @return all accounts
     */
    public List<Account> getAccounts() {
        return null;
    }

    /**
     * Get all customers.
     *
     * @return all customer
     */
    public List<Customer> getCustomers() {
        return null;
    }

    /**
     * Get all customers.
     *
     * @return all customer
     */
    public List<Transaction> getTransactions() {
        return null;
    }

    /**
     * Apply the actions of a transaction to all effected accounts and store it in the transaction log.
     *
     * @param transaction transaction to be applied
     * @see #transactions
     */
    public void applyTransaction(Transaction transaction) {
        throw new NotYetImplementedException();
    }

    /**
     * Generate a new {@link AccountId} not currently in use for a new account.
     *
     * @return unused account id
     */
    private AccountId generateAvailableAccountId() {
        throw new NotYetImplementedException();
    }

    /**
     * Generate a new {@link CustomerId} not currently in use for a new customer.
     *
     * @return unused customer id
     */
    private CustomerId generateAvailableCustomerId() {
        throw new NotYetImplementedException();
    }

    /**
     * Add a (new) account to the database.
     *
     * @param account the new account
     */
    public void addAccount(Account account) {
        throw new NotYetImplementedException();
    }

    /**
     * Deposit money into an account.
     *
     * @param accountId the id of the account
     * @param amount    the amount to be deposited
     */
    public void deposit(AccountId accountId, Money amount) {
        throw new NotYetImplementedException();
    }

}
