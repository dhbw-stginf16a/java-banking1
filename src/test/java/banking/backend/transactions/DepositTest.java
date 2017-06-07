package banking.backend.transactions;

import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountTest;
import banking.backend.persons.Customer;
import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the functionality of {@link Deposit}
 * <p>
 * Created by zeiske on 30.05.2017.
 */
class DepositTest {

    /**
     * Generates a dummyAccount that can't deposit and doesn't have an accountId
     *
     * @return Account
     */
    static Account getDummyAccountNotDeposit() {
        return new DummyAccountNotDeposit(CustomerTest.getDummyCustomer());
    }

    @Test
    void testApply() throws TransactionFailedException {
        Account dummyAccount = AccountTest.getDummyAccount();
        Account dummyAccountNotDeposit = getDummyAccountNotDeposit();

        Money balanceBefore = dummyAccount.getBalance();

        Deposit normalAccountDeposit = new Deposit(new Money(10), dummyAccount);
        normalAccountDeposit.apply();

        assertEquals(balanceBefore.add(new Money(10)), dummyAccount.getBalance(), "The Balance should be changed after applying the transaction");

        balanceBefore = dummyAccountNotDeposit.getBalance();

        Deposit notDepositAccountDeposit = new Deposit(new Money(10), dummyAccountNotDeposit);
        assertThrows(TransactionFailedException.class, notDepositAccountDeposit::apply);

        assertEquals(balanceBefore, dummyAccountNotDeposit.getBalance(), "The balance shouldn't have changed due to a failed transaction");
    }

    private static class DummyAccountNotDeposit extends Account {

        /**
         * Constructs an account and initializes the holder.
         * If the customer is null throws {@link IllegalArgumentException}
         * <p>
         * Subclasses should override this and check if the holder is eligible for an account of this type.
         *
         * @param holder the holder of this account
         */
        public DummyAccountNotDeposit(Customer holder) {
            super(holder);
        }

        @Override
        public void deposit(Money amount) {
            throw new UnsupportedOperationException("This account doesn't support depositing");
        }

        @Override
        protected Money getOverdraft() {
            return new Money(0);
        }

        @Override
        protected Percentage getBorrowingInterest() {
            return new Percentage(0);
        }

        @Override
        protected Percentage getSavingInterest() {
            return new Percentage(0);
        }
    }
}