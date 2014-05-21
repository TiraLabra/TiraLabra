package fi.jleh.reittiopas.app;

import java.util.List;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        Router router = new Router();

        int id1 = 0;
        int id2;
        
        System.out.println("Give station ids");
        
        while (id1 != -1) {
        	id1 = scanner.nextInt();
            id2 = scanner.nextInt();
            
            Station station1 = stationList.get(id1);
            Station station2 = stationList.get(id2);
            
            System.out.println(station1.getName());
            System.out.println(station2.getName());
            
            try {
            	router.findRoute(station1, station2);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
        
        scanner.close();
    }
}
