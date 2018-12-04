package sth.app.main;

import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {

  /**
   * @param receiver
   */
  public DoSave(SchoolManager receiver) {
    super(Label.SAVE, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {

    // 1) Usar o parser
    // 2) se o utilizador tiver nos dados lidos pelo parser
    // 3) faz replace da school
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
  }

}
