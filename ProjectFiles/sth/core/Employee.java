package sth.core;

public class Employee extends Person {

	public Person (int id, String name, int phoneNumber) {
		super(id,name,phoneNumber);
	}

	@Override
		String toString () {
			return "FUNCIONÁRIO|"+ super._id + "|" + super._name;
		}
}
