package sth.app.exception;

import pt.tecnico.po.ui.DialogException;

/**
 *
 */
public class NoSuchCourseException extends DialogException {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201810051538L;

    /** Course name. */
    private String _name;

    /**
     * @param name
     */
    public NoSuchCourseException(String name) {
        _name = name;
    }

    /** @see pt.tecnico.po.ui.DialogException#getMessage() */
    @Override
    public String getMessage() {
        return Message.noSuchCourse(_name);
    }

}