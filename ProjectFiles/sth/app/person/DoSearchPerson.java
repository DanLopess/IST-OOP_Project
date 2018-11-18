package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import sth.app.exception.NoSuchPersonException;

/**
 * 4.2.4. Search person.
 */
public class DoSearchPerson extends Command<SchoolManager> {
	private Input<String> _string;
  /**
   * @param receiver
   */
  public DoSearchPerson(SchoolManager receiver) {
    super(Label.SEARCH_PERSON, receiver);
		_string = _form.addStringInput(Message.requestPersonName());
  }

  @Override
  public final void execute() throws NoSuchPersonException{
		_form.parse();

		List<String> _persons;
		_persons = _receiver.DoSearchPerson(_string.value());
		Collections.sort(_persons);
		Iterator<String> iterator = _persons.iterator();

		while (iterator.hasNext()) {
			_display.addLine(iterator.next());
		}
		_display.display();
	}

}
