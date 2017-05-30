package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

abstract class Investments extends Account {

    private static final Money OVERDRAFT = new Money("0.00");
    private static final Percentage BORROWING_INTEREST = new Percentage("0%");

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     */
    public Investments(Customer holder) {
        super(holder);
        if (holder.getAge() < 18) {
            throw new IllegalArgumentException("Holder has to be at least 18");
        }
    }

    /**
     * Get the overdraft - which is zero for an investment
     *
     * @return zero
     */
    @Override
    protected Money getOverdraft() {
        return OVERDRAFT;
    }

    /**
     * Get the borrowing interest - which is zero for an investment
     *
     * @return zero
     */
    @Override
    protected Percentage getBorrowingInterest() {
        return BORROWING_INTEREST;
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
