package banking.backend.accounts;

import banking.backend.persons.Customer;
import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This test all functions of a {@link DebitCard}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class DebitCardTest {

    @Test
    public void testOverdraft() {
        AccountTest.testOverdraft(new DebitCard(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testBorrowingInterest() {
        AccountTest.testBorrowingInterest(new DebitCard(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testSavingInterest() {
        AccountTest.testSavingInterest(new DebitCard(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testReceiveInvoice() {
        AccountTest.testReceiveInvoice(new DebitCard(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testDeposit() {
        assertThrows(UnsupportedOperationException.class, () -> AccountTest.testDeposit(new DebitCard(CustomerTest.getDummyCustomer())));
    }

    @Test
    public void testSendInvoice() throws InsufficientFundsException {
        AccountTest.testSendInvoice(new DebitCard(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        AccountTest.testWithdraw(new DebitCard(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructing() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Customer customerOfLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(18, false);
        Customer customerOfLegalAgeBusiness = CustomerTest.getDummyCustomer(18, true);
        Customer customerOfNoneLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(17, false);
        Customer customerOfNoneLegalAgeBusiness = CustomerTest.getDummyCustomer(17, true);

        assertSame(customerOfLegalAgeNoneBusiness, customerOfLegalAgeNoneBusiness.setupAccount(DebitCard.class).getHolder());
        assertSame(customerOfLegalAgeBusiness, customerOfLegalAgeBusiness.setupAccount(DebitCard.class).getHolder());
        assertSame(customerOfNoneLegalAgeNoneBusiness, customerOfNoneLegalAgeNoneBusiness.setupAccount(DebitCard.class).getHolder());
        assertSame(customerOfNoneLegalAgeBusiness, customerOfNoneLegalAgeBusiness.setupAccount(DebitCard.class).getHolder());
    }
}