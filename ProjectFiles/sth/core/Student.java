package sth.core;

public class Student extends Person {
	private isRepresentative;
	


@Override
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
