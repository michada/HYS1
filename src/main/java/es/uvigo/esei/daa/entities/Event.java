package es.uvigo.esei.daa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "event")
public class Event implements Serializable, Comparable<Event> {

	private static final long serialVersionUID = 1L;

	public enum EventStatus {
		PROGRAMMED, CANCELLED, COMPLETED;
	}

	public enum Visibility {
		PUBLIC, PRIVATE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String title;
	@Column(columnDefinition="Text")
	private String description;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Enumerated(EnumType.STRING)
	private EventStatus status;
	@Enumerated(EnumType.STRING)
	private Visibility visibility;
	
	// Is used for store distance between a source location given
	// and the destiny event location
	@Transient
	private double distanceFromSrc;

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

	@ManyToOne
	@JsonBackReference
	@NotNull
	private Category category;

	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Event() {

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

	public int getNumAssistants() {
		return assistants.size();
	}

	@Override
	public int compareTo(Event e) {
		if (e.distanceFromSrc == this.distanceFromSrc)
			return 0;
		else 
			return this.distanceFromSrc > e.distanceFromSrc ? 1 : -1;
	}
	
	public double getDistanceFromSrc() {
		return distanceFromSrc;
	}

	public void setDistanceFromSrc(double distanceFromSrc) {
		this.distanceFromSrc = distanceFromSrc;
	}

}
