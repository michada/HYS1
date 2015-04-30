package es.uvigo.esei.daa.services.pojo;
import java.io.Serializable;

import es.uvigo.esei.daa.entities.Category;


public class CategoryPojo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	public CategoryPojo(){
		
	}
	
	public CategoryPojo(Category cat){
		id = cat.getId();
		name = cat.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
