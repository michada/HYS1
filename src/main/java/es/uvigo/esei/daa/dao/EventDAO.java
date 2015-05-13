package es.uvigo.esei.daa.dao;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
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
	private final static Logger LOG = Logger.getLogger(EventDAO.class
			.getSimpleName());

	@Autowired
	private SessionFactory sessionFactory;

	public List<Event> listEvents(EventFilterBean eventFilterBean,
			PagBean pagBean) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Event.class)
				.addOrder(Order.asc("date")).setFetchSize(Integer.MAX_VALUE);
		// Filters
		for (Criterion exp : eventFilterBean.getFilters()) {
			criteria.add(exp);
		}
		List<Event> eventList = criteria.list();
		pagBean.setNumElemTotal(eventList.size());

		if (eventFilterBean.getSrcLocation() != null) {
			orderByLocationDesc(eventList, eventFilterBean.getSrcLocation());
		}

		int lastElement = pagBean.getFirstElement() + pagBean.getNumElemPag();
		return eventList.subList(pagBean.getFirstElement(),
				Math.min(lastElement, eventList.size()));
	}

	private void orderByLocationDesc(List<Event> eventList, Location srcLocation) {
		for (Event event : eventList) {
			event.setDistanceFromSrc(LocationUtil.getDistance(srcLocation,
					event.getLocation()));
		}
		Collections.sort(eventList);
	}
}
