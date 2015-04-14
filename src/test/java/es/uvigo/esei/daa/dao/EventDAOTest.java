package es.uvigo.esei.daa.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import es.uvigo.esei.daa.AbstractTestCase;
import es.uvigo.esei.daa.TestUtils;
import es.uvigo.esei.daa.entities.Event;
public class EventDAOTest extends AbstractTestCase {
	@Autowired
	private EventDAO dao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
		TestUtils.clearTestDatabase();
		//TestUtils.clearTestDatabase(sessionFactory);
	}
	
	@BeforeTransaction
	public void beforeTransaction() throws Exception {
		TestUtils.initTestDatabase();
	}
	
	@Before
	public void setUp() throws Exception {
		//this.dao = new PeopleDAO();
	}

	@AfterTransaction
	public void AfterTransaction() throws Exception {
		TestUtils.clearTestDatabase();
	}
	
	@After
	public void tearDown() throws Exception {
		//TestUtils.clearTestDatabase();
		//this.dao = null;
	}

	@Test
	public void testGetPublicEvents() throws DAOException {
		assertEquals(6, this.dao.getPublicEvents().size());
	}
	
	@Test
	public void testAllEvents() throws DAOException {
		assertEquals(8, this.dao.getAllEvents().size());
	}
}
