package banking.backend.accounts;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * This tests the functionality of AccountId.
 * Created by zeiske on 29.05.2017.
 */
class AccountIdTest {
    @RepeatedTest(10)
    void testHashCode() {
        AccountId instance = new AccountId();
        AccountId instanceCopy = new AccountId(instance.toString());
        AccountId otherAccountId = new AccountId();

        assertEquals(instance.hashCode(), instanceCopy.hashCode(), "The hashcode of to exactly same account IDs must be the same.");
        if (!instance.equals(otherAccountId)) {
            assertNotEquals(otherAccountId, instance, "The hashcode of " + otherAccountId + "schould be different than " + instance + " hashcode");
        }
    }

    @RepeatedTest(10)
    void equals() {
        AccountId instance = new AccountId();
        AccountId instanceCopy = new AccountId(instance.toString());
        AccountId otherAccountId = new AccountId();

        assertNotEquals(instance, otherAccountId);
        assertEquals(instance, instanceCopy);
    }

    @RepeatedTest(100)
    void constructorString() {
        int numberWithOutChecksum = (int) (Math.random() * 10000000);
        numberWithOutChecksum = (numberWithOutChecksum * 100) % 1000000000;
        int moduloBefore = numberWithOutChecksum % 97;
        numberWithOutChecksum = numberWithOutChecksum + 98 - ((numberWithOutChecksum % 97));
        if (numberWithOutChecksum % 97 != 1) {
            int moduloAfter = numberWithOutChecksum % 97;
            throw new RuntimeException("This schouldn't happen " + moduloBefore + ", " + moduloAfter);
        }
        String id = "" + numberWithOutChecksum;
        assertEquals(id, new AccountId(id).toString(), "Constructing a AccountID out of the String should give you the same AccountID as inputted");
    }

}