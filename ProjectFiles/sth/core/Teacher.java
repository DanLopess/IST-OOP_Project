package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.Course;
import sth.core.Discipline;
import sth.core.Project;
import sth.core.School;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;

/**
 * The Teacher Class
 */
public class Teacher extends Person {
	private List<Discipline> _disciplines;

	/**
	* Teacher's class constructor
	* @param id Teacher's id
	* @param name Teacher's name
	* @param phoneNumber Teacher's phoneNumber
	 */
	public Teacher (int id, String name, int phoneNumber) throws BadEntryException {
		super(id, name, phoneNumber);
		_disciplines = new ArrayList<Discipline>();
	}

	Discipline getDiscipline(String name) throws NoSuchDisciplineIdException {
		Iterator<Discipline> iterator = _disciplines.iterator();
		Discipline d;
		while(iterator.hasNext()) {
			d = iterator.next();
			if(d.getName().equals(name)) {
				return d;
			}
		}
		throw new NoSuchDisciplineIdException("No such discipline: " + name);
	}

	List<Discipline> getAllDisciplines() {
		return _disciplines;
	}

	/**
	 * creates a project to a certain discipline
	 * @param dName name of the discipline
	 * @param pName name of the project
	 * @param description description of the project
	 */
	void createProject(String dName, String pName) {
		Iterator<Discipline> iterator = _disciplines.iterator();
		Discipline d;

		while(iterator.hasNext()) {
			d = iterator.next();
			if (d.getName().equals(dName)) {
				d.createProject(pName);
			}
		}
	}

	/**
	 * @param d is the discipline being added to teacher's _disciplines
	 */
	void addDiscipline(Discipline d) {
		_disciplines.add(d);
		d.addTeacher(this);
	}


@Override
	public String toString () {
		List<String> toString = new ArrayList<String>();
		
		toString.add("DOCENTE|"+ super.getId() + "|" + super.getName());

		Iterator<Discipline> iterator = _disciplines.iterator();
		Discipline d;
		while(iterator.hasNext()) {
			d = iterator.next();
			toString.add(d.toString());
		}

		Collections.sort(toString);
		Iterator<String> iterator2 = toString.iterator();
		String s = new String();
		while(iterator.hasNext()) {
			iterator.next();
			s = s + iterator.next() + "\n";
		}
		return s;
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
