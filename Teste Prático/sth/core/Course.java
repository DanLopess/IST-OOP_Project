package sth.core;

import java.text.Normalizer;
import java.util.*;
import sth.core.exception.NoSuchDisciplineIdException;

public class Course implements Comparable<Course>, java.io.Serializable {
	private String _name;
	private List<Discipline> _disciplines;
	private Map<Integer, Student> _students;
	private List<Student> _representatives;
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	Course (String name) {
		_name = name;
		_disciplines = new ArrayList<Discipline>();
		_students = new HashMap<Integer, Student>(); // key is students' id for easy mapping
		_representatives = new ArrayList<Student>();
	}

	public String getName() {
		return _name;
	}

	private void addDiscipline (Discipline d) {
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

	Map<Integer,Person> getRepresentatives() {
		Iterator<Map.Entry<Integer, Student>> entries = _students.entrySet().iterator();
		Map<Integer,Person> representatives = new HashMap<Integer, Person>();

		while (entries.hasNext()) {
			Map.Entry<Integer, Student> entry = entries.next();
			if(entry.getValue().isRepresentative()) {
				representatives.put(entry.getKey(), entry.getValue());
			}
		}
		return representatives;
	}

	Discipline getDiscipline(String discipline) throws NoSuchDisciplineIdException {
		Iterator<Discipline> iterator = _disciplines.iterator();

		while(iterator.hasNext()) {
			Discipline d = iterator.next();
			if (d.getName().equals(discipline))
				return d;
		}
		throw new NoSuchDisciplineIdException(discipline);
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
