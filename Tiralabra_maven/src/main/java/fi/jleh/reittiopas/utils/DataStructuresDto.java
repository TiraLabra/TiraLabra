package fi.jleh.reittiopas.utils;

import java.util.List;
import java.util.Map;

import fi.jleh.reittiopas.model.Service;
import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.quadtree.QuadTree;

/**
 *	Provides access to Reittiopas data structures
 */
public class DataStructuresDto {

	private List<Station> stationList;
	private Map<Integer, Station> stationMap;
	private List<Service> services;
	private QuadTree stationSpatial;

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public Map<Integer, Station> getStationMap() {
		return stationMap;
	}

	public void setStationMap(Map<Integer, Station> stationMap) {
		this.stationMap = stationMap;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public QuadTree getStationSpatial() {
		return stationSpatial;
	}

	public void setStationSpatial(QuadTree stationSpatial) {
		this.stationSpatial = stationSpatial;
	}
}
