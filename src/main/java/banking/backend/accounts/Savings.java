package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

abstract class Savings extends Account {

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     */
    public Savings(Customer holder) {
        super(holder);
    }

    /**
     * Get the overdraft - which is zero for a savings account
     *
     * @return zero
     */
    @Override
    protected Money getOverdraft() {
        return new Money("0€");
    }

    /**
     * Get the borrowing interest - which is zero for a savings account
     *
     * @return zero
     */
    @Override
    protected Percentage getBorrowingInterest() {
        return new Percentage("0%");
    }

    @Override
    public void sendInvoice(Money amount) throws InsufficientFundsException {
        throw new UnsupportedOperationException("You can't send invoices from a Savings account");
    }
}
