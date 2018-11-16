package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;

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

		List<String> _persons;
		try {
			_persons = _receiver.DoShowAllPersons();
			Collections.sort(_persons);
			Iterator<String> iterator = _persons.iterator();

			while (iterator.hasNext()) {
				_display.addLine(iterator.next());
			}
			_display.display();
	} catch (NoSuchPersonException nspe) {
			_display.popup(Message.fileNotFound());
		}
	}
}
