package banking.backend.accounts;

import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

/**
 * Test all features of {@link PersonalLoan}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class PersonalLoanTest {

    @Test
    public void testOverdraft() {
        LoanTest.testOverdraft(new PersonalLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testBorrowingInterest() {
        LoanTest.testBorrowingInterest(new PersonalLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSavingInterest() {
        LoanTest.testSavingInterest(new PersonalLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testReceiveInvoice() {
        LoanTest.testReceiveInvoice(new PersonalLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testDeposit() {
        LoanTest.testDeposit(new PersonalLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSendInvoice() {
        LoanTest.testSendInvoice(new PersonalLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testWithdraw() {
        LoanTest.testWithdraw(new PersonalLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testInit() {
        LoanTest.testInit(new PersonalLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructing() throws IllegalAccessException, InstantiationException {
        AccountTest.testConstructing(PersonalLoan.class, 18, false);
    }
}