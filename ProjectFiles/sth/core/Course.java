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
		_students = new HashMap<Integer, Student>(); // key is students' id for easy mapping
		_representatives = new ArrayList<Student>();
	}

	public String getName() {
		return _name;
	}

	void addDiscipline (Discipline d) {
		if(!(_disciplines.contains(d))) {
			_disciplines.add(d);
		}
	}

	void addStudent (Student s) {
		if(_students.size() < 200) { 
			_students.put(s.getId(), s);
		}
	}

	boolean addRepresentative (Student s) { 
		if(_representatives.size() < 7 && !(_representatives.contains(s))) {
			_representatives.add(s);
			return true;
		}
		return false;
	}

	void removeRepresentative (Student st) {
		Iterator<Object> iterator  = _representatives.iterator();
		
		while (iterator.hasNext()) {
			Student s = i.next();
			if(s.getId() == st.getId())
				iterator.remove();
		}
	}


	Discipline parseDiscipline (String name) {
		Iterator<Discipline> iterator = _disciplines.iterator();
		Discipline d;
		while(iterator.hasNext()) {
			d = iterator.next();
			if(d.getName().equals(name)) {
				return d;
			}
		}
		return null;
	}

	Student parseRepresentative (String name) {
		Iterator<Student> iterator = _representatives.iterator();
		Student s;
		while(iterator.hasNext()) {
			s = iterator.next();
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

	Student parseStudent (String name) {
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
