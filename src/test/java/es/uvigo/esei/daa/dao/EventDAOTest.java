package es.uvigo.esei.daa.dao;

import static org.junit.Assert.assertEquals;

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
import es.uvigo.esei.daa.bean.EventFilterBean;
import es.uvigo.esei.daa.bean.PagBean;
import es.uvigo.esei.daa.entities.Location;
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
	
	@Test
	public void testListEvents() throws DAOException {
		PagBean pagBean = new PagBean();
		pagBean.setNumElemPag(5);
		pagBean.setNumPag(0);
		
		EventFilterBean eventFilterBean = new EventFilterBean();
		
		Location srcLocation = new Location();
		srcLocation.setLatitude(0.0);
		srcLocation.setLongitude(0.0);
		eventFilterBean.setSrcLocation(srcLocation);
		
		assertEquals(5, this.dao.listEvents(
				eventFilterBean, pagBean).size());
	}
}
