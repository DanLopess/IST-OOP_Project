package sth.core;

import sth.core.exception.BadEntryException;
import java.util.List;
import java.util.ArrayList;

public abstract class Person {
	private int _id;
	private String _name;
	private int _phoneNumber;

	/*
	* Person - class constructor
	*/
	public Person (int id, String name, int phoneNumber) throws BadEntryException {
		if (id >= 100000 && id <= 999999) {
			_id = id;
			_name = name;
			_phoneNumber = phoneNumber;
		} else{
			throw new BadEntryException("Invalid id " + id);
		}
	}

	/*
	* setPhoneNumber - changes Person's phone number
	*/
	public void setPhoneNumber(int phoneNumber) {
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
	public int getPhoneNumber() {
		return _phoneNumber;
	}

	/* 
	* toString - 'Compiles' person's info into a String 
	*/
	public abstract String toString();

	/**
	 * Parses the context information for a person from the import file.
	 * This method defines the default behavior: no extra information is needed
	 * thus it throws the exception.
	 **/
	void parseContext(String context, School school) throws BadEntryException {
		throw new BadEntryException("Should not have extra context: " + context);
	}
}
