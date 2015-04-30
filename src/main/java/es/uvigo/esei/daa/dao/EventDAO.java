package es.uvigo.esei.daa.dao;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

	public List<Event> listEvents(EventFilterBean eventFilterBean,
			PagBean pagBean) {
		Session session = sessionFactory.getCurrentSession();
		
		// Get the count
		Criteria criteriaTotal = session.createCriteria(Event.class);
		// Filters
		for (Criterion exp:eventFilterBean.getFilters()) {		
				criteriaTotal.add(exp);
		}
		criteriaTotal.setProjection(Projections.rowCount());
		pagBean.setNumElemTotal((Long)criteriaTotal.uniqueResult());
		
		// Get the elements
		Criteria criteria = session.createCriteria(Event.class)
				.setFirstResult(pagBean.getFirstElement())
				.setMaxResults(pagBean.getNumElemPag())
				.addOrder(Order.asc("date"));
		// Filters
		for (Criterion exp:eventFilterBean.getFilters()) {		
			criteria.add(exp);
		}
		@SuppressWarnings("unchecked")
		List<Event> eventList = criteria.list();
		if (eventFilterBean.getSrcLocation() != null) {
			orderByLocationDesc(eventList, eventFilterBean.getSrcLocation());
		}
		return eventList;
	}

	private void orderByLocationDesc(List<Event> eventList, Location srcLocation) {
		for (Event event : eventList) {
			event.setDistanceFromSrc(LocationUtil.getDistance(srcLocation,
					event.getLocation()));
		}
		Collections.sort(eventList);
	}
}
