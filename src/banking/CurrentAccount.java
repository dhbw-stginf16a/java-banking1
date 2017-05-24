package banking;

/**
 * A current account is the most versatile since it allow all kinds of operations
 */
class CurrentAccount extends Account {
	/**
	 * The interest applied
	 */
	public static final Percentage SAVING_INTEREST = null;
	public static final Money OVERDRAFT = null;
	public static final Percentage BORROWING_INTEREST = null;

	public CurrentAccount() {

	}

	@Override
	protected void receiveInvoice(Money amount) {
	}

	@Override
	protected void sendInvoice(Money amount) throws InsufficientFundsException {
	}

	@Override
	protected void withdraw(Money amount) throws InsufficientFundsException {
	}

	@Override
	protected void deposit(Money amount) {
	}
}
