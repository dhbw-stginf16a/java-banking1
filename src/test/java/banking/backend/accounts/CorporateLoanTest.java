package banking.backend.accounts;

import banking.backend.Money;
import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Test the behavior of {@link CorporateLoan}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class CorporateLoanTest {

    /**
     * Generates a corporate loan that has an initialized amount but no {@link AccountId}.
     *
     * @return an instance of {@link CorporateLoan}
     */
    private static CorporateLoan getValidCorporateLoan() {
        final CorporateLoan corporateLoan = new CorporateLoan(CustomerTest.getDummyCustomer(18, true));
        corporateLoan.initAmount(new Money(100));
        return corporateLoan;
    }

    @Test
    public void testOverdraft() {
        LoanTest.testOverdraft(getValidCorporateLoan());
    }

    @Test
    public void testBorrowingInterest() {
        LoanTest.testBorrowingInterest(getValidCorporateLoan());
    }

    @Test
    public void testSavingInterest() {
        LoanTest.testSavingInterest(getValidCorporateLoan());
    }

    @Test
    public void testReceiveInvoice() {
        LoanTest.testReceiveInvoice(getValidCorporateLoan());
    }

    @Test
    public void testDeposit() {
        LoanTest.testDeposit(getValidCorporateLoan());
    }

    @Test
    public void testSendInvoice() {
        LoanTest.testSendInvoice(getValidCorporateLoan());
    }

    @Test
    public void testWithdraw() {
        LoanTest.testWithdraw(getValidCorporateLoan());
    }

    @Test
    public void testInit() {
        LoanTest.testInit(new CorporateLoan(CustomerTest.getDummyCustomer(18, true)));
    }

    @Test
    public void testConstructing() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        AccountTest.testConstructing(CorporateLoan.class, 18, true);
    }
}