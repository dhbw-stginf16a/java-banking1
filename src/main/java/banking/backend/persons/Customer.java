package banking.backend.persons;

import banking.NotYetImplementedException;
import banking.backend.DateTime;
import banking.backend.Money;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.accounts.JuniorAccount;

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
	 *
	 * @param name             the customer's name
	 * @param address          the customer's address
	 * @param birthdate        the customer's date of birth
	 * @param telephoneNumber  the customer's telephone number
	 * @param businessCustomer whether they are a business customer
	 */
	public Customer(String name, String address, DateTime birthdate, String telephoneNumber, boolean businessCustomer) {
		super(name, address, birthdate, telephoneNumber);
	}

	/**
	 * Get the unique customer id.
	 * If it has not been set a {@link IllegalStateException} is thrown.
     *
     * @return The unique CustomerId
     * @throws IllegalStateException if the CustomerId wasn't set already.
     */
    public CustomerId getCustomerId() {
        throw new NotYetImplementedException();
	}

	/**
	 * Set the unique customer id for the first and only time.
	 * Any attempt to overwrite the id throws a {@link IllegalStateException}.
	 *
	 * @param customerId the id of the customer
     * @throws IllegalStateException if the CustomerId was already set.
     */
    public void setCustomerId(CustomerId customerId) {
        throw new NotYetImplementedException();
	}

	/**
	 * Creates a new account of the specified type and add it to this as well as the bank.
	 *
	 * @param accountType class of the account to be created
	 * @return the newly created account
	 */
    public <T extends Account> T setupAccount(Class<T> accountType) {
        throw new NotYetImplementedException();
    }

	/**
	 * Creates a new {@link JuniorAccount} after a check if the guardian is a valid guardian
	 * and add it to this as well as the bank.
	 *
	 * @param accountType should be {@code JuniorAccount.class}
     * @param guardian The guardian for the Junior account
     * @return the newly created account
     */
    public <T extends JuniorAccount> T setupAccount(Class<T> accountType, Person guardian) {
        throw new NotYetImplementedException();
    }

	/**
	 * Issue an invoice on behalf of this customer.
	 * Sends a transaction to the bank for the actual changes.
	 * Checks if the customer has required permissions on this account.
	 *
	 * @param origin      the account from which the money is drawn
	 * @param destination the account to which the money is transferred
	 * @param amount      the amount of money to be transferred
	 */
	public void invoice(Account origin, AccountId destination, Money amount) {
		throw new NotYetImplementedException();
	}

	/**
	 * Withdraw some money from an account.
	 * Sends a transaction to the bank for the actual changes.
	 * Checks if the customer has required permissions on this account.
	 *
	 * @param account the account from which the money is withdrawn
	 * @param amount  the amount of money to be withdrawn
	 */
	public void withdraw (Account account, Money amount) {
		throw new NotYetImplementedException();
	}

    /**
     * Checks whether this is equal to another Customer by comparing the CustomerIds.
     *
     * @param o the object maybe
     * @return false if other object or different customerId - true otherwise
     */
    @Override
    public boolean equals(Object o) {
        throw new NotYetImplementedException();
    }
}
