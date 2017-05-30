package banking.backend.accounts;

import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test the behavior of {@link CorporateSavings}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class CorporateSavingsTest {

    @Test
    public void testOverdraft() {
        AccountTest.testOverdraft(new CorporateSavings(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testBorrowingInterest() {
        AccountTest.testBorrowingInterest(new CorporateSavings(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testSavingInterest() {
        AccountTest.testOverdraft(new CorporateSavings(CustomerTest.getDummyCustomer()), false);
    }

    @Test
    public void testReceiveInvoice() {
        AccountTest.testReceiveInvoice(new CorporateSavings(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testDeposit() {
        AccountTest.testDeposit(new CorporateSavings(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSendInvoice() throws InsufficientFundsException {
        assertThrows(UnsupportedOperationException.class, () -> AccountTest.testSendInvoice(new CorporateSavings(CustomerTest.getDummyCustomer())));
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        AccountTest.testWithdraw(new CorporateSavings(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructing() {
        AccountTest.testConstructing(CorporateSavings.class, 18, true);
    }
}