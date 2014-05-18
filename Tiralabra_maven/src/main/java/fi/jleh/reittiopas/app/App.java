package fi.jleh.reittiopas.app;

import java.util.List;

import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.parser.ReittiopasXMLParser;
import fi.jleh.reittiopas.router.Router;
import fi.jleh.reittiopas.utils.DataStructuresDto;
import fi.jleh.reittiopas.utils.Unzipper;

/**
 * Main class for Reittiopas application.
 *
 */
public class App {
	
    public static void main( String[] args ) {   
        String file = Unzipper.unzipTimetableData();
        DataStructuresDto data = ReittiopasXMLParser.parseXML(file);
        
        List<Station> stationList = data.getStationList();

        Station station1 = stationList.get(2000);
        Station station2 = stationList.get(4500);
        
        System.out.println(station1.getName());
        System.out.println(station2.getName());
        
        try {
        	new Router().findRoute(station1, station2);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
