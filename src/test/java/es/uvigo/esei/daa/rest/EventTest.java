package es.uvigo.esei.daa.rest;

import static es.uvigo.esei.daa.TestUtils.assertOkStatus;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
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
import es.uvigo.esei.daa.entities.Event;

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
	}

	@BeforeTransaction
	public void verifyInitialDatabaseState() throws Exception {
		// logic to verify the initial state before a transaction is started
		TestUtils.initTestDatabase();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();

		// Session session = sessionFactory.getCurrentSession();
		// TestUtils.initTestDatabase(sessionFactory);
		/*
		 * session.getTransaction().commit(); session.flush();
		 * session.beginTransaction(); session.flush();
		 */
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

		//super.set("sessionFactory", sessionFactory);
		
		rc.register(EventResource.class);
		//rc.register(SpringLifecycleListener.class);
		rc.register(RequestContextFilter.class);
		//rc.register(JacksonJsonProvider.class);
		rc.register(JacksonFeature.class);
		
		rc.property("contextConfigLocation",
				"classpath:spring/applicationContext.xml");
		return rc;
	}

	@Override
	protected void configureClient(ClientConfig config) {
		super.configureClient(config);

		//super.set("sessionFactory", sessionFactory);

		//config.register(JacksonJsonProvider.class);
		config.property("com.sun.jersey.api.json.POJOMappingFeature",
				Boolean.TRUE);
	}

	@Test
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void testList() throws IOException {
		final Response response = target("event").request().get();
		assertOkStatus(response);

		final List<Event> events = response
				.readEntity(new GenericType<List<Event>>() {
				});
		assertEquals(1, events.size());
	}

	/*@Test
	public void testGet() throws IOException {
		final Response response = target("people/4").request().get();
		assertOkStatus(response);

		final Person person = response.readEntity(Person.class);
		assertEquals(4, person.getId());
		assertEquals("María", person.getName());
		assertEquals("Márquez", person.getSurname());
	}

	@Test
	public void testGetInvalidId() throws IOException {
		assertBadRequestStatus(target("people/100").request().get());
	}

	@Test
	public void testAdd() throws IOException {
//		final Form form = new Form();
//		form.param("name", "Xoel");
//		form.param("surname", "Ximénez");
		
		Person p = new Person();
		p.setName("Xoel");
		p.setSurname("Ximénez");

		final Response response = target("people").request(
				MediaType.APPLICATION_JSON_TYPE)
		// MediaType.APPLICATION_JSON)
				.post(Entity.entity(p,
				// MediaType.APPLICATION_JSON));
						MediaType.APPLICATION_JSON_TYPE));
		assertOkStatus(response);

		final Person person = response.readEntity(Person.class);
		assertEquals(11, person.getId());
		assertEquals("Xoel", person.getName());
		assertEquals("Ximénez", person.getSurname());
	}

	@Test
	public void testAddMissingName() throws IOException {
//		final Form form = new Form();
//		form.param("surname", "Ximénez");
		
		Person p = new Person();
		p.setSurname("Ximénez");

		final Response response = target("people").request(
				MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(p,
						MediaType.APPLICATION_JSON_TYPE));

		assertBadRequestStatus(response);
	}

	@Test
	public void testAddMissingSurname() throws IOException {
//		final Form form = new Form();
//		form.param("name", "Xoel");
		
		Person p = new Person();
		p.setName("Xoel");

		final Response response = target("people").request(
				MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(p,
						MediaType.APPLICATION_JSON_TYPE));

		assertBadRequestStatus(response);
	}

	@Test
	public void testModify() throws IOException {
//		final Form form = new Form();
//		form.param("name", "Marta");
//		form.param("surname", "Méndez");
		
		Person p = new Person();
		p.setId(4);
		p.setName("Marta");
		p.setSurname("Méndez");

		final Response response = target("people/4").request(
				MediaType.APPLICATION_JSON_TYPE)
				.put(Entity.entity(p,
						MediaType.APPLICATION_JSON_TYPE));
		assertOkStatus(response);

		final Person person = response.readEntity(Person.class);
		assertEquals(4, person.getId());
		assertEquals("Marta", person.getName());
		assertEquals("Méndez", person.getSurname());
	}

	@Test
	public void testModifyName() throws IOException {
//		final Form form = new Form();
//		form.param("name", "Marta");
		
		Person p = new Person();
		p.setName("Marta");

		final Response response = target("people/4").request(
				MediaType.APPLICATION_JSON_TYPE)
				.put(Entity.entity(p,
						MediaType.APPLICATION_JSON_TYPE));

		assertBadRequestStatus(response);
	}

	@Test
	public void testModifySurname() throws IOException {
//		final Form form = new Form();
//		form.param("surname", "Méndez");
		
		Person p = new Person();
		p.setSurname("Méndez");

		final Response response = target("people/4").request(
				MediaType.APPLICATION_JSON_TYPE)
				.put(Entity.entity(p,
						MediaType.APPLICATION_JSON_TYPE));

		assertBadRequestStatus(response);
	}

	@Test
	public void testModifyInvalidId() throws IOException {
//		final Form form = new Form();
//		form.param("name", "Marta");
//		form.param("surname", "Méndez");
		
		Person p = new Person();
		p.setName("Marta");
		p.setSurname("Méndez");

		final Response response = target("people/100").request(
				MediaType.APPLICATION_JSON_TYPE)
				.put(Entity.entity(p,
						MediaType.APPLICATION_JSON_TYPE));

		assertBadRequestStatus(response);
	}

	@Test
	public void testDelete() throws IOException {
		final Response response = target("people/4").request().delete();
		assertOkStatus(response);

		assertEquals(4, (int) response.readEntity(Integer.class));
	}

	@Test
	public void testDeleteInvalidId() throws IOException {
		assertBadRequestStatus(target("people/100").request().delete());
	}*/
}
