package sth.core;

public class Employee extends Person {

	public Employee (int id, String name, int phoneNumber) {
		super(id,name,phoneNumber);
	}

	@Override
		public String toString () {
			return ("FUNCIONÁRIO|"+ super.getId() + "|" + super.getName());
		}
}
