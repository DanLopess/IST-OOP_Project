package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.Course;
import sth.core.Discipline;
import sth.core.School;
import java.util.ArrayList;
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
		if (_disciplines.size() < 6){
			_disciplines.add(d);
		}
	}

	/**
	 * @param rep boolean that indicates if student is or is not a representative
	 */
	void setRepresentative (boolean rep) {
		if (rep){
			if (_course.addRepresentative(this)) {
				_isRepresentative = true;
			}
		} else {
			_course.removeRepresentative(this);
			_isRepresentative = false;
		}
	}

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
		if (_isRepresentative){
			return "DELEGADO|"+ super.getId( )+ "|" + super.getName();
		} else {
			return "ALUNO|"+ super.getId() + "|" + super.getName();
		}
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
