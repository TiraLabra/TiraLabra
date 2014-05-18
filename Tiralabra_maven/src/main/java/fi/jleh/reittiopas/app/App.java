package fi.jleh.reittiopas.app;

import fi.jleh.reittiopas.parser.ReittiopasXMLParser;
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
        
        System.out.println(data.getStationList().get(0));
    }
}
