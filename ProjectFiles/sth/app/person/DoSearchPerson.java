package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import java.util.Map;
import java.util.HashMap;

/**
 * 4.2.4. Search person.
 */
public class DoSearchPerson extends Command<SchoolManager> {
	private Map<Integer, String> _persons;
	private Input<String> _string;
  /**
   * @param receiver
   */
  public DoSearchPerson(SchoolManager receiver) {
    super(Label.SEARCH_PERSON, receiver);
		_string = _form.addStringInput(Message.requestPersonName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
		_persons = receiver.DoSearchPerson(_string);
		Iterator<Map.Entry<Integer, String>> entries = _persons.entrySet().iterator();
		while()
	}

}
