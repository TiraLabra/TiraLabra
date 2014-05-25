package fi.jleh.reittiopas.routing;

import org.junit.BeforeClass;
import org.junit.Test;

import fi.jleh.reittiopas.exception.RoutingFailureException;
import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.parser.ReittiopasXMLParser;
import fi.jleh.reittiopas.router.Router;
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
	public void testRouting() throws RoutingFailureException {
		Router router = new Router(dataStructures);
		Station start = dataStructures.getStationMap().get(1113127);
		Station end = dataStructures.getStationMap().get(1465552);
		
		router.findRoute(start, end);
	}
	
	public void testRoutingWithStartTime() throws RoutingFailureException {
		Router router = new Router(dataStructures);
		Station start = dataStructures.getStationMap().get(1113127);
		Station end = dataStructures.getStationMap().get(1465552);
		
		router.findRoute(start, end, "0700");
	}
}
