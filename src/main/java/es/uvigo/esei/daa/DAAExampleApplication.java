package es.uvigo.esei.daa;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

import es.uvigo.esei.daa.rest.PeopleResource;
import es.uvigo.esei.daa.rest.SubjectResource;

public class DAAExampleApplication extends ResourceConfig {
	public DAAExampleApplication() {
		// register application resources
		register(PeopleResource.class);
		register(SubjectResource.class);
		
		// register filters
		// which is a Spring filter that provides a bridge between JAX-RS and Spring request attributes
		register(RequestContextFilter.class);
		
		// register features
		// which is a feature that registers Jackson JSON providers � you need it for the application to understand JSON data
		register(JacksonFeature.class);
		
		//register(CORSResponseFilter.class);
	}
}