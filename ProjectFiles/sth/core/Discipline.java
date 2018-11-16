package sth.core;

import sth.core.Course;
import sth.core.Teacher;
import sth.core.Project;
import sth.core.Student;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchPersonIdException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

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
		/*if it didnt return, throw exception */
		throw new NoSuchProjectIdException("No such project name ");

	}

	Teacher getTeacher(String name) throws NoSuchPersonIdException {
		Iterator<Teacher> iterator = _teachers.iterator();
		Teacher t;

		while(iterator.hasNext()) {
			t = iterator.next();
			if (t.getName().equals(name)) {
				return t;
			}
		}
		/*if it didnt return, throw exception */
		throw new NoSuchPersonIdException("No such person name ");
	}

	Student getStudent (String name) throws NoSuchPersonIdException {
		Iterator<Student> iterator = _students.iterator();
		Student s;

		while(iterator.hasNext()) {
			s = iterator.next();
			if (s.getName().equals(name)) {
				return s;
			}
		}

		/*if it didnt return, throw exception */
		throw new NoSuchPersonIdException("No such person name " );
	}

	void addTeacher (Teacher t) {
		_teachers.add(t);
	}

	void enrollStudent (Student s) {
		_students.add(s);
	}

	void createProject(String name, String description) {
		Project p = new Project(name, description);
		_projects.add(p);
	}

	public String toString() {
		return (_course.toString() + "|" + _name);
	}

}
