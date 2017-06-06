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
 * Tests the functionality of {@link Invoice}
 * <p>
 * Created by zeiske on 30.05.2017.
 */
class InvoiceTest {

    /**
     * Generates a dummyAccount that can't Invoice and doesn't have an accountId
     *
     * @return Account
     */
    static Account getDummyAccountNotInvoice() {
        return new DummyAccountNotInvoice(CustomerTest.getDummyCustomer());
    }

    @Test
    void testApply() throws TransactionFailedException {
        Account dummyAccount1 = AccountTest.getDummyAccount(new Money(100), new Percentage(0), new Percentage(0), new Money(100));
        Account dummyAccount2 = AccountTest.getDummyAccount(new Money(100), new Percentage(0), new Percentage(0), new Money(100));
        Account dummyAccountNotInvoice = getDummyAccountNotInvoice();

        Money balanceBefore1 = dummyAccount1.getBalance();
        Money balanceBefore2 = dummyAccount2.getBalance();

        Invoice normalAccountInvoice = new Invoice(new Money(10), dummyAccount1, dummyAccount2);
        normalAccountInvoice.apply();

        assertEquals(balanceBefore1.add(new Money(10)), dummyAccount1.getBalance(), "The Balance should be changed after applying the transaction");
        assertEquals(balanceBefore2.subtract(new Money(10)), dummyAccount2.getBalance(), "The Balance should be changed after applying the transaction");


        balanceBefore1 = dummyAccount1.getBalance();
        balanceBefore2 = dummyAccount2.getBalance();

        Invoice notNormalAccountInvoice1 = new Invoice(new Money(10), dummyAccountNotInvoice, dummyAccount2);
        assertThrows(TransactionFailedException.class, notNormalAccountInvoice1::apply);

        assertEquals(balanceBefore1, dummyAccount1.getBalance(), "The Balance shouldn't be changed after failed the transaction");
        assertEquals(balanceBefore2, dummyAccount2.getBalance(), "The Balance shouldn't be changed after failed the transaction");


        balanceBefore1 = dummyAccount1.getBalance();
        balanceBefore2 = dummyAccount2.getBalance();

        Invoice notNormalAccountInvoice2 = new Invoice(new Money(10), dummyAccount1, dummyAccountNotInvoice);
        assertThrows(TransactionFailedException.class, notNormalAccountInvoice2::apply);

        assertEquals(balanceBefore1, dummyAccount1.getBalance(), "The Balance shouldn't be changed after failed the transaction");
        assertEquals(balanceBefore2, dummyAccount2.getBalance(), "The Balance shouldn't be changed after failed the transaction");

    }

    private static class DummyAccountNotInvoice extends Account {

        /**
         * Constructs an account and initializes the holder.
         * If the customer is null throws {@link IllegalArgumentException}
         * <p>
         * Subclasses should override this and check if the holder is eligible for an account of this type.
         *
         * @param holder the holder of this account
         */
        public DummyAccountNotInvoice(Customer holder) {
            super(holder);
        }

        @Override
        public void sendInvoice(Money amount) {
            throw new UnsupportedOperationException("This account doesn't support Invoicing");
        }

        @Override
        public void receiveInvoice(Money amount) {
            throw new UnsupportedOperationException("This account doesn't support Invoicing");
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