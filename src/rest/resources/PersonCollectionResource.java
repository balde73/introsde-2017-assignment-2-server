package rest.resources;

import rest.dao.PersonDao;
import rest.dao.PersonDaoImpl;
import rest.model.Person;

import java.util.List;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/*
 * TODO 
 * - There is a problem with the EntityManager injection through @PersistenceUnit or @PersistenceContext
 * - will look into it later
 */

@Stateless
@LocalBean
public class PersonCollectionResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	PersonDao personDao;
	
	public PersonCollectionResource(UriInfo uriInfo, Request request) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.personDao = new PersonDaoImpl();
	}

	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	public Response getPeople() {
		System.out.println("Getting list of people...");
		try {
		    List<Person> list_person = this.personDao.findAll();
		    GenericEntity entity = new GenericEntity<List<Person>>(list_person) {};
			return Response.ok(entity).build();
		}
		catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addPerson(Person p) {
		System.out.println(p.getActivityPreferenceProfile());
	    Person addedPerson = this.personDao.add(new Person(p));
	    if(addedPerson == null) {
	    	// TODO: fix status response. Not always a bad request
	    	return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    return Response.status(Response.Status.OK).entity(addedPerson).build();
	}
}
