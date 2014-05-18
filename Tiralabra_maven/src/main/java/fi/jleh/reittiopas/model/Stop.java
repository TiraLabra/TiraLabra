package fi.jleh.reittiopas.model;

public class Stop {

	private Station station;
	private String arrival;
	private int order;
	
	public Stop(Station station, String arrival, int order) {
		this.station = station;
		this.arrival = arrival;
		this.order = order;
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
}
