package es.uvigo.esei.daa.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uvigo.esei.daa.services.PublicFacade;
import es.uvigo.esei.daa.services.pojo.PublicCategoryPojo;

@Component
@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
	@Autowired
	private PublicFacade facade;

	public CategoryResource() {

	}

	@GET
	public Response list() {
		List<PublicCategoryPojo> list = facade.getCategories();
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
}