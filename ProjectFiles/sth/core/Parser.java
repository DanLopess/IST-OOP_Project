package sth.core;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;

import java.util.Collection;
import java.util.ArrayList;

import sth.core.exception.BadEntryException;

public class Parser {

  private School _school;
  private Person _person;

  Parser(School s) {
    _school = s;
  }

  void parseFile(String fileName) throws IOException, BadEntryException {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }
  }

  private void parseLine(String line) throws BadEntryException {
    if (line.startsWith("#"))
      parseContext(line);
    else
      parseHeaderPerson(line);
  }

  private void parseHeaderPerson(String header) throws BadEntryException {
    String[] components = header.split("\\|");
    int id;
    int phone;

    if (components.length != 4)
      throw new BadEntryException("Invalid line " + line);

    id = Integer.parseInt(components[1]);
    phone = Integer.parseInt(components[2]);

    switch (components[0]) {
      case "FUNCIONÁRIO":
        _person = new Employee(id, ohone, components[3]);
        break;

      case "DOCENTE":
        _person = new Teacher(id, ohone, components[3]);
        break;

      case "ALUNO":
        _person = new Student(id, ohone, components[3], false);
        break;

      case "DELEGADO":
        _person = new Student(id, ohone, components[3], true);
        break;

      default:
        throw new BadEntryException("Invalid token " + components[0] + "in line describing a person");
     }

    _school.addPerson(person);
  }

  private void parseContext(String line) throws BadEntryException {
    String lineContex = line.substring(2);

    _person.parseContext(lineContext, school);
  }
}