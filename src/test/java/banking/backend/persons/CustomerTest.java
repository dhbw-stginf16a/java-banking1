package banking.backend.persons;

import banking.NotYetImplementedException;
import banking.backend.DateTimeTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contains all test for non private methods of {@link Customer}
 */
public class CustomerTest {
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
        return new Customer("Testi Testdummy", "Teststraße 77a\n77777 Testingen", DateTimeTest.getDateTimeFromAge(age), "+49 827 8362783", isBusiness);
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
        return getDummyCustomer(21, isBusiness);
    }

    /**
     * A dummy test to show that these tests aren't ready
     */
    @Test
    @DisplayName("This test shows that not all test for the CustomerClass are done.")
    public void notYetImplementedTest() {
        throw new NotYetImplementedException(); // TODO remove this if all tests are implemented
    }

    /**
     * A dummy test to show that these tests aren't ready.
     * <p>
     * The test of {@link Customer#setupAccount} should be done in the AccountTest with the checking of all prerequisite for the accounts.
     */
    @Test
    @DisplayName("This test serves as a reminder that the check of Customer.setupAccount should be done somewhere else.")
    public void notYetImplementedTest1() {
        throw new NotYetImplementedException(); // TODO remove this when the other test is implemented
    }

    /**
     * Tests the Getter and Setter of CustomerId
     */
    @Test
    public void getAndSetCustomerId() {
        Customer customer = getDummyCustomer();
        assertThrows(IllegalStateException.class, customer::getCustomerId);

        CustomerId customerId = new CustomerId(); // TODO edit after implementing the CustomerId
        assertThrows(IllegalArgumentException.class, () -> customer.setCustomerId(null));

        customer.setCustomerId(customerId);

        assertThrows(IllegalStateException.class, () -> customer.setCustomerId(customerId));

        assertEquals(customerId, customer.getCustomerId());
        assertSame(customerId, customer.getCustomerId());
    }
}
