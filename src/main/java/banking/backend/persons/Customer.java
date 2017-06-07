package banking.backend.persons;

import banking.backend.Bank;
import banking.backend.DateTime;
import banking.backend.Money;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.accounts.JuniorAccount;
import banking.backend.transactions.Invoice;
import banking.backend.transactions.TransactionFailedException;
import banking.backend.transactions.Withdrawal;

import java.lang.reflect.InvocationTargetException;

/**
 * A customer is any person who has an account with the company.
 * They might not have one upon creation but an account should be added shortly after.
 */
public class Customer extends Person {
    /**
     * A unique identifier of this customer.
     */
    private CustomerId customerId = null;

    /**
     * Determines whether the customer is a business customer.
     * This grants them access to special accounts.
     */
    private boolean businessCustomer;

    /**
     * Constructs a new customer with all attributes defined.
     * The date of birth cannot lie in the future.
     *
     * @param name             the customer's name
     * @param address          the customer's address
     * @param birthdate        the customer's date of birth
     * @param telephoneNumber  the customer's telephone number
     * @param businessCustomer whether they are a business customer
     */
    public Customer(String name, String address, DateTime birthdate, String telephoneNumber, boolean businessCustomer) {
        super(name, address, birthdate, telephoneNumber);
        this.businessCustomer = businessCustomer;
    }

    /**
     * Get the unique customer id.
     * If it has not been set a {@link IllegalStateException} is thrown.
     *
     * @return The unique CustomerId
     * @throws IllegalStateException if the CustomerId wasn't set already.
     */
    public CustomerId getCustomerId() {
        if (customerId == null) {
            throw new IllegalStateException("Customer id has not been set.");
        }
        return customerId;
    }

    /**
     * Set the unique customer id for the first and only time.
     * Any attempt to overwrite the id throws a {@link IllegalStateException}.
     *
     * @param customerId the id of the customer
     * @throws IllegalStateException if the customer id was already set.
     * @throws IllegalArgumentException if supplied the customer id is null
     */
    public void setCustomerId(CustomerId customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("The customer id cannot be set to null.");
        }
        if (this.customerId != null) {
            throw new IllegalStateException("The customer id can only be set once.");
        }
        this.customerId = customerId;
    }

    /**
     * Creates a new account of the specified type and add it to the bank.
     *
     * @param accountType class of the account to be created
     * @return the newly created account
     * @throws IllegalAccessException when the constructor of the account class is private
     * @throws InstantiationException when the account class is not instantiable
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public <T extends Account> T setupAccount(Class<T> accountType) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T account = accountType.getDeclaredConstructor(Customer.class).newInstance(this);
        Bank.getInstance().addAccount(account);
        return account;
    }

    /**
     * Creates a new {@link JuniorAccount} after a check if the guardian is a valid guardian
     * and add it to the bank.
     *
     * @param accountType should be {@code JuniorAccount.class}
     * @param guardian The guardian for the Junior account
     * @throws IllegalAccessException when the constructor of the account class is private
     * @throws InstantiationException when the account class is not instantiable
     * @return the newly created account
     */
    public <T extends JuniorAccount> T setupAccount(Class<T> accountType, Person guardian) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (guardian == null) {
            throw new IllegalArgumentException("A junior account needs an associated guardian.");
        }
        T account = accountType.getDeclaredConstructor(Customer.class, Person.class).newInstance(this, guardian);
        Bank.getInstance().addAccount(account);
        return account;
    }

    /**
     * Issue an invoice on behalf of this customer.
     * Sends a transaction to the bank for the actual changes.
     * Checks if the customer has required permissions on this account.
     *
     * @param origin      the account from which the money is drawn
     * @param destination the account to which the money is transferred
     * @param amount      the amount of money to be transferred
     * @throws TransactionFailedException if the transaction was not successful
     * @throws IllegalArgumentException if one of the parameters is null
     */
    public void invoice(Account origin, AccountId destination, Money amount) throws TransactionFailedException {
        if (origin == null || destination == null || amount == null) {
            throw new IllegalArgumentException("Can't perform invoice if origin, destination or amount ist null");
        }

        Bank bank = Bank.getInstance();

        if (!origin.getHolder().equals(this)) {
            throw new IllegalArgumentException("Can't perform invoices for accounts that aren't belonging to yourself");
        }

        Account account = bank.getAccount(destination);
        if (account == null) {
            throw new TransactionFailedException("It is not possible to deposit money into an account that does not exist");
        }

        Invoice invoice = new Invoice(amount, account, origin);
        bank.applyTransaction(invoice);
    }

    /**
     * Withdraw some money from an account.
     * Sends a transaction to the bank for the actual changes.
     * Checks if the customer has required permissions on this account.
     *
     * @param account the account from which the money is withdrawn
     * @param amount  the amount of money to be withdrawn
     * @throws TransactionFailedException if the transaction was not successful
     * @throws IllegalArgumentException if one of the parameters is null
     */
    public void withdraw(Account account, Money amount) throws TransactionFailedException {
        if (account == null || amount == null) {
            throw new IllegalArgumentException("Can't perform withdrawal if the account or amount ist null");
        }

        Bank bank = Bank.getInstance();

        if (!account.getHolder().equals(this)) {
            throw new IllegalArgumentException("Can't perform withdrawal for accounts that aren't belonging to yourself");
        }

        Withdrawal withdrawal = new Withdrawal(amount, account);
        bank.applyTransaction(withdrawal);
    }

    /**
     * Checks whether this is equal to another Customer by comparing the customer ids.
     *
     * @param o the object to compare to
     * @return false if other class or different customer id - true otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Customer) {
            if (customerId != null) {
                return customerId.equals(((Customer) o).customerId);
            }
            return this == o;
        }
        return false;
    }

    /**
     * Return true if this is a business customer.
     *
     * @return whether this is a business customer
     */
    public boolean isBusinessCustomer() {
        return businessCustomer;
    }
}
