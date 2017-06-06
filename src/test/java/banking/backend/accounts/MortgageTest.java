package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by guserav on 27.05.2017.
 */
class MortgageTest {

    /**
     * Generates a mortgage that has an initialized amount but no {@link AccountId}.
     *
     * @return an instance of {@link Mortgage}
     */
    private static Mortgage getValidMortgage() {
        final Mortgage mortgage = new Mortgage(CustomerTest.getDummyCustomer());
        mortgage.initAmount(new Money(100));
        return mortgage;
    }

    @Test
    public void testOverdraft() {
        LoanTest.testOverdraft(getValidMortgage());
    }

    @Test
    public void testBorrowingInterest() {
        LoanTest.testBorrowingInterest(getValidMortgage());
    }

    @Test
    public void testSavingInterest() {
        LoanTest.testSavingInterest(getValidMortgage());
    }

    @Test
    public void testReceiveInvoice() {
        LoanTest.testReceiveInvoice(getValidMortgage());
    }

    @Test
    public void testDeposit() {
        LoanTest.testDeposit(getValidMortgage());
    }

    @Test
    public void testSendInvoice() {
        LoanTest.testSendInvoice(getValidMortgage());
    }

    @Test
    public void testWithdraw() {
        LoanTest.testWithdraw(getValidMortgage());
    }

    @Test
    public void testInit() {
        LoanTest.testInit(new Mortgage(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructing() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        AccountTest.testConstructing(Mortgage.class, 18, false);
    }
}