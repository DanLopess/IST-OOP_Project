package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;

/**
 * 4.2.3. Show all persons.
 */
public class DoShowAllPersons extends Command<SchoolManager> {
	private SchoolManager _receiver;

  /**
   * @param receiver
   */
  public DoShowAllPersons(SchoolManager receiver) {
    super(Label.SHOW_ALL_PERSONS, receiver);
    _receiver = receiver;
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    //FIXME implement command
  }

}
