package banking.backend.accounts;

import banking.backend.Money;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Provides several methods for testing all Loans.
 * Created by guserav on 27.05.2017.
 */
class LoanTest {

    public static void testOverdraft(Loan toTest) {
        AccountTest.testOverdraft(toTest, true);
    }

    public static void testBorrowingInterest(Loan toTest) {
        AccountTest.testBorrowingInterest(toTest, false);
    }

    public static void testSavingInterest(Loan toTest) {
        AccountTest.testOverdraft(toTest, true);
    }

    public static void testReceiveInvoice(Loan toTest) {
        AccountTest.testReceiveInvoice(toTest);
    }

    public static void testDeposit(Loan toTest) {
        AccountTest.testDeposit(toTest);
    }

    public static void testSendInvoice(Loan toTest) {
        assertThrows(UnsupportedOperationException.class, () -> AccountTest.testSendInvoice(toTest));
    }

    public static void testWithdraw(Loan toTest) {
        assertThrows(UnsupportedOperationException.class, () -> AccountTest.testWithdraw(toTest));
    }

    public static void testInit(Loan toTest) {
        assertThrows(IllegalStateException.class, toTest::getBalance);

        assertThrows(IllegalArgumentException.class, () -> toTest.initAmount(new Money(0)));
        assertThrows(IllegalArgumentException.class, () -> toTest.initAmount(new Money(-1)));

        toTest.initAmount(new Money(10));

        assertThrows(IllegalStateException.class, () -> toTest.initAmount(new Money(1)));

        assertEquals(new Money(-10), toTest.getBalance());
    }
}