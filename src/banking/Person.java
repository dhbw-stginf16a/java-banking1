package banking;

/**
 * A person affiliated with the bank.
 */
class Person {
	/**
	 * The persons name.
	 * The format 'Lastname, Firstname' is recommended.
	 * Does not have to be unique.
	 */
	public String name;

	/**
	 * The address of the person.
	 * For sending newsletters ;)
	 */
	public String address;

	/**
	 * The date of birth.
	 * Is used to check age
	 */
	public DateTime birthdate;

	/**
	 * The telephone number of the person.
	 */
	public String telephoneNumber;

	/**
	 * Constructs a basic person
	 *
	 * @param name the name of the person
	 * @param birthdate the date of birth of the person
	 */
	public Person(String name, DateTime birthdate) {

	}

	/**
	 * Calculates the current age of the person.
	 *
	 * @return the age of the person
	 */
	public int getAge () {
	    return -1;
	}

	/**
	 * Have this person deposit some money on an account.
	 *
	 * @param accountId the id of the account
	 * @param amount the amount to be deposited
	 */
	public void deposit (AccountId accountId, Money amount) {

	}

}
