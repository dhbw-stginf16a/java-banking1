package banking.backend;

import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.persons.Customer;
import banking.backend.persons.CustomerId;
import banking.backend.transactions.Deposit;
import banking.backend.transactions.Transaction;
import banking.backend.transactions.TransactionFailedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The bank class holds general information about what the bank knows.
 * It stores all the customers, their accounts, all employees and the transactions between accounts.
 * It is the main class with which the GUI handles the business logic.
 * <p>
 * Bank is not instantiable, an instance can instead be obtained through the static {@link #getInstance} method.
 */
public class Bank {
    /**
     * This is where the one and only instance of Bank is stored.
     * When requesting an instance of Bank via {@link #getInstance()}
     * this value should be returned or created if null.
     */
    private static Bank instance = null;

    /**
     * The map of all customers indexed by their {@link CustomerId}
     */
    private Map<CustomerId, Customer> customers = new HashMap<>();

    /**
     * The map of all accounts index by their {@link AccountId}
     */
    private Map<AccountId, Account> accounts = new HashMap<>();

    /**
     * The list of all past transactions. Failed and successful.
     */
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * Not visible to avoid unwanted instantiation.
     * Use {@link #getInstance()}
     *
     * @throws UnsupportedOperationException if instance is already set.
     */
    private Bank() {
        if (instance != null) {
            throw new UnsupportedOperationException("There cannot be more than one instance of Bank.");
        }
    }

    /**
     * Get the singleton instance of bank.
     */
    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    /**
     * Add a new customer to the database of the bank and set their id to an unused one.
     *
     * @param customer the new customer
     */
    public void addCustomer(Customer customer) {
        CustomerId customerId = generateAvailableCustomerId();
        customer.setCustomerId(customerId);
        if (!customers.containsValue(customer)) {
            customers.put(customerId, customer);
        }
    }

    /**
     * Get the customer with the requested id.
     *
     * @param customerId the customerId to look up the customer
     * @return the customer if found or null if not
     */
    public Customer getCustomer(CustomerId customerId) {
        return customers.get(customerId);
    }

    /**
     * Get all accounts belonging to a specific customer.
     *
     * @param customer the customer whose accounts are to be returned
     * @return all accounts of the customer
     */
    public List<Account> getCustomerAccounts(Customer customer) {
        CustomerId customerId = customer.getCustomerId();
        return this.accounts.values().stream().filter(
                (Account account) -> account.getHolder().getCustomerId().equals(customerId)).collect(Collectors.toList());
    }

    /**
     * Get all accounts belonging to a specific user.
     *
     * @return all accounts
     */
    public List<Account> getAccounts() {
        return new ArrayList<>(accounts.values());
    }

    /**
     * Get the account with the requested id.
     *
     * @param accountId the account id to look up the account
     * @return the account if found or null if not
     */
    public Account getAccount(AccountId accountId) {
        return accounts.get(accountId);
    }

    /**
     * Get all customers.
     *
     * @return all customer
     */
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers.values());
    }

    /**
     * Get all transactions.
     *
     * @return all transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Apply the actions of a transaction to all affected accounts and store it in the transaction log.
     *
     * @param transaction transaction to be applied
     * @see #transactions
     * @throws TransactionFailedException if it was not possible to complete the transaction
     */
    public void applyTransaction(Transaction transaction) throws TransactionFailedException {
        transaction.apply();
        transactions.add(transaction);
    }

    /**
     * Generate a new {@link AccountId} not currently in use for a new account.
     *
     * @return unused account id
     */
    private AccountId generateAvailableAccountId() {
        AccountId accountId;
        do {
            accountId = new AccountId();
        } while (accounts.containsKey(accountId));
        return accountId;
    }

    /**
     * Generate a new {@link CustomerId} not currently in use for a new customer.
     *
     * @return unused customer id
     */
    private CustomerId generateAvailableCustomerId() {
        CustomerId customerId;
        do {
            customerId = new CustomerId();
        } while (customers.containsKey(customerId));
        return customerId;
    }

    /**
     * Add a new account to the database of the bank and set their id to an unused one.
     *
     * @param account the new account
     * @throws IllegalArgumentException if the account does not have a holder
     */
    public void addAccount(Account account) {
        if (account.getHolder() == null) {
            throw new IllegalArgumentException("To add an account it must have a holder.");
        }
        AccountId accountId = generateAvailableAccountId();
        account.setAccountId(accountId);
        if (!accounts.containsValue(account)) {
            accounts.put(accountId, account);
        }
    }

    /**
     * Deposit money into an account.
     *
     * @param accountId the id of the account
     * @param amount    the amount to be deposited
     * @throws TransactionFailedException if the transaction was not successful
     */
    public void deposit(AccountId accountId, Money amount) throws TransactionFailedException {
        Account account = getAccount(accountId);
        if (account == null) {
            throw new TransactionFailedException("It is not possible to deposit money into an account that does not exist");
        }
        Deposit deposit = new Deposit(amount, account);
        applyTransaction(deposit);
    }

}
