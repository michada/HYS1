package es.uvigo.esei.daa.services.pojo;

import java.io.Serializable;
import java.util.Date;

import es.uvigo.esei.daa.entities.Event;
import es.uvigo.esei.daa.entities.Event.Visibility;

public class AllEventPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String description;
	private Date date;
	private String status;
	private int numAssistants;
	private String visibility;
	private LocationPojo location;
	private CategoryPojo category;

	public AllEventPojo() {

	}

	public AllEventPojo(Event event) {
		this.id = event.getId();
		this.title = event.getTitle();
		this.description = event.getDescription();
		this.date = event.getDate();
		this.status = event.getStatus().toString();
		this.numAssistants = event.getNumAssistants();
		this.visibility = event.getVisibility().toString();
		this.location = new LocationPojo(event.getLocation());
		this.category = new CategoryPojo(event.getCategory());
	}

	public LocationPojo getLocation() {
		return location;
	}

	public void setLocation(LocationPojo location) {
		this.location = location;
	}

	public CategoryPojo getCategory() {
		return category;
	}

	public void setCategory(CategoryPojo category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumAssistants() {
		return numAssistants;
	}

	public void setNumAssistants(int numAssistants) {
		this.numAssistants = numAssistants;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
}
