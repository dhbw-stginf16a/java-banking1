package banking.backend.persons;

import banking.backend.DateTime;
import banking.backend.Money;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.accounts.JuniorAccount;

import java.util.List;

public class Customer extends Person {
	private long customerNumber;
	private List<Account> accounts;
	private boolean business;

	public Customer(String name, String address, DateTime birthdate, String telephoneNumber, boolean business) {
		super(name, birthdate);
	}

	public Account setupAccount (Class<? extends Account> accountType) {
		return null;
	}

	public Account setupAccount (Class<? extends JuniorAccount> accountType, Person guardian) {
	    return null;
	}

	public void invoice (Account origin, AccountId destination, Money amount) {

	}

	public void withdraw (Account account, Money amount) {

	}

}
