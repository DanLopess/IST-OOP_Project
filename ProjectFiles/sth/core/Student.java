package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;


/**
 * The Student class.
 */
public class Student extends Person {
	private boolean _isRepresentative;
	private List<Discipline> _disciplines;
	private Course _course;

	/**
	 * Student's class constructor
	 * @param id student's id
	 * @param name student's name
	 * @param phoneNumber student's phoneNumber
	 * @param rep boolean to indicate if student is a representative or not
	 *
	 */
	public Student (int id, String name, int phoneNumber, boolean rep) throws BadEntryException {
		super(id, name, phoneNumber);
		_isRepresentative = rep;
	}

	/**
	 * @return student's course
	 */
	Course getCourse () {
		return _course;
	}

	/**
	 * @param d adds a discipline to student's discipline List
	 */
	void addDiscipline (Discipline d) {
		if (_disciplines.size() < 6 && !(_disciplines.contains(d))) {
			_disciplines.add(d);
		}
	}

	Discipline getDiscipline(String name) throws NoSuchDisciplineIdException {
		Iterator<Discipline> iterator = _disciplines.iterator();

		while(iterator.hasNext()) {
			Discipline d = iterator.next();
			if (d.getName().equals(name))
				return d;
		}
		throw new NoSuchDisciplineIdException("Discipline not found: " + name);
	}

	/**
	 * @param rep boolean that indicates if student is or is not to be a representative
	 */
	void setRepresentative (boolean rep) {
		if (rep){
			if (_course.addRepresentative(this)) { // if able to add representative
				_isRepresentative = true;
			}
		} else { 
			_course.removeRepresentative(this);
			_isRepresentative = false;
		}
	}

	/**
	 * === Representative related methods ===
	 */

	/**
	 * @return boolean of whether student is a representative
	 */
	boolean isRepresentative () {
		return _isRepresentative;
	}

	void submitAnswerToSurvey () {
		// TODO in final version
	}

	void submitProject () {
		// TODO in final version
	}

@Override
	public String toString () {
		List<String> toString = new ArrayList<String>();
		if (_isRepresentative){
			toString.add("DELEGADO|"+ super.getId( )+ "|" + super.getName());
		} else {
			toString.add("ALUNO|"+ super.getId() + "|" + super.getName());
		}

		Iterator<Discipline> iterator = _disciplines.iterator();
		Discipline d;
		while(iterator.hasNext()) {
			d = iterator.next();
			toString.add("* " + _course.getName() + " - " + d.getName());
		}
		Collections.sort(toString);


		return "";
	}

	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] =  lineContext.split("\\|"); // TODO USE THIS TO SORT STRINGS IN APP

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
