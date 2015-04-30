package es.uvigo.esei.daa.bean;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.SimpleExpression;

import es.uvigo.esei.daa.entities.Location;

public class EventFilterBean {
	// Source location
	private Location srcLocation;
	private List<Criterion> filters;
	// Max distance from source 
	private double maxDistance;
	
	public EventFilterBean() {
		setFilters(new ArrayList<Criterion>());
	}
	
	
	
	public Location getSrcLocation() {
		return srcLocation;
	}

	public void setSrcLocation(Location srcLocation) {
		this.srcLocation = srcLocation;
	}

	public double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
	}



	public List<Criterion> getFilters() {
		return filters;
	}



	public void setFilters(List<Criterion> filters) {
		this.filters = filters;
	}
}
