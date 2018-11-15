package sth.core;

import sth.core.Student;
import sth.core.Discipline;
import sth.core.exception.BadEntryException;
import java.util.ArrayList;
import java.util.List;
import java.util.iterator;
import java.util.HashMap;
import java.util.Map;

public class Course {
	private String _name;
	private List<Discipline> _disciplines;
	private Map<Student> _students;
	private List<Student> _representatives;


	public Course (String name) {
		_name = name;
		_disciplines = new ArrayList<Discipline>();
		_students = new HashMap<Integer, Student>(); // in the integer we will use students id
		_representatives = new ArrayList<Student>();
	}

	public String getName() {
		return _name;
	}

	void addDiscipline (Discipline d) throws BadEntryException {
		if(_disciplines.size() < 6) {
			_disciplines.add(s);
		} else{
			throw new BadEntryException("Too many disciplines");
		}
	}

	void addStudent (Student s) throws BadEntryException {
		if(_students.size() <= 200) {
			_students.add(s);
		} else{
			throw new BadEntryException("Too many students");
		}
	}

	boolean addRepresentative (Student s) {
		if(_representatives.size() < 7) {
			try {
				_representatives.add(s);
				return true;
			} catch (BadEntryException) {
				System.err.println("Invalid entry");
			}
		}
		return false;
	}

	boolean removeRepresentative (Student s) {
		_representatives.remove(s); // FIXME this doesnt work
	}


	Discipline parseDiscipline (String name) {
		Iterator<Discipline> iterator = new Iterator<Discipline>();

	}

	Student parseRepresentative (String name) {
		Iterator<Student> iterator = new Iterator<Student>();

	}

	Student parseStudent (String name) {
		Iterator<Student> iterator = new Iterator<Student>();

	}

	public String toString() {
		return _course.toString() + "|" + _name;
	}
}
