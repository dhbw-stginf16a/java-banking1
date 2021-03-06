package banking;

/**
 * Throw if a method has not yet been implemented.
 * These implementations have to be added in the near future for the program to work!
 */
public class NotYetImplementedException extends RuntimeException {
    public NotYetImplementedException() {
        super("This function is not yet implemented");
    }
}
