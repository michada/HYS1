package es.uvigo.esei.daa.facade;

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
import es.uvigo.esei.daa.facade.fake.LocationFake;
import es.uvigo.esei.daa.services.FacadeException;
import es.uvigo.esei.daa.services.PublicFacade;
public class PublicFacadeTest extends AbstractTestCase {
	@Autowired
	private PublicFacade facade;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
		TestUtils.clearTestDatabase();
	}
	
	@BeforeTransaction
	public void beforeTransaction() throws Exception {
		TestUtils.initTestDatabase();
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@AfterTransaction
	public void AfterTransaction() throws Exception {
		TestUtils.clearTestDatabase();
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPublicEvents() throws FacadeException {
		assertEquals(6, this.facade.getPublicEventList().size());
	}
	
	@Test
	public void testAllEvents() throws FacadeException {
		assertEquals(8, this.facade.getAllEventList().size());
	}
	
	// http://www.movable-type.co.uk/scripts/latlong.html
	@Test
	public void testCheckDistanceIsIn() {
		// Grados decimales
		LocationFake l1 = new LocationFake(10.0, 20.0);
		LocationFake l2 = new LocationFake(11.0, 19.0);
		
		assertEquals(true, facade.checkDistance(l1, l2, 152.71));
	}
	
	@Test
	public void testCheckDistanceIsInBoundaries() {
		// Grados decimales
		LocationFake l1 = new LocationFake(0.0, 0.0);
		LocationFake l2 = new LocationFake(20.0, 20.0);
		
		assertEquals(true, facade.checkDistance(l1, l2, 3112.));
	}
	
	@Test
	public void testCheckDistanceIsNotInLong() {
		// Grados decimales
		LocationFake l1 = new LocationFake(0.0, 0.0);
		LocationFake l2 = new LocationFake(20.1, 0.0);
		
		assertEquals(false, facade.checkDistance(l1, l2, 2224.));
	}
	
	@Test
	public void testCheckDistanceIsNotInLatitut() {
		// Grados decimales
		LocationFake l1 = new LocationFake(0.0, 0.0);
		LocationFake l2 = new LocationFake(0.0, 20.1);
		
		assertEquals(false, facade.checkDistance(l1, l2, 2224.));
	}
}
