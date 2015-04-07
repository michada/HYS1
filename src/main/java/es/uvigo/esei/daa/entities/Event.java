package es.uvigo.esei.daa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="event")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum EventStatus {
		PROGRAMMED,CANCELLED,COMPLETED

	}
	
	public enum Visibility {
		PUBLIC, PRIVATE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String title;
	private String description;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Enumerated(EnumType.STRING)
	private EventStatus status;
	@Enumerated(EnumType.STRING)
	private Visibility visibility;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private CulturalElement culturalElement;
	@ManyToOne
	@JsonBackReference
	private Personality personality;
	@ManyToOne
	@JsonBackReference
	@NotNull
	private User creator;
	@ManyToMany
	@JoinTable(name = "event_moderators", inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> moderators;
	@ManyToMany
	@JoinTable(name = "event_assistants", inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> assistants;
	@ManyToOne
	@JsonBackReference
	@NotNull
	private Location location;
	
	public Event() {

	}

	public CulturalElement getCulturalElement() {
		return culturalElement;
	}

	public void setCulturalElement(CulturalElement culturalElement) {
		this.culturalElement = culturalElement;
	}

	public Personality getPersonality() {
		return personality;
	}

	public void setPersonality(Personality personality) {
		this.personality = personality;
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getModerators() {
		return moderators;
	}

	public void setModerators(List<User> moderators) {
		this.moderators = moderators;
	}

	public List<User> getAssistants() {
		return assistants;
	}

	public void setAssistants(List<User> assistants) {
		this.assistants = assistants;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus status) {
		this.status = status;
	}
	

	public Visibility getVisibility() {
		return visibility;
	}
	
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	
	public int getNumAssistants(){
		return assistants.size();
	}
}
