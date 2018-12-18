package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.SurveyIdFinishedException;
import sth.core.exception.DuplicateSurveyIdException;
import sth.core.exception.OpeningSurveyIdException;
import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.FinishingSurveyIdException;
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
public class Student extends Person implements java.io.Serializable {

  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
	private boolean _isRepresentative;
	private List<Discipline> _disciplines;
	private List<Discipline> _repDisciplines;
	private Course _course;
	private List<Survey> _surveysAnswered;

	/**
	 * Student's class constructor
	 * @param id student's id
	 * @param name student's name
	 * @param phoneNumber student's phoneNumber
	 * @param rep boolean to indicate if student is a representative or not
	 *
	 */
	Student (int id, String name, int phoneNumber, boolean rep) throws BadEntryException {
		super(id, name, phoneNumber);
		_isRepresentative = rep;
		_disciplines = new ArrayList<Discipline>();
		_surveysAnswered = new ArrayList<Survey>();
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
	
	void submitAnswerToSurvey (String discipline, String pName, int hours, String comment) throws NoSuchDisciplineIdException, NoSuchProjectIdException, 
	NoSurveyIdException
	{
		Discipline d = this.getDiscipline(discipline);
		Project p = d.getProject(pName);
		Survey s = p.getSurvey();

		if (s != null) {
			p.answerSurvey(hours, comment);
			if (!_surveysAnswered.contains(s)) {
				if (p.studentSubmited(super.getId())) {
					if (p.getSurvey() != null) {
						p.answerSurvey(hours, comment);
					} else {
						throw new NoSurveyIdException("","");
					}
				} else {	
					throw new NoSuchProjectIdException(pName);
				}
				_surveysAnswered.add(s);
			} else {
				//DO nothing because has already answered survey
			}
		} else {
			throw new NoSurveyIdException("","");
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

	void createSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, DuplicateSurveyIdException
	{
		Discipline d = _course.getDiscipline(discipline); // can perform actions only if discipline belongs to representative's course
		Project p = d.getProject(pName);
		p.createSurvey();
		
	}

	void cancelSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, NonEmptySurveyIdException, NoSurveyIdException, SurveyIdFinishedException, OpeningSurveyIdException
	{
		Discipline d = _course.getDiscipline(discipline);
		d.cancelSurvey(pName);
	}

	void openSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException , NoSurveyIdException, OpeningSurveyIdException
	{
		Discipline d = _course.getDiscipline(discipline);
		Project p = d.getProject(pName);
		p.openSurvey();
	}

	void closeSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException , NoSurveyIdException, ClosingSurveyIdException
	{
		Discipline d = _course.getDiscipline(discipline);
		Project p = d.getProject(pName);
		p.closeSurvey();
	}

	void finishSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException , NoSurveyIdException, FinishingSurveyIdException
	{
		Discipline d = _course.getDiscipline(discipline);
		d.finishSurvey(pName);
	}

	String showSurveys(String discipline) throws NoSuchDisciplineIdException, NoSurveyIdException, NoSuchProjectIdException {
		Discipline d = getDiscipline(discipline);
		return d.showSurveys();
	}	

	@Override
	public String toString () {
		String toString;
		if (_isRepresentative){
			toString = "DELEGADO|"+ super.toString();
		} else {
			toString = "ALUNO|" + super.toString();
		}

		List<Discipline> disciplines = new ArrayList<>(_disciplines);
		Collections.sort(disciplines);
		Iterator<Discipline> iterator = disciplines.iterator();
		while(iterator.hasNext()) {
			Discipline d = iterator.next();
			toString += "\n* " + _course.getName() + " - " + d.getName();
		}
		return toString;
	}

	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] =  lineContext.split("\\|"); 

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		if (_course == null) {
			_course = school.parseCourse(components[0]);
			_course.addStudent(this);
			if (isRepresentative()) {
				_course.addRepresentative(this);
			}
		}

		Discipline dis = _course.parseDiscipline(components[1], _course);
		dis.enrollStudent(this);
		addDiscipline(dis);
	}
}
