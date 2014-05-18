package fi.jleh.reittiopas.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import fi.jleh.reittiopas.exception.XMLParsingException;
import fi.jleh.reittiopas.model.Service;
import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.model.Stop;
import fi.jleh.reittiopas.utils.DataStructuresDto;


public class ReittiopasXMLParser {

	/**
	 * Parses timetable XML file and creates Java objects for timetable data.
	 * Returns object that can be used to access parsed data.
	 * @param pathToFile
	 * @return
	 * @throws XMLParsingException
	 */
	public static DataStructuresDto parseXML(String pathToFile) throws XMLParsingException {
		System.out.println("Started XML parsing");
		long start = System.currentTimeMillis();
		
		final List<Station> stationList = new ArrayList<Station>();
		final TreeMap<Integer, Station> stationMap = new TreeMap<>();
		final List<Service> services = new ArrayList<>();
		
		try {
			File xmlFile = new java.io.File(pathToFile);
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler() {

				// Parse values only in one language
				private boolean synonymOn = false;
				
				private Service currentService;
				
				public void startElement(String uri, String localName, String qName, Attributes attr) {
					
					// Parse stations. Stations under Synonym node are station names in
					// swedish and they are ignored.
					if (qName.equalsIgnoreCase("station") && !synonymOn) {
						int id = Integer.parseInt(attr.getValue("StationId"));
						
						Station station = new Station(
								id,
								attr.getValue("Name"), 
								Double.parseDouble(attr.getValue("X")),
								Double.parseDouble(attr.getValue("Y")), 
								Integer.parseInt(attr.getValue("city_id")));
						
						stationList.add(station);
						stationMap.put(id, station);
					}
					
					// Start service parsing
					if (qName.equalsIgnoreCase("service")) {
						currentService = new Service(Integer.parseInt(attr.getValue("ServiceId")));
						services.add(currentService);
					}
					
					// Service data is in ServiceNbr node under service node.
					if (qName.equalsIgnoreCase("ServiceNbr")) {
						currentService.setCompany(Integer.parseInt(attr.getValue("CompanyId")));
						currentService.setLineNumber(attr.getValue("Variant"));
						currentService.setServiceNumber(attr.getValue("ServiceNbr"));
						currentService.setName(attr.getValue("Name"));
					}
					
					// Parse service stops
					if (qName.equalsIgnoreCase("stop")) {
						// Get station for stop
						Station station = stationMap.get(Integer.parseInt(attr.getValue("StationId")));
						
						Stop stop = new Stop(station, attr.getValue("Arrival"), 
								Integer.parseInt(attr.getValue("Ix")), currentService);
						
						currentService.getStops().add(stop);
						// Add stop to station so we can access lines stopping at station through it
						station.getStops().add(stop);
					}
					
					if (qName.equalsIgnoreCase("Synonym")) {
						synonymOn = true;
					}
				}
				
				public void endElement(String uri, String localName, String qName) {
					if (qName.equalsIgnoreCase("synonym")) {
						synonymOn = false;
					}
				}
			};
			
			parser.parse(xmlFile, handler);
			
			long end = (System.currentTimeMillis() - start) / 1000;
			System.out.println("Parsing completed in " + end + " s");
			System.out.println("Found " + stationList.size() + " stations");
			System.out.println("Found " + services.size() + " lines");
		} catch (Exception e) {
			throw new XMLParsingException("XML parsing failed", e);
		}
		
		DataStructuresDto dto = new DataStructuresDto();
		dto.setServices(services);
		dto.setStationList(stationList);
		dto.setStationMap(stationMap);
		
		return dto;
	}
}
