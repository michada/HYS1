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

import es.uvigo.esei.daa.entities.Event;
import es.uvigo.esei.daa.services.FacadeException;
import es.uvigo.esei.daa.services.PublicFacade;

@Component
@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {
	private final static Logger LOG = Logger.getLogger("EventResource");
	
	@Autowired
	private PublicFacade facade;
	
	public EventResource() {

	}

	@GET
	public Response list() {
		try {
			return Response.ok(this.facade.getEventList(), MediaType.APPLICATION_JSON).build();
		} catch (FacadeException e) {
			LOG.log(Level.SEVERE, "Error listing people", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
}
