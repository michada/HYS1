package es.uvigo.esei.daa.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uvigo.esei.daa.services.FacadeException;
import es.uvigo.esei.daa.services.PublicFacade;
import es.uvigo.esei.daa.services.pojo.AllEventPojo;
import es.uvigo.esei.daa.services.pojo.PublicEventPojo;

@Component
@Path("/event/{categoryId}")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {
	private final static Logger LOG = Logger.getLogger(EventResource.class
			.getSimpleName());

	@Autowired
	private PublicFacade facade;

	public EventResource() {

	}

	@GET
	public Response list(@CookieParam("token") String token,
			@PathParam("categoryId") String categoryId) {
		try {
			if (facade.checkToken(token) == null) {
				List<PublicEventPojo> list = this.facade
						.getPublicEventList(categoryId);
				return Response.ok(list, MediaType.APPLICATION_JSON).build();
			} else {
				List<AllEventPojo> list = this.facade
						.getAllEventList(categoryId);
				return Response.ok(list, MediaType.APPLICATION_JSON).build();
			}

		} catch (FacadeException e) {
			LOG.log(Level.SEVERE, "Error listing events", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
}
