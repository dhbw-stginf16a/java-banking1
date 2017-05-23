package banking;

import java.util.List;
import java.util.Map;

class Bank {
	protected List<Transaction> transactions;
	protected Map<CustomerId, Customer> customers;
	protected Map<AccountId, Account> accounts;
	protected List<Employee> employees;

	public Bank() {

	}

	public void addCustomer (Customer customer) {

	}

	public List<Account> getAccounts (Customer customer) {
	    return null;
	}

	/** Get the singleton instance of bank */
	public static Bank getBank () {
		return null;
	}

	public void applyTransaction (Transaction transaction) {

	}

	public AccountId generateAvailableAccountId () {
	    return null;
	}

	public void addAccount (Account account) {

	}

}
