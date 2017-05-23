package banking;

import java.util.List;

class Customer extends Person {
	protected long customerNumber;
	protected List<Account> accounts;
	protected boolean business;

	public Customer() {

	}

	public void Customer (String name, String address, DateTime birthdate, String telephoneNumber, boolean business) {

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
