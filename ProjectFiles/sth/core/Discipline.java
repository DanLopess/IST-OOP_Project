package sth.core;

import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchProjectIdException;

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
		_teachers = new HashMap<>();
		_students = new HashMap<>();
		_projects = new ArrayList<>();
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

	Teacher getTeacher(String name)  throws NoSuchPersonIdException{
		Iterator<Map.Entry<Integer, Teacher>> entries = _teachers.entrySet().iterator();

		while (entries.hasNext()) {
		    Map.Entry<Integer, Teacher> entry = entries.next();
		    if(entry.getValue().getName().equals(name)) {
				return entry.getValue();
			}
		}
		throw new NoSuchPersonIdException(0);
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

	public String toString() {
		return ("* " + _course.getName() + " - " + _name);
	}
}
