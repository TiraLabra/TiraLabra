package fi.jleh.reittiopas.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class for station in public transport.
 *
 */
public class Station {

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
		this.stops = new ArrayList<>();
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
}
