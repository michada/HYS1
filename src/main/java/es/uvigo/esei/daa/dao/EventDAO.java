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

	public List<Event> getPublicEvents(String categoryId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;

		System.out.println(categoryId);

		if (!categoryId.equals("0")) {
			String hql = "from Event e where e.category.id = :categoryId and e.visibility = 'PUBLIC' order by e.date ASC";
			query = session.createQuery(hql);
			query.setString("categoryId", categoryId);
		} else {
			String hql = "from Event e where e.visibility = 'PUBLIC' order by e.date ASC";
			query = session.createQuery(hql);
		}

		List<Event> eventList = query.list();
		return eventList;
	}

	public List<Event> getAllEvents(String categoryId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;

		if (!categoryId.equals("0")) {
			String hql = "from Event e where e.category.id = :categoryId order by e.date ASC";
			query = session.createQuery(hql);
			query.setString("categoryId", categoryId);
		} else {
			String hql = "from Event e order by e.date ASC";
			query = session.createQuery(hql);
		}

		List<Event> eventList = query.list();
		return eventList;
	}
}