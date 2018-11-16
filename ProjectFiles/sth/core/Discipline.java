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

	Project getProject(String name) {
		Iterator<Project> iterator = _projects.iterator();
		Project p;

		while(iterator.hasNext()) {
			p = iterator.next();
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	Teacher getTeacher(String name)  {
		Iterator<Map.Entry<Integer, Teacher>> entries = _teachers.entrySet().iterator();

		while (entries.hasNext()) {
		    Map.Entry<Integer, Teacher> entry = entries.next();
		    if(entry.getValue().getName().equals(name)) {
					return entry.getValue();
				}
		}
		return null;

	}

	Student getStudent (String name) {
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

	void addTeacher (Teacher t) {
		_teachers.put(t.getId(), t);
	}

	void enrollStudent (Student s) {
		if (_students.size() < _capacity) {
			_students.put(s.getId(), s);
		}
	}

	void createProject(String name, String description) {
		Project p = new Project(name, description);
		_projects.add(p);
	}

	public String toString() {
		return ("* " + _course.getName() + "" + "- " + _name);
	}
}
