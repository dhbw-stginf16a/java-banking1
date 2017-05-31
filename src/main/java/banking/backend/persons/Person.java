package banking.backend.persons;

import banking.backend.DateTime;

/**
 * A person affiliated with the bank.
 * Can be a {@link Customer} or a guardian of an underage(&le;16) customer.
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
     * Constructs a new person with all attributes defined.
     * The date of birth cannot lie in the future.
     *
     * @param name            the name of the person
     * @param address         the address of birth of the person
     * @param birthdate       the date of birth of the person
     * @param telephoneNumber the telephoneNumber of birth of the person
     * @throws IllegalArgumentException if one argument (except the telephone number) is null or empty
     */
    public Person(String name, String address, DateTime birthdate, String telephoneNumber) {
        if (name == null || address == null || birthdate == null || name.isEmpty() || address.isEmpty()) {
            throw new IllegalArgumentException("None of the parameters can be null.");
        }
        if (birthdate.getAge() < 0) {
            throw new IllegalArgumentException("The holder's birthday cannot lie in the future.");
        }
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Calculates the current age of the person.
     *
     * @return the age of the person
     */
    public int getAge() {
        return birthdate.getAge();
    }

    /**
     * Get the person's name.
     *
     * @return the person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the person's address.
     *
     * @return the person's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get the person's birthdate.
     *
     * @return the person's birthdate
     */
    public DateTime getBirthdate() {
        return birthdate;
    }

    /**
     * Get the person's telephone number.
     *
     * @return the person's telephone number
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }
}
