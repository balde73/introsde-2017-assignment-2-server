package rest.utils;

import java.util.ArrayList;
import java.util.List;

import rest.dao.ActivityDao;
import rest.dao.ActivityDaoImpl;
import rest.dao.ActivityTypeDao;
import rest.dao.ActivityTypeDaoImpl;
import rest.dao.PersonDao;
import rest.dao.PersonDaoImpl;
import rest.model.Activity;
import rest.model.ActivityType;
import rest.model.Person;

public class DBHandler {
	
	public boolean isEmpty() {
		PersonDao personDao = new PersonDaoImpl();
		Person person = personDao.findById(10003);
		return person == null;
	}
	
	public void populate() {
		
		ActivityTypeDao activityTypeDao = new ActivityTypeDaoImpl();
		PersonDao personDao = new PersonDaoImpl();
		
		ActivityType at1 = new ActivityType("Sport");
		ActivityType at2 = new ActivityType("Social");
		ActivityType at3 = new ActivityType("Education");
		
		activityTypeDao.add(at1);
		activityTypeDao.add(at2);
		activityTypeDao.add(at3);
		
		Person p1 = new Person(10001, "Baldessari", "Federico", "balde", "1993-01-07", "federico.baldessari@studenti.unitn.it");
		Person p2 = new Person(10002, "Pizzini", "Martina", "marty", "1995-05-05", "martina.pizzini@studenti.unitn.it");
		Person p3 = new Person(10003, "Tassinari", "Giovanni", "giova", "1994-04-01", "giovanni.tassinari@studenti.unitn.it");
		Person p4 = new Person(10004, "Minigozzi", "Giada", "jade", "1993-07-01", "giada.minigozzi@studenti.unitn.it");
		Person p5 = new Person(10005, "Bolt", "Usain", "usain", "1986-08-21", "usain.bolt@iaaf.com");
		
		Activity a1 = new Activity(10001, "tennis with friends", "tennis with friends description","Milano", "2017-12-10T11:50:00.000", at1, p1);
		Activity a2 = new Activity(10002, "meeting Barb", "meeting Barb description", "Roma", "2017-12-13T11:50:00.000", at2, p1);
		Activity a3 = new Activity(10003, "golf at Camp North","golf at Camp North description","Verona", "2017-12-24T09:40:00.000", at1, p2);
		Activity a4 = new Activity(10004, "football at Camp North","football at Camp North description","Rovereto", "2018-01-13T11:50:00.000", at1, p3);
		Activity a5 = new Activity(10005, "sde lesson", "sde lesson description","Milano","2018-01-13T09:00:00.000", at3, p4);
		Activity a6 = new Activity(10006, "running for fun","running for fun description","Hong Kong", "2018-01-13T07:50:00.000", at1, p5);
		
		p1.addActivity(a1);
		p1.addActivity(a2);
		p2.addActivity(a3);
		p3.addActivity(a4);
		p4.addActivity(a5);
		p5.addActivity(a6);
		
		personDao.add(p1);
		personDao.add(p2);
		personDao.add(p3);
		personDao.add(p4);
		personDao.add(p5);
	}
}
