package banking.backend.accounts;

import banking.backend.persons.Customer;
import banking.backend.persons.CustomerTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test the behavior of {@link StudentSavings}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class StudentSavingsTest {
    private static int DEFAULT_STUDENT_AGE = 17;
    @Test
    public void testOverdraft() {
        AccountTest.testOverdraft(new StudentSavings(CustomerTest.getDummyCustomer(DEFAULT_STUDENT_AGE, false)), true);
    }

    @Test
    public void testBorrowingInterest() {
        AccountTest.testBorrowingInterest(new StudentSavings(CustomerTest.getDummyCustomer(DEFAULT_STUDENT_AGE, false)), true);
    }

    @Test
    public void testSavingInterest() {
        AccountTest.testSavingInterest(new StudentSavings(CustomerTest.getDummyCustomer(DEFAULT_STUDENT_AGE, false)), false);
    }

    @Test
    public void testReceiveInvoice() {
        AccountTest.testReceiveInvoice(new StudentSavings(CustomerTest.getDummyCustomer(DEFAULT_STUDENT_AGE, false)));
    }

    @Test
    public void testDeposit() {
        AccountTest.testDeposit(new StudentSavings(CustomerTest.getDummyCustomer(DEFAULT_STUDENT_AGE, false)));
    }

    @Test
    public void testSendInvoice() throws InsufficientFundsException {
        assertThrows(UnsupportedOperationException.class, () -> AccountTest.testSendInvoice(new StudentSavings(CustomerTest.getDummyCustomer(DEFAULT_STUDENT_AGE, false))));
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        AccountTest.testWithdraw(new StudentSavings(CustomerTest.getDummyCustomer(DEFAULT_STUDENT_AGE, false)));
    }

    @Test
    public void testConstructing() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Customer customerOfLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(18, false);
        Customer customerOfLegalAgeBusiness = CustomerTest.getDummyCustomer(18, true);
        Customer customerOfNoneLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(17, false);
        Customer customerOfNoneLegalAgeBusiness = CustomerTest.getDummyCustomer(17, true);

        AccountTest.assertThrowsWithCause(IllegalArgumentException.class, () -> customerOfLegalAgeBusiness.setupAccount(StudentSavings.class));
        assertEquals(customerOfNoneLegalAgeNoneBusiness, customerOfNoneLegalAgeNoneBusiness.setupAccount(StudentSavings.class).getHolder());
        AccountTest.assertThrowsWithCause(IllegalArgumentException.class, () -> customerOfLegalAgeNoneBusiness.setupAccount(StudentSavings.class));
        AccountTest.assertThrowsWithCause(IllegalArgumentException.class, () -> customerOfNoneLegalAgeBusiness.setupAccount(StudentSavings.class));
    }
}