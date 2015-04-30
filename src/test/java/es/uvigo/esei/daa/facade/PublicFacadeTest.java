package es.uvigo.esei.daa.facade;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
import es.uvigo.esei.daa.services.FacadeException;
import es.uvigo.esei.daa.services.PublicFacade;
import es.uvigo.esei.daa.services.pojo.PublicEventPojo;
import es.uvigo.esei.daa.util.LocationUtil;
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
		//assertEquals(6, this.facade.getPublicEventList().size());
	}
	
	@Test
	public void testAllEvents() throws FacadeException {
		//assertEquals(8, this.facade.getAllEventList().size());
	}
	
	@Test
	public void testGetPublicEventsByCategory() throws FacadeException {
		PagBean pagBean = new PagBean();
		pagBean.setNumElemPag(5);
		pagBean.setNumPag(0);
		
		EventFilterBean eventFilterBean = new EventFilterBean();
		
		Location srcLocation = new Location();
		srcLocation.setLatitude(0.0);
		srcLocation.setLongitude(0.0);
		
		eventFilterBean.getFilters().add(
				Restrictions.eq("category.id",new Integer(1)));
		List<PublicEventPojo> events =  this.facade.getPublicEventList(
				srcLocation, eventFilterBean, pagBean);
		
		assertEquals(2, pagBean.getNumElemTotal());
	}
	
	@Test
	public void testGetPublicEventsPaginated() throws FacadeException {
		PagBean pagBean = new PagBean();
		pagBean.setNumElemPag(5);
		pagBean.setNumPag(0);
		
		EventFilterBean eventFilterBean = new EventFilterBean();
		
		Location srcLocation = new Location();
		srcLocation.setLatitude(0.0);
		srcLocation.setLongitude(0.0);
		
		this.facade.getPublicEventList(
				srcLocation, eventFilterBean, pagBean);
		
		assertEquals(6, pagBean.getNumElemTotal());
	}
	
	@Test
	public void testAllEventsPaginated() throws FacadeException {
		PagBean pagBean = new PagBean();
		pagBean.setNumElemPag(5);
		pagBean.setNumPag(0);
		
		EventFilterBean eventFilterBean = new EventFilterBean();
		
		Location srcLocation = new Location();
		srcLocation.setLatitude(0.0);
		srcLocation.setLongitude(0.0);
		eventFilterBean.setSrcLocation(srcLocation);
		
		this.facade.getAllEventList(srcLocation,
				eventFilterBean, pagBean);
		
		assertEquals(8, pagBean.getNumElemTotal());
	}
	
	// http://www.movable-type.co.uk/scripts/latlong.html
	@Test
	public void testCheckDistanceIsIn() {
		// Grados decimales
		Location l1 = new Location();
		l1.setLatitude(10.0);
		l1.setLongitude(20.0);
		
		Location l2 = new Location();
		l1.setLatitude(11.0);
		l1.setLongitude(19.0);
		
		assertEquals(true, LocationUtil.checkDistance(l1, l2, 152.71));
	}
	
	@Test
	public void testCheckDistanceIsInBoundaries() {
		// Grados decimales
		Location l1 = new Location();
		l1.setLatitude(0.0);
		l1.setLongitude(0.0);
		
		Location l2 = new Location();
		l1.setLatitude(10.0);
		l1.setLongitude(20.0);
		
		assertEquals(true, facade.checkDistance(l1, l2, 3112.));
	}
	
	@Test
	public void testCheckDistanceIsNotInLong() {
		// Grados decimales
		Location l1 = new Location();
		l1.setLatitude(0.0);
		l1.setLongitude(0.0);
		
		Location l2 = new Location();
		l1.setLatitude(20.1);
		l1.setLongitude(0.0);
		
		assertEquals(false, facade.checkDistance(l1, l2, 2224.));
	}
	
	@Test
	public void testCheckDistanceIsNotInLatitut() {
		// Grados decimales
		Location l1 = new Location();
		l1.setLatitude(0.0);
		l1.setLongitude(0.0);
		Location l2 = new Location();
		l1.setLatitude(0.0);
		l1.setLongitude(20.1);
		
		assertEquals(false, facade.checkDistance(l1, l2, 2224.));
	}
}
