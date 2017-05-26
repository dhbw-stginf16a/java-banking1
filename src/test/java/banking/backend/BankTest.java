package banking.backend;

import banking.backend.accounts.Account;
import banking.backend.persons.Customer;
import banking.backend.transactions.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the {@link Bank} class.
 */
class BankTest {
    /**
     * Reset the Bank instance by setting it to null to have equal conditions before each test.
     * Each test will set up their own bank instance as they need.
     * The next call to {@link Bank#getInstance()} will set it again.
     *
     * @throws NoSuchFieldException   if the name of {@link Bank#instance} was changed
     * @throws IllegalAccessException
     */
    @BeforeEach
    static void resetInstance() throws NoSuchFieldException, IllegalAccessException {
        // Reset the private field where the instance is stored
        Field instance = Bank.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    /**
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @AfterAll
    static void tearDown() throws NoSuchFieldException, IllegalAccessException {
        resetInstance();
    }

    /**
     * Check whether a non null instance of Bank is returned and it is the same every time.
     *
     * @throws NoSuchFieldException   if the name of {@link Bank#instance} was changed
     * @throws IllegalAccessException
     */
    @Test
    void getInstance() throws NoSuchFieldException, IllegalAccessException {
        Bank firstInstance = Bank.getInstance();
        assertNotNull(firstInstance);

        Bank secondInstance = Bank.getInstance();
        assertSame(firstInstance, secondInstance);
    }

    /**
     * Check whether adding a customer adds them and exactly them.
     */
    @Test
    void addCustomer() {
        List<Customer> customers;
        Bank bank = Bank.getInstance();

        Customer dummyCustomer = CustomerTest.getDummyCustomer();
        bank.addCustomer(dummyCustomer);
        customers = bank.getCustomers();
        // Never return null
        assertNotNull(customers);
        // The one added customer should be there
        assertEquals(customers.size(), 1);
        assertSame(customers.get(0), dummyCustomer);
    }

    /**
     * Check if the same customer is returned if we have previously added them
     * and that null is returned if the customer is not there.
     */
    @Test
    void getCustomer() {
        Bank bank = Bank.getInstance();
        Customer dummyCustomer = CustomerTest.getDummyCustomer();

        // Customer should not be there before adding them
        assertNull(bank.getCustomer(dummyCustomer.getCustomerId()));

        bank.addCustomer(dummyCustomer);
        assertSame(bank.getCustomer(dummyCustomer.getCustomerId()), dummyCustomer);
    }

    /**
     * Check if the same account is returned if we have previously added them
     * and that null is returned if the account is not there.
     */
    @Test
    void getAccount() {
        Bank bank = Bank.getInstance();
        Account dummyAccount = AccountTest.getDummyAccount();

        // Customer should not be there before adding them
        assertNull(bank.getAccount(dummyAccount.getAccountId()));

        bank.addAccount(dummyAccount);
        assertSame(bank.getAccount(dummyAccount.getAccountId()), dummyAccount);
    }

    /**
     *
     */
    @Test
    void getCustomerAccounts() {
    }

    /**
     * Check if never returns null but an empety list if there are no
     * accounts and return a list with all added accounts.
     */
    @Test
    void getAccounts() {
        List<Account> accounts;
        Bank bank = Bank.getInstance();

        accounts = bank.getAccounts();
        // Never return null
        assertNotNull(accounts);
        // Start out with no accounts
        assertEquals(accounts.size(), 0);

        addAccount();
    }

    /**
     * Check if never returns null but an empety list if there are no
     * customers and return a list with all added customers.
     */
    @Test
    void getCustomers() {
        List<Customer> customers;
        Bank bank = Bank.getInstance();

        customers = bank.getCustomers();
        // Never return null
        assertNotNull(customers);
        // Start out with no customers
        assertEquals(customers.size(), 0);

        addCustomer();
    }

    /**
     * TODO: Javadoc
     */
    @Test
    void getTransactions() {
        Bank bank = Bank.getInstance();
        List<Transaction> transactions = bank.getTransactions();

        // Never return null
        assertNotNull(transactions);
        // Start out with no transactions
        assertEquals(transactions.size(), 0);

        // TODO: Check if added Transaction are returned
        // Might not do this with {@link Bank#applyTransacion} but via
        // reflection and just injecting transactions into the list
    }

    @Test
    void applyTransaction() {
    }

    /**
     * Check if the same account is returned if we have previously added them
     * and that null is returned if the account is not there.
     */
    @Test
    void addAccount() {
        List<Account> accounts;
        Bank bank = Bank.getInstance();

        Account dummyAccount = AccountTest.getDummyAccount();
        bank.addAccount(dummyAccount);
        accounts = bank.getAccounts();
        // Never return null
        assertNotNull(accounts);
        // The one added account should be there
        assertEquals(accounts.size(), 1);
        assertSame(accounts.get(0), dummyAccount);
    }

    @Test
    void deposit() {

    }

}