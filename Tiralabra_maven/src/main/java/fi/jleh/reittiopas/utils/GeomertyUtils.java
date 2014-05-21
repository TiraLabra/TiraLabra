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
		return calculateDistance(station1.getX(), station2.getX(), 
				station1.getY(), station2.getY());
	}
	
	/**
	 * Calculates distance between two coordinates.
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 * @return
	 */
	public static double calculateDistance(double x1, double x2, double y1, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		
		double x = dx * Math.cos(dy / 2);
		
		return Math.sqrt(x * x + dy * dy) * 6371;
	}
}
