package fi.jleh.reittiopas.model;

public class Stop {

	private Station station;
	private String arrival;
	private Service service;
	private int order;
	
	public Stop(Station station, String arrival, int order, Service service) {
		this.station = station;
		this.arrival = arrival;
		this.order = order;
		this.service = service;
	}
	
	public Stop(Station station) {
		this.station = station;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Stop [station=" + station.getName() + ", arrival=" + arrival
				+ ", service=" + service.getLineNumber() + ", order=" + order + "]";
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
