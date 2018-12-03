package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;
import java.io.IOException;
import java.io.FileNotFoundException;
import sth.core.School;
import sth.core.Person;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


/**
 * The façade class.
 */
public class SchoolManager {
	private School _school;
	private Person _loggedUser;

	public SchoolManager() {
		_school = new School();
	}

	/**
	 * @param datafile
	 * @throws ImportFileException
	 * @throws InvalidCourseSelectionException
	 */
	public void importFile(String datafile) throws ImportFileException {
		try {
			_school.importFile(datafile);
		} catch (IOException | BadEntryException e) {
			throw new ImportFileException(e);
		}
	}

	/**
	 * Do the login of the user with the given identifier.
	 * @param id identifier of the user to login
	 * @throws NoSuchPersonIdException if there is no uers with the given identifier
	 */
	public void login(int id) throws NoSuchPersonIdException {
		_loggedUser = _school.parsePersonById(id);
	}

	/**
	 * @return true when the currently logged in person is an administrative
	 */
	public boolean isLoggedUserAdministrative() {
		if (_loggedUser instanceof Employee)
			return true;
		return false;
	}

	/**
	 * @return true when the currently logged in person is a professor
	 */
	public boolean isLoggedUserProfessor() {
		if (_loggedUser instanceof Teacher)
			return true;
		return false;
	}

	/**
	 * @return true when the currently logged in person is a student
	 */
	public boolean isLoggedUserStudent() {
		if (_loggedUser instanceof Student)
			return true;
		return false;
	}

	/**
	 * @return true when the currently logged in person is a representative
	 */
	public boolean isLoggedUserRepresentative() {
		if ((_loggedUser instanceof Student) && ((Student)_loggedUser).isRepresentative()) 
			return true;
		return false;
	}

	public Person getLoggedUser() {
		return _loggedUser;
	}

	public Map<Integer, Person> getAllUsers() {
		return (_school.getAllUsers());
	}

	/**
	 * === Person's Portal ===
	 */
	
	public List<String> showPerson() {
		return _loggedUser.toString();
	}
	
	public List<String> changePhoneNumber(int phoneNumber) {
		_loggedUser.setPhoneNumber(phoneNumber);
		return this.showPerson();
	}

	public List<List<String>> getAllPersons() {  
		Map<Integer,Person> users = _school.getAllUsers();
		//Convert HashMap to TreeMap.It will be sorted in natural order. (by id)
		Map<Integer,Person> usersTree = new TreeMap<Integer,String>(users); 

		Iterator<Map.Entry<Integer, Person>> entries = usersTree.entrySet().iterator();
		List<List<String>> personsToString = new ArrayList<ArrayList<String>>();

		while (entries.hasNext()) {
			Map.Entry<Integer, Person> entry = entries.next();
			personsToString.add(entry.getValue().toString());
		}

		return personsToString;
	}

	public List<List<String>> searchPerson(String name) { // Sorted by name
		Iterator<Map.Entry<Integer, Person>> entries = _school.getAllUsers().entrySet().iterator();
		List<Person> persons = new ArrayList<Person>();
		List<List<String>> personsToString = new ArrayList<ArrayList<String>>();
		Iterator<Person> iterator = new persons.iterator();

		while (entries.hasNext()) {
			Map.Entry<Integer, Person> entry = entries.next();
			if(entry.getValue().getName().contains(name)) {
				persons.add(entry.getValue());
			}
		}
		Collections.sort(Persons, new Comparator<Person>() {
		@Override
			public int compare(Person p1, Person p2) {
        		return p1.getName().compareTo(p2.getName());
   			 }
		});

		while (iterator.hasNext()) {
			Person p = iterator.next();
			personsToString.add(p.toString);
		}

		return personsToString;
	}

	/**
	 * === Teacher's portal ===
	 */

	public void createProject(String discipline, String pName) throws NoSuchDisciplineIdException {
		if(isLoggedUserProfessor()) {
			Discipline d = ((Teacher)_loggedUser).getDiscipline(discipline);
			d.createProject(pName);
		}
	}

	public void closeProject(String discipline, String pName) throws NoSuchProjectIdException, 
							NoSuchDisciplineIdException {
		if(isLoggedUserProfessor()) {
			Discipline d = ((Teacher)_loggedUser).getDiscipline(discipline);
			d.closeProject(pName);
		}

	}

	public String getProjectSubmissions()  {
		return null;
	}

	public List<List<String>> getDisciplineStudents(String name) throws NoSuchDisciplineIdException {
		if(isLoggedUserProfessor()) {
			ArrayList<String> _students = new ArrayList<String>();
			return _students; // TODO get all disciplines
		}
		return null;
	}

	public String getSurveyResults() {
		return null;
	}

	
	/**
	 * === Student's portal ===
	 */
	

	 
	/**
	 * === Representative's portal ===
	 */
}
