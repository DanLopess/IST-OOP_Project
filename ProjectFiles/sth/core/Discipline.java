package sth.core;

import sth.core.Survey;
import sth.core.Survey;
import sth.core.Course;
import sth.core.Teacher;
import sth.core.Project;
import sth.core.Student;
import sth.core.exception.NoSuchProjectIdException;
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

	List<String> getProjectSubmissions(String pName) throws NoSuchProjectIdException {
		Project p = this.getProject(pName);
		List<String> submissionsToString = new ArrayList<String>();

		submissionsToString.add(_name + " - " + pName);
		//Convert HashMap(from projectSubmissions) to TreeMap.It will be sorted in natural order. (by id)
		Map<Integer,Submission> usersTree = new TreeMap<Integer,Submission>(p.getSubmissions()); 

		Iterator<Map.Entry<Integer, Submission>> entries = usersTree.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<Integer, Submission> entry = entries.next();
			submissionsToString.add(entry.getValue().toString());
		}

		return submissionsToString;
	}

	List<String> surveyToString(String pName) throws NoSuchProjectIdException {
		Project p = this.getProject(pName);
		Survey s = p.getSurvey();
		List<String> results = new ArrayList<String>();
		results.add(_name + " - " + p.getName());
		// TODO show different survey info for diff states
		return results;
	}

	public String toString() {
		return ("* " + _course.getName() + " - " + _name);
	}
}
