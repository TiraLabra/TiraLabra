package fi.jleh.reittiopas.parser;

import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import fi.jleh.reittiopas.model.Service;
import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.model.Stop;

/**
 * Reittiopas XML elements handler class.
 *
 */
public class ReittiopasXMLHandler extends DefaultHandler {
	
	private List<Station> stationList;
	private Map<Integer, Station> stationMap;
	private List<Service> services;
	
	// Parse values only in one language
	private boolean synonymOn = false;
	
	private Service currentService;
	
	/**
	 * Data is parsed to given data structures.
	 * @param stationList
	 * @param stationMap
	 * @param services
	 */
	public ReittiopasXMLHandler(List<Station> stationList, Map<Integer, Station> stationMap, List<Service> services) {
		this.stationList = stationList;
		this.stationMap = stationMap;
		this.services = services;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attr) {
		
		// Parse stations. Stations under Synonym node are station names in
		// swedish and they are ignored.
		if (qName.equalsIgnoreCase("station") && !synonymOn) {
			parseStation(attr);
		}
		
		// Start service parsing
		if (qName.equalsIgnoreCase("service")) {
			parseService(attr);
		}
		
		// Service data is in ServiceNbr node under service node.
		if (qName.equalsIgnoreCase("ServiceNbr")) {
			parseServiceData(attr);
		}
		
		// Parse service stops
		if (qName.equalsIgnoreCase("stop")) {
			parseStop(attr);
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
	
	private void parseStation(Attributes attr) {
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
	
	private void parseStop(Attributes attr) {
		// Get station for stop
		Station station = stationMap.get(Integer.parseInt(attr.getValue("StationId")));
		
		Stop stop = new Stop(station, attr.getValue("Arrival"), 
				Integer.parseInt(attr.getValue("Ix")), currentService);
		
		currentService.getStops().add(stop);
		// Add stop to station so we can access lines stopping at station through it
		station.getStops().add(stop);
	}
	
	private void parseService(Attributes attr) {
		currentService = new Service(Integer.parseInt(attr.getValue("ServiceId")));
		services.add(currentService);
	}
	
	private void parseServiceData(Attributes attr) {
		currentService.setCompany(Integer.parseInt(attr.getValue("CompanyId")));
		currentService.setLineNumber(attr.getValue("Variant"));
		currentService.setServiceNumber(attr.getValue("ServiceNbr"));
		currentService.setName(attr.getValue("Name"));
	}
}
