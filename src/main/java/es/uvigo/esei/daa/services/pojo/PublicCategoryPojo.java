package es.uvigo.esei.daa.services.pojo;

import java.io.Serializable;

import es.uvigo.esei.daa.entities.Category;

public class PublicCategoryPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	
	public PublicCategoryPojo() {
	}

	public PublicCategoryPojo(Category c) {
		this.id = c.getId();
		this.name = c.getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}