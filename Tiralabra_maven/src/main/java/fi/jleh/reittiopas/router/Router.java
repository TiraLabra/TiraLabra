package fi.jleh.reittiopas.router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fi.jleh.reittiopas.exception.RoutingFailureException;
import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.model.Stop;
import fi.jleh.reittiopas.utils.DataStructuresDto;
import fi.jleh.reittiopas.utils.GeomertyUtils;

/**
 * Class to find best route between stops.
 *
 */
public class Router {

	private DataStructuresDto dataStructures;
	
	public Router(DataStructuresDto dto) {
		this.dataStructures = dto;
	}

	/**
	 * Finds route between two stations.
	 * This implementation ignores time data.
	 * @param start
	 * @param end
	 * @throws RoutingFailureException
	 */
	public void findRoute(Station start, Station end) throws RoutingFailureException {
		Set<Station> visitedNodes = new HashSet<Station>();
		List<Station> openNodes = new ArrayList<Station>();
		
		Map<Station, Station> cameFrom = new HashMap<Station, Station>();
		Map<Station, Stop> cameFromStop = new HashMap<Station, Stop>();
		
		Map<Station, Double> costFromStart = new HashMap<Station, Double>();
		Map<Station, Double> estimatedCost = new HashMap<Station, Double>();
		
		// Values for start node
		costFromStart.put(start, 0.0);
		estimatedCost.put(start, GeomertyUtils.calculateDistance(start, end));
		openNodes.add(start);
		
		while (!openNodes.isEmpty()) {
			Station current = getBestStation(openNodes, estimatedCost);
			
			if (current == end) {
				System.out.println("Route found");
				printPath(cameFrom, cameFromStop, current);
				
				return;
			}
			
			openNodes.remove(current);
			visitedNodes.add(current);
			
			// We can access lines and next stations through stops 
			for (Stop stop : current.getStops()) {
				int stopNumber = stop.getOrder();
				
				if (stopNumber + 1 < stop.getService().getStops().size()) {
					// Get next station on line
					Station station = stop.getService().getStops().get(stopNumber + 1).getStation();
					
					// If station is already checked, ignore it
					if (visitedNodes.contains(station))
						continue;
					
					double tentativeScore = estimatedCost.get(current) 
							+ GeomertyUtils.calculateDistance(current, station);
					
					if (!openNodes.contains(station) 
							|| tentativeScore < costFromStart.get(station)) {
						cameFrom.put(station, current);
						cameFromStop.put(station, stop);
						
						double linePenalty = 0;
						if (cameFromStop.get(current) != null 
								&& stop.getService().getId() != cameFromStop.get(current).getService().getId())
							linePenalty = 1000;
						
						costFromStart.put(station, tentativeScore);
						estimatedCost.put(station, tentativeScore + linePenalty); // TODO: Add heuristics
						
						if (!openNodes.contains(station)) {
							openNodes.add(station);
						}
					}
				} else {
					continue;
				}
			}
		}
		
		throw new RoutingFailureException("Routing has failed");
	}
	
	/*
	 * Helper method to get stations from open list in order.
	 * Should be convert into data structure.
	 */
	private Station getBestStation(List<Station> stations, Map<Station, Double> estimatedCost) {
		double bestValue = Double.MAX_VALUE;
		Station bestStation = null;
		
		for (Station station : stations) {
			double value = estimatedCost.get(station);
			
			if (value < bestValue) {
				bestStation = station;
				bestValue = value;
			}
		}
		
		return bestStation;
	}
	
	private void printPath(Map<Station, Station> cameFrom, Map<Station, Stop> stops, 
			Station station) {
		if (station != null) {
			if (stops.get(station) != null)
				System.out.println(station.getName() + " " + stops.get(station).getService().getLineNumber());
			else
				System.out.println(station.getName());
			
			printPath(cameFrom, stops, cameFrom.get(station));
		}
	}
}
