package sth.core.Person;

public abstract class Person {
	private int _id;
	private String _name;
	private int _phoneNumber;

	public Person (int id, String name, int _phoneNumber) throws BadEntryException{
			if (id >= 100000 && id <= 999999 && _phoneNumber) {

			} else {
				if (id >= 100000 && id <= 999999) {
					throw new BadEntryException("Invalid id \n");
				}
				if () {

				}
			}
	}

	public void setPhoneNumber(int phoneNumber) throws BadEntryException{
			if()
	}

	public String getName() {
		private String tempName = new String(_name); /* Auxiliary variable */
		return tempName;
	}

	public int getId() {
		private int tempId; /* Auxiliary variable */
		tempId = _id;
		return tempId;
	}
	
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
