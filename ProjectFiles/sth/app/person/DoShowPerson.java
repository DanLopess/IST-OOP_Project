package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import sth.core.Person;

/**
 * 4.2.1. Show person.
 */
public class DoShowPerson extends Command<SchoolManager> {
  private Display _display;
  /**
   * @param receiver
   */
  public DoShowPerson(SchoolManager receiver) {
    super(Label.SHOW_PERSON, receiver);
		_display = new Display();
  }

  @Override
  public final void execute() {
		_display.add(_receiver.getLoggedUser().toString());
		_display.display();
  }

}
