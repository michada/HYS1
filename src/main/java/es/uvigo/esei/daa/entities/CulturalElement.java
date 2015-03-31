package es.uvigo.esei.daa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "culturalElement")
public class CulturalElement implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String title;
	private String description;
	private String image;
	@OneToMany(mappedBy = "culturalElement")
	private List<Event> eventsAboutMe;

	public CulturalElement() {

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Event> getEventsAboutMe() {
		return eventsAboutMe;
	}

	public void setEventsAboutMe(List<Event> eventsAboutMe) {
		this.eventsAboutMe = eventsAboutMe;
	}
	

}
