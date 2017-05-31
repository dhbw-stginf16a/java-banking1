package banking.backend.accounts;

import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test the behavior of {@link CorporateSavings}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class CorporateSavingsTest {

    @Test
    public void testOverdraft() {
        AccountTest.testOverdraft(new CorporateSavings(CustomerTest.getDummyCustomer(18, true)), true);
    }

    @Test
    public void testBorrowingInterest() {
        AccountTest.testBorrowingInterest(new CorporateSavings(CustomerTest.getDummyCustomer(18, true)), true);
    }

    @Test
    public void testSavingInterest() {
        AccountTest.testOverdraft(new CorporateSavings(CustomerTest.getDummyCustomer(18, true)), false);
    }

    @Test
    public void testReceiveInvoice() {
        AccountTest.testReceiveInvoice(new CorporateSavings(CustomerTest.getDummyCustomer(18, true)));
    }

    @Test
    public void testDeposit() {
        AccountTest.testDeposit(new CorporateSavings(CustomerTest.getDummyCustomer(18, true)));
    }

    @Test
    public void testSendInvoice() throws InsufficientFundsException {
        assertThrows(UnsupportedOperationException.class, () -> AccountTest.testSendInvoice(new CorporateSavings(CustomerTest.getDummyCustomer(18, true))));
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        AccountTest.testWithdraw(new CorporateSavings(CustomerTest.getDummyCustomer(18, true)));
    }

    @Test
    public void testConstructing() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        AccountTest.testConstructing(CorporateSavings.class, 18, true);
    }
}