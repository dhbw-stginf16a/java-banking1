package banking.backend.accounts;

import banking.backend.persons.Customer;
import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This test all functions of a {@link CreditCards}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class CreditCardsTest {

    @Test
    public void testOverdraft() {
        AccountTest.testOverdraft(new CreditCards(CustomerTest.getDummyCustomer()), false);
    }

    @Test
    public void testBorrowingInterest() {
        AccountTest.testBorrowingInterest(new CreditCards(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testSavingInterest() {
        AccountTest.testOverdraft(new CreditCards(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testReceiveInvoice() {
        assertThrows(UnsupportedOperationException.class, () -> AccountTest.testReceiveInvoice(new CreditCards(CustomerTest.getDummyCustomer())));
    }

    @Test
    public void testDeposit() {
        assertThrows(UnsupportedOperationException.class, () -> AccountTest.testDeposit(new CreditCards(CustomerTest.getDummyCustomer())));
    }

    @Test
    public void testSendInvoice() throws InsufficientFundsException {
        AccountTest.testSendInvoice(new CreditCards(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        AccountTest.testWithdraw(new CreditCards(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructing() throws IllegalAccessException, InstantiationException {
        Customer customerOfLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(18, false);
        Customer customerOfLegalAgeBusiness = CustomerTest.getDummyCustomer(18, true);
        Customer customerOfNoneLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(17, false);
        Customer customerOfNoneLegalAgeBusiness = CustomerTest.getDummyCustomer(17, true);

        assertSame(customerOfLegalAgeNoneBusiness, customerOfLegalAgeNoneBusiness.setupAccount(CreditCards.class).getHolder());
        assertSame(customerOfLegalAgeBusiness, customerOfLegalAgeBusiness.setupAccount(CreditCards.class).getHolder());
        assertThrows(IllegalArgumentException.class, () -> customerOfNoneLegalAgeNoneBusiness.setupAccount(CreditCards.class));
        assertThrows(IllegalArgumentException.class, () -> customerOfNoneLegalAgeBusiness.setupAccount(CreditCards.class));
    }
}