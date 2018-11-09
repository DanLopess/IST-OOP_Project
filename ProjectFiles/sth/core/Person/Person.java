package sth.core.Person;

public abstract class Person {
	private int _id;
	private String _name;
	private int _phoneNumber;

	public Person (int id, String name, int phoneNumber) throws BadEntryException {
			if (id >= 100000 && id <= 999999 && phoneNumber) {
				_id = id;
				_name = name;
				_phoneNumber = phoneNumber
			} else {
				if (id >= 100000 && id <= 999999) {
					throw new BadEntryException("Invalid id " + id);
				}
				if () {

				}
			}
	}

	/* setPhoneNumber - changes Person's phone number */
	public void setPhoneNumber(int phoneNumber) throws BadEntryException{
			if () {

			}
	}
	/* Returns person's name */
	public String getName() {
		String tempName = new String(_name); /* Auxiliary variable */
		return tempName;
	}

	/* getId - Returns person's id */
	public int getId() {
		int tempId; /* Auxiliary variable */
		tempId = _id;
		return tempId;
	}

	/* getPhoneNumber - Returns person's phone number */
	public int getPhoneNumber() {
		int tempPhone = new int(_phoneNumber); /* Auxiliary variable */
		return tempPhone;
	}

	/* toString - 'Compiles' person's info into a String */
	public String toString() {

	}

	/**
   * Parses the context information for a person from the import file.
   * This method defines the default behavior: no extra information is needed
   * thus it throws the exception.
   **/
  void parseContext(String context, School school) throws BadEntryException {
    throw new BadEntryException("Should not have extra context: " + context);
  }
}



}
