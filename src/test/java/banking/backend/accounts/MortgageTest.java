package banking.backend.accounts;

import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

/**
 * Created by guserav on 27.05.2017.
 */
class MortgageTest {

    @Test
    public void testOverdraft() {
        LoanTest.testOverdraft(new Mortgage(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testBorrowingInterest(Loan toTest) {
        LoanTest.testBorrowingInterest(new Mortgage(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSavingInterest(Loan toTest) {
        LoanTest.testSavingInterest(new Mortgage(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testReceiveInvoice(Loan toTest) {
        LoanTest.testReceiveInvoice(new Mortgage(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testDeposit(Loan toTest) {
        LoanTest.testDeposit(new Mortgage(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSendInvoice(Loan toTest) {
        LoanTest.testSendInvoice(new Mortgage(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testWithdraw(Loan toTest) {
        LoanTest.testWithdraw(new Mortgage(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testInit() {
        LoanTest.testInit(new Mortgage(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructing() {
        AccountTest.testConstructing(Mortgage.class, 18, false);
    }
}