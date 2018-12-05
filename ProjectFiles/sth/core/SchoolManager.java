package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.DuplicateSurveyIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.SurveyIdFinishedException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.FinishingSurveyIdException;
import sth.core.exception.OpeningSurveyIdException;
import java.io.IOException;
import java.io.FileNotFoundException;
import sth.core.School;
import sth.core.Person;
import java.util.*;

/**
 * The façade class.
 */
public class SchoolManager {
	private School _school;
	private Person _loggedUser;
	private String _fileName;

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
			_fileName = datafile;
		} catch (IOException | BadEntryException e) {
			throw new ImportFileException(e);
		}
	}

	public boolean hasFileName() {
		if (_fileName != null) 
			return true;
		else
			return false;
	}

	/**
	 * Do the login of the user with the given identifier.
	 * @param id identifier of the user to login
	 * @throws NoSuchPersonIdException if there is no uers with the given identifier
	 */
	public void login(int id) throws NoSuchPersonIdException {
		_loggedUser = _school.parsePersonById(id);
	}

	public void doOpen(String datafile) throws ImportFileException, NoSuchPersonIdException {
        try {
            Person newLoggin ;
            // Loads new school information
            School newSchool = new School();
            newSchool.importFile(datafile);
            // Tries to login the new user 
            newLoggin = newSchool.parsePersonById(_loggedUser.getId());
            // if successful, overrides school data.
            _school = newSchool;
            _loggedUser = newLoggin;
        } catch (IOException | BadEntryException e) {
            throw new ImportFileException(e);
        }
	}
	
	public void doSave(String fileName) throws NoSuchPersonIdException {

		if (_school.parsePersonById(_loggedUser.getId()) != null){
			if(fileName == null) {
				
			} else {
				_fileName = fileName;
				
			}
		} else {
			throw new NoSuchPersonIdException(_loggedUser.getId());
		}
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
	 * auxiliary method that receives a map of users
	 * and using each user individual toString
	 * returns all those users' toString
	 */
	public String printUsers(Map<Integer,Person> users) {
		//Convert HashMap to TreeMap.It will be sorted in natural order. (by id)
		Map<Integer,Person> usersTree = new TreeMap<Integer,Person>(users); 
		String toStringLines = new String();
		
		Iterator<Map.Entry<Integer, Person>> entries = usersTree.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<Integer, Person> entry = entries.next();
			toStringLines = toStringLines + entry.getValue().toString() + "\n";
		}

		return toStringLines;
	}

	/**
	 * === Person's Portal ===
	 */
	
	public String showPerson() {
		return _loggedUser.toString();
	}
	
	public String changePhoneNumber(int phoneNumber) {
		_loggedUser.setPhoneNumber(phoneNumber);
		return this.showPerson();
	}

	public String getAllPersons() {  
		return printUsers(_school.getAllUsers());
	}

	public String searchPerson(String name) { // Sorted by name
		Iterator<Map.Entry<Integer, Person>> entries = _school.getAllUsers().entrySet().iterator();
		List<Person> persons = new ArrayList<Person>();
		String personsToString = new String();
		Iterator<Person> iterator = persons.iterator();

		while (entries.hasNext()) {
			Map.Entry<Integer, Person> entry = entries.next();
			if(entry.getValue().getName().contains(name)) {
				persons.add(entry.getValue());
			}
		}
		Collections.sort(persons, new Comparator<Person>() {
		@Override
			public int compare(Person p1, Person p2) {
        		return p1.getName().compareTo(p2.getName());
   			 }
		});

		while (iterator.hasNext()) {
			Person p = iterator.next();
			personsToString = personsToString + p.toString() + "\n";
		}

		return personsToString;
	}

	/**
	 * === Teacher's portal ===
	 */

	public void createProject(String discipline, String pName) throws NoSuchDisciplineIdException {
		if(this.isLoggedUserProfessor()) {
			Discipline d = ((Teacher)_loggedUser).getDiscipline(discipline);
			d.createProject(pName);
		}
	}

	public void closeProject(String discipline, String pName) throws NoSuchProjectIdException, 
	NoSuchDisciplineIdException 
	{
		if(this.isLoggedUserProfessor()) {
			Discipline d = ((Teacher)_loggedUser).getDiscipline(discipline);
			d.closeProject(pName);
		}

	}

	public String getProjectSubmissions(String discipline, String pName)  throws NoSuchProjectIdException, 
	NoSuchDisciplineIdException 
	{	
		if(this.isLoggedUserProfessor()) {
			Discipline d = ((Teacher)_loggedUser).getDiscipline(discipline);
			return(d.getProjectSubmissions(pName));
		}
		return null;
	}

	public String getDisciplineStudents(String discipline) throws NoSuchDisciplineIdException {
		if(this.isLoggedUserProfessor()) {
			Discipline d = ((Teacher)_loggedUser).getDiscipline(discipline);
			//Convert HashMap to TreeMap.It will be sorted in natural order. (by id)
			Map<Integer,Student> usersTree = new TreeMap<Integer,Student>(d.getAllStudents()); 
			String toStringLines = new String();
			
			Iterator<Map.Entry<Integer, Student>> entries = usersTree.entrySet().iterator();

			while (entries.hasNext()) {
				Map.Entry<Integer, Student> entry = entries.next();
				toStringLines = toStringLines + entry.getValue().toString() + "\n";
			}

			return toStringLines;
		}
		return null;
	}

	public String getSurveyResults(String discipline, String pName) throws NoSuchDisciplineIdException , 
	NoSuchProjectIdException 
	{
		if(this.isLoggedUserProfessor()) {
			Project p = (((Teacher)_loggedUser).getDiscipline(discipline)).getProject(pName);
			Survey s = p.getSurvey();
			return s.toString();
		}
		return null;
	}

	
	/**
	 * === Student's portal ===
	 */
	public void deliverProject(String discipline, String pName, String text)  throws NoSuchDisciplineIdException,
	NoSuchProjectIdException 
	{
		if (this.isLoggedUserStudent())
			((Student)_loggedUser).submitProject(discipline, pName, text);
	}

	public void fillSurvey (String discipline, String pName, int hours) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException 
	{
		if (this.isLoggedUserStudent())
			((Student)_loggedUser).submitAnswerToSurvey(discipline, pName, hours);
	}
	 
	/**
	 * === Representative's portal ===
	 */

	public void createSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, DuplicateSurveyIdException
	{
		if (this.isLoggedUserRepresentative())	{
			Project p = (((Student)_loggedUser).getDiscipline(discipline)).getProject(pName);
			if (p.getSurvey() == null) {
				
			}
		}
		//if... representative, call student function: createSurvey
	}

	public void cancelSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, NonEmptySurveyIdException, NoSurveyIdException, SurveyIdFinishedException
	{
		//if... representative, call student function: cancelSurvey...
	}

	public void openSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, NoSurveyIdException, OpeningSurveyIdException  
	{
		
	}

	public void closeSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, NoSurveyIdException, ClosingSurveyIdException
	{
		
	}

	public void finishSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, FinishingSurveyIdException, NoSurveyIdException
	{
		
	}

	public String getDisciplineSurveys(String discipline) throws NoSuchDisciplineIdException	{
		return "";
	}

}
