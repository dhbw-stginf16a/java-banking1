package banking.backend;

import banking.NotYetImplementedException;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.persons.Customer;
import banking.backend.persons.CustomerId;
import banking.backend.persons.Employee;
import banking.backend.transactions.Transaction;

import java.util.List;
import java.util.Map;

/**
 * The bank class holds general information about what the bank knows.
 * It stores all the customers, their accounts, all employees and the transactions between accounts.
 * It is the main class with which the GUI handles the business logic.
 *
 * Bank is not instantiable, an instance can instead be obtained through the static {@link #getInstance} method.
 */
public class Bank {
    private static Bank instance = null;

    /**
     * The list of all past transactions. Failed and successful.
     */
    private List<Transaction> transactions;

    /**
     * The map of all customers indexed by their {@link CustomerId}
     */
    private Map<CustomerId, Customer> customers;

    /**
     * The map of all accounts index by their {@link AccountId}
     */
    private Map<AccountId, Account> accounts;

    /**
     * The list of all employees of this bank.
     */
    private List<Employee> employees;

    /**
     * Not visible to avoid unwanted instantiation.
     * Use {@link #getInstance()}
     */
    private Bank() {}

    /**
     * Get the singleton instance of bank
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
     * Get all accounts belonging to a specific user.
     *
     * @param customer the customer whose accounts are to be returned
     * @return all accounts of the customer
     */
    public List<Account> getAccounts(Customer customer) {
        throw new NotYetImplementedException();
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
     * Generate a new {@link AccountId} not currently in use for a new user.
     *
     * @return unused account id
     */
    public AccountId generateAvailableAccountId() {
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

}
