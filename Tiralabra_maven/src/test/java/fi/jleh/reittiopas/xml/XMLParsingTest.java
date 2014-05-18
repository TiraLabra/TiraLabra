package fi.jleh.reittiopas.xml;

import org.junit.Test;

import fi.jleh.reittiopas.parser.ReittiopasXMLParser;
import fi.jleh.reittiopas.utils.Unzipper;


public class XMLParsingTest {

	@Test
	public void testXMLParsing() {
		// Tests that XML file parsing is completed without errors.
		// This test could be made faster if we use smaller 
		// set of timetables and stations in XML
		String filePath = Unzipper.unzipTimetableData();
		ReittiopasXMLParser.parseXML(filePath);
	}
}
