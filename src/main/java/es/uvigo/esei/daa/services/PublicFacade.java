package es.uvigo.esei.daa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.daa.dao.EventDAO;
import es.uvigo.esei.daa.entities.Event;

@Service
@Transactional
public class PublicFacade {
	@Autowired
	private EventDAO dao;

	public List<Event> getEventList() throws FacadeException {
		List<Event> list = null;
		list = dao.getPublicEvents();
		return list;
	}
}
