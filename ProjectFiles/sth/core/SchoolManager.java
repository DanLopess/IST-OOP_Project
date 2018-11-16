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
    //FIXME implement method
  }

  /**
   * @return true when the currently logged in person is an administrative
   */
  public boolean isLoggedUserAdministrative() {
    //FIXME implement predicate
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
		if (_loggedUser instanceof Student) && _loggedUser.isRepresentative());
			return true;
		return false;

  }

	public Person getLoggedUser() {
		return _loggedUser;
	}

	public Map<Integer, Person> getAllUsers() {
		return (_school.getAllPersons());
	}

	public void DoChangePhoneNumber(int phoneNumber) {
		_loggedUser.setPhoneNumber(phoneNumber);
	}

	public String DoSearchPerson(String name) {
		private Map<Integer, Person> _users = _school.getAllPersons();
		Iterator<Map.Entry<Integer, Person>> entries = _users.entrySet().iterator();
		private Map<Integer, String> _persons = new HashMap<Integer, String>();
		Person p;

		while (entries.hasNext()) {
		    Map.Entry<Integer, Person> entry = entries.next();
				p = entry.getValue();
		    if(p.getName().contains(name)) {
					_persons.put(p.getId(), p.getName());
				}
		}
		return _persons;
	}



}
