package es.uvigo.esei.daa.rest;

import static es.uvigo.esei.daa.TestUtils.assertOkStatus;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.filter.RequestContextFilter;

import es.uvigo.esei.daa.TestUtils;
import es.uvigo.esei.daa.services.pojo.AllEventPojo;
import es.uvigo.esei.daa.services.pojo.AllEventPojoPag;
import es.uvigo.esei.daa.services.pojo.PublicEventPojo;
import es.uvigo.esei.daa.services.pojo.PublicEventPojoPag;

public class EventTest extends JerseyTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
		TestUtils.clearTestDatabase();
	}

	@Before
	public void verifyInitialDatabaseState() throws Exception {
		// logic to verify the initial state before a transaction is started
		TestUtils.initTestDatabase();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void afterTransaction() throws Exception {
		TestUtils.clearTestDatabase();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	protected Application configure() {
		ResourceConfig rc = new ResourceConfig();
		
		rc.register(EventResource.class);
		rc.register(CategoryResource.class);

		rc.register(RequestContextFilter.class);
		rc.register(JacksonFeature.class);

		rc.property("contextConfigLocation",
				"classpath:spring/applicationContext.xml");
		return rc;
	}

	@Override
	protected void configureClient(ClientConfig config) {
		super.configureClient(config);
		config.property("com.sun.jersey.api.json.POJOMappingFeature",
				Boolean.TRUE)
				.register(JacksonFeature.class);
	}

	@Test
	public void testPublicList() throws IOException {

		final Response response = target("event/0?page=1&filters=programmed&filters=cancelled&filters=completed").
				request().
				get();
		assertOkStatus(response);
		
		PublicEventPojoPag eventsPag = response
				.readEntity(new GenericType<PublicEventPojoPag>() {
				});
		
		final List<PublicEventPojo> events = eventsPag.getListEvents();
		
		assertEquals(9, events.size());
	}

	@Test
	public void testAllEventList() throws IOException {
		final WebTarget target = target("event/0?page=2&filters=programmed&filters=cancelled&filters=completed");
		final Response response = target.request()
				.cookie("token", "T21hcjpMdWNhcw==").get();
		assertOkStatus(response);
		
		AllEventPojoPag eventsPag = response
			.readEntity(new GenericType<AllEventPojoPag>(){});
		
		final List<AllEventPojo> events = eventsPag.getListEvents();
		
		assertEquals(15, events.size());
	}
	
	@Test
	public void testSearchProgrammedPublicEventList() throws IOException {
		final WebTarget target = target("event/0?text=terror&filters=cancelled&page=1");
		final Response response = target.request()
				.get();
		PublicEventPojoPag eventsPag = response
				.readEntity(new GenericType<PublicEventPojoPag>() {
				});
		final List<PublicEventPojo> events = eventsPag.getListEvents();	
		assertEquals(1, events.size());
	}
	
	@Test
	public void testSearchProgrammedAndCompletedBookPublicEventList() throws IOException {
		final WebTarget target = target("event/1?text=xyxy&filters=cancelled&filters=programmed&page=1");
		final Response response = target.request()
				.get();
		PublicEventPojoPag eventsPag = response
				.readEntity(new GenericType<PublicEventPojoPag>() {
				});
		final List<PublicEventPojo> events = eventsPag.getListEvents();	
		assertEquals(1, events.size());
	}
	
	@Test
	public void testSearchCancelledFilmPublicAndPrivateEventList() throws IOException {
		final WebTarget target = target("event/2?text=xyxy&filters=cancelled&page=1");
		final Response response = target.request()
				.cookie("token", "T21hcjpMdWNhcw==").get();
		assertOkStatus(response);
		
		AllEventPojoPag eventsPag = response
				.readEntity(new GenericType<AllEventPojoPag>() {
				});
		
		final List<AllEventPojo> events = eventsPag.getListEvents();
		assertEquals(1, events.size());
	}
	

}
