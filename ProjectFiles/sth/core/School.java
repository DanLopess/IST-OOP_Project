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

	Person parsePersonById(int id) {
		Iterator<Person> iterator = _users.iterator();
		Person p;
		while (iterator.hasNext()) {
				p = iterator.next();
				if(p.getValue().getId() == id) {
					return p;
				}
		}
		return null;
	}

	void addPerson(Person p) {
		_nextPersonId++;
		_users.put(_nextPersonId, p);
	}

	public List<Person> getAllUsers() {
		return _users;
	}
}
