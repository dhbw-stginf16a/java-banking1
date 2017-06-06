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
 * Tests the functionality of {@link Withdrawal}
 * <p>
 * Created by zeiske on 30.05.2017.
 */
class WithdrawalTest {

    /**
     * Generates a dummyAccount that can't Withdrawal and doesn't have an accountId
     *
     * @return Account
     */
    static Account getDummyAccountNotWithdrawal() {
        return new DummyAccountNotWithdrawal(CustomerTest.getDummyCustomer());
    }

    @Test
    void testApply() throws TransactionFailedException {
        Account dummyAccount = AccountTest.getDummyAccount(new Money(100), new Percentage(0), new Percentage(0), new Money(100));
        Account dummyAccountNotWithdrawal = getDummyAccountNotWithdrawal();

        Money balanceBefore = dummyAccount.getBalance();

        Withdrawal normalAccountWithdrawal = new Withdrawal(new Money(10), dummyAccount);
        normalAccountWithdrawal.apply();

        assertEquals(balanceBefore.subtract(new Money(10)), dummyAccount.getBalance(), "The Balance should be changed after applying the transaction");

        balanceBefore = dummyAccountNotWithdrawal.getBalance();

        Withdrawal notWithdrawalAccountWithdrawal = new Withdrawal(new Money(10), dummyAccountNotWithdrawal);
        assertThrows(TransactionFailedException.class, notWithdrawalAccountWithdrawal::apply);

        assertEquals(balanceBefore, dummyAccountNotWithdrawal.getBalance(), "The balance shouldn't have changed due to a failed transaction");
    }

    private static class DummyAccountNotWithdrawal extends Account {

        /**
         * Constructs an account and initializes the holder.
         * If the customer is null throws {@link IllegalArgumentException}
         * <p>
         * Subclasses should override this and check if the holder is eligible for an account of this type.
         *
         * @param holder the holder of this account
         */
        public DummyAccountNotWithdrawal(Customer holder) {
            super(holder);
            this.balance = new Money(100);
        }

        @Override
        public void withdraw(Money amount) {
            throw new UnsupportedOperationException("This account doesn't support Withdrawaling");
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