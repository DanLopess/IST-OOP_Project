package sth.core;

import sth.core.exception.BadEntryException;

public abstract class Person {
	private int _id;
	private String _name;
	private String _phoneNumber;

	/*
	* Person - class constructor
	*/
	public Person (int id, String name, String phoneNumber) throws BadEntryException {
		if( !(id >= 100000 && id <= 999999) ){
			throw new BadEntryException("Invalid id " + id);
		}
		_id = id;
		_name = name;
		_phoneNumber = phoneNumber;
	}

	/*
	* setPhoneNumber - changes Person's phone number
	*/
	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	/*
	* Returns person's name
	*/
	public String getName() {
		return _name;
	}

	/*
	* getId - Returns person's id
	*/
	public int getId() {
		return _id;
	}

	/*
	* getPhoneNumber - Returns person's phone number
	*/
	public String getPhoneNumber() {
		return _phoneNumber;
	}

    @Override
    public String toString() {
        return this._id + "|" + this._name;
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
