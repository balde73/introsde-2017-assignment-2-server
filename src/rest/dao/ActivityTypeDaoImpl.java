package rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import rest.model.Activity;
import rest.model.ActivityType;

public class ActivityTypeDaoImpl extends JpaDao<ActivityType, String> implements ActivityTypeDao{

	public ActivityTypeDaoImpl() {
		super(ActivityType.class);
	}
	
	public ActivityType findByName(String activityTypeName) {
		return this.findById(activityTypeName);
	}

}
