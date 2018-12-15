package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.Notification;
import java.util.List;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;
import java.util.Iterator;
import java.io.*;

public abstract class Person implements java.io.Serializable, Observer {
	private int _id;
	private String _name;
	private int _phoneNumber;
	private List<Notification> _notifications;
  	/** Serial number for serialization. */
  	private static final long serialVersionUID = 201810051538L;
	/*
	* Person - class constructor
	*/
	public Person (int id, String name, int phoneNumber) throws BadEntryException {
		if (id >= 100000 && id <= 999999) {
			_id = id;
			_name = name;
			_phoneNumber = phoneNumber;
			_notifications = new ArrayList<Notification>();;
		} else{
			throw new BadEntryException("Invalid id " + id);
		}
	}

	/*
	* setPhoneNumber - changes Person's phone number
	*/
	void setPhoneNumber(int phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	/*
	* Returns person's name
	*/
	public String getName() {
		return _name;
	}

	/*
	* getId - Returns person's id
	*/
	public int getId() {
		return _id;
	}

	/*
	* getPhoneNumber - Returns person's phone number
	*/
	public int getPhoneNumber() {
		return _phoneNumber;
	}

	String getNotifications() {
		Iterator<Notification> iterator = _notifications.iterator();
		String notifications = "";
		while(iterator.hasNext()) {
			Notification n = iterator.next();
			notifications = notifications + n.getMessage() + "\n";
		}
		clearNotifications();
		return notifications;
	}

	boolean hasNotifications() {
		if (_notifications.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	void clearNotifications() {
		_notifications.clear();
	}

	void addNotification(Notification n) {
		System.out.println("Added notification: \n" + n.getMessage() + "\n");
		_notifications.add(n);
	}

	/* 
	* toString - 'Compiles' person's info into a String 
	*/
	public String toString(){
		return _id+"|"+_phoneNumber+"|"+_name;
	}

	/**
	 * Parses the context information for a person from the import file.
	 * This method defines the default behavior: no extra information is needed
	 * thus it throws the exception.
	 **/
	void parseContext(String context, School school) throws BadEntryException {
		throw new BadEntryException("Should not have extra context: " + context);
	}

@Override
	public void update(Observable o, Object arg) {
		this.addNotification((Notification)arg);
	}
}
