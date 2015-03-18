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
import es.uvigo.esei.daa.entities.Person;
public class PeopleDAOTest extends AbstractTestCase {
	@Autowired
	private PeopleDAO dao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
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
	public void testGet() throws DAOException, SQLException {
		List<Person> people = this.dao.list();
		
		final Person person = this.dao.get(4);
		
		assertEquals(4, person.getId());
		assertEquals("María", person.getName());
		assertEquals("Márquez", person.getSurname());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetInvalidId() throws DAOException {
		this.dao.get(100);
	}

	@Test
	public void testList() throws DAOException {
		assertEquals(10, this.dao.list().size());
	}

	@Test
	public void testDelete() throws DAOException {
		this.dao.delete(4);
		
		assertEquals(9, this.dao.list().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteInvalidId() throws DAOException {
		this.dao.delete(100);
	}

	@Test
	public void testModify() throws DAOException {
		this.dao.modify(5, "John", "Doe");
		
		final Person person = this.dao.get(5);
		
		assertEquals(5, person.getId());
		assertEquals("John", person.getName());
		assertEquals("Doe", person.getSurname());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyInvalidId() throws DAOException {
		this.dao.modify(100, "John", "Doe");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullName() throws DAOException {
		this.dao.modify(5, null, "Doe");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullSurname() throws DAOException {
		this.dao.modify(5, "John", null);
	}

	@Test
	public void testAdd() throws DAOException {
		final Person person = this.dao.add("John", "Doe");
		
		assertEquals("John", person.getName());
		assertEquals("Doe", person.getSurname());
		
		final Person personGet = this.dao.get(person.getId());
		
		assertEquals(person.getId(), personGet.getId());
		assertEquals("John", personGet.getName());
		assertEquals("Doe", personGet.getSurname());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullName() throws DAOException {
		this.dao.add(null, "Doe");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullSurname() throws DAOException {
		this.dao.add("John", null);
	}
}
