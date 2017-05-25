package banking.backend.accounts;

import banking.NotYetImplementedException;
import banking.backend.Money;
import banking.backend.persons.Customer;

class DebitCard extends Cards {

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     */
    public DebitCard(Customer holder) {
        super(holder);
        throw new NotYetImplementedException();
    }

    /**
     * Get the overdraft - which is zero for a debit card
     *
     * @return zero
     */
    @Override
    protected Money getOverdraft() {
        throw new NotYetImplementedException();
    }
}
