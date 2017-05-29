package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.persons.Customer;

class CreditCards extends Cards {

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     * @throws IllegalArgumentException if the Customer is younger than 18
     */
    public CreditCards(Customer holder) {
        super(holder);
        if (holder.getAge() < 18) {
            throw new IllegalArgumentException("You need to be at least 18.");
        }
    }

    @Override
    protected Money getOverdraft() {
        return new Money(2000);
    }

    @Override
    public void receiveInvoice(Money amount) {
        throw new UnsupportedOperationException("A credit card can't receive invoices");
    }
}
