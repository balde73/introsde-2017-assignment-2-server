package rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import rest.dao.ActivityTypeDao;
import rest.dao.ActivityTypeDaoImpl;
import rest.model.ActivityType;

public class ActivityTypeResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	ActivityTypeDao activityTypeDao;
	
	public ActivityTypeResource(UriInfo uriInfo, Request request) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.activityTypeDao = new ActivityTypeDaoImpl();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getActivityType() {
		System.out.println("Getting activitytype list...");
		List<ActivityType> activityTypes = this.activityTypeDao.findAll();
		return Response.ok().entity(new GenericEntity<List<ActivityType>>(activityTypes){}).build();
	}
	
}
