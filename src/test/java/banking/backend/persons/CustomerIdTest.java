package banking.backend.persons;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * This tests the functionality of CustomerId.
 * Created by zeiske on 29.05.2017.
 */
class CustomerIdTest {
    @RepeatedTest(10)
    void testHashCode() {
        CustomerId instance = new CustomerId();
        CustomerId instanceCopy = new CustomerId(instance.toString());
        CustomerId otherCustomerId = new CustomerId();

        assertEquals(instance.hashCode(), instanceCopy.hashCode(), "The hashcode of to exactly same account IDs must be the same.");
        if (!instance.equals(otherCustomerId)) {
            assertNotEquals(otherCustomerId, instance, "The hashcode of " + otherCustomerId + "schould be different than " + instance + " hashcode");
        }
    }

    @RepeatedTest(10)
    void equals() {
        CustomerId instance = new CustomerId();
        CustomerId instanceCopy = new CustomerId(instance.toString());
        CustomerId otherCustomerId = new CustomerId();

        assertNotEquals(instance, otherCustomerId);
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
        assertEquals(id, new CustomerId(id).toString(), "Constructing a CustomerId out of the String should give you the same CustomerId as inputted");
    }

}