package fi.jleh.reittiopas.geom;

import fi.jleh.reittiopas.model.QuadtreePoint;

/**
 * Simple point class for test use.
 *
 */
public class SimplePoint implements QuadtreePoint {

	private double x;
	private double y;
	
	public SimplePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "SimplePoint [x=" + x + ", y=" + y + "]";
	}
}
