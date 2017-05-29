package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;

abstract class Cards extends Account {
    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer doesn't fit the requirements of the card
     */
    public Cards(Customer holder) {
        super(holder);
        throw new NotYetImplementedException();
    }

    /**
     * Get the borrowing interest - which is zero for a card
     *
     * @return zero
     */
    @Override
    protected Percentage getBorrowingInterest() {
        throw new NotYetImplementedException();
    }

    /**
     * Get the saving interest - which is zero for a card
     *
     * @return zero
     */
    @Override
    protected Percentage getSavingInterest() {
        throw new NotYetImplementedException();
    }

    @Override
    protected void deposit(Money amount) {
        throw new UnsupportedOperationException("A Card doesn't support depositing.");
    }
}
