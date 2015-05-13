package es.uvigo.esei.daa.rest;

import static es.uvigo.esei.daa.TestUtils.assertOkStatus;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
				Boolean.TRUE).register(JacksonFeature.class);
	}

	@Test
	public void testPublicList() throws IOException {
		final WebTarget target = target("event").path("0")
				.queryParam("page", 1).queryParam("filters", "programmed")
				.queryParam("filters", "cancelled")
				.queryParam("filters", "completed");
		final Response response = target.request().get();
		assertOkStatus(response);

		PublicEventPojoPag eventsPag = response
				.readEntity(new GenericType<PublicEventPojoPag>() {
				});

		final List<PublicEventPojo> events = eventsPag.getListEvents();

		assertEquals(15, events.size());
		assertEquals(19, eventsPag.getPageBean().getNumElemTotal());
	}

	@Test
	public void testAllEventList() throws IOException {
		final WebTarget target = target("event").path("0")
				.queryParam("page", 2).queryParam("filters", "programmed")
				.queryParam("filters", "cancelled")
				.queryParam("filters", "completed");
		final Response response = target.request()
				.cookie("token", "T21hcjpMdWNhcw==").get();
		assertOkStatus(response);

		AllEventPojoPag eventsPag = response
				.readEntity(new GenericType<AllEventPojoPag>() {
				});

		final List<AllEventPojo> events = eventsPag.getListEvents();

		assertEquals(15, events.size());
		assertEquals(31, eventsPag.getPageBean().getNumElemTotal());
	}

	@Test
	public void testSearchProgrammedPublicEventList() throws IOException {
		final WebTarget target = target("event").path("0")
				.queryParam("text", "terror")
				.queryParam("filters", "cancelled").queryParam("page", 1);
		final Response response = target.request().get();
		PublicEventPojoPag eventsPag = response
				.readEntity(new GenericType<PublicEventPojoPag>() {
				});
		final List<PublicEventPojo> events = eventsPag.getListEvents();
		assertEquals(1, events.size());
		assertEquals(1, eventsPag.getPageBean().getNumElemTotal());
	}

	@Test
	public void testSearchProgrammedAndCompletedBookPublicEventList()
			throws IOException {
		final WebTarget target = target("event").path("1")
				.queryParam("text", "harry").queryParam("filters", "cancelled")
				.queryParam("filters", "programmed").queryParam("page", 1);
		final Response response = target.request().get();
		PublicEventPojoPag eventsPag = response
				.readEntity(new GenericType<PublicEventPojoPag>() {
				});
		final List<PublicEventPojo> events = eventsPag.getListEvents();
		assertEquals(2, events.size());
		assertEquals(2, eventsPag.getPageBean().getNumElemTotal());
	}

	@Test
	public void testSearchCancelledFilmPublicAndPrivateEventList()
			throws IOException {
		final WebTarget target = target("event").path("2")
				.queryParam("text", "Semana")
				.queryParam("filters", "cancelled")
				.queryParam("page", 1);
		final Response response = target.request()
				.cookie("token", "T21hcjpMdWNhcw==").get();
		assertOkStatus(response);

		AllEventPojoPag eventsPag = response
				.readEntity(new GenericType<AllEventPojoPag>() {
				});

		final List<AllEventPojo> events = eventsPag.getListEvents();
		assertEquals(1, events.size());
		assertEquals(1, eventsPag.getPageBean().getNumElemTotal());
	}
	
	@Test
	public void testSearchEventAndNotFound() throws IOException {
		final WebTarget target = target("event").path("0")
				.queryParam("text", "NotExistantText")
				.queryParam("page", 1);
		final Response response = target.request()
				.cookie("token", "T21hcjpMdWNhcw==").get();
		assertOkStatus(response);

		AllEventPojoPag eventsPag = response
				.readEntity(new GenericType<AllEventPojoPag>() {
				});

		final List<AllEventPojo> events = eventsPag.getListEvents();
		assertTrue(events.isEmpty());
		assertEquals(0, eventsPag.getPageBean().getNumElemTotal());
	}
	
	@Test
	public void testListAllProgrammedEventsAndSetLocation() throws IOException {
		final WebTarget target = target("event").path("0")
				.queryParam("latitude", "42.335789299999995")
				.queryParam("longitude", "-7.863880999999998")
				.queryParam("filters", "programmed")
				.queryParam("page", 1);
		final Response response = target.request().get();
		assertOkStatus(response);
		AllEventPojoPag eventsPag = response
				.readEntity(new GenericType<AllEventPojoPag>() {
				});

		final List<AllEventPojo> events = eventsPag.getListEvents();
		assertEquals("Representación de capítulos de Anatomía de Grey", events.get(0).getTitle());
	}

}
