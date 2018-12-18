package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import sth.core.Person;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.app.exception.NoSuchDisciplineException;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;


/**
 * 4.4.4. Show course students.
 */
public class DoRemoveDisciplineStudents extends Command<SchoolManager> {
    private Input<String> _disciplineName;
    private Input<String> _studentName;
    /**
     * @param receiver
     */
    public DoRemoveDisciplineStudents(SchoolManager receiver) {
        super(Label.SHOW_REMOVE_STUDENTS, receiver);
        _studentName = _form.addStringInput(Message.requestName());
        _disciplineName = _form.addStringInput(Message.requestDisciplineName());
    }

    @Override
    public final void execute() throws DialogException {
        _form.parse();
        try {
            _receiver.removeDisciplineStudents(_disciplineName.value(), _studentName.value());
        } catch (NoSuchDisciplineIdException e) {
            throw new NoSuchDisciplineException(_disciplineName.value());
        }
    }

}