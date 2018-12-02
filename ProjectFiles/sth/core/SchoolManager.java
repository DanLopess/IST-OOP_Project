package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchProjectException;
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
		if (_loggedUser instanceof Student && _loggedUser.isRepresentative()) 
			return true;
		return false;

	}

	public Person getLoggedUser() {
		return _loggedUser;
	}

	public Map<Integer, Person> getAllUsers() {
		return (_school.getAllUsers());
	}

	public String DoChangePhoneNumber(int phoneNumber) {
		_loggedUser.setPhoneNumber(phoneNumber);
		return (_loggedUser.toString());
	}

	public List<String> DoSearchPerson(String name) throws NoSuchPersonException {
		Iterator<Person> iterator = _school.getAllUsers().iterator();
		ArrayList<String> _persons = new ArrayList<String>();
		Person p;

		while (iterator.hasNext()) {
			p = iterator.next();
		if(p.getName().contains(name)) {
				_persons.add(p.toString());
			}
		}
		if(_persons.size()>0)
			return _persons;
		else {
			throw new NoSuchPersonException(1);
		}
	}


	public List<String> DoShowAllPersons() {
		Iterator<Person> iterator = _school.getAllUsers().iterator();
		ArrayList<String> _persons = new ArrayList<String>();
		Person p;

		while (iterator.hasNext()) {
			p = iterator.next();
			_persons.add(p.toString());
		}
		return _persons;
	}

	public String DoShowPerson() {
		return _loggedUser.toString();
	}



	public void DoCloseProject(String discipline, String pName) throws NoSuchDisciplineException,
					NoSuchProjectException {
		Teacher teacher;
		if(isLoggedUserProfessor()) {
			//_loggedUser.createProject(); loggeduser doesnt have this method
		}

	}

	public void DoCreateProject(String discipline, String pName) throws NoSuchDisciplineException,
					NoSuchProjectException, DuplicateProjectException{

	}

	public List<String> DoShowDisciplineStudents(String name) throws NoSuchDisciplineException {
		if(isLoggedUserProfessor()) {
			ArrayList<String> _students = new ArrayList<String>();
			return _students; // not implemented because loggedUser is an abstract type
												// To be fixed in final version
		}
		return null;
	}

	public String DoShowProjectSubmissions() throws NoSuchDisciplineException,
			NoSuchProjectException, NoSurveyException {
		return null;
	}

	public String DoShowSurveyResults() throws NoSuchDisciplineException,
			NoSuchProjectException {
		return null;
	}
}
