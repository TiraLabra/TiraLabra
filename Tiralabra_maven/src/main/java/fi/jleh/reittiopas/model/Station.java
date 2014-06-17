package fi.jleh.reittiopas.model;

import java.util.List;

import fi.jleh.reittiopas.datastructures.DefaultList;

/**
 * Model class for station in public transport.
 *
 */
public class Station implements QuadtreePoint {

	private int id;
	private String name;
	private double x;
	private double y;
	private int city;
	private List<Stop> stops;
	
	public Station(int id, String name, double x, double y, int city) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.city = city;
		this.stops = new DefaultList<Stop>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public List<Stop> getStops() {
		return stops;
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", name=" + name + ", stops=" + stops
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
