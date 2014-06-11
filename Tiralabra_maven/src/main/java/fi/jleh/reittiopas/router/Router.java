package fi.jleh.reittiopas.router;

import java.util.List;
import java.util.Map;
import java.util.Set;

import fi.jleh.reittiopas.datastructures.BinaryHeap;
import fi.jleh.reittiopas.datastructures.DefaultHashMap;
import fi.jleh.reittiopas.datastructures.DefaultSet;
import fi.jleh.reittiopas.datastructures.Heap;
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

	private final int LINE_CHANGE_PENALTY = 100;
	private final double WALK_DISTANCE = 100;
	private final double WALK_PENALTY = 20; // Walk penalty per meter
	private final double WALKING_SPEED = 1.5; // In m/s
	private final double TIME_MODIFIER = 100;
	private final double BUS_COST = 10; // It is better to travel with rails 
	
	private DataStructuresDto dataStructures;
	
	private Set<Station> visitedNodes;
	private Heap<Station> openNodes;
	
	private Map<Station, Station> cameFrom;
	private Map<Station, Stop> cameFromStop;
	private Map<Station, String> timeAtStation;
	
	private Map<Station, Double> costFromStart;
	private Map<Station, Double> estimatedCost;

	private String routeStartTime;
	
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
		
		routeStartTime = startTime;
		
		// Values for start node
		costFromStart.put(start, 0.0);
		estimatedCost.put(start, GeomertyUtils.calculateDistance(start, end));
		openNodes.insert(GeomertyUtils.calculateDistance(start, end), start);
		timeAtStation.put(start, startTime);
		
		while (!openNodes.isEmpty()) {
			Station current = openNodes.getAndRemoveMin();
			
			//debugPrint(current);
			
			if (current == end) {
				long time = System.currentTimeMillis() - processStart;
				System.out.println("Route found " + time + " ms");
				
				return new RouterResult(cameFrom, cameFromStop, timeAtStation, start, end);
			}
			
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
				
				double costToStart = estimatedCost.get(current) 
						+ GeomertyUtils.calculateDistance(current, station) + timeFromStart(stop.getArrival());
				
				if (!openNodes.contains(station) || costToStart < costFromStart.get(station)) {
					addStopToOpenSet(station, current, stop, costToStart);
				}
			}
		}
	}
	
	private void addStopToOpenSet(Station station, Station current, Stop stop, double costToStart) {
		// Do time check
		double timeScore = calculateTimeScore(timeAtStation.get(current), stop.getArrival());
		
		// Can't go back in time
		if (timeScore < 0)
			return;

		// We give some penalty for changing line
		double linePenalty = 0;
		if (timeScore != 0)
			linePenalty = calculateLineChangePenalty(current, stop);
		
		cameFrom.put(station, current);
		cameFromStop.put(station, stop);
		timeAtStation.put(station, stop.getArrival());
		
		double cost = costToStart + linePenalty + timeScore + BUS_COST;
		
		//costFromStart.put(station, costToStart + timeScore);
		costFromStart.put(station, costToStart);
		estimatedCost.put(station, cost);
		
		if (!openNodes.contains(station)) {
			openNodes.insert(cost, station);
		}
	}
	
	private double calculateTimeScore(String strTime1, String strTime2) {
		Integer time1 = Integer.parseInt(strTime1);
		Integer time2 = Integer.parseInt(strTime2);

		if (time2 <= time1)
			return -1;
		
		int time = Integer.parseInt(TimeUtils.calculateTimeDifference(strTime1, strTime2));
		
		// Waiting is not so bad if it isn't too long
		if (time < 10)
			return 0;
		
		return time * TIME_MODIFIER;
	}
	
	private int timeFromStart(String timeNow) {
		try {
			return Integer.parseInt(TimeUtils.calculateTimeDifference(routeStartTime, timeNow));
		} catch (NumberFormatException e) { // Time now is before route start time
			return 5000000;
		}
		
	}
	
	private double calculateLineChangePenalty(Station current, Stop stop) {
		double linePenalty = 0;
		if (cameFromStop.get(current) != null
				&& stop.getService() != null // When walk to other station service is null
				&& cameFromStop.get(current).getService() != null) { // As above
			if (stop.getService().getId() != cameFromStop.get(current).getService().getId())
				linePenalty = LINE_CHANGE_PENALTY;
		}
		
		return linePenalty;
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
			int walkTime = (int) Math.round(((walkDistance * WALKING_SPEED) / 60));
			String timeAfterWalk = TimeUtils.getTimeAfterWalk(timeAtStation.get(current), walkTime);
			double costToStart = GeomertyUtils.calculateDistance(current, nearbyStation) + timeFromStart(timeAfterWalk);
			
			if (!openNodes.contains(nearbyStation) || costToStart < costFromStart.get(nearbyStation)) {
				cameFrom.put(nearbyStation, current);
				cameFromStop.put(nearbyStation, new Stop(current)); // Create pseudo stop for walking
				
				double timeScore = 0;
				timeAtStation.put(nearbyStation, timeAfterWalk);
				timeScore = calculateTimeScore(timeAfterWalk, startTime);
				
				costFromStart.put(nearbyStation, costToStart + timeScore);
				double cost = costToStart + timeScore + WALK_PENALTY * walkDistance;
				estimatedCost.put(nearbyStation, cost);
				
				if (!openNodes.contains(nearbyStation)) {
					openNodes.insert(cost, nearbyStation);
				}
			}
		}
	}
	
	private void initializeDataStructures() {
		visitedNodes = new DefaultSet<Station>();
		openNodes = new BinaryHeap<Station>();
		
		cameFrom = new DefaultHashMap<Station, Station>(2000);
		cameFromStop = new DefaultHashMap<Station, Stop>(2000);
		timeAtStation = new DefaultHashMap<Station, String>(2000);
		
		costFromStart = new DefaultHashMap<Station, Double>(2000);
		estimatedCost = new DefaultHashMap<Station, Double>(2000);
	}
}
