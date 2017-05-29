package banking.backend.persons;

import banking.NotYetImplementedException;
import banking.backend.DateTime;

/**
 * A person affiliated with the bank.
 */
public class Person {
    /**
     * The persons name.
     * The format 'Lastname, Firstname' is recommended.
     * Does not have to be unique.
     */
    private String name;

    /**
     * The address of the person.
     * For sending newsletters ;)
     */
    private String address;

    /**
     * The date of birth.
     * Is used to check age
     */
    private DateTime birthdate;

    /**
     * The telephone number of the person.
     */
    private String telephoneNumber;

    /**
     * Constructs a basic person
     *
     * @param name            the name of the person
     * @param address         the address of birth of the person
     * @param birthdate       the date of birth of the person
     * @param telephoneNumber the telephoneNumber of birth of the person
     * @throws IllegalArgumentException if one argument (except the telephone number) is null
     */
    public Person(String name, String address, DateTime birthdate, String telephoneNumber) {
        throw new NotYetImplementedException();
    }

    /**
     * Calculates the current age of the person.
     *
     * @return the age of the person
     */
    public int getAge() {
        throw new NotYetImplementedException();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public DateTime getBirthdate() {
        return birthdate;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }
}
