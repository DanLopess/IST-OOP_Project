package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.Course;
import sth.core.Discipline;
import sth.core.Project;
import sth.core.School;

import java.util.*;

/**
 * The Teacher Class
 */
public class Teacher extends Person {
    private List<Discipline> _disciplines = new ArrayList<>() ;

    /**
     * Teacher's class constructor
     *
     * @param id          Teacher's id
     * @param name        Teacher's name
     * @param phoneNumber Teacher's phoneNumber
     */
    public Teacher(int id, String name, String phoneNumber) throws BadEntryException {
        super(id, name, phoneNumber);
    }

    Discipline getDiscipline(String name) throws NoSuchDisciplineIdException {
        for( Discipline d : _disciplines ){
            if (d.getName().equals(name)) {
                return d;
            }
        }
        throw new NoSuchDisciplineIdException("No such discipline: " + name);
    }

    List<Discipline> getAllDisciplines() {
        return _disciplines;
    }

    /**
     * creates a project to a certain discipline
     *
     * @param dName name of the discipline
     * @param pName name of the project
     */
    void createProject(String dName, String pName) {
        for( Discipline d : _disciplines ){
            if (d.getName().equals(dName)) {
                d.createProject(pName);
                return;
            }
        }
        //TODO: Throw Exception?
    }

    /**
     * @param d is the discipline being added to teacher's _disciplines
     */
    void addDiscipline(Discipline d) {
        _disciplines.add(d);
        d.addTeacher(this);
    }


    @Override
    public String toString() {
        String result = "";
        List<Discipline> disciplines = new ArrayList<>(_disciplines);

        result = "DOCENTE|" + super.toString();

        Collections.sort(disciplines, new Comparator<Discipline>() {
            @Override
            public int compare(Discipline o1, Discipline o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for( Discipline d : disciplines ){
            result += "\n" + d.toString();
        }
        return result;
    }

    void parseContext(String lineContext, School school) throws BadEntryException {
        String components[] = lineContext.split("\\|");

        if (components.length != 2)
            throw new BadEntryException("Invalid line context " + lineContext);

        Course course = school.parseCourse(components[0]);
        Discipline discipline = course.parseDiscipline(components[1]);

        addDiscipline(discipline);
        discipline.addTeacher(this);
    }
}
