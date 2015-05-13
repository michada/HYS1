package es.uvigo.esei.daa.services.pojo;

import java.util.List;

import es.uvigo.esei.daa.bean.PagBean;

public class PublicEventPojoPag {
	private List<PublicEventPojo> listEvents;
	private PagBean pageBean;
	
	public PagBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PagBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<PublicEventPojo> getListEvents() {
		return listEvents;
	}
	public void setListEvents(List<PublicEventPojo> listEvents) {
		this.listEvents = listEvents;
	}
}
