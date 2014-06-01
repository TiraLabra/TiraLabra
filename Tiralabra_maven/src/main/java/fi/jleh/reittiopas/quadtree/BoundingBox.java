package fi.jleh.reittiopas.quadtree;

import fi.jleh.reittiopas.model.QuadtreePoint;

/**
 * Class representation of a bounding box.
 * Note that this implementation might not work all over the Earth surface.
 */
public class BoundingBox {

	private final double EARTH_RADIUS = 6371010;
	
	private double centerX;
	private double centerY;
	private double radius;
	
	private double maxX;
	private double minX;
	private double maxY;
	private double minY;

	/**
	 * Creates new bounding box.
	 * 
	 * @param centerX X-coordinate
	 * @param centerY Y-coordinate
	 * @param radius radius in meters
	 */
	public BoundingBox(double centerX, double centerY, double radius) {
		if (radius > 100000)
			throw new IllegalArgumentException("Radius must be under 100000 m");
		
		this.centerX = centerX;
		this.centerY = centerY;
		this.setRadius(radius);
		
		// Create box
		// Convert degrees to radians so we can do the math.
		double centerXrad = Math.toRadians(centerX);
		double centerYrad = Math.toRadians(centerY);
		
		double radDist = radius / EARTH_RADIUS;
		
		double minLat = centerYrad - radDist;
		double maxLat = centerYrad + radDist;
		
		double deltaLon = Math.asin(Math.sin(radDist) / Math.cos(centerXrad));
		double minLon = centerXrad - deltaLon;
		double maxLon = centerXrad + deltaLon;
		
		this.maxY = Math.toDegrees(maxLat);
		this.minY = Math.toDegrees(minLat);
		this.maxX = Math.toDegrees(maxLon);
		this.minX = Math.toDegrees(minLon);
	}
	
	/**
	 * Creates new bounding box.
	 * 
	 * @param left
	 * @param right
	 * @param top
	 * @param bottom
	 */
	public BoundingBox(double left, double right, double top, double bottom) {
		this.minX = left;
		this.maxX = right;
		this.maxY = top;
		this.minY = bottom;
		
		this.centerX = (minX + maxX) / 2;
		this.centerY = (minY + maxY) / 2;
	}

	/**
	 * Test if bounding box contains a given point.
	 * @param point
	 * @return
	 */
	public boolean containsPoint(QuadtreePoint point) {
		// TODO: Check is equals to used correctly here
		return (point.getX() <= maxX && point.getX() >= minX)
				&& (point.getY() <= maxY && point.getY() >= minY);
	}
	
	public boolean intersects(BoundingBox boundingBox) {
		return !(this.minX > boundingBox.getMaxX()
				|| this.maxX < boundingBox.getMinX()
				|| this.maxY < boundingBox.getMinY()
				|| this.minY > boundingBox.getMaxY());
	}
	
	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public double getMaxX() {
		return maxX;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public double getMinX() {
		return minX;
	}

	public void setMinX(double minX) {
		this.minX = minX;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	@Override
	public String toString() {
		return "BoundingBox [maxX=" + maxX + ", minX=" + minX + ", maxY="
				+ maxY + ", minY=" + minY + "]";
	}
}
