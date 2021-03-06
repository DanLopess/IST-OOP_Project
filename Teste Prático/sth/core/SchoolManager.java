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
import sth.core.exception.DuplicateProjectIdException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.*;
import sth.core.School;
import sth.core.Person;
import java.util.*;
import sth.core.exception.NoSuchCourseIdException;

/**
 * The façade class.
 */
public class SchoolManager {
	private School _school;
	private Person _loggedUser;
	private String _fileName;


	public SchoolManager() {
		_school = new School();
		_fileName = null;
	}

	/**
	 * @param datafile
	 * @throws ImportFileException
	 */
	public void importFile(String datafile) throws ImportFileException {
		try {
			_school.importFile(datafile);
		} catch (IOException | BadEntryException e) {
			throw new ImportFileException(e);
		}
	}

	public String getCourseRepresentatives(String cName) throws NoSuchCourseIdException{
		Course c = _school.getCourse(cName);
		Map<Integer, Person> representatives = c.getRepresentatives();
		if (representatives.size() == 0) {
			throw new NoSuchCourseIdException(cName);
		} else {
			return printUsers(representatives);
		}
	}

	public boolean needsFileName() {
		if (_fileName == null) 
			return true;
		else
			return false;
	}

	public String getFileName() {
		return _fileName;
	}

	/**
	 * Do the login of the user with the given identifier.
	 * @param id identifier of the user to login
	 * @throws NoSuchPersonIdException if there is no uers with the given identifier
	 */
	public void login(int id) throws NoSuchPersonIdException {
		_loggedUser = _school.parsePersonById(id);
	}

	public boolean hasNotifications() {
		return _loggedUser.hasNotifications();
	}

	public String getNotifications() {
		return _loggedUser.getNotifications();
	}

	private Object readObject(String inputFilename) throws IOException, BadEntryException, ClassNotFoundException {
		ObjectInputStream obIn = null;
		try { 
			FileInputStream fpin = new FileInputStream(inputFilename);
			obIn = new ObjectInputStream(fpin);
			Object anObject = obIn.readObject();
			return anObject;
		} 
		finally {
			if (obIn != null)
				obIn.close();
		}
	}

	public void open(String fileName) throws ImportFileException, NoSuchPersonIdException {
        try {
            Person newLoggin ;
            // Loads new school information
            School newSchool = (School)readObject(fileName);
            // Tries to login the new user 
            newLoggin = newSchool.parsePersonById(_loggedUser.getId());
            // if successful, overrides school data.
            _school = newSchool;
            _loggedUser = newLoggin;
			_fileName = new String(fileName);
        } catch (IOException | BadEntryException | ClassNotFoundException e) {
            throw new ImportFileException(e);
        }
	}
	
	private void saveObject(String outputFilename, Object anObject) throws IOException {
		ObjectOutputStream obOut = null;
		try {
			FileOutputStream fpout = null;
			fpout = new FileOutputStream(outputFilename);
			obOut = new ObjectOutputStream(fpout);
			obOut.writeObject(anObject);
		} 
		finally {
			if (obOut != null)
				obOut.close();
		}
	}

	public void save(String fileName) throws IOException {
		if(this.needsFileName()) {
			_fileName = new String(fileName);
		}
		saveObject(_fileName, (Object)_school); // saves the school object 
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

	public int getLoggedUserId() {
		return _loggedUser.getId();
	}

	public Map<Integer, Person> getAllUsers() {
		return (_school.getAllUsers());
	}

	/**
	 * auxiliary method that receives a map of users
	 * and using each user individual toString
	 * returns all those users' toString
	 */
	private String printUsers(Map<Integer,Person> users) {
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
		String personsToString = new String("");

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
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			Person p = iterator.next();
			personsToString = personsToString + p.toString() + "\n";
		}

		return personsToString;
	}

	/**
	 * === Teacher's portal ===
	 */

	public void createProject(String discipline, String pName) throws NoSuchDisciplineIdException, DuplicateProjectIdException,
			NoSuchProjectIdException {
		if(this.isLoggedUserProfessor()) {
			((Teacher)_loggedUser).createProject(discipline,pName);
		}
	}

	public void closeProject(String discipline, String pName) throws NoSuchProjectIdException, 
	NoSuchDisciplineIdException , OpeningSurveyIdException
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

	public String removeDisciplineStudents(String discipline, String name) throws NoSuchDisciplineIdException {
		if(this.isLoggedUserProfessor()) {
			Discipline d = ((Teacher)_loggedUser).getDiscipline(discipline);
			d.removeStudent(name);

		}
		return null;
	}

	public String getSurveyResults(String discipline, String pName) throws NoSuchDisciplineIdException , 
	NoSuchProjectIdException, NoSurveyIdException
	{
		// both teacher and student can get survey results
		if(this.isLoggedUserProfessor() ) { 
			Discipline d = (((Teacher)_loggedUser).getDiscipline(discipline));
			String survey = d.surveyToString(pName, true);
			return survey;
		}
		if (this.isLoggedUserStudent() || this.isLoggedUserRepresentative()) {
			Discipline d = (((Student)_loggedUser).getDiscipline(discipline));
			String survey = d.surveyToString(pName, false);
			return survey;
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

	public void fillSurvey (String discipline, String pName, int hours, String comment) throws NoSuchDisciplineIdException,
	NoSuchProjectIdException, NoSurveyIdException
	{
		if (this.isLoggedUserStudent())
			((Student)_loggedUser).submitAnswerToSurvey(discipline, pName, hours, comment);
	}

	/**
	 * === Representative's portal ===
	 */

	public void createSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, DuplicateSurveyIdException
	{
		if (this.isLoggedUserRepresentative())	{
			((Student)_loggedUser).createSurvey(discipline, pName);
		}
	}

	public void cancelSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, NonEmptySurveyIdException, NoSurveyIdException, SurveyIdFinishedException, OpeningSurveyIdException
	{
		if (this.isLoggedUserRepresentative())	{
			((Student)_loggedUser).cancelSurvey(discipline, pName);
		}
	}

	public void openSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, NoSurveyIdException, OpeningSurveyIdException  
	{
		if (this.isLoggedUserRepresentative())	{
			((Student)_loggedUser).openSurvey(discipline, pName);
		}
	}

	public void closeSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, NoSurveyIdException, ClosingSurveyIdException
	{
		if (this.isLoggedUserRepresentative())	{
			((Student)_loggedUser).closeSurvey(discipline, pName);
		}
	}

	public void finishSurvey(String discipline, String pName) throws NoSuchDisciplineIdException, 
	NoSuchProjectIdException, FinishingSurveyIdException, NoSurveyIdException
	{
		if (this.isLoggedUserRepresentative())	{
			((Student)_loggedUser).finishSurvey(discipline, pName);
		}
	}

	public String getDisciplineSurveys(String discipline) throws NoSuchDisciplineIdException, NoSurveyIdException,NoSuchProjectIdException	{
		if (this.isLoggedUserRepresentative())	{
			return (((Student)_loggedUser).showSurveys(discipline));
		}
		return null;
	}

}
