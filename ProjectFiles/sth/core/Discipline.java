package sth.core;

import sth.core.Survey;
import sth.core.Survey;
import sth.core.Course;
import sth.core.Teacher;
import sth.core.Project;
import sth.core.Student;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchPersonIdException;
import java.util.*;

public class Discipline {
	private String _name;
	private int _capacity;
	private Course _course;
	private Map<Integer, Teacher> _teachers;
	private Map<Integer, Student> _students;
	private List<Project> _projects;

	public Discipline (String name, int capacity, Course c) {
		_name = name;
		_capacity = capacity;
		_course = c;
		_teachers = new HashMap<Integer, Teacher>();
		_students = new HashMap<Integer, Student>();
		_projects = new ArrayList<Project>();
	}

	public String getName () {
		return _name;
	}

	int getCapacity () {
		return _capacity;
	}

	Course getCourse () {
		return _course;
	}

	Project getProject(String name) throws NoSuchProjectIdException {
		Iterator<Project> iterator = _projects.iterator();
		Project p;

		while(iterator.hasNext()) {
			p = iterator.next();
			if (p.getName().equals(name)) {
				return p;
			}
		}
		throw new NoSuchProjectIdException(name);
	}

	Teacher getTeacher(String name) {
		Iterator<Map.Entry<Integer, Teacher>> entries = _teachers.entrySet().iterator();

		while (entries.hasNext()) {
		    Map.Entry<Integer, Teacher> entry = entries.next();
		    if(entry.getValue().getName().equals(name)) {
				return entry.getValue();
			}
		}
		return null;
	}

	Student getStudent(String name) {
		Iterator<Map.Entry<Integer, Student>> entries = _students.entrySet().iterator();

		while (entries.hasNext()) {
		    Map.Entry<Integer, Student> entry = entries.next();
		    if(entry.getValue().getName().equals(name)) {
				return entry.getValue();
			}
		}
		return null;
	}

	Map<Integer, Student> getAllStudents() {
		return _students;
	}

	void addTeacher(Teacher t) {
		_teachers.putIfAbsent(t.getId(), t);
	}

	void enrollStudent(Student s) {
		if (_students.size() < _capacity) {
			_students.putIfAbsent(s.getId(), s);
		}
	}

	void createProject(String name) {
		Project p = new Project(name);
		_projects.add(p);
	}

	void closeProject(String name) throws NoSuchProjectIdException {
		Project p = this.getProject(name);
		p.close();
	}

	String getProjectSubmissions(String pName) throws NoSuchProjectIdException {
		Project p = this.getProject(pName);
		String submissionsToString = new String();

		submissionsToString = submissionsToString + _name + " - " + pName + "\n";
		//Convert HashMap(from projectSubmissions) to TreeMap.It will be sorted in natural order. (by id)
		Map<Integer,Submission> usersTree = new TreeMap<Integer,Submission>(p.getSubmissions()); 

		Iterator<Map.Entry<Integer, Submission>> entries = usersTree.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<Integer, Submission> entry = entries.next();
			submissionsToString = submissionsToString + entry.getValue().toString() + "\n";
		}

		return submissionsToString;
	}

	String surveyToString(String pName) throws NoSuchProjectIdException {
		Project p = this.getProject(pName);
		Survey s = p.getSurvey();
		String results = new String();
		results = results + _name + " - " + p.getName();
		results = results + s.toString();
		return results;
	}

	void submitAnswerToSurvey (String pName, int hours, String comment, int id) throws NoSuchProjectIdException, NoSurveyIdException
	{
		Project p = this.getProject(pName);
		if (p.studentSubmited(id)) {

		} else {	
			throw NoSuchProjectIdException(pName);
		}
		// TODO if student has submited... / if student has not answered to survey
		// create answer type
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
	public String toString() {
		return ("* " + _course.getName() + " - " + _name);
	}
}
