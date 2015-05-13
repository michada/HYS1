package es.uvigo.esei.daa.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uvigo.esei.daa.services.FacadeException;
import es.uvigo.esei.daa.services.PublicFacade;
import es.uvigo.esei.daa.services.pojo.PublicCategoryPojo;

@Component
@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
	private final static Logger LOG = Logger.getLogger(CategoryResource.class.getSimpleName());
	
	@Autowired
	private PublicFacade facade;

	public CategoryResource() {

	}

	@GET
	public Response list() {
		try {
			List<PublicCategoryPojo> list;
			list = facade.getCategoryList();
			return Response.ok(list, MediaType.APPLICATION_JSON).build();
		} catch (FacadeException e) {
			LOG.log(Level.SEVERE, "Error listing categories", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
}