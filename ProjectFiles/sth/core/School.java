package sth.core;

import java.util.Iterator;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.Person;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

	private List<Person> _users;
	private List<Course> _courses;
	private Parser _parser;
	private String _name;
	private int _nextPersonId;

	public School () {
		_name = "Instituto Superior Tecnico";
		_users = new ArrayList<Person>();
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
		return null;
	}


	/**
	 * find a person through its name
   * @param name
   */
	Person parsePerson(String name) {
		Iterator<Person> iterator = _users.iterator();
		Person p;
		while (iterator.hasNext()) {
				p = iterator.next();
				if(p.getName().equals(name)) {
					return p;
				}
		}
		return null;
	}


	/**
	 * this method searches for a person through its id
   * @param id an integer that is relate
   */
	Person parsePersonById(int id) {
		Iterator<Person> iterator = _users.iterator();
		Person p;
		while (iterator.hasNext()) {
				p = iterator.next();
				if(p.getId() == id) {
					return p;
				}
		}
		return null;
	}


	/**
   * this method adds a given person to all school's users list
	 * @param person an instance of the class person
   */
	void addPerson(Person person) {
		_users.add(person);
	}

	/**
   * This method returns a list of persons with all school's users.
	 *
   */
	public List<Person> getAllUsers() {
		return _users;
	}
}
