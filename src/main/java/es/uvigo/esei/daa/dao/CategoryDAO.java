package es.uvigo.esei.daa.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.daa.entities.Category;

@Repository
public class CategoryDAO extends DAO {
	private final static Logger LOG = Logger.getLogger("CategoryDAO");
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Category> getCategories() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Category";
		Query query = session.createQuery(hql);
		List<Category> eventList = query.list();
		return eventList;
	}
}
