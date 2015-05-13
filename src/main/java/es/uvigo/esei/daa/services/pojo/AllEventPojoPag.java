package es.uvigo.esei.daa.services.pojo;

import java.util.List;

import es.uvigo.esei.daa.bean.PagBean;

public class AllEventPojoPag {
	private List<AllEventPojo> listEvents;
	private PagBean pageBean;
	
	public List<AllEventPojo> getListEvents() {
		return listEvents;
	}
	public void setListEvents(List<AllEventPojo> listEvents) {
		this.listEvents = listEvents;
	}
	public PagBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PagBean pageBean) {
		this.pageBean = pageBean;
	}
}
