package banking;

import java.util.List;

class Customer extends Person {
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
