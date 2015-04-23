package es.uvigo.esei.daa.facade.fake;

public class LocationFake {
	private Double longitud,lat;
	public LocationFake(Double longitud, Double lat) {
		this.setLongitud(longitud);
		this.setLat(lat);
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
}