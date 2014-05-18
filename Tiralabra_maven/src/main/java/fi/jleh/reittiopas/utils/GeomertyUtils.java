package fi.jleh.reittiopas.utils;

import fi.jleh.reittiopas.model.Station;

public class GeomertyUtils {

	/**
	 * Get distance between two stations.
	 * @param station1
	 * @param station2
	 * @return Distance in meters
	 */
	public static double calculateDistance(Station station1, Station station2) {
		double dx = station1.getX() - station2.getX();
		double dy = station1.getY() - station2.getY();
		
		double x = dx * Math.cos(dy / 2);
		
		return Math.sqrt(x * x + dy * dy) * 6371;
	}
}
