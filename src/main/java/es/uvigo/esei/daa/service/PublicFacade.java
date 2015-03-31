package es.uvigo.esei.daa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.daa.dao.DAOException;
import es.uvigo.esei.daa.dao.PeopleDAO;
import es.uvigo.esei.daa.entities.Person;

@Service
@Transactional
public class PublicFacade {
	@Autowired
	private PeopleDAO dao;

	public Person getPersonById(int id) {
		Person person = dao.get(id);
		return person;
	}

	public List<Person> getPeopleList() throws FacadeException {
		List<Person> list = null;
		try {
			list = dao.list();
		} catch (DAOException e) {
			throw new FacadeException(e);
		}
		return list;
	}

	public void deletePersonById(int id) throws FacadeException,
	 IllegalArgumentException {
		try {
			dao.delete(id);
		} catch (DAOException e) {
			throw new FacadeException(e);
		}
	}

	public Person modifyPerson(Person person) throws FacadeException,
		IllegalArgumentException {
		Person personOut = null;
		try {
			personOut = dao.modify(person.getId(), 
					person.getName(), 
					person.getSurname());
		} catch (DAOException e) {
			throw new FacadeException(e);
		}
		return personOut;
	}

	public Person addPerson(Person person) throws FacadeException,
		IllegalArgumentException {
		Person personOut = null;
		try {
			personOut = dao.add(person.getName(), person.getSurname());
		} catch (DAOException e) {
			throw new FacadeException(e);
		}
		return personOut;
	}
}
