package es.uvigo.esei.daa.services.pojo;

import java.util.List;

public class PublicEventPojoList {
	private List<PublicEventPojo> pojos;

	public PublicEventPojoList(List<PublicEventPojo> pojos) {
		super();
		this.pojos = pojos;
	}

	public List<PublicEventPojo> getPojos() {
		return pojos;
	}

	public void setPojos(List<PublicEventPojo> pojos) {
		this.pojos = pojos;
	}
}
