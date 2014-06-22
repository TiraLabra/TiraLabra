package fi.jleh.reittiopas.routing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import fi.jleh.reittiopas.exception.RoutingFailureException;
import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.parser.ReittiopasXMLParser;
import fi.jleh.reittiopas.quadtree.BoundingBox;
import fi.jleh.reittiopas.router.Router;
import fi.jleh.reittiopas.router.RouterResult;
import fi.jleh.reittiopas.utils.DataStructuresDto;
import fi.jleh.reittiopas.utils.Unzipper;


public class RoutingTest {

	private static DataStructuresDto dataStructures;
	
	@BeforeClass
	public static void parseXML() {
		String filePath = Unzipper.unzipTimetableData();
		dataStructures = new ReittiopasXMLParser().parseXML(filePath);
	}
	
	@Test
	public void testRoutingWithStartTime() throws RoutingFailureException {
		Router router = new Router(dataStructures);
		Station start = dataStructures.getStationMap().get(1113127);
		Station end = dataStructures.getStationMap().get(1465552);
		
		RouterResult route = router.findRoute(start, end, "0700");
		assertNotNull(route);
	}
	
	@Test
	public void testRoutingResultTime() throws RoutingFailureException {
		Router router = new Router(dataStructures);
		Station start = dataStructures.getStationMap().get(1113127);
		Station end = dataStructures.getStationMap().get(1465552);
		
		RouterResult route = router.findRoute(start, end, "0000");
		Integer time = Integer.parseInt(route.calculateRouteTime());
		
		assertNotNull(time);
		assertTrue(time > 0);
	}
	
	/**
	 * While improving algorithm, make sure that routing is not going worse.
	 * @throws RoutingFailureException
	 */
	@Test
	public void routingIsNotGoingWorse1() throws RoutingFailureException {
		Router router = new Router(dataStructures);
		Station start = dataStructures.getStationMap().get(1113127);
		Station end = dataStructures.getStationMap().get(1465552);
		
		RouterResult route = router.findRoute(start, end, "0700");
		Integer time = Integer.parseInt(route.calculateRouteTime());
		
		int previousBestForRoute = 1208;
		printStats(previousBestForRoute, time, route);
		
		assertTrue(time <= previousBestForRoute);
	}
	
	@Test
	public void routingIsNotGoingWorse2() throws RoutingFailureException {
		Router router = new Router(dataStructures);
		Station start = dataStructures.getStationMap().get(1230109);
		Station end = dataStructures.getStationMap().get(2611249);
		
		RouterResult route = router.findRoute(start, end, "1200");
		Integer time = Integer.parseInt(route.calculateRouteTime());
		
		int previousBestForRoute = 741;
		printStats(previousBestForRoute, time, route);
		
		assertTrue(time <= previousBestForRoute);
	}
	
	private void printStats(int previousBestForRoute, int time, RouterResult route) {
		if (time < previousBestForRoute) {
			System.out.println("BETTER ROUTE FOUND");
			route.printPath();
		}
		
		System.out.println("Route with time " + time + " best: " + previousBestForRoute);
	}
	
	// Performance tests are here, so we don't have to reload XML
	
	@Test
	public void testQuadtreePerformance() {
		System.out.println("Quadtree performance test");
		Station station = dataStructures.getStationMap().get(1113127);
		BoundingBox box = new BoundingBox(station.getX(), station.getY(), 100);
		
		long start = System.currentTimeMillis();
		for (Station stat : dataStructures.getStationList()) {
			box.containsPoint(stat);
		}
		
		long time1 = (System.currentTimeMillis() - start);
		System.out.println("O(n) time: " + time1 + " ms");
		
		start = System.currentTimeMillis();
		dataStructures.getStationSpatial().queryRange(box);

		long time2 = (System.currentTimeMillis() - start);
		System.out.println("Quadtree time: " + time2 + " ms");
		
		System.out.println("Difference " + (time1 - time2) + " ms");
	}
}
