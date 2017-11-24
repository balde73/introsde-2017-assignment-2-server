package rest.resources;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
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
import rest.utils.DBHandler;

@Stateless
@LocalBean
@Path("/")
public class LifeCoachResource {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	DBHandler db;
	PersonDao personDao;
	ActivityDao activityDao;
	ActivityTypeDao activityTypeDao;
	
	public LifeCoachResource() throws ParseException {
		// Is database OK? If not populate!
		System.out.println("--- richiesta ---");
		this.db = new DBHandler();
		if(this.db.isEmpty()) {
			System.out.println("--- POPULATE ---");
			this.db.populate();
		}
	}
	
	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	public String getPersonsBrowser() {
		System.out.println("benvenuto");
		return "benvenuto";
	}
	
	@Path("/person")
	public PersonCollectionResource getPerson() {
		return new PersonCollectionResource(uriInfo, request);
	}
	
	@Path("/person/{personId}")
	public PersonResource getPerson(@PathParam("personId") Integer id) {
		return new PersonResource(uriInfo, request, id);
	}
	
	@Path("/person/{personId}/{activity_type}")
	public PersonActivityResource test(@PathParam("personId") Integer id, @PathParam("activity_type") String activityType) {
		return new PersonActivityResource(uriInfo, request, id, activityType);
	}
	
	@Path("/activity_types")
	public ActivityTypeResource getActivtyTypes() {
		System.out.println("activity_types!!");
		return new ActivityTypeResource(uriInfo, request);
	}
}
