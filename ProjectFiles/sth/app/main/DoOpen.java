package sth.app.main;

import java.io.FileNotFoundException;
import sth.core.exception.ImportFileException;
import java.io.IOException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.exception.NoSuchPersonIdException;

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
	public final void execute() {
    _form.parse();
    try {
        _receiver.doOpen(_inputFilename.value());
    } catch (ImportFileException | DialogException | NoSuchPersonIdException e) {
        e.printStackTrace();
    }
  }
}
