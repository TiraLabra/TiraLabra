package fi.jleh.reittiopas.app;

import java.util.Scanner;

import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.parser.ReittiopasXMLParser;
import fi.jleh.reittiopas.router.Router;
import fi.jleh.reittiopas.router.RouterResult;
import fi.jleh.reittiopas.utils.DataStructuresDto;
import fi.jleh.reittiopas.utils.Unzipper;

/**
 * Main class for Reittiopas application.
 *
 */
public class App {
	
    public static void main( String[] args ) {   
        String file = Unzipper.unzipTimetableData();
        DataStructuresDto data = new ReittiopasXMLParser().parseXML(file);

        Scanner scanner = new Scanner(System.in);
        Router router = new Router(data);

        int id1 = 0;
        int id2;
        
        System.out.println("Give two station ids and start time");
        System.out.println("eg: 1113127 1465552 0700");
        
        // 1113127 1465552 0700
        // 1230109 2611249 1200
        
        while (id1 != -1) {
        	id1 = scanner.nextInt();
            id2 = scanner.nextInt();
            String startTime = scanner.next();
            
            Station station1 = data.getStationMap().get(id1);
            Station station2 = data.getStationMap().get(id2);
            
            System.out.println(station1.getName());
            System.out.println(station2.getName());
            
            try {
            	RouterResult route = router.findRoute(station1, station2, startTime);
            	route.printPath();
            	System.out.println("Route time " + route.calculateRouteTime());
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
        
        scanner.close();
    }
}
