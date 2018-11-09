package sth.core;

import java.io.IOException;
import java.io.StreamTokienizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;

import java.util.Collection;
import java.util.ArrayList;

import sth.core.exception.BadEntryException;

public class SchoolParser{
	private School _school1;
	private Person _person;
	int _id, _phone;	

	SchoolParser(School x){
		_school = x;
	}	
	
	void parseFile(String fileName) throws IOException, BadEntryException {
		try(BufferedReader reader = new BufferedReader(new fileReader(fileName))) {
			String line;

			while((line = reader.readLine()) != null){
				parseLine(line);
			}
		}
	}

	private void parseLine(String line) throws BadEntryException{
		String[] components = line.split("\\]");
		Person person;

		if(components.length =! 4){
			throw new BadEntryException("invalid line " + line);
		}
		id = Integer.parseInt(components[1]);
		phone = Integer.parseInt(components[0]);

		switch(components[0]){
			case "FUNCIONARIO": 
				_person = new Employee(id, components[3], phone);
				break;
			case "DELEGADO": 
				_person = new Student(id, components[3], phone);
				break;
			case "ALUNO":
				_person = new Student(id, components[3], phone, false);
				break;
			case "DOCENTE":
				_person = new Teacher(id, components[3], phone);
				break;
			default:
				throw new BadEntryException("invalid line " + line)

		}
		_school.addPerson(_person);
	}

	private void parseContext(String line) throws BadEntryException {
		String context = line.subString(2);
	
	}	
}

public class Person {
	void parseContext(String context, School school) throws BadEntryException {
		throw new BadEntryException("Context not supported for this type");
	}
}

public class Teacher extends Person {
	void parseContext(String context, School school) throws BadEntryException {
		String[] cont = context.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("invalid line "+line);

		Course course = school.parseCourse(comp[0]);
		Discipline disp = course.parseDiscipline(comp[1]);
		discp.addTeacher(this);
	}
}

public class Student extends Person {
	void parseContext(String context, School school) throws BadEntryException {
		String[] cont = context.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("invalid line "+line);
		if (_course == null){
			_course = school.parseCourse(comp[0]);
			_course.addStudent(this);
		}

		Discipline disp = course.parseDiscipline(comp[1]);
		discp.enrollStudent(this);
	}
}



