package es.uvigo.esei.daa.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.daa.bean.EventFilterBean;
import es.uvigo.esei.daa.bean.PagBean;
import es.uvigo.esei.daa.dao.CategoryDAO;
import es.uvigo.esei.daa.dao.DAOException;
import es.uvigo.esei.daa.dao.EventDAO;
import es.uvigo.esei.daa.dao.UsersDAO;
import es.uvigo.esei.daa.entities.Category;
import es.uvigo.esei.daa.entities.Event;
import es.uvigo.esei.daa.entities.Event.Visibility;
import es.uvigo.esei.daa.entities.Location;
import es.uvigo.esei.daa.services.pojo.AllEventPojo;
import es.uvigo.esei.daa.services.pojo.PublicCategoryPojo;
import es.uvigo.esei.daa.services.pojo.PublicEventPojo;
import es.uvigo.esei.daa.util.LocationUtil;

@Service
@Transactional
public class PublicFacade {
	@Autowired
	private CategoryDAO categoryDao;

	@Autowired
	private EventDAO eventDao;

	@Autowired
	private UsersDAO userDao;

	public List<PublicEventPojo> getPublicEventList(String categoryId)
			throws FacadeException {
		List<Event> list = null;
		
		list = eventDao.getPublicEvents(categoryId);

		List<PublicEventPojo> listEventPojo = new ArrayList<PublicEventPojo>();
		for (Event event : list) {
			PublicEventPojo pojo = new PublicEventPojo(event);
			listEventPojo.add(pojo);
		}

		return listEventPojo;
	}

	public List<AllEventPojo> getAllEventList(String categoryId)
			throws FacadeException {
		List<Event> list = null;
		
		list = eventDao.getAllEvents(categoryId);

		List<AllEventPojo> listEventPojo = new ArrayList<AllEventPojo>();
		for (Event event : list) {
			AllEventPojo pojo = new AllEventPojo(event);
			listEventPojo.add(pojo);
		}

		return listEventPojo;
	}

	public List<AllEventPojo> getPublicEventList(
			EventFilterBean eventFilter,
			PagBean pagBean
			) throws FacadeException {
		List<Event> list = null;
		
		eventFilter.getFilters().add(Restrictions.eq("visibility", 
				Visibility.PUBLIC));
		list = eventDao.listEvents(eventFilter, pagBean);

		List<AllEventPojo> listEventPojo = new ArrayList<AllEventPojo>();
		for (Event event : list) {
			AllEventPojo pojo = new AllEventPojo(event);
			listEventPojo.add(pojo);
		}

		return listEventPojo;
	}
	
	public List<AllEventPojo> getAllEventList(
			EventFilterBean eventFilter,
			PagBean pagBean
			) throws FacadeException {
		List<Event> list = null;
		list = eventDao.listEvents(eventFilter, pagBean);

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
		return LocationUtil.checkDistance(l1, l2, distance);
	}
	
	public List<PublicCategoryPojo> getCategoryList() throws FacadeException {
		List<Category> list = null;
		list = categoryDao.getCategories();

		List<PublicCategoryPojo> listCategoryPojo = new ArrayList<PublicCategoryPojo>();
		for (Category category : list) {
			PublicCategoryPojo pojo = new PublicCategoryPojo(category);
			listCategoryPojo.add(pojo);
		}

		return listCategoryPojo;
	}
}