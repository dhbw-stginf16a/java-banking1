package banking.backend.accounts;

import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

/**
 * Test the behavior of {@link CurrentAccount}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class CurrentAccountTest {

    @Test
    public void testOverdraft() {
        AccountTest.testOverdraft(new CurrentAccount(CustomerTest.getDummyCustomer()), false);
    }

    @Test
    public void testBorrowingInterest() {
        AccountTest.testBorrowingInterest(new CurrentAccount(CustomerTest.getDummyCustomer()), false);
    }

    @Test
    public void testSavingInterest() {
        AccountTest.testOverdraft(new CurrentAccount(CustomerTest.getDummyCustomer()), false);
    }

    @Test
    public void testReceiveInvoice() {
        AccountTest.testReceiveInvoice(new CurrentAccount(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testDeposit() {
        AccountTest.testDeposit(new CurrentAccount(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSendInvoice() throws InsufficientFundsException {
        AccountTest.testSendInvoice(new CurrentAccount(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        AccountTest.testWithdraw(new CurrentAccount(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructing() throws IllegalAccessException, InstantiationException {
        AccountTest.testConstructing(CurrentAccount.class, 16, false);
    }
}