package es.uvigo.esei.daa.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uvigo.esei.daa.entities.Subject;

@Component
public class SubjectDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(subject);
	}

	public Subject get(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Subject S where S.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Subject> subjectList = query.list();
		if (subjectList.isEmpty()) {
			throw new IllegalArgumentException("Invalid id");
		}
		return subjectList.get(0);
	}
	
	public List<Subject> list() {
		Session session = sessionFactory.getCurrentSession();
		List<Subject> subjectList = session.createQuery("from Subject").list();
		return subjectList;
	}
}
