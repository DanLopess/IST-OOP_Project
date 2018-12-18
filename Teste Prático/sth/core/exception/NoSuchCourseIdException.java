package sth.core.exception;

/** Exception thrown when the requested course does not exist. */
public class NoSuchCourseIdException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201809021324L;

    /** Project id. */
    private String _courseName;

    /**
     * @param id
     */
    public NoSuchCourseIdException(String id) {
        _courseName = id;
    }

    /** @return id */
    public String getId() {
        return _courseName;
    }

}
