package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import java.util.Map;
import java.util.HashMap;

/**
 * 4.2.4. Search person.
 */
public class DoSearchPerson extends Command<SchoolManager> {
	private Map<Integer, String> _persons;
	private Input<String> _string;
	private String line;
  /**
   * @param receiver
   */
  public DoSearchPerson(SchoolManager receiver) {
    super(Label.SEARCH_PERSON, receiver);
		_string = _form.addStringInput(Message.requestPersonName());
  }

  @Override
  public final void execute() {
		_form.parse();
		try {
			line = _receiver.DoSearchPerson(_string.value());
			
	} catch (NoSuchPersonException nspe) {
			_display.popup(Message.fileNotFound());
		}
	}

}
