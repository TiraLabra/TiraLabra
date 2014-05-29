package fi.jleh.reittiopas.router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fi.jleh.reittiopas.exception.RoutingFailureException;
import fi.jleh.reittiopas.model.QuadtreePoint;
import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.model.Stop;
import fi.jleh.reittiopas.quadtree.BoundingBox;
import fi.jleh.reittiopas.utils.DataStructuresDto;
import fi.jleh.reittiopas.utils.GeomertyUtils;
import fi.jleh.reittiopas.utils.TimeUtils;

/**
 * Class to find best route between stops.
 *
 */
public class Router {

	private final int LINE_CHANGE_PENALTY = 10000;
	private final double WALK_DISTANCE = 100;
	private final double WALK_PENALTY = 5000; // Walk penalty per meter
	private final double WALKING_SPEED = 1.5; // In m/s
	private final double TIME_MODIFIER = 10000;
	
	private DataStructuresDto dataStructures;
	
	private Set<Station> visitedNodes = new HashSet<Station>();
	private List<Station> openNodes = new ArrayList<Station>();
	
	private Map<Station, Station> cameFrom = new HashMap<Station, Station>();
	private Map<Station, Stop> cameFromStop = new HashMap<Station, Stop>();
	private Map<Station, String> timeAtStation = new HashMap<Station, String>();
	
	private Map<Station, Double> costFromStart = new HashMap<Station, Double>();
	private Map<Station, Double> estimatedCost = new HashMap<Station, Double>();
	
	private long processStart;
	
	public Router(DataStructuresDto dto) {
		this.dataStructures = dto;
	}
	
	/**
	 * Finds routes between two stations on given start time.
	 * @param start
	 * @param end
	 * @param startTime
	 * @throws RoutingFailureException
	 */
	public RouterResult findRoute(Station start, Station end, String startTime) throws RoutingFailureException {
		processStart = System.currentTimeMillis();
		initializeDataStructures();
		
		// Values for start node
		costFromStart.put(start, 0.0);
		estimatedCost.put(start, GeomertyUtils.calculateDistance(start, end));
		openNodes.add(start);
		timeAtStation.put(start, startTime);
		
		while (!openNodes.isEmpty()) {
			Station current = getBestStation(openNodes, estimatedCost);
			
			if (current == end) {
				long time = System.currentTimeMillis() - processStart;
				System.out.println("Route found " + time + " ms");
				
				return new RouterResult(cameFrom, cameFromStop, timeAtStation, start, end);
			}
			
			openNodes.remove(current);
			visitedNodes.add(current);
			
			// We can access lines and next stations through stops 
			processStops(current.getStops(), current, startTime);
			
			// Because all transportation modes or lines doesn't stop at same stations
			// (eg. trains don't stop at bus stops) we need to check nearest stations
			// where user can walk, to get better results.
			processNearbyStations(current, timeAtStation.get(current));
		}
		
		throw new RoutingFailureException("Routing has failed");
	}
	
	private void processStops(List<Stop> stops, Station current, String startTime) {
		for (Stop stop : stops) {
			int stopNumber = stop.getOrder();
			
			// We can go only one direction with one line
			if (stopNumber + 1 < stop.getService().getStops().size()) {
				// Get next station on line
				Station station = stop.getService().getStops().get(stopNumber + 1).getStation();
				
				// If station is already checked, ignore it
				if (visitedNodes.contains(station))
					continue;
				
				double tentativeScore = estimatedCost.get(current) 
						+ GeomertyUtils.calculateDistance(current, station);
				
				if (!openNodes.contains(station) || tentativeScore < costFromStart.get(station)) {
					// Do time check
					double timeScore = calculateTimeScore(timeAtStation.get(current), stop.getArrival());
					
					// Can't go back in time
					if (timeScore < 0)
						continue;
					
					// Time from start
					tentativeScore += Integer.parseInt(stop.getArrival()) - Integer.parseInt(startTime);
				
					
					cameFrom.put(station, current);
					cameFromStop.put(station, stop);
					timeAtStation.put(station, stop.getArrival());
					
					// We give some penalty for changing line
					double linePenalty = 0;
					if (cameFromStop.get(current) != null
							&& stop.getService() != null // When walk to other station service is null
							&& cameFromStop.get(current).getService() != null) { // As above
						if (stop.getService().getId() != cameFromStop.get(current).getService().getId())
							linePenalty = LINE_CHANGE_PENALTY;
						else
							timeScore = 0; // Reset time score, no need to change the vehicle
					}
					
					costFromStart.put(station, tentativeScore + timeScore);
					estimatedCost.put(station, tentativeScore + linePenalty + timeScore);
					
					if (!openNodes.contains(station)) {
						openNodes.add(station);
					}
				}
			}
		}
	}
	
	private double calculateTimeScore(String strTime1, String strTime2) {
		Integer time1 = Integer.parseInt(strTime1);
		Integer time2 = Integer.parseInt(strTime2);

		if (time2 <= time1)
			return -1;
		
		int time = Integer.parseInt(TimeUtils.calculateTimeDifference(strTime1, strTime2));
		
		// Waiting is not so bad if it isn't too long
		if (time < 5)
			return 0;
		
		return time * TIME_MODIFIER;
	}
	
	private void processNearbyStations(Station current, String startTime) {
		BoundingBox boundingBox = new BoundingBox(current.getX(), current.getY(), WALK_DISTANCE);
		List<QuadtreePoint> nearbyStations = dataStructures.getStationSpatial().queryRange(boundingBox);
		
		for (QuadtreePoint point : nearbyStations) {
			Station nearbyStation = (Station) point;
			
			// Station already checked
			if (visitedNodes.contains(nearbyStation))
				continue;
			
			double walkDistance = GeomertyUtils.calculateDistance(current, nearbyStation);
			double tentativeScore = estimatedCost.get(current) + walkDistance;
			int walkTime = (int) Math.round(((walkDistance * WALKING_SPEED) / 60));
			
			if (!openNodes.contains(nearbyStation) 
					|| tentativeScore < costFromStart.get(nearbyStation)) {
				cameFrom.put(nearbyStation, current);
				cameFromStop.put(nearbyStation, new Stop(current)); // Create pseudo stop for walking
				
				double timeScore = 0;
				String timeAfterWalk = TimeUtils.getTimeAfterWalk(timeAtStation.get(current), walkTime);
				timeAtStation.put(nearbyStation, timeAfterWalk); // Walking time not yet checked
				timeScore = calculateTimeScore(timeAfterWalk, startTime);
				
				
				costFromStart.put(nearbyStation, tentativeScore + timeScore);
				estimatedCost.put(nearbyStation, tentativeScore + timeScore + WALK_PENALTY * walkDistance);
				
				if (!openNodes.contains(nearbyStation)) {
					openNodes.add(nearbyStation);
				}
			}
		}
	}
	
	private void initializeDataStructures() {
		visitedNodes = new HashSet<Station>();
		openNodes = new ArrayList<Station>();
		
		cameFrom = new HashMap<Station, Station>();
		cameFromStop = new HashMap<Station, Stop>();
		timeAtStation = new HashMap<Station, String>();
		
		costFromStart = new HashMap<Station, Double>();
		estimatedCost = new HashMap<Station, Double>();
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
}
