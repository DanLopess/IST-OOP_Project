package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.Course;
import sth.core.Discipline;
import sth.core.School;
import java.util.ArrayList;
import java.util.List;
import java.util.iterator;

public class Teacher extends Person {
	private List<Discipline> _disciplines;

	public Teacher (int id, String name, int phoneNumber) throws BadEntryException {
		isRepresentative = false;
		super(id, name, phoneNumber);
		_disciplines = new ArrayList<Discipline>();
	}

	/**
   * creates a project to a certain discipline
   * @param dName name of the discipline
   * @param pName name of the project
	 * @param description description of the project
	 *
   */
	void createProject(String dName, String pName, String description) {

	}

	void addDiscipline(Discipline d) {
		_disciplines.add(d);
		d.addTeacher(this);
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

		addDiscipline(discipline);
    discipline.addTeacher(this);
  }
}
