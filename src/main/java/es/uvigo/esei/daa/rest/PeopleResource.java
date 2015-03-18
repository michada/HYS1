package es.uvigo.esei.daa.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uvigo.esei.daa.entities.Person;
import es.uvigo.esei.daa.service.FacadeException;
import es.uvigo.esei.daa.service.PublicFacade;

@Component
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {
	private final static Logger LOG = Logger.getLogger("PeopleResource");
	
	@Autowired
	private PublicFacade facade;
	
	public PeopleResource() {

	}

	@GET
	public Response list() {
		try {
			return Response.ok(this.facade.getPeopleList(), MediaType.APPLICATION_JSON).build();
		} catch (FacadeException e) {
			LOG.log(Level.SEVERE, "Error listing people", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/{id}")
	public Response get(
		@PathParam("id") int id
	) {
		try {
			return Response.ok(this.facade.getPersonById(id), MediaType.APPLICATION_JSON).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} /*catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error getting a person", e);
			return Response.serverError().entity(e.getMessage()).build();
		}*/
	}

	@DELETE
	@Path("/{id}")
	public Response delete(
		@PathParam("id") int id
	) {
		try {
			this.facade.deletePersonById(id);
			
			return Response.ok(id).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (FacadeException e) {
			LOG.log(Level.SEVERE, "Error deleting a person", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modify(Person person) {
		try {
			return Response.ok(this.facade.modifyPerson(person)).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (FacadeException e) {
			LOG.log(Level.SEVERE, "Error modifying a person", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Person person) {
		try {
			return Response.ok(this.facade.addPerson(person)).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (FacadeException e) {
			LOG.log(Level.SEVERE, "Error adding a person", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
}
