package fi.jleh.reittiopas.parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import fi.jleh.reittiopas.exception.XMLParsingException;
import fi.jleh.reittiopas.model.QuadtreePoint;
import fi.jleh.reittiopas.model.Service;
import fi.jleh.reittiopas.model.Station;
import fi.jleh.reittiopas.quadtree.BoundingBox;
import fi.jleh.reittiopas.quadtree.QuadTree;
import fi.jleh.reittiopas.utils.DataStructuresDto;


public class ReittiopasXMLParser {

	/**
	 * Parses timetable XML file and creates Java objects for timetable data.
	 * Returns object that can be used to access parsed data.
	 * @param pathToFile
	 * @return
	 * @throws XMLParsingException
	 */
	public DataStructuresDto parseXML(String pathToFile) throws XMLParsingException {
		System.out.println("Started XML parsing");
		long start = System.currentTimeMillis();
		
		final List<Station> stationList = new ArrayList<Station>();
		final Map<Integer, Station> stationMap = new TreeMap<Integer, Station>();
		final List<Service> services = new ArrayList<Service>();
		
		try {
			InputStream is = new FileInputStream(pathToFile);
			XMLReader parser = XMLReaderFactory.createXMLReader();
			
			parser.setContentHandler(new ReittiopasXMLHandler(stationList, stationMap, services));
			parser.parse(new InputSource(is));
			
			long end = (System.currentTimeMillis() - start) / 1000;
			System.out.println("Parsing completed in " + end + " s");
			System.out.println("Found " + stationList.size() + " stations");
			System.out.println("Found " + services.size() + " lines");
		} catch (Exception e) {
			throw new XMLParsingException("XML parsing failed: " + e, e);
		}
		
		DataStructuresDto dto = new DataStructuresDto();
		dto.setServices(services);
		dto.setStationList(stationList);
		dto.setStationMap(stationMap);
		dto.setStationSpatial(createStationQuadtree(stationList));
		
		return dto;
	}
	
	private QuadTree createStationQuadtree(List<Station> stationList) {
		BoundingBox boundingBox = new BoundingBox(23.6041, 25.706741, 60.988, 59.90);
		QuadTree quadTree = new QuadTree(boundingBox);
		
		for (QuadtreePoint point : stationList) {
			boolean insert = quadTree.insert(point);
			
			if (!insert) {
				throw new RuntimeException("Insert of a point " + point + " failed!");
			}
		}
		
		return quadTree;
	}
}
