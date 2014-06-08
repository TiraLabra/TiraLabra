package fi.jleh.reittiopas.router;

import java.util.Map;

import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.model.Stop;
import fi.jleh.reittiopas.utils.TimeUtils;

/**
 * Contains result data structures of routing task.
 *
 */
public class RouterResult {

	private Map<Station, Station> cameFrom;
	private Map<Station, Stop> cameFromStop;
	private Map<Station, String> timeAtStation;
	private Station start;
	private Station end;
	
	
	public RouterResult(Map<Station, Station> cameFrom, Map<Station, Stop> cameFromStop,
			Map<Station, String> timeAtStation, Station start, Station end) {
		this.cameFrom = cameFrom;
		this.cameFromStop = cameFromStop;
		this.timeAtStation = timeAtStation;
		this.start = start;
		this.end = end;
	}

	/**
	 * Prints route for debugging.
	 */
	public void printPath() {
		StringBuilder wkt = new StringBuilder();
		
		Station station = end;
		
		while (station != null) {
			if (cameFromStop.get(station) != null) {
				String lineNumber = "Walk";
				
				if (cameFromStop.get(station).getService() != null)
					lineNumber = cameFromStop.get(station).getService().getLineNumber();
				
				System.out.println(station.getName() + " " + timeAtStation.get(station) 
						+ " " + lineNumber);
			}
			else
				System.out.println(station.getName());
			
			// Create geometry of the route as WKT Linestring
			if (wkt.length() == 0)
				wkt.append("LINESTRING (");
			else
				wkt.append(", ");
			
			wkt.append(station.getX());
			wkt.append(" ");
			wkt.append(station.getY());
			
			station = cameFrom.get(station);
		}
		
		wkt.append(")");
		System.out.println(wkt);
	}

	/**
	 * Returns time for route.
	 * @return
	 */
	public String calculateRouteTime() {
		return TimeUtils.calculateTimeDifference(timeAtStation.get(start), timeAtStation.get(end));
	}
	
	public Map<Station, Station> getCameFrom() {
		return cameFrom;
	}


	public void setCameFrom(Map<Station, Station> cameFrom) {
		this.cameFrom = cameFrom;
	}


	public Map<Station, Stop> getCameFromStop() {
		return cameFromStop;
	}


	public void setCameFromStop(Map<Station, Stop> cameFromStop) {
		this.cameFromStop = cameFromStop;
	}


	public Map<Station, String> getTimeAtStation() {
		return timeAtStation;
	}


	public void setTimeAtStation(Map<Station, String> timeAtStation) {
		this.timeAtStation = timeAtStation;
	}


	public Station getStart() {
		return start;
	}


	public void setStart(Station start) {
		this.start = start;
	}


	public Station getEnd() {
		return end;
	}


	public void setEnd(Station end) {
		this.end = end;
	}
}
