package rest.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import rest.dao.ActivityDao;
import rest.dao.ActivityDaoImpl;
import rest.dao.ActivityTypeDao;
import rest.dao.ActivityTypeDaoImpl;
import rest.dao.PersonDao;
import rest.dao.PersonDaoImpl;
import rest.model.Activity;
import rest.model.ActivityType;
import rest.model.Person;
import rest.utils.DateHandler;

@Stateless
@LocalBean
public class PersonActivityResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer userId;
	String activityType;
	
	ActivityTypeDao activityTypeDao;
	ActivityDao activityDao;
	PersonDao personDao;
	
	public PersonActivityResource(UriInfo uriInfo, Request request, Integer id, String activityType) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.userId = id;
		this.activityType = activityType;
		
		this.activityDao = new ActivityDaoImpl();
		this.activityTypeDao = new ActivityTypeDaoImpl();
		this.personDao = new PersonDaoImpl();
	}
	
	public List<Activity> getActivityByPersonAndType() {	    
		ActivityType activityType = this.activityTypeDao.findByName(this.activityType);
		Person person = this.personDao.findById(this.userId);
		List<Activity> activities = this.activityDao.getAllActivityByPersonAndType(person, activityType);
		return activities;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	public Response getActivityByPersonAndType(@QueryParam("before") String endDateString, @QueryParam("after") String startDateString) {
		List<Activity> activities = this.getActivityByPersonAndType();
		System.out.println(endDateString);
		if(endDateString == null && startDateString == null) {
			GenericEntity entity = new GenericEntity<List<Activity>>(activities) {};
			return Response.status(Response.Status.OK).entity(entity).build();
		}
		if(endDateString == null || startDateString == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		
		List<Activity> approvedActivities = new ArrayList<Activity>();
		
		DateHandler startDate = new DateHandler(startDateString);
		DateHandler endDate = new DateHandler(endDateString);
		if(!startDate.isValid() || !endDate.isValid()) {
			Response.status(Response.Status.BAD_REQUEST).build();
		}
		for(Activity a : activities) {
			Date date = a.getStartDate();
			if(startDate.isBefore(date) && endDate.isAfter(date)) {
				approvedActivities.add(a);
			}
		}
		GenericEntity entity = new GenericEntity<List<Activity>>(approvedActivities) {};
		return Response.status(Response.Status.OK).entity(entity).build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response modifiyActivityByPersonAndType(Activity newActivity) {
		ActivityType activityType = this.activityTypeDao.findByName(this.activityType);
		if(activityType==null) {
			System.out.println("no activitytype found");
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Person person = this.personDao.findById(this.userId);
		if(person == null) {
			System.out.println("no person found");
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Activity addActivity = new Activity();
		addActivity.setName(newActivity.getName());
		addActivity.setDescription(newActivity.getDescription());
		addActivity.setStartDate(newActivity.getStartDate());
		addActivity.setPlace(newActivity.getPlace());
		addActivity.setActivityType(activityType);
		addActivity.setPerson(person);
		
		Activity addedActivity = this.activityDao.add(addActivity);
		return Response.status(Response.Status.OK).entity(addedActivity).build();
	}
	
	@GET
	@Path("/{activity_id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getActivityByPersonAndTypeAndId(@PathParam("activity_id") int id) {
		List<Activity> activities = this.getActivityByPersonAndType();
		for(Activity a : activities) {
			if(id == a.getId())
				return Response.status(Response.Status.OK).entity(a).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("/{activity_id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response modifyActivityByPersonAndTypeAndId(@PathParam("activity_id") int id, ActivityType toActivityType) {
		System.out.println("####");
		System.out.println(toActivityType);
		List<Activity> activities = this.getActivityByPersonAndType();
		Activity fromActivity = null;
		for(Activity a : activities) {
			if(id == a.getId())
				fromActivity = a;
		}
		fromActivity.setActivityType(toActivityType);
		System.out.println(fromActivity);
		Activity newActivityType = activityDao.merge(fromActivity);
		return Response.status(Response.Status.OK).entity(newActivityType).build();
	}
	
}
