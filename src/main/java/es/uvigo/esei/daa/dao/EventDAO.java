package es.uvigo.esei.daa.dao;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.daa.bean.EventFilterBean;
import es.uvigo.esei.daa.bean.PagBean;
import es.uvigo.esei.daa.entities.Event;
import es.uvigo.esei.daa.entities.Location;
import es.uvigo.esei.daa.util.LocationUtil;

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
	
	public List<Event> getAllEvents(
			EventFilterBean eventFilterBean, 
			PagBean pagBean) {
		List<Event> eventList = null;
		Session session = sessionFactory.getCurrentSession();
		
		int firstElement = pagBean.getNumElemPag() 
				* ( (pagBean.getNumPag() - 1) +1 );
		
		eventList = session.createCriteria(Event.class).
			setFirstResult(firstElement).
			setMaxResults(pagBean.getNumElemPag()).
			addOrder(Order.desc("date"))
			.list();
		
		for (Event event:eventList) {
			Location dstlocation = event.getLocation();
			if (LocationUtil.checkDistance(eventFilterBean.getSrcLocation(),
					dstlocation, eventFilterBean.getMaxDistance())) {
				event.setDistanceFromSrc(
						LocationUtil.getDistance(
								eventFilterBean.getSrcLocation(),
								dstlocation));
			}
		}	
		Collections.sort(eventList);
		
		return eventList;
	}
}
