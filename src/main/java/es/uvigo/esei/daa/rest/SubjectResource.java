package es.uvigo.esei.daa.rest;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uvigo.esei.daa.dao.SubjectDAO;

@Component
@Path("/subject")
@Produces(MediaType.APPLICATION_JSON)
public class SubjectResource {
	
	private final static Logger LOG = Logger.getLogger("SubjectResource");
	
	@Autowired
	private SubjectDAO subjectDAO;

	public SubjectResource() {
		
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response list() {
		return Response.ok(this.subjectDAO.list(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}")
	public Response get(
		@PathParam("id") int id
	) {
		try {
			return Response.ok(this.subjectDAO.get(id), MediaType.APPLICATION_JSON).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} 
	}

}
