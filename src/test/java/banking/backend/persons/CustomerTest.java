package banking.backend.persons;

import banking.NotYetImplementedException;
import banking.backend.DateTime;
import banking.backend.DateTimeTest;
import banking.backend.Money;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contains all test for non private methods of {@link Customer}
 */
public class CustomerTest {
    public static final String DUMMY_NAME = "Testi Testdummy";
    public static final String DUMMY_ADDRESS = "Teststraße 77a\n77777 Testingen";
    public static final String DUMMY_TELEPHONE_NUMBER = "+49 827 8362783";
    /**
     * Default values of the dummy customer used for testing.
     */
    private static final int DUMMY_BUSINESS_DEFAULT_AGE = 21;
    public static final DateTime DUMMY_BIRTH_DATE = DateTimeTest.getDateTimeFromAge(DUMMY_BUSINESS_DEFAULT_AGE);

    /**
     * Get a dummy customer for testing purposes, that doesn't contain a CustomerId.
     * <p>
     * To add a CustomerId please use {@link banking.backend.Bank#addCustomer(Customer)}.
     *
     * @return a generic customer without a {@link CustomerId}
     */
    public static Customer getDummyCustomer() {
        return getDummyCustomer(false);
    }

    /**
     * Get a dummy customer for testing purposes, that doesn't contain a CustomerId.
     * <p>
     * To add a CustomerId please use {@link banking.backend.Bank#addCustomer(Customer)}.
     *
     * @param age        The age of the Customer to generate
     * @param isBusiness if the Customer is a business customer
     * @return a generic customer without a {@link CustomerId}
     */
    public static Customer getDummyCustomer(int age, boolean isBusiness) {
        return new Customer(DUMMY_NAME, DUMMY_ADDRESS, DateTimeTest.getDateTimeFromAge(age), DUMMY_TELEPHONE_NUMBER, isBusiness);
    }

    /**
     * Get a dummy customer for testing purposes, that doesn't contain a CustomerId.
     * <p>
     * To add a CustomerId please use {@link banking.backend.Bank#addCustomer(Customer)}.
     *
     * @param isBusiness if the Customer is a business customer
     * @return a generic customer without a {@link CustomerId}
     */
    public static Customer getDummyCustomer(boolean isBusiness) {
        return getDummyCustomer(DUMMY_BUSINESS_DEFAULT_AGE, isBusiness);
    }

    /**
     * A dummy test to show that these tests aren't ready.
     * <p>
     * The test of {@link Customer#setupAccount} should be done in the {@link AccountTest} with the checking of all prerequisite for the accounts.
     */
    @Test
    @DisplayName("This test serves as a reminder that the check of Customer.setupAccount should be done somewhere else.")
    public void notYetImplementedTest1() {
        throw new NotYetImplementedException(); // TODO remove this when the other test is implemented
    }

    /**
     * Test functionality of the Customer constructor.
     */
    @Test
    public void customer() {
        Customer dummyCustomer = getDummyCustomer();
        // Check if all attributes are correctly set
        assertAll(
                () -> assertThrows(IllegalStateException.class, dummyCustomer::getCustomerId),
                () -> assertEquals(DUMMY_NAME, dummyCustomer.getName()),
                () -> assertEquals(DUMMY_ADDRESS, dummyCustomer.getAddress()),
                () -> assertEquals(DUMMY_BIRTH_DATE, dummyCustomer.getBirthdate()),
                () -> assertEquals(DUMMY_TELEPHONE_NUMBER, dummyCustomer.getTelephoneNumber()),
                () -> assertEquals(false, dummyCustomer.isBusinessCustomer())
        );
        // No parameter of the constructor can be null except for telephone
        assertThrows(IllegalArgumentException.class, () -> new Customer(null, "address", new DateTime(), "tel", false));
        assertThrows(IllegalArgumentException.class, () -> new Customer("name", null, new DateTime(), "tel", false));
        assertThrows(IllegalArgumentException.class, () -> new Customer("name", "address", null, "tel", false));
        assertNotNull(new Customer("name", "address", new DateTime(), null, false), "Telephone can be null");

        // No parameter of the constructor can be empty except for telehpone number
        assertThrows(IllegalArgumentException.class, () -> new Customer("", "address", new DateTime(), "tel", false));
        assertThrows(IllegalArgumentException.class, () -> new Customer("name", "", new DateTime(), "tel", false));
        assertNotNull(new Customer("name", "address", new DateTime(), "", false), "Telephone can be null");

        // A business customer must be at least 21 years old
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Customer(
                        "name", "address", DateTimeTest.getDateTimeFromAge(20), "tel", false)),
                () -> assertNotNull(new Customer("name", "address", DateTimeTest.getDateTimeFromAge(21), "tel", false))

        );

        // The date of birth cannot lie in the future
        assertThrows(IllegalArgumentException.class, () -> new Customer(
                "name", "address", DateTimeTest.getDateTimeFromAge(-1), "tel", false));
    }

    /**
     * Tests the Getter and Setter of CustomerId.
     */
    @Test
    public void getAndSetCustomerId() {
        Customer customer = getDummyCustomer();
        assertThrows(IllegalStateException.class, customer::getCustomerId);

        CustomerId customerId = new CustomerId();
        assertThrows(IllegalArgumentException.class, () -> customer.setCustomerId(null));

        customer.setCustomerId(customerId);

        assertThrows(IllegalStateException.class, () -> customer.setCustomerId(customerId));

        Customer customer2 = getDummyCustomer();
        customer2.setCustomerId(customerId);

        assertEquals(customerId, customer.getCustomerId());
        assertEquals(customer, customer2);
        assertSame(customerId, customer.getCustomerId());
    }

    /**
     * Test if {@link Customer#invoice(Account, AccountId, Money)} actually performs the Invoice
     * <p>
     * This includes error testing
     */
    @Test
    public void invoice() {
        throw new NotYetImplementedException();
    }

    /**
     * Test if {@link Customer#withdraw(Account, Money)} actually performs the Withdraw
     * <p>
     * This includes error testing
     */
    @Test
    public void withdraw() {
        throw new NotYetImplementedException();
    }
}
