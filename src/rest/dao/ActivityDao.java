package rest.dao;

import java.util.List;

import rest.model.Activity;
import rest.model.ActivityType;
import rest.model.Person;

public interface ActivityDao extends Dao<Activity, Integer>{
	public List<Activity> getAllActivityByPersonAndType(Person p, ActivityType a);
	public Activity merge(Activity a);
}
