package banking.backend.accounts;

import banking.backend.persons.Customer;
import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test the behavior of {@link StudentSavings}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class StudentSavingsTest {
    @Test
    public void testOverdraft() {
        AccountTest.testOverdraft(new StudentSavings(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testBorrowingInterest() {
        AccountTest.testBorrowingInterest(new StudentSavings(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testSavingInterest() {
        AccountTest.testOverdraft(new StudentSavings(CustomerTest.getDummyCustomer()), false);
    }

    @Test
    public void testReceiveInvoice() {
        AccountTest.testReceiveInvoice(new StudentSavings(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testDeposit() {
        AccountTest.testDeposit(new StudentSavings(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSendInvoice() throws InsufficientFundsException {
        assertThrows(UnsupportedOperationException.class, () -> AccountTest.testSendInvoice(new StudentSavings(CustomerTest.getDummyCustomer())));
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        AccountTest.testWithdraw(new StudentSavings(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructing() throws InstantiationException, IllegalAccessException {
        Customer customerOfLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(18, false);
        Customer customerOfLegalAgeBusiness = CustomerTest.getDummyCustomer(18, true);
        Customer customerOfNoneLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(17, false);
        Customer customerOfNoneLegalAgeBusiness = CustomerTest.getDummyCustomer(17, true);

        assertThrows(IllegalArgumentException.class, () -> customerOfLegalAgeBusiness.setupAccount(StudentSavings.class));
        assertSame(customerOfLegalAgeBusiness, customerOfNoneLegalAgeNoneBusiness.setupAccount(StudentSavings.class).getHolder());
        assertThrows(IllegalArgumentException.class, () -> customerOfLegalAgeNoneBusiness.setupAccount(StudentSavings.class));
        assertThrows(IllegalArgumentException.class, () -> customerOfNoneLegalAgeBusiness.setupAccount(StudentSavings.class));
    }
}