package sth.core;

import sth.core.exception.BadEntryException;

public class Employee extends Person {

	public Employee (int id, String name, int phoneNumber) throws BadEntryException {
		super(id,name,phoneNumber);
	}

	@Override
		public String toString () {
			return ("FUNCIONÁRIO|"+ super.getId() + "|" + super.getName());
		}
}
