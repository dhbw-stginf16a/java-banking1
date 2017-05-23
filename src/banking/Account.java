package banking;

abstract class Account {
	protected Money balance;
	protected AccountId accountId;
	public Customer holder;

	public Account() {

	}

	/** Overdraft limit of the account */
	public static Money getOverdraft () {
	    return null;
	}

	public Percentage getBorrowingInterest () {
		return null;
	}

	public Percentage getSavingInterest () {
		return null;
	}

	public void receiveInvoice (Money amount) {

	}

	public void sendInvoice (Money amount) {

	}

	public void withdraw (Money amount) {

	}

	public void deposit (Money amount) {

	}

}
