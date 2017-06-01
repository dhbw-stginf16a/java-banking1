package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Test all features of {@link PersonalLoan}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class PersonalLoanTest {

    /**
     * Generates a personal loan that has an initialized amount but no {@link AccountId}.
     *
     * @return an instance of {@link PersonalLoan}
     */
    private static PersonalLoan getValidPersonalLoan() {
        final PersonalLoan personalLoan = new PersonalLoan(CustomerTest.getDummyCustomer());
        personalLoan.initAmount(new Money(100));
        return personalLoan;
    }

    @Test
    public void testOverdraft() {
        LoanTest.testOverdraft(getValidPersonalLoan());
    }

    @Test
    public void testBorrowingInterest() {
        LoanTest.testBorrowingInterest(getValidPersonalLoan());
    }

    @Test
    public void testSavingInterest() {
        LoanTest.testSavingInterest(getValidPersonalLoan());
    }

    @Test
    public void testReceiveInvoice() {
        LoanTest.testReceiveInvoice(getValidPersonalLoan());
    }

    @Test
    public void testDeposit() {
        LoanTest.testDeposit(getValidPersonalLoan());
    }

    @Test
    public void testSendInvoice() {
        LoanTest.testSendInvoice(getValidPersonalLoan());
    }

    @Test
    public void testWithdraw() {
        LoanTest.testWithdraw(getValidPersonalLoan());
    }

    @Test
    public void testInit() {
        LoanTest.testInit(new PersonalLoan(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructing() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        AccountTest.testConstructing(PersonalLoan.class, 18, false);
    }
}