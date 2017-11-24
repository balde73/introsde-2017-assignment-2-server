package rest.dao;

import rest.model.ActivityType;

public interface ActivityTypeDao extends Dao<ActivityType, String>{
	public ActivityType findByName(String activityTypeName);
}
