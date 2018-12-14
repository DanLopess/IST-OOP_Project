package sth.app.main;

import java.io.FileNotFoundException;
import sth.core.exception.ImportFileException;
import java.io.IOException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.exception.NoSuchPersonIdException;
import sth.app.exception.NoSuchPersonException;

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {
  private Input<String> _inputFilename;

  /**
   * @param receiver
   */
  public DoOpen(SchoolManager receiver) {
    super(Label.OPEN, receiver);
    _inputFilename = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
	public final void execute() throws NoSuchPersonException {
    _form.parse();
    int id = 0;
    try {
      id = _receiver.getLoggedUserId();
      _receiver.open(_inputFilename.value());
      if (_receiver.hasNotifications()) {
        _display.clear();
        _display.add(_receiver.getNotifications());
        _display.display();
      }
    } 
    catch (ImportFileException e) {
      _display.clear();
      _display.add(Message.fileNotFound());
      _display.display();
    }
    catch (NoSuchPersonIdException e) { 
      throw new NoSuchPersonException(id);
    }
  }
}
