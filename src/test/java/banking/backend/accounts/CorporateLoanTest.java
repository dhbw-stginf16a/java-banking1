package banking.backend.accounts;

import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

/**
 * Test the behavior of {@link CorporateLoan}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class CorporateLoanTest {
    @Test
    public void testOverdraft() {
        LoanTest.testOverdraft(new CorporateLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testBorrowingInterest() {
        LoanTest.testBorrowingInterest(new CorporateLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSavingInterest() {
        LoanTest.testSavingInterest(new CorporateLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testReceiveInvoice() {
        LoanTest.testReceiveInvoice(new CorporateLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testDeposit() {
        LoanTest.testDeposit(new CorporateLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSendInvoice() {
        LoanTest.testSendInvoice(new CorporateLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testWithdraw() {
        LoanTest.testWithdraw(new CorporateLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testInit() {
        LoanTest.testInit(new CorporateLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructing() throws IllegalAccessException, InstantiationException {
        AccountTest.testConstructing(CorporateLoan.class, 18, true);
    }
}