package sth.core;

import java.util.Iterator;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.Person;
import java.io.IOException;
import java.util.*;
import sth.core.exception.NoSuchCourseIdException;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;

	private Map<Integer, Person> _people;
	private List<Course> _courses;
	private Parser _parser;
	private String _name;
	private int _nextPersonId;

	public School () {
		_name = "Instituto Superior Tecnico";
		_people = new HashMap <Integer, Person>();
		_courses = new ArrayList<Course>();
		_parser = new Parser(this);
	}

	/**
	 * @param filename
	 * @throws BadEntryException
	 * @throws IOException
	 */
	void importFile(String filename) throws IOException, BadEntryException {
		_parser.parseFile(filename);
	}

	/**
	* finds a course through its name
	* @param name
	*/
	Course parseCourse(String name) {
		Iterator<Course> iterator = _courses.iterator();
		Course c;
		while(iterator.hasNext()) {
			c = iterator.next();
			if (c.getName().equals(name)) {
				return c;
			}
		}
		Course newCourse = new Course(name);
		_courses.add(newCourse);
		return newCourse;
	}

	Course getCourse(String name) throws NoSuchCourseIdException {
		Iterator<Course> iterator = _courses.iterator();
		Course c;
		while(iterator.hasNext()) {
			c = iterator.next();
			if (c.getName().equals(name)) {
				return c;
			}
		}
		throw new NoSuchCourseIdException(name);
	}

	/**
	* find a person through its name
	* @param name
	* @return Person
	*/
	Person parsePerson(String name) {
		Iterator<Map.Entry<Integer, Person>> entries = _people.entrySet().iterator();

		while (entries.hasNext()) {
		    Map.Entry<Integer, Person> entry = entries.next();
		    if(entry.getValue().getName().equals(name)) {
					return entry.getValue();
			}
		}
		return null;
	}


	/**
	* this method searches for a person through its id
	   * @param id an integer that is relate
	   * @return Person 
   	*/
	Person parsePersonById(int id) throws NoSuchPersonIdException{
		if (_people.containsKey(id)) 
			return _people.get(id);
		else 
			throw new NoSuchPersonIdException(id);
	}


	/**
	 * this method adds a given person to all school's users list
	 * @param person an instance of the class person
	 */
	void addPerson(Person person) {
		_people.put(person.getId(), person);
	}

	/**
   	* This method returns a list of persons with all school's users.
	*
   */
	Map<Integer, Person> getAllUsers() {
		return _people;
	}
}
