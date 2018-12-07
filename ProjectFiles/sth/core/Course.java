package sth.core;

import java.text.Normalizer;
import java.util.*;

public class Course implements Comparable<Course> {
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
		Iterator<Student> iterator  = _representatives.iterator();
		
		while (iterator.hasNext()) {
			Student s = iterator.next();
			if(s.getId() == st.getId())
				iterator.remove();
		}
	}


	Discipline parseDiscipline(String name, Course course) {
		Iterator<Discipline> iterator = _disciplines.iterator();
		Discipline d;
		while(iterator.hasNext()) {
			d = iterator.next();
			if(d.getName().equals(name)) {
				return d;
			}
		}
		Discipline newDiscipline = new Discipline(name, 20, course);
		course.addDiscipline(newDiscipline);
		_disciplines.add(newDiscipline);
		return newDiscipline;
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

	@Override
	public int compareTo(Course o) {
		String o1 = Normalizer.normalize(getName(), Normalizer.Form.NFD);
		String o2 = Normalizer.normalize(o.getName(), Normalizer.Form.NFD);
		return o1.compareTo(o2);
	}
}
