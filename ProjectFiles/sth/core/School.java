package sth.core;

import java.util.Iterator;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;
import java.util.Map;
import java.util.HashMap;
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

	private Map<Integer, Person> _users;
	private List<Course> _courses;
	private Parser _parser;
	private String _name;

	public School () {
		_name = "Instituto Superior Tecnico";
		_users = new HashMap<Integer, Person>();
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
		Iterator<Map.Entry<Integer, Person>> entries = _users.entrySet().iterator();

		while (entries.hasNext()) {
		    Map.Entry<Integer, Person> entry = entries.next();
		    if(entry.getValue().getName().equals(name)) {
					return entry.getValue();
				}
		}
		return null;
	}

	public void addPerson(Person p) {
		_users.put(p.getId(), p);
	}

	public Map<Integer, Person> getAllPersons() {
		return _users;
	}



}
