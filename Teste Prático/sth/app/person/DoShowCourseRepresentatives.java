package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import sth.core.exception.NoSuchCourseIdException;
import sth.app.exception.NoSuchCourseException;

/**
 * 4.2.3. Show all persons.
 */
public class DoShowCourseRepresentatives extends Command<SchoolManager> {
    private Input<String> _string;
    /**
     * @param receiver
     */
    public DoShowCourseRepresentatives(SchoolManager receiver) {
        super(Label.SHOW_REPRESENTATIVES, receiver);
        _string = _form.addStringInput(Message.requestCourseName());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws NoSuchCourseException{
        _form.parse();
        _display.clear();

        String persons;
        try {
            persons = _receiver.getCourseRepresentatives(_string.value());
            _display.add(persons);
            _display.display();
        } catch (NoSuchCourseIdException e) {
            throw new NoSuchCourseException(_string.value());
        }

    }
}