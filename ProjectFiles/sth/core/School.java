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

  //FIXME define object fields (attributes and, possibly, associations)
	private Map<Integer, Person> _users;
	private List<Course> _courses;

  //FIXME implement constructors if needed
	public School () {
		_users = new HashMap<Integer, Person>();
	}

  /**
   * @param filename
   * @throws BadEntryException
   * @throws IOException
   */
  void importFile(String filename) throws IOException, BadEntryException {
    //FIXME implement text file reader
  }

  //FIXME implement other methods
	Course parseCourse(String name) {
		return null; //FIXME
	}

	Person parsePerson(String name) {
		return null; //FIXME
	}

	public void addPerson(Person p) {
		_users.put(p.getId(), p);
	}



}
