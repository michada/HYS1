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
}
