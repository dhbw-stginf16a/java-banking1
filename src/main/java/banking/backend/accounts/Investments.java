package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

abstract class Investments extends Account {
    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     */
    public Investments(Customer holder) {
        super(holder);
        throw new NotYetImplementedException();
    }

    /**
     * Get the overdraft - which is zero for an investment
     *
     * @return zero
     */
    @Override
    protected Money getOverdraft() {
        throw new NotYetImplementedException();
    }

    /**
     * Get the borrowing interest - which is zero for an investment
     *
     * @return zero
     */
    @Override
    protected Percentage getBorrowingInterest() {
        throw new NotYetImplementedException();
    }

    @Override
    public void sendInvoice(Money amount) throws InsufficientFundsException {
        throw new UnsupportedOperationException("An Investment can't send invoices");
    }

    @Override
    public void withdraw(Money amount) throws InsufficientFundsException {
        throw new UnsupportedOperationException("You can't withdraw money from an Investments, because it's ours now and you will never see it again!");
    }
}
