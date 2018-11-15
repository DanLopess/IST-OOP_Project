package sth.core;

import sth.core.exception.BadEntryException;

public class Teacher extends Person {

	public Teacher (int id, String name, int phoneNumber) throws BadEntryException {
		isRepresentative = false;
		super(id, name, phoneNumber);
	}



@Override
	public String toString () {
		return "DOCENTE|"+ super._id + "|" + super._name;
	}

  void parseContext(String lineContext, School school) throws BadEntryException {
    String components[] =  lineContext.split("\\|");

    if (components.length != 2)
      throw new BadEntryException("Invalid line context " + lineContext);

    Course course = school.parseCourse(components[0]);
    Discipline discipline = course.parseDiscipline(components[1]);

    discipline.addTeacher(this);
  }
}
