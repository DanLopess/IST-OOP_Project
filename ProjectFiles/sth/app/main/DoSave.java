package sth.app.main;

import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {
  //FIXME add input fields if needed

  /**
   * @param receiver
   */
  public DoSave(SchoolManager receiver) {
    super(Label.SAVE, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
		/*
		public void saveObject(String outputFilename, Object anObject) throws IOException {
			ObjectOutputStream obOut = null;
			try {
			FileOutputStream fpout = new FileOutputStream(outputFilename);
			obOut = new ObjectOutputStream(fpout);
			obOut.writeObject(anObject);
			} finally {
			if (obOut != null)
			obOut.close();
			}
		}*/
    //FIXME implement command if file not found... SaveAs
  }

}
