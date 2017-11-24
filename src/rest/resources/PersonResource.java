package rest.resources;

import rest.dao.PersonDao;
import rest.dao.PersonDaoImpl;
import rest.model.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Stateless
@LocalBean
public class PersonResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	int id;
	
	PersonDao personDao;
	
	public PersonResource(UriInfo uriInfo, Request request, int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.personDao = new PersonDaoImpl();
	}
	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getPerson() {
		Person person = personDao.findById(id);
		if (person == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(person).build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updatePerson(Person p) {
		Person person = personDao.findById(this.id);
		if(person == null) {
			System.out.println("error!");
			// there is no person in database with this.id
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Person updatedPerson = personDao.update(person, p);
		if(updatedPerson == null) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.status(Response.Status.OK).entity(updatedPerson).build();
	}

	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response deletePerson() {
		personDao.remove(this.id);
		return Response.status(Response.Status.OK).build();
	}
}
