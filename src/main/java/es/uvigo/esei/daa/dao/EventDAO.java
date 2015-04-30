package es.uvigo.esei.daa.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.daa.entities.Event;

@Repository
public class EventDAO extends DAO {
	private final static Logger LOG = Logger.getLogger("EventDAO");
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Event> getPublicEvents() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Event e where e.visibility = 'PUBLIC' order by e.date ASC";
		Query query = session.createQuery(hql);
		List<Event> eventList = query.list();
		return eventList;
	}
	
	public List<Event> getAllEvents(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Event e order by e.date ASC";
		Query query = session.createQuery(hql);
		List<Event> eventList = query.list();
		return eventList;
	}
	
//	public List<Event> getAllEvents(
//			SearchFiltersBean $busquedaSubastasBean, 
//			PagBean pagBean) {
//		
//		// Filters
//		
//	}
}
