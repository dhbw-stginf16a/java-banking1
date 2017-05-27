package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.Percentage;
import banking.backend.persons.Customer;
import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    /**
     * Generates a DummyAccount that perform anything implemented in Account.
     * <p>
     * The accountId isn't set.
     *
     * @return a Dummy instance of Account
     */
    public static Account getDummyAccount() {
        return getDummyAccount(new Money(0), new Percentage(0), new Percentage(0), new Money(0));
    }

    /**
     * Generates a dummy account that has a specific overdraft, borrowingInterest and savingInterest.
     * <p>
     * The accountId isn't set.
     *
     * @param overdraft         The amount of Overdraft
     * @param borrowingInterest The Borrowing interest of the account
     * @param savingInterest    The Saving interest of the account
     * @param balance           The initial Balance
     * @return The account with the specified values
     */
    public static Account getDummyAccount(Money overdraft, Percentage borrowingInterest, Percentage savingInterest, Money balance) {
        return new DummyAccount(CustomerTest.getDummyCustomer(), overdraft, borrowingInterest, savingInterest, balance);
    }

    /**
     * This test whether you can deposit on the account
     *
     * @param toTest the Account to Test
     */
    public static void testDeposit(Account toTest) {
        Money balanceBefore = toTest.getBalance();
        Money balanceToAdd = new Money(100);

        toTest.deposit(balanceToAdd);

        assertEquals(balanceBefore.add(balanceToAdd), toTest.getBalance());

        assertThrows(IllegalArgumentException.class, () -> toTest.deposit(new Money(0)));
        assertThrows(IllegalArgumentException.class, () -> toTest.deposit(new Money(-1)));

        assertEquals(balanceBefore.add(balanceToAdd), toTest.getBalance());
    }

    /**
     * Tests whether the receiveInvoice method works and really adds money to the account
     *
     * @param toTest the Account to Test
     */
    public static void testReceiveInvoice(Account toTest) {
        Money balanceBefore = toTest.getBalance();
        Money balanceToAdd = new Money(100);

        toTest.receiveInvoice(balanceToAdd);

        assertEquals(balanceBefore.add(balanceToAdd), toTest.getBalance());

        assertThrows(IllegalArgumentException.class, () -> toTest.receiveInvoice(new Money(0)));
        assertThrows(IllegalArgumentException.class, () -> toTest.receiveInvoice(new Money(-1)));

        assertEquals(balanceBefore.add(balanceToAdd), toTest.getBalance());
    }

    /**
     * Tests whether the Account can send Invoices and removes the send amount from the balance.
     *
     * @param toTest the {@link Account} to test
     * @throws InsufficientFundsException if the test is written wrong
     */
    public static void testSendInvoice(Account toTest) throws InsufficientFundsException {
        toTest.balance = new Money(100); // Overwrites the balance to 100

        toTest.sendInvoice(new Money(99));
        assertEquals(new Money(1), toTest.getBalance());

        toTest.sendInvoice(new Money(1));
        assertEquals(new Money(0), toTest.getBalance());

        //Tests if only non-negative Values are allowed and that a invalid parameter doesn't change the balance
        assertThrows(IllegalArgumentException.class, () -> toTest.sendInvoice(new Money(0)));
        assertThrows(IllegalArgumentException.class, () -> toTest.sendInvoice(new Money(-1)));
        assertEquals(new Money(0), toTest.getBalance());

        Money overdraftLimit = toTest.getOverdraft();

        //Check for InsufficientFundsException to be throw by taking to much money
        assertThrows(InsufficientFundsException.class, () -> toTest.sendInvoice(overdraftLimit.add(new Money(0, 1))));
        assertEquals(new Money(0), toTest.getBalance());

        if (overdraftLimit.compareTo(new Money(0)) > 0) {
            toTest.sendInvoice(overdraftLimit);
        }
        assertEquals(overdraftLimit.negate(), toTest.getBalance());

        assertThrows(InsufficientFundsException.class, () -> toTest.sendInvoice(new Money(0, 1)));
        assertEquals(overdraftLimit.negate(), toTest.getBalance());

        assertEquals(new Money(-1), toTest.getBalance());
    }

    /**
     * Tests whether the Account could be withdrawn form and removes the send amount from the balance.
     *
     * @param toTest the {@link Account} to test
     * @throws InsufficientFundsException if the test is written wrong
     */
    public static void testWithdraw(Account toTest) throws InsufficientFundsException {
        toTest.balance = new Money(100); // Sets the balance to 100

        toTest.withdraw(new Money(99));
        assertEquals(new Money(1), toTest.getBalance());

        toTest.withdraw(new Money(1));
        assertEquals(new Money(0), toTest.getBalance());

        //Tests if only non-negative Values are allowed and that a invalid parameter doesn't change the balance
        assertThrows(IllegalArgumentException.class, () -> toTest.withdraw(new Money(0)));
        assertThrows(IllegalArgumentException.class, () -> toTest.withdraw(new Money(-1)));
        assertEquals(new Money(0), toTest.getBalance());

        Money overdraftLimit = toTest.getOverdraft();

        //Check for InsufficientFundsException to be throw by taking to much money
        assertThrows(InsufficientFundsException.class, () -> toTest.withdraw(overdraftLimit.add(new Money(0, 1))));
        assertEquals(new Money(0), toTest.getBalance());

        if (overdraftLimit.compareTo(new Money(0)) > 0) {
            toTest.withdraw(overdraftLimit);
        }
        assertEquals(overdraftLimit.negate(), toTest.getBalance());

        assertThrows(InsufficientFundsException.class, () -> toTest.withdraw(new Money(0, 1)));
        assertEquals(overdraftLimit.negate(), toTest.getBalance());

        assertEquals(new Money(-1), toTest.getBalance());
    }

    /**
     * Tests if the Overdraft is zero or nonZero and positive
     *
     * @param toTest the Account to test
     * @param isZero true to check for zero, false for checking for non-zero positive
     */
    public static void testOverdraft(Account toTest, boolean isZero) {
        if (isZero) {
            assertEquals(new Money(0), toTest.getOverdraft());
        } else {
            assertTrue(toTest.getOverdraft().compareTo(new Money(0)) > 0);
        }
    }

    /**
     * Tests if the Saving interest is zero or nonZero
     *
     * @param toTest the Account to test
     * @param isZero true to check for zero, false for non zero
     */
    public static void testSavingInterest(Account toTest, boolean isZero) {
        if (isZero) {
            assertEquals(new Percentage(0), toTest.getSavingInterest());
        } else {
            assertNotEquals(new Percentage(0), toTest.getSavingInterest());
        }
    }

    /**
     * Tests if the Borrowing interest is zero or nonZero
     *
     * @param toTest the Account to test
     * @param isZero true to check for zero, false for non zero
     */
    public static void testBorrowingInterest(Account toTest, boolean isZero) {
        if (isZero) {
            assertEquals(new Percentage(0), toTest.getBorrowingInterest());
        } else {
            assertNotEquals(new Percentage(0), toTest.getBorrowingInterest());
        }
    }

    /**
     * Test the base implementation of {@link Account#deposit(Money)}
     */
    @Test
    public void deposit() {
        testDeposit(getDummyAccount());
    }

    /**
     * Test the base implementation of {@link Account#receiveInvoice(Money)}
     */
    @Test
    public void receiveInvoice() {
        testReceiveInvoice(getDummyAccount());
    }

    /**
     * Test the base implementation of {@link Account#sendInvoice(Money)}
     */
    @Test
    public void sendInvoice() throws InsufficientFundsException {
        Account account = getDummyAccount(new Money(1), new Percentage(0), new Percentage(0), new Money(100));

        testSendInvoice(account);
    }

    /**
     * Test the base implementation of {@link Account#withdraw(Money)}
     */
    @Test
    public void withdraw() throws InsufficientFundsException {
        Account account = getDummyAccount(new Money(1), new Percentage(0), new Percentage(0), new Money(100));

        testWithdraw(account);
    }

    /**
     * Tests the Getter and Setter of CustomerId
     */
    @Test
    public void getAndSetAccountId() {
        Account customer = getDummyAccount();
        assertThrows(IllegalStateException.class, customer::getAccountId);

        AccountId accountId = new AccountId(); // TODO edit after implementing the AccountId
        assertThrows(IllegalArgumentException.class, () -> customer.setAccountId(null));

        customer.setAccountId(accountId);

        assertThrows(IllegalStateException.class, () -> customer.setAccountId(accountId));

        assertEquals(accountId, customer.getAccountId());
        assertSame(accountId, customer.getAccountId());
    }

    /**
     * Checks if the Account applies the borrowing interest correctly
     */
    @Test
    public void applyBorrowingInterest() {
        Account accountWithPositivePercentage = getDummyAccount(new Money(100), new Percentage(1), new Percentage(1), new Money(-100));
        accountWithPositivePercentage.applyBorrowingInterest();
        assertEquals(new Money(-101), accountWithPositivePercentage.getBalance());
        accountWithPositivePercentage.balance = new Money(1);
        accountWithPositivePercentage.applyBorrowingInterest();
        assertEquals(new Money(1), accountWithPositivePercentage.getBalance());

        Account accountWithZeroPercentage = getDummyAccount(new Money(100), new Percentage(0), new Percentage(0), new Money(-100));
        accountWithZeroPercentage.applyBorrowingInterest();
        assertEquals(new Money(-100), accountWithZeroPercentage.getBalance());
        accountWithZeroPercentage.balance = new Money(1);
        accountWithZeroPercentage.applyBorrowingInterest();
        assertEquals(new Money(1), accountWithZeroPercentage.getBalance());

        Account accountWithNegativePercentage = getDummyAccount(new Money(100), new Percentage(-1), new Percentage(-1), new Money(-100));
        assertThrows(IllegalStateException.class, accountWithNegativePercentage::applyBorrowingInterest);
    }

    /**
     * Checks if the Account applies the saving interest correctly.
     */
    @Test
    public void applySavingInterest() {
        Account accountWithPositivePercentage = getDummyAccount(new Money(100), new Percentage(1), new Percentage(1), new Money(100));
        accountWithPositivePercentage.applySavingInterest();
        assertEquals(new Money(101), accountWithPositivePercentage.getBalance());
        accountWithPositivePercentage.balance = new Money(-1);
        accountWithPositivePercentage.applySavingInterest();
        assertEquals(new Money(-1), accountWithPositivePercentage.getBalance());

        Account accountWithZeroPercentage = getDummyAccount(new Money(100), new Percentage(0), new Percentage(0), new Money(100));
        accountWithZeroPercentage.applySavingInterest();
        assertEquals(new Money(100), accountWithZeroPercentage.getBalance());
        accountWithZeroPercentage.balance = new Money(-1);
        accountWithZeroPercentage.applySavingInterest();
        assertEquals(new Money(-1), accountWithZeroPercentage.getBalance());

        Account accountWithNegativePercentage = getDummyAccount(new Money(100), new Percentage(-1), new Percentage(-1), new Money(-100));
        assertThrows(IllegalStateException.class, accountWithNegativePercentage::applySavingInterest);
    }

    /**
     * A dummy Account to check the behavior of Account and can perform everything implemented in Account.
     */
    private static class DummyAccount extends Account {
        private final Money overdraft;
        private final Percentage borrowingInterest;
        private final Percentage savingInterest;

        private DummyAccount(Customer holder, Money overdraft, Percentage borrowingInterest, Percentage savingInterest, Money balance) {
            super(holder);
            this.overdraft = overdraft;
            this.borrowingInterest = borrowingInterest;
            this.savingInterest = savingInterest;
            this.balance = balance;
        }

        @Override
        protected Money getOverdraft() {
            return overdraft;
        }

        @Override
        protected Percentage getBorrowingInterest() {
            return borrowingInterest;
        }

        @Override
        protected Percentage getSavingInterest() {
            return savingInterest;
        }
    }
}
