package sth.core;

import sth.core.Survey;
import sth.core.Survey;
import sth.core.Course;
import sth.core.Teacher;
import sth.core.Project;
import sth.core.Student;
import sth.core.SurveyState;
import sth.core.SurveyFinished;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.OpeningSurveyIdException;
import sth.core.exception.FinishingSurveyIdException;
import sth.core.exception.SurveyIdFinishedException;
import sth.core.exception.NonEmptySurveyIdException;
import java.text.Normalizer;
import java.util.*;

public class Discipline extends Observable implements Comparable<Discipline>, java.io.Serializable { 
	private String _name;
	private int _capacity;
	private Course _course;
	private Map<Integer, Teacher> _teachers;
	private Map<Integer, Student> _students;
	private List<Student> _representatives;
	private List<Project> _projects;
	private List<Observer> _observers;

  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
	  
	Discipline (String name, int capacity, Course c) {
		_name = name;
		_capacity = capacity;
		_course = c;
		_teachers = new HashMap<Integer, Teacher>();
		_students = new HashMap<Integer, Student>();
		_projects = new ArrayList<Project>();
		_observers = new ArrayList<Observer>();
		_representatives = new ArrayList<Student>();

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
		_observers.add((Observer)t);
	}

	void enrollStudent(Student s) {
		if (_students.size() < _capacity) {
			_students.putIfAbsent(s.getId(), s);
			_observers.add((Observer)s);
		}
	}

	void addRepresentative(Student s) {
		if (!_representatives.contains(s)) {
			_representatives.add(s);
			_observers.add((Observer)s);
		}
		
	}

	void createProject(String name) throws DuplicateProjectIdException {
		Iterator<Project> iterator = _projects.iterator();
		Project p;
		Project pAux = null;

		while(iterator.hasNext()) {
			p = iterator.next();
			if (p.getName().equals(name) && !p.isClosed()) {
				pAux = p;
				break;
			}
		}
		if (pAux != null) {
			throw new DuplicateProjectIdException(name);
		} else {
			Project pAdd = new Project(name);
			_projects.add(pAdd);
		}
	}

	void closeProject(String name) throws NoSuchProjectIdException, OpeningSurveyIdException {
		Project p = this.getProject(name);
		if (!p.isClosed()){
			p.close();
			if (p.getSurvey() != null){
				Notification n = new Notification("Pode preencher inquérito do projecto " + name + " da disciplina " + _name);
				for (Observer observer : _observers) {
					observer.update(this, n);
				}
			}
		}
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

	void finishSurvey(String pName) throws NoSurveyIdException, FinishingSurveyIdException, NoSuchProjectIdException {
		Project p = this.getProject(pName);
		p.finishSurvey(); // if was able to finish survey and no exception was thrown, then can notifiy
		Notification n = new Notification("Resultados do inquérito do projecto " + pName + " da disciplina " + _name);
		for (Observer observer : _observers) {
        		observer.update(this, n);
      	}
	}

	void cancelSurvey(String pName) throws SurveyIdFinishedException, NoSurveyIdException, NonEmptySurveyIdException, OpeningSurveyIdException,
		NoSuchProjectIdException {
		Project p = this.getProject(pName);
		boolean opened = p.cancelSurvey(); // if was able to cancel survey and no exception was thrown, then can notifiy
		if (opened){
			Notification n = new Notification("Pode preencher inquérito do projecto " + pName + " da disciplina " + _name);
			for (Observer observer : _observers) {
					observer.update(this, n);
			}
		}
	}

	String surveyToString(String pName, boolean detailed) throws NoSuchProjectIdException, NoSurveyIdException {
		Project p = this.getProject(pName);
		Survey s = p.getSurvey(); 
		String toString = new String("");
		if (s != null){
			String surveyState = s.toString();
			toString = toString + _name + " - " + pName + " " + surveyState + "\n";
			if (s.getState() instanceof SurveyFinished) {
				toString = toString + s.printAnswers(detailed);
			}
			return toString;
		}
		else
			throw new NoSurveyIdException("", "");
	}

	String showSurveys() throws NoSuchDisciplineIdException, NoSurveyIdException, NoSuchProjectIdException {
		Iterator<Project> iterator = _projects.iterator();
		List<String> projectNames = new ArrayList<String>();
		Project p;
		String result = "";

		while(iterator.hasNext()) {
			p = iterator.next();
			projectNames.add(p.getName());
		}
		
		Collections.sort(projectNames); // sort projects name

		Iterator<String> iterator2 = projectNames.iterator();
		while(iterator2.hasNext()) {
			String project = iterator2.next();
			Survey s = this.getProject(project).getSurvey(); 
			if (s != null){
				if (s.getState() instanceof SurveyFinished) {
					result = result + _name + " - " + project + " - " 
					+ s.getNAnswers() + " - " + s.getTimes(false) + "horas\n";
				} else {
					result = result + _name + " - " + project + " " + (s.getState()).getName() + "\n";
				}
			}
		}
		return result;
	}

@Override
	public String toString() {
		return "* " + _course.getName() + " - " + _name;
	}

	public int compareTo(Discipline d2) {
		int res = _course.compareTo(d2._course);

		if(res != 0){
			return res;
		}
		String o1 = Normalizer.normalize(getName(), Normalizer.Form.NFD);
		String o2 = Normalizer.normalize(d2.getName(), Normalizer.Form.NFD);
		return o1.compareTo(o2);
	}
}
