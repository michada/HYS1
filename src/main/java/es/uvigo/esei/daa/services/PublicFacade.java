package es.uvigo.esei.daa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.me.jstott.jcoord.LatLng;
import es.uvigo.esei.daa.dao.DAOException;
import es.uvigo.esei.daa.dao.EventDAO;
import es.uvigo.esei.daa.dao.UsersDAO;
import es.uvigo.esei.daa.entities.Event;
import es.uvigo.esei.daa.entities.Location;
import es.uvigo.esei.daa.services.pojo.AllEventPojo;
import es.uvigo.esei.daa.services.pojo.PublicEventPojo;

@Service
@Transactional
public class PublicFacade {
	@Autowired
	private EventDAO eventDao;

	@Autowired
	private UsersDAO userDao;

	public List<PublicEventPojo> getPublicEventList() throws FacadeException {
		List<Event> list = null;
		list = eventDao.getPublicEvents();

		List<PublicEventPojo> listEventPojo = new ArrayList<PublicEventPojo>();
		for (Event event : list) {
			PublicEventPojo pojo = new PublicEventPojo(event);
			listEventPojo.add(pojo);
		}

		return listEventPojo;
//		return eventDao.getPublicEvents().stream()
//			.map(PublicEventPojo::new)
//		.collect(toList());
	}

	public List<AllEventPojo> getAllEventList() throws FacadeException {
		List<Event> list = null;
		list = eventDao.getAllEvents();

		List<AllEventPojo> listEventPojo = new ArrayList<AllEventPojo>();
		for (Event event : list) {
			AllEventPojo pojo = new AllEventPojo(event);
			listEventPojo.add(pojo);
		}

		return listEventPojo;
	}

	public String checkToken(String token) throws FacadeException {
		String login = null;
		if (token != null && !token.isEmpty()) { 
			try {
				login = userDao.checkToken(token);
			} catch (DAOException d) {
				throw new FacadeException();
			}
		}
		return login;
	}
	
	public boolean checkDistance(Location l1, Location l2, Double distance) {
		// Euclidean distance
		LatLng latLng1 = new LatLng(l1.getLatitude(), l1.getLongitude());
		LatLng latLng2 = new LatLng(l2.getLatitude(), l2.getLongitude());
		double module = latLng2.distance(latLng1);
		return module <= distance;
	}
}
