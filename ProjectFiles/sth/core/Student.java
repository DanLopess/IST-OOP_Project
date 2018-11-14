package sth.core;

import sth.core.exception.BadEntryException;

public class Student extends Person {
	private boolean isRepresentative;

	public Student (int id, String name, int phoneNumber, boolean isR) throws BadEntryException {
		if (isR) {
			isRepresentative = true;
		}
		super(id, name, phoneNumber);
	}

	Course getCourse() {

	}

	void addDiscipline(Discipline d) {

	}

	void setRepresentative(boolean rep) {
		
	}



@Override
	String toString () {

	}

  void parseContext(String lineContext, School school) throws BadEntryException {
    String components[] =  lineContext.split("\\|");

    if (components.length != 2)
      throw new BadEntryException("Invalid line context " + lineContext);

    if (_course == null) {
      _course = school.parseCourse(components[0]);
      _course.addStudent(this);
    }

    Discipline dis = _course.parseDiscipline(components[1]);

    dis.enrollStudent(this);
  }
}
