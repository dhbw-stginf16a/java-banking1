package banking.backend;

import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.accounts.CurrentAccount;
import banking.backend.persons.Customer;
import banking.backend.persons.CustomerId;
import banking.backend.persons.CustomerTest;
import banking.backend.transactions.Deposit;
import banking.backend.transactions.Transaction;
import banking.backend.transactions.TransactionFailedException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * @throws IllegalAccessException never
     */
    private static void resetInstance() throws NoSuchFieldException, IllegalAccessException {
        // Reset the private field where the instance is stored
        Field instance = Bank.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    /**
     * Reset bank instance to clean up all changes made by testin.
     *
     * @throws NoSuchFieldException   if the name of {@link Bank#instance} was changed
     * @throws IllegalAccessException never
     */
    @AfterAll
    static void tearDown() throws NoSuchFieldException, IllegalAccessException {
        resetInstance();
    }

    /**
     * Reset the Bank instance by setting it to null to have equal conditions before each test.
     * Each test will set up their own bank instance as they need.
     * The next call to {@link Bank#getInstance()} will set it again.
     *
     * @throws NoSuchFieldException   if the name of {@link Bank#instance} was changed
     * @throws IllegalAccessException never
     */
    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        resetInstance();
    }

    /**
     * Check whether a non null instance of Bank is returned and it is the same every time.
     */
    @Test
    void getInstance() {
        Bank firstInstance = Bank.getInstance();
        assertNotNull(firstInstance);

        Bank secondInstance = Bank.getInstance();
        assertSame(firstInstance, secondInstance);
    }

    /**
     * Check whether adding a customer adds them and exactly them
     * and an id is set to the customer after adding them to the bank.
     */
    @Test
    void addCustomer() {
        List<Customer> customers;
        Bank bank = Bank.getInstance();

        Customer dummyCustomer = CustomerTest.getDummyCustomer();
        // new customer should not have a customer id
        // because its set by addCustomer
        assertThrows(IllegalStateException.class, dummyCustomer::getCustomerId);

        bank.addCustomer(dummyCustomer);
        customers = bank.getCustomers();
        // Never return null
        assertNotNull(customers);

        // The one added customer should be there
        assertEquals(customers.size(), 1);
        assertSame(customers.get(0), dummyCustomer);

        // Should not throw a IllegalStateException because id has been added
        dummyCustomer.getCustomerId();

        // Should not add the same customer more than once
        bank.addCustomer(dummyCustomer);
        assertEquals(customers.size(), 1);
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
     * Check whether the added accounts of a customer are returned.
     */
    @Test
    void getCustomerAccounts() {
        Bank bank = Bank.getInstance();
        List<Account> customerAccounts;
        Customer dummyCustomer = CustomerTest.getDummyCustomer();
        Customer dummyCustomer2 = CustomerTest.getDummyCustomer();

        // should not contain any accounts before adding any
        customerAccounts = bank.getCustomerAccounts(dummyCustomer);
        assertNotNull(customerAccounts);
        assertEquals(customerAccounts.size(), 0);
        customerAccounts = bank.getCustomerAccounts(dummyCustomer2);
        assertNotNull(customerAccounts);
        assertEquals(customerAccounts.size(), 0);

        bank.addCustomer(dummyCustomer);
        Account account1 = dummyCustomer.setupAccount(CurrentAccount.class);
        Account account2 = dummyCustomer.setupAccount(CurrentAccount.class);
        assertEquals(customerAccounts.size(), 2);
        bank.addCustomer(dummyCustomer2);
        Account account3 = dummyCustomer.setupAccount(CurrentAccount.class);
        Account account4 = dummyCustomer.setupAccount(CurrentAccount.class);

        // should contain those and only those accounts added
        customerAccounts = bank.getCustomerAccounts(dummyCustomer);
        assertEquals(customerAccounts.size(), 4);
        assertTrue(customerAccounts.contains(account1));
        assertTrue(customerAccounts.contains(account2));
        assertTrue(customerAccounts.contains(account3));
        assertTrue(customerAccounts.contains(account4));
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
     * Test both methods {@link Bank#getTransactions()} and {@link Bank#applyTransaction(Transaction)}.
     *
     * @throws TransactionFailedException if the transaction has failed
     */
    @Test
    void testTransaction() throws TransactionFailedException {
        Bank bank = Bank.getInstance();
        final boolean[] applied = {false};
        Transaction transaction = new Transaction(new Money(1)) {
            @Override
            public void apply() throws TransactionFailedException {
                applied[0] = true;
            }

            @Override
            public String toString() {
                return null;
            }
        };
        assertNotNull(bank.getTransactions());
        assertEquals(bank.getTransactions().size(), 0);
        bank.applyTransaction(transaction);
        assertTrue(applied[0]);
        assertEquals(bank.getTransactions().size(), 1);
        assertSame(bank.getTransactions().get(0), transaction);
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
        // new customer should not have a account id
        // because its set by addAccount
        assertThrows(IllegalStateException.class, dummyAccount::getAccountId);

        bank.addAccount(dummyAccount);
        accounts = bank.getAccounts();
        // Never return null
        assertNotNull(accounts);

        // The one added account should be there
        assertEquals(accounts.size(), 1);
        assertSame(accounts.get(0), dummyAccount);

        // Should not throw a IllegalStateException because id has been added
        dummyAccount.getAccountId();

        // Should not add the same account more than once
        bank.addAccount(dummyAccount);
        assertEquals(accounts.size(), 1);
    }

    /**
     * Check if money can be deposited to accounts that exist.
     * Only check if the correct transaction gets created and added to the log.
     * To check if the money actually has been added is responsibility of the Transaction tests.
     */
    @Test
    void deposit() {
        Bank bank = Bank.getInstance();
        assertAll(
                // Should not be possible to deposit money into an account that does not exist.
                () -> assertThrows(TransactionFailedException.class, () -> {
                    Deposit deposit = new Deposit(new Money(1), AccountTest.getDummyAccount());
                    bank.applyTransaction(deposit);
                }),
                () -> {
                    Account account = AccountTest.getDummyAccount();
                    Money amount = new Money(1);
                    bank.addAccount(account);
                    bank.deposit(account.getAccountId(), amount);

                    // Transaction with the correct values should be added to the transaction log
                    Deposit transaction = (Deposit) bank.getTransactions().get(0);
                    assertEquals(transaction.getAmount(), amount);
                    assertSame(transaction.getCreditor(), account);
                    // Transaction should be applied
                    assertNotEquals(transaction.getStatus(), Transaction.Status.FAILED);
                }
        );
    }

    /**
     * Test whether a (private) method of Bank returns only unique objects and they are instance of a class.
     *
     * @param methodName the method to check
     * @param iterations how many results to check
     * @param c          which class the return values should be instance of
     * @throws NoSuchMethodException     if the methodName does not exist on Bank
     * @throws InvocationTargetException if the called method throws an exception
     * @throws IllegalAccessException    never
     * @throws ClassCastException        if the method does not return an object of type c
     */
    private <T> void returnUniqueTest(String methodName, int iterations, Class<T> c)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Bank bank = Bank.getInstance();
        Method generateAvailableId = Bank.class.getDeclaredMethod(methodName, c);

        generateAvailableId.setAccessible(true);

        Set<T> set = new HashSet<>();
        for (int i = 0; i < iterations; i++) {
            T id = (T) generateAvailableId.invoke(bank, null);
            set.add(id);
        }
        assertEquals(set.size(), iterations);
    }

    /**
     * Verify that all returned account ids are unique.
     *
     * @throws NoSuchMethodException     if the generateAvailableAccountId does not exist on Bank
     * @throws InvocationTargetException if the called method throws an exception
     * @throws IllegalAccessException    never
     */
    @Test
    void generateAvailableAccountId() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        returnUniqueTest("generateAvailableAccountId", 100, AccountId.class);
    }

    /**
     * Verify that all returned customer ids are unique.
     *
     * @throws NoSuchMethodException     if the generateAvailableAccountId does not exist on Bank
     * @throws InvocationTargetException if the called method throws an exception
     * @throws IllegalAccessException    never
     */
    @Test
    void generateAvailableCustomerId() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        returnUniqueTest("generateAvailableCustomerId", 100, CustomerId.class);
    }
}
