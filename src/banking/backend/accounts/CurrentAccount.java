package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.Percentage;

/**
 * A current account is the most versatile since it allow all kinds of operations
 */
public class CurrentAccount extends Account {
    /**
	 * The interest applied
	 */
	private static final Percentage SAVING_INTEREST = null;
	private static final Money OVERDRAFT = null;
	private static final Percentage BORROWING_INTEREST = null;

	public CurrentAccount() {
        throw new NotYetImplementedException();
    }

	@Override
	protected void receiveInvoice(Money amount) {
        throw new NotYetImplementedException();
    }

	@Override
	protected void sendInvoice(Money amount) throws InsufficientFundsException {
        throw new NotYetImplementedException();
    }

	@Override
	protected void withdraw(Money amount) throws InsufficientFundsException {
        throw new NotYetImplementedException();
    }

	@Override
	protected void deposit(Money amount) {
        throw new NotYetImplementedException();
    }
}
