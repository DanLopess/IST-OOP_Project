package sth.app.main;

import java.io.IOException;
import java.util.*;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.Person;
import sth.app.exception.NoSuchPersonException;
import sth.core.exception.NoSuchPersonIdException;

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
		if (_receiver.needsFileName() == true) {
			_outputFileName = _form.addStringInput(Message.newSaveAs());
		}	
		
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
		try {
			if (_receiver.needsFileName()) {
				_form.parse();
				_receiver.save(_outputFileName.value());
			} else {
				_receiver.save(null); 
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
  }
}
