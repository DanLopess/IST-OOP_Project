package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;
import sth.app.exception.NoSuchPersonException;
import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.DuplicateProjectException;
import sth.app.exception.NoSurveyException;
import java.io.IOException;
import java.io.FileNotFoundException;
import sth.core.School;
import sth.core.Person;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
		if (_loggedUser instanceof Student /*&& _loggedUser.isRepresentative()*/)
			return true;
		return false;

  }

	public Person getLoggedUser() {
		return _loggedUser;
	}

	public List<Person> getAllUsers() {
		return (_school.getAllUsers()); // sort
	}

	public String DoChangePhoneNumber(int phoneNumber) {
		_loggedUser.setPhoneNumber(phoneNumber);
		return (_loggedUser.toString());
	}

	public String DoSearchPerson(String name) throws NoSuchPersonException {
		/*Iterator<Map.Entry<Integer, Person>> entries = _school.getAllUsers().entrySet().iterator();
		Map<Integer, Person> _persons = new HashMap<Integer, Person>();
		Person p;

		while (entries.hasNext()) {
		    Map.Entry<Integer, Person> entry = entries.next();
				p = entry.getValue();
		    if(p.getName().contains(name)) {
					_persons.put(p.getId(), p);
				}
		}*/
		return null; //also sort and return a list of strings (description of each person)
	}


	public List<String> DoShowAllPersons() {
		return null;
	}

	public String DoShowPerson() {
		return _loggedUser.toString();
	}



	public void DoCloseProject(String discipline, String pName) throws NoSuchDisciplineException,
	 				NoSuchProjectException {
		Teacher teacher;
		if(isLoggedUserProfessor()) {
			_loggedUser.createProject()
		}

	}

	public void DoCreateProject(String discipline, String pName) throws NoSuchDisciplineException,
	 				NoSuchProjectException, DuplicateProjectException{

	}

	public String DoShowDisciplineStudents(String name) throws NoSuchDisciplineException {
		if(isLoggedUserProfessor()) {
			// search for discipline and show students
			//parse discipline and get discipline then parse students
			//Discipline _discipline;// = _loggedUser.getDiscipline(name);
			//Map<Integer,Student> _students = _discipline.getAllStudents();
			String _students1 = new String();
			return _students1;//add students to List<String> by order
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
