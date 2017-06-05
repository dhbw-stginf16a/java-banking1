package banking.backend.persons;

import banking.backend.Bank;
import banking.backend.DateTime;
import banking.backend.DateTimeTest;
import banking.backend.Money;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.accounts.CurrentAccount;
import banking.backend.transactions.TransactionFailedException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

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
     * Test functionality of the Customer constructor.
     */
    @Test
    public void customer() {
        Customer dummyCustomer = getDummyCustomer();

        assertAll("Check if all attributes are correctly set",
                () -> assertThrows(IllegalStateException.class, dummyCustomer::getCustomerId),
                () -> assertEquals(DUMMY_NAME, dummyCustomer.getName()),
                () -> assertEquals(DUMMY_ADDRESS, dummyCustomer.getAddress()),
                () -> assertEquals(DUMMY_BIRTH_DATE, dummyCustomer.getBirthdate()),
                () -> assertEquals(DUMMY_TELEPHONE_NUMBER, dummyCustomer.getTelephoneNumber()),
                () -> assertEquals(false, dummyCustomer.isBusinessCustomer())
        );

        assertAll("No parameter of the constructor can be null except for telephone",
                () -> assertThrows(IllegalArgumentException.class, () -> new Customer(null, "address", new DateTime(), "tel", false)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Customer("name", null, new DateTime(), "tel", false)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Customer("name", "address", null, "tel", false)),
                () -> assertNotNull(new Customer("name", "address", new DateTime(), null, false), "Telephone can be null")
        );

        assertAll("No parameter of the constructor can be empty except for telehpone number",
                () -> assertThrows(IllegalArgumentException.class, () -> new Customer("", "address", new DateTime(), "tel", false)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Customer("name", "", new DateTime(), "tel", false)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Customer("name", "address", new DateTime(), "", false))
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
    public void invoice() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, TransactionFailedException {
        Customer dummy = getDummyCustomer();
        Account dummyAccount1 = dummy.setupAccount(CurrentAccount.class);
        Account dummyAccount2 = dummy.setupAccount(CurrentAccount.class);
        Money balance1 = dummyAccount1.getBalance();
        Money balance2 = dummyAccount2.getBalance();
        Money amount = new Money(1);

        dummy.invoice(dummyAccount1, dummyAccount2.getAccountId(), amount);
        assertEquals(balance1.subtract(amount), dummyAccount1.getBalance(), "The Balance should change after invoice");
        assertEquals(balance2.add(amount), dummyAccount2.getBalance(), "The Balance should change after the invoice");

        assertThrows(IllegalArgumentException.class, () -> dummy.invoice(null, dummyAccount2.getAccountId(), amount));
        assertThrows(IllegalArgumentException.class, () -> dummy.invoice(dummyAccount1, null, amount));
        assertThrows(IllegalArgumentException.class, () -> dummy.invoice(dummyAccount1, dummyAccount2.getAccountId(), null));

        assertThrows(IllegalArgumentException.class, () -> dummy.invoice(dummyAccount1, dummyAccount2.getAccountId(), new Money(0)));
        assertThrows(IllegalArgumentException.class, () -> dummy.invoice(dummyAccount1, dummyAccount2.getAccountId(), new Money(-1)));

        assertEquals(balance1.subtract(amount), dummyAccount1.getBalance(), "The balance shouldn't have changed after failed invoices.");
        assertEquals(balance2.add(amount), dummyAccount2.getBalance(), "The balance shouldn't have changed after failed invoices.");

        assertFalse(Bank.getInstance().getTransactions().isEmpty(), "The Transactions list shouldn't be empty after a successful transaction");
    }

    /**
     * Test if {@link Customer#withdraw(Account, Money)} actually performs the Withdraw
     * <p>
     * This includes error testing
     */
    @Test
    public void withdraw() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, TransactionFailedException {
        Customer dummy = getDummyCustomer();
        Account dummyAccount1 = dummy.setupAccount(CurrentAccount.class);
        Money balance1 = dummyAccount1.getBalance();
        Money amount = new Money(1);

        dummy.withdraw(dummyAccount1, amount);
        assertEquals(balance1.subtract(amount), dummyAccount1.getBalance(), "The Balance should change after withdrawal");
        assertThrows(IllegalArgumentException.class, () -> dummy.withdraw(null, amount));
        assertThrows(IllegalArgumentException.class, () -> dummy.withdraw(dummyAccount1, null));

        assertThrows(IllegalArgumentException.class, () -> dummy.withdraw(dummyAccount1, new Money(0)));
        assertThrows(IllegalArgumentException.class, () -> dummy.withdraw(dummyAccount1, new Money(-1)));

        assertEquals(balance1.subtract(amount), dummyAccount1.getBalance(), "The balance shouldn't have changed after failed withdrawal.");

        assertFalse(Bank.getInstance().getTransactions().isEmpty(), "The Transactions list shouldn't be empty after a successful withdrawal");
    }
}
