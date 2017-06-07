package banking.backend.accounts;

import banking.backend.persons.Customer;
import banking.backend.persons.CustomerTest;
import banking.backend.persons.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the behavior of {@link JuniorAccount}
 * <p>
 * Created by guserav on 27.05.2017.
 */
class JuniorAccountTest {

    @Test
    public void testOverdraft() {
        AccountTest.testOverdraft(new JuniorAccount(CustomerTest.getDummyCustomer(10, false), CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testBorrowingInterest() {
        AccountTest.testBorrowingInterest(new JuniorAccount(CustomerTest.getDummyCustomer(10, false), CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testSavingInterest() {
        AccountTest.testSavingInterest(new JuniorAccount(CustomerTest.getDummyCustomer(10, false), CustomerTest.getDummyCustomer()), false);
    }

    @Test
    public void testReceiveInvoice() {
        AccountTest.testReceiveInvoice(new JuniorAccount(CustomerTest.getDummyCustomer(10, false), CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testDeposit() {
        AccountTest.testDeposit(new JuniorAccount(CustomerTest.getDummyCustomer(10, false), CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSendInvoice() throws InsufficientFundsException {
        AccountTest.testSendInvoice(new JuniorAccount(CustomerTest.getDummyCustomer(10, false), CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        AccountTest.testWithdraw(new JuniorAccount(CustomerTest.getDummyCustomer(10, false), CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructingWithValidLegalGuardian() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Person legalGuardian = CustomerTest.getDummyCustomer(18, false);
        Customer customerOfLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(16, false);
        Customer customerOfLegalAgeBusiness = CustomerTest.getDummyCustomer(16, true);
        Customer customerOfNoneLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(16 - 1, false);
        Customer customerOfNoneLegalAgeBusiness = CustomerTest.getDummyCustomer(16 - 1, true);

        AccountTest.assertThrowsWithCause(IllegalArgumentException.class, () -> customerOfLegalAgeNoneBusiness.setupAccount(JuniorAccount.class, legalGuardian));
        assertSame(customerOfNoneLegalAgeNoneBusiness, customerOfNoneLegalAgeNoneBusiness.setupAccount(JuniorAccount.class, legalGuardian).getHolder());
        AccountTest.assertThrowsWithCause(IllegalArgumentException.class, () -> customerOfLegalAgeBusiness.setupAccount(JuniorAccount.class, legalGuardian));
        AccountTest.assertThrowsWithCause(IllegalArgumentException.class, () -> customerOfNoneLegalAgeBusiness.setupAccount(JuniorAccount.class, legalGuardian));
    }

    @Test
    public void testConstructingWithInvalidLegalGuardian() throws IllegalAccessException, InstantiationException {
        Person legalGuardian = CustomerTest.getDummyCustomer(17, false);
        Customer customerOfLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(16, false);
        Customer customerOfLegalAgeBusiness = CustomerTest.getDummyCustomer(16, true);
        Customer customerOfNoneLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(16 - 1, false);
        Customer customerOfNoneLegalAgeBusiness = CustomerTest.getDummyCustomer(16 - 1, true);

        AccountTest.assertThrowsWithCause(IllegalArgumentException.class, () -> customerOfLegalAgeNoneBusiness.setupAccount(JuniorAccount.class, legalGuardian));
        AccountTest.assertThrowsWithCause(IllegalArgumentException.class, () -> customerOfNoneLegalAgeNoneBusiness.setupAccount(JuniorAccount.class, legalGuardian));
        AccountTest.assertThrowsWithCause(IllegalArgumentException.class, () -> customerOfLegalAgeBusiness.setupAccount(JuniorAccount.class, legalGuardian));
        AccountTest.assertThrowsWithCause(IllegalArgumentException.class, () -> customerOfNoneLegalAgeBusiness.setupAccount(JuniorAccount.class, legalGuardian));
    }

    @Test
    public void testConstructingWithoutLegalGuardian() throws IllegalAccessException, InstantiationException {
        Customer customerOfNoneLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(16 - 1, false);
        assertThrows(NoSuchMethodException.class, () -> customerOfNoneLegalAgeNoneBusiness.setupAccount(JuniorAccount.class));
    }
}