package es.uvigo.esei.daa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.daa.dao.EventDAO;
import es.uvigo.esei.daa.entities.Event;
import es.uvigo.esei.daa.services.pojo.PublicEventPojo;

@Service
@Transactional
public class PublicFacade {
	@Autowired
	private EventDAO dao;

	public List<PublicEventPojo> getEventList() throws FacadeException {
		List<Event> list = null;
		list = dao.getPublicEvents();
		
		List<PublicEventPojo> listEventPojo = new ArrayList<PublicEventPojo>();
		for (Event event: list) {
			PublicEventPojo pojo = new PublicEventPojo(event);
			listEventPojo.add(pojo);
		}
		
		return listEventPojo;
	}
}
