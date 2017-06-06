package banking.backend.accounts;

public class AccountId {
    /**
     * The sum total of Places in the AccountId
     */
    private static final int COUNT_OF_PLACES = 9;

    /**
     * The count of check digits at the end of the AccountId
     */
    private static final int COUNT_OF_CHECK_DIGITS = 2;

    /**
     * Checksum Modulo
     */
    private static final int MODULO_CHECKSUM = 97;

    private int savedId;

    /**
     * Generates a random new AccountId
     */
    public AccountId() {
        int numberWithOutChecksum = (int) (Math.random() * Math.pow(10, COUNT_OF_PLACES - COUNT_OF_CHECK_DIGITS));
        numberWithOutChecksum = (numberWithOutChecksum * 100) % ((int) Math.pow(10, COUNT_OF_PLACES));
        int moduloBefore = numberWithOutChecksum % MODULO_CHECKSUM;
        savedId = numberWithOutChecksum + MODULO_CHECKSUM + 1 - ((numberWithOutChecksum % MODULO_CHECKSUM));
        if (savedId % MODULO_CHECKSUM != 1) {
            int moduloAfter = numberWithOutChecksum % MODULO_CHECKSUM;
            throw new RuntimeException("This shouldn't happen " + moduloBefore + ", " + moduloAfter);
        }
    }


    /**
     * Must become a nine digit number that contains a 2 check digits
     *
     * @param id the String representation of the AccountId
     * @throws IllegalArgumentException if the id given isn't a valid AccountId
     */
    public AccountId(String id) {
        try {
            savedId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The accountId can only contain numbers", e);
        }
        if (savedId % MODULO_CHECKSUM != 1) {
            throw new IllegalArgumentException("The accountId ist invalid please check for typos.");
        }
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the {@code hashCode} method
     * must consistently return the same integer, provided no information
     * used in {@code equals} comparisons on the object is modified.
     * This integer need not remain consistent from one execution of an
     * application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     * method, then calling the {@code hashCode} method on each of
     * the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     * according to the {@link java.lang.Object#equals(java.lang.Object)}
     * method, then calling the {@code hashCode} method on each of the
     * two objects must produce distinct integer results.  However, the
     * programmer should be aware that producing distinct integer results
     * for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java&trade; programming language.)
     *
     * @return a hash code value for this object.
     * @see java.lang.Object#equals(java.lang.Object)
     * @see java.lang.System#identityHashCode
     */
    @Override
    public int hashCode() {
        return (int) (savedId / Math.pow(10, COUNT_OF_CHECK_DIGITS));
    }

    /**
     * Check if two account ids are the same.
     *
     * @param obj other account id to compare to
     * @return equivalence
     */
    @Override
    public boolean equals(Object obj) {
        return !(obj == null || !(obj instanceof AccountId)) && savedId == ((AccountId) obj).savedId;
    }

    /**
     * Returns the String Representation of the AccountId with its checksum in it.
     *
     * @return String
     */
    @Override
    public String toString() {
        return Integer.toString(savedId);
    }
}
