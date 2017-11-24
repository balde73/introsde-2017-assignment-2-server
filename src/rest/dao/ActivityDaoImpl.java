package rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import rest.model.Activity;
import rest.model.ActivityType;
import rest.model.Person;

public class ActivityDaoImpl extends JpaDao<Activity, Integer> implements ActivityDao{
	public ActivityDaoImpl() {
		super(Activity.class);
	}

	@Override
	public List<Activity> getAllActivityByPersonAndType(Person person, ActivityType activityType) {
		try {
			return this.getEntityManager().createNamedQuery("Activity.findAllByPersonAndType", Activity.class)
			.setParameter("person", person)
			.setParameter("activityType", activityType)
			.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Activity merge(Activity activity) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(activity);
		tx.commit();
		return activity;
	}
	
	
}
