package es.uvigo.esei.daa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String password;
	@NotNull
	private String userName;

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Event> eventsCreated;
	@ManyToMany(mappedBy = "moderators")
	private List<Event> eventsModerated;
	@ManyToMany(mappedBy = "assistants")
	private List<Event> eventsToAttend;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Event> getEventsCreated() {
		return eventsCreated;
	}

	public void setEventsCreated(List<Event> eventsCreated) {
		this.eventsCreated = eventsCreated;
	}

	public List<Event> getEventsModerated() {
		return eventsModerated;
	}

	public void setEventsModerated(List<Event> eventsModerated) {
		this.eventsModerated = eventsModerated;
	}

	public List<Event> getEventsToAttend() {
		return eventsToAttend;
	}

	public void setEventsToAttend(List<Event> eventsToAttend) {
		this.eventsToAttend = eventsToAttend;
	}

}