package es.uvigo.esei.daa.util;

import uk.me.jstott.jcoord.LatLng;
import es.uvigo.esei.daa.entities.Location;

public class LocationUtil {
	public static double getDistance(Location l1, Location l2) {
		// Euclidean distance
		LatLng latLng1 = new LatLng(l1.getLatitude(), l1.getLongitude());
		LatLng latLng2 = new LatLng(l2.getLatitude(), l2.getLongitude());
		return latLng2.distance(latLng1);
	}
	
	public static boolean checkDistance(Location l1, Location l2, Double distance) {
		// Euclidean distance
		LatLng latLng1 = new LatLng(l1.getLatitude(), l1.getLongitude());
		LatLng latLng2 = new LatLng(l2.getLatitude(), l2.getLongitude());
		double module = latLng2.distance(latLng1);
		
		return module <= distance;
	}
}
