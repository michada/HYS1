package es.uvigo.esei.daa.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.daa.entities.Person;

//REQUIRES_NEW funciona
	// NESTED No lo acepta por defecto
	// SUPPORTS NO FUNCIONA
	// 
@Repository
public class PeopleDAO extends DAO {
	private final static Logger LOG = Logger.getLogger("PeopleDAO");

	@Autowired
	private SessionFactory sessionFactory;

	public Person get(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Person S where S.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Person> peopleList = query.list();
		if (peopleList.isEmpty()) {
			throw new IllegalArgumentException("Invalid id");
		}
		return peopleList.get(0);
	}

	public List<Person> list() throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		List<Person> peopleList = session.createQuery("from Person").list();
		return peopleList;
	}

	public void delete(int id) throws DAOException, IllegalArgumentException {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Person S where S.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Person> peopleList = query.list();
		if (peopleList.isEmpty()) {
			throw new IllegalArgumentException("Invalid id");
		}
		session.delete(peopleList.get(0));	
	}

	public Person modify(int id, String name, String surname)
			throws DAOException, IllegalArgumentException {
		if (name == null || surname == null) {
			throw new IllegalArgumentException("name and surname can't be null");
		}
		Session session = sessionFactory.getCurrentSession();
		Person person = null;
		String hql = "from Person S where S.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Person> peopleList = query.list();
		if (peopleList.isEmpty()) {
			throw new IllegalArgumentException("Invalid id");
		}

		person = peopleList.get(0);
		person.setId(id);
		person.setName(name);
		person.setSurname(surname);
		session.persist(person);
		return person;
	}

	
	public Person add(String name, String surname) throws DAOException,
			IllegalArgumentException {
		if (name == null || surname == null) {
			throw new IllegalArgumentException("name and surname can't be null");
		}

		Person person = new Person();
		person.setName(name);
		person.setSurname(surname);
		save(person);
		return person;
	}

	public void save(Person person) {
		Session session = sessionFactory.getCurrentSession();
		session.save(person);
	}
}
