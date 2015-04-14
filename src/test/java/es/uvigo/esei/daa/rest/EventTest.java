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
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.filter.RequestContextFilter;

import es.uvigo.esei.daa.TestUtils;
import es.uvigo.esei.daa.services.pojo.AllEventPojo;
import es.uvigo.esei.daa.services.pojo.PublicEventPojo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class EventTest extends JerseyTest {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	TransactionTemplate txTemplate;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
		TestUtils.clearTestDatabase();
	}

	@BeforeTransaction
	public void verifyInitialDatabaseState() throws Exception {
		// logic to verify the initial state before a transaction is started
		TestUtils.initTestDatabase();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@AfterTransaction
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
				Boolean.TRUE);
	}

	@Test
	public void testList() throws IOException {
		final Response response = target("event").request().get();
		assertOkStatus(response);

		final List<PublicEventPojo> events = response
				.readEntity(new GenericType<List<PublicEventPojo>>() {
				});
		assertEquals(6, events.size());
	}

	@Test
	public void testAllEventList() throws IOException {
		final WebTarget target = target("event");
		final Response response = target.request()
				.cookie("token", "bXJqYXRvOm1yamF0bw==").get();
		assertOkStatus(response);

		final List<AllEventPojo> events = response
				.readEntity(new GenericType<List<AllEventPojo>>() {
				});
		assertEquals(8, events.size());
	}
}
