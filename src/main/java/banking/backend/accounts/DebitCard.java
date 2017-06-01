package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.persons.Customer;

public class DebitCard extends Cards {

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException never
     */
    public DebitCard(Customer holder) {
        super(holder);
    }

    /**
     * Get the overdraft - which is zero for a debit card
     *
     * @return zero
     */
    @Override
    protected Money getOverdraft() {
        return new Money(2000);
    }
}
