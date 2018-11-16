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
	private Display _display;
  /**
   * @param receiver
   */
  public DoSearchPerson(SchoolManager receiver) {
    super(Label.SEARCH_PERSON, receiver);
		_string = _form.addStringInput(Message.requestPersonName());
		_display = new Display();
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
		_form.parse();
		line = _receiver.DoSearchPerson(_string.value());
		// display persons
	}

}
