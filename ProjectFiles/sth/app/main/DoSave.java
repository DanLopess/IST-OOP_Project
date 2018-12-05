package sth.app.main;

import java.io.IOException;
import java.util.*;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {
	private Input<String> _outputFileName;
	
	/**
   * @param receiver
   */
  public DoSave(SchoolManager receiver) {
		super(Label.SAVE, receiver);
		if (!_receiver.hasFileName()) {
			_outputFileName = _form.addStringInput(Message.newSaveAs());
		}	
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
		_form.parse();
		if (!_receiver.hasFileName()) {
			_receiver.doSave(_inputFilename.value());
		} else {
			_receiver.doSave(null);
		}
		
  }
}
