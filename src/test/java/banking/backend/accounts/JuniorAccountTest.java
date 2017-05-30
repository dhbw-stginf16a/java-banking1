package banking.backend.accounts;

import banking.backend.persons.Customer;
import banking.backend.persons.CustomerTest;
import banking.backend.persons.Person;
import org.junit.jupiter.api.Test;

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
        AccountTest.testOverdraft(new CurrentAccount(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testBorrowingInterest() {
        AccountTest.testBorrowingInterest(new CurrentAccount(CustomerTest.getDummyCustomer()), true);
    }

    @Test
    public void testSavingInterest() {
        AccountTest.testOverdraft(new CurrentAccount(CustomerTest.getDummyCustomer()), false);
    }

    @Test
    public void testReceiveInvoice() {
        AccountTest.testReceiveInvoice(new CurrentAccount(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testDeposit() {
        AccountTest.testDeposit(new CurrentAccount(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testSendInvoice() throws InsufficientFundsException {
        AccountTest.testSendInvoice(new CurrentAccount(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        AccountTest.testWithdraw(new CurrentAccount(CustomerTest.getDummyCustomer()));
    }

    @Test
    public void testConstructingWithValidLegalGuardian() {
        Person legalGuardian = CustomerTest.getDummyCustomer(18, false);
        Customer customerOfLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(16, false);
        Customer customerOfLegalAgeBusiness = CustomerTest.getDummyCustomer(16, true);
        Customer customerOfNoneLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(16 - 1, false);
        Customer customerOfNoneLegalAgeBusiness = CustomerTest.getDummyCustomer(16 - 1, true);

        assertThrows(IllegalArgumentException.class, () -> customerOfLegalAgeNoneBusiness.setupAccount(JuniorAccount.class, legalGuardian));
        assertSame(customerOfLegalAgeBusiness, customerOfNoneLegalAgeNoneBusiness.setupAccount(JuniorAccount.class, legalGuardian).getHolder());
        assertThrows(IllegalArgumentException.class, () -> customerOfLegalAgeBusiness.setupAccount(JuniorAccount.class, legalGuardian));
        assertThrows(IllegalArgumentException.class, () -> customerOfNoneLegalAgeBusiness.setupAccount(JuniorAccount.class, legalGuardian));
    }

    @Test
    public void testConstructingWithInvalidLegalGuardian() {
        Person legalGuardian = CustomerTest.getDummyCustomer(17, false);
        Customer customerOfLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(16, false);
        Customer customerOfLegalAgeBusiness = CustomerTest.getDummyCustomer(16, true);
        Customer customerOfNoneLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(16 - 1, false);
        Customer customerOfNoneLegalAgeBusiness = CustomerTest.getDummyCustomer(16 - 1, true);

        assertThrows(IllegalArgumentException.class, () -> customerOfLegalAgeNoneBusiness.setupAccount(JuniorAccount.class, legalGuardian));
        assertThrows(IllegalArgumentException.class, () -> customerOfNoneLegalAgeNoneBusiness.setupAccount(JuniorAccount.class, legalGuardian));
        assertThrows(IllegalArgumentException.class, () -> customerOfLegalAgeBusiness.setupAccount(JuniorAccount.class, legalGuardian));
        assertThrows(IllegalArgumentException.class, () -> customerOfNoneLegalAgeBusiness.setupAccount(JuniorAccount.class, legalGuardian));
    }

    @Test
    public void testConstructingWithoutLegalGuardian() {
        Customer customerOfNoneLegalAgeNoneBusiness = CustomerTest.getDummyCustomer(16 - 1, false);
        assertThrows(RuntimeException.class, () -> customerOfNoneLegalAgeNoneBusiness.setupAccount(JuniorAccount.class));
    }
}