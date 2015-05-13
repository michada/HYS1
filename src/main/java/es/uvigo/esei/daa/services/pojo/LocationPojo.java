package es.uvigo.esei.daa.services.pojo;
import java.io.Serializable;

import es.uvigo.esei.daa.entities.Location;


public class LocationPojo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private double longitude;
	private double latitude;
	
	public LocationPojo(){
		
	}
	
	public LocationPojo(Location loc){
		id = loc.getId();
		longitude = loc.getLongitude();
		latitude = loc.getLatitude();
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double x) {
		this.longitude = x;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double y) {
		this.latitude = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
