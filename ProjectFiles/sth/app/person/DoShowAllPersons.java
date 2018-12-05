package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;

/**
 * 4.2.3. Show all persons.
 */
public class DoShowAllPersons extends Command<SchoolManager> {

  /**
   * @param receiver
   */
  public DoShowAllPersons(SchoolManager receiver) {
    super(Label.SHOW_ALL_PERSONS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
		_form.parse();
		_display.clear();
		
		List<String> _persons;
		_persons = _receiver.getAllPersons();
		Iterator<String> iterator = _persons.iterator();

		while (iterator.hasNext()) {
			String next = iterator.next();
			Iterator<String> iterator2 = _persons.iterator();
			while (iterator2.hasNext()) {
				_display.addLine(iterator2.next());
			}
		}
		_display.display();
	}
}
