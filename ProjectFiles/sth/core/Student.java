package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.Course;
import sth.core.Discipline;
import sth.core.School;
import sth.core.Survey;
import sth.core.SurveyState;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;


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
		_disciplines = new ArrayList<>();
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
		throw new NoSuchDisciplineIdException(name);
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

	void submitProject (String discipline, String pName, String message) throws NoSuchDisciplineIdException,
	NoSuchProjectIdException
	{
		Discipline dis = this.getDiscipline(discipline);
		Project prjct = dis.getProject(pName);
		Submission s = new Submission(message, this.getId());
		prjct.addSubmission(this.getId(), s);
	}

<<<<<<< HEAD
	void submitAnswerToSurvey (String discipline, String pName, int hours, String comment) throws NoSuchDisciplineIdException,
	NoSuchProjectIdException 
	{

		Discipline d = this.getDiscipline(discipline);
		d.submitAnswerToSurvey()
		// TODO if student has submited... / if student has not answered to survey / send user id so can check if has submitted
		// create answer type
=======
	void submitAnswerToSurvey (String dName, String pName, int hours, String comentario) throws NoSuchDisciplineIdException,
	NoSuchProjectIdException 
	{
		Discipline dis = this.getDiscipline(dName);
		Project project = dis.getProject(pName);
		Submission submission = new Submission(comentario, hours);
		project.addSubmission(this.getId(), submission);
>>>>>>> ac50d345f7eab14d593c25f1a164ac3e5a1984b5
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

	void createSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException 
	{
		//if... representative, call student function: createSurvey
	}

	void cancelSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException 
	{
		//if... representative, call student function: cancelSurvey...
	}

	void openSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException 
	{
		
	}

	void closeSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException 
	{
		
	}

	void finishSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException 
	{
		
	}

	String showSurveys(String discipline) throws NoSuchDisciplineIdException	{
		return null;
	}	

	@Override
	public String toString () {
		String toString;
		if (_isRepresentative){
			toString = "DELEGADO|"+ super.toString();
		} else {
			toString = "ALUNO|" + super.toString();
		}

		// Sao as disciplinas q teem de ser ordenadas e nao as Strings!!!
		Iterator<Discipline> iterator = _disciplines.iterator();
		while(iterator.hasNext()) {
			Discipline d = iterator.next();
			toString += "\n* " + _course.getName() + " - " + d.getName();
		}
		return toString;
	}

	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] =  lineContext.split("\\|"); // TODO USE THIS TO SORT STRINGS IN APP

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		if (_course == null) {
			_course = school.parseCourse(components[0]);
			_course.addStudent(this);
		}

		Discipline dis = _course.parseDiscipline(components[1], _course);
		dis.enrollStudent(this);
		addDiscipline(dis);
	}
}
