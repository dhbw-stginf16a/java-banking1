package banking.backend.accounts;

import banking.backend.Percentage;
import banking.backend.persons.Customer;

class PensionPlan extends Investments {

    private static final Percentage SAVING_INTEREST = new Percentage("5%");

    /**
     * Constructs an account and initializes the holder.
     * If the customer is null throws {@link IllegalArgumentException}
     *
     * @param holder the holder of this account
     */
    public PensionPlan(Customer holder) {
        super(holder);
        if (holder.getAge() < 18) {
            throw new IllegalArgumentException("Holder has to be at least 18");
        }
    }

    @Override
    protected Percentage getSavingInterest() {
        return SAVING_INTEREST;
    }
}
