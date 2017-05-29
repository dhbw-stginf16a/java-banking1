package banking.backend.accounts;

import banking.NotYetImplementedException;
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
        throw new NotYetImplementedException();
    }

    @Override
    protected Money getOverdraft() {
        throw new NotYetImplementedException();
    }

    @Override
    public void receiveInvoice(Money amount) {
        throw new UnsupportedOperationException("A credit card can't receive invoices");
    }
}
