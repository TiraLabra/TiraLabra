package fi.jleh.reittiopas.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class for transportation line.
 * 
 */
public class Service {

	private int id;
	private int company;
	private String serviceNumber;
	private String lineNumber;
	private String name;
	private List<Stop> stops;

	public Service(int id) {
		this.id = id;
		this.stops = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Stop> getStops() {
		return stops;
	}

	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}

}
