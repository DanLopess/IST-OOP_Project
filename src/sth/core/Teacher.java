package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.NoSuchProjectIdException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;

/**
 * The Teacher Class
 */
public class Teacher extends Person implements java.io.Serializable {

  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
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
		throw new NoSuchDisciplineIdException("");
	}

	List<Discipline> getAllDisciplines() {
		return _disciplines;
	}

	/**
	 * creates a project to a certain discipline
	 * @param dName name of the discipline
	 * @param pName name of the project
	 */
	void createProject(String dName, String pName) throws NoSuchDisciplineIdException, DuplicateProjectIdException {
		Iterator<Discipline> iterator = _disciplines.iterator();
		Discipline d;

		while(iterator.hasNext()) {
			d = iterator.next();
			if (d.getName().equals(dName)) {
				d.createProject(pName);
				return;
			}
		}
		throw new NoSuchDisciplineIdException(dName);
	}

	/**
	 * @param d is the discipline being added to teacher's _disciplines
	 */
	private void addDiscipline(Discipline d) {
		_disciplines.add(d);
		d.addTeacher(this);
	}


@Override
	public String toString () {
		String toString = "DOCENTE|"+super.toString();

		List<Discipline> disciplines = new ArrayList<>(_disciplines);
		Collections.sort(disciplines);
		Iterator<Discipline> iterator = disciplines.iterator();
		while(iterator.hasNext()) {
			Discipline d = iterator.next();
			toString += "\n" + d.toString();
		}
		return toString;
	}

	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] =  lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		Course course = school.parseCourse(components[0]);
		Discipline discipline = course.parseDiscipline(components[1], course);
		addDiscipline(discipline);
		discipline.addTeacher(this);
	}
}
