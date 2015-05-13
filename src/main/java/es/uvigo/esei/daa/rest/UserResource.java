package es.uvigo.esei.daa.rest;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource  {
	
}
