package sth.core;

import sth.core.Student;
import sth.core.Discipline;
import sth.core.exception.BadEntryException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class Course {
	private String _name;
	private List<Discipline> _disciplines;
	private Map<Integer, Student> _students;
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
			_disciplines.add(d);
		} else{
			throw new BadEntryException("Too many disciplines");
		}
	}

	void addStudent (Student s) throws BadEntryException {
		if(_students.size() <= 200) {
			_students.put(s.getId(), s);
		} else{
			throw new BadEntryException("Too many students");
		}
	}

	boolean addRepresentative (Student s) {
		if(_representatives.size() < 7) {
			try {
				_representatives.add(s);
				return true;
			} catch (BadEntryException bde) {
				System.err.println("Invalid entry");
			}
		}
		return false;
	}

	boolean removeRepresentative (Student s) {
		_representatives.remove(s); // FIXME this doesnt work
		return false;
	}


	Discipline parseDiscipline (String name) {
		Iterator<Discipline> iterator = _disciplines.iterator();

	}

	Student parseRepresentative (String name) {
		Iterator<Student> iterator = _representatives.iterator();

	}

	Student parseStudent (String name) {
		// cant use iterator like this Iterator<Student> iterator = _students.iterator();
		Iterator<Map.Entry<Integer, Student>> entries = _students.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry<Integer, Student> entry = entries.next();
		    if(entry.getValue().getName().equals(name)) {
					return entry.getValue();
				}
		}
		return null;
	}
}
