package com.mycompany.tiralabra_maven;

import java.io.FileInputStream;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class Main {

    public static void main(String[] args) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        try (FileInputStream fis = new FileInputStream(args[0])) {
            XMLEventReader reader = factory.createXMLEventReader(fis);
            ArrayList<Station> stations = new ArrayList<Station>();
            ArrayList<Service> services = new ArrayList<Service>();
            Delivery d = null;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.getEventType() == XMLEvent.START_ELEMENT) {
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();
                    if (elementName.equals("Station")) {
                        Attribute id = startElement.getAttributeByName(new QName("StationId"));
                        Attribute name = startElement.getAttributeByName(new QName("Name"));
                        stations.add(new Station(Integer.parseInt(id.getValue()), name.getValue()));
                    }
                    if (elementName.equals("Delivery")) {
                        Attribute companyId = startElement.getAttributeByName(new QName("CompanyId"));
                        Attribute firstday = startElement.getAttributeByName(new QName("Firstday"));
                        Attribute lastday = startElement.getAttributeByName(new QName("Lastday"));
                        d = new Delivery(Integer.parseInt(companyId.getValue()), firstday.getValue(), lastday.getValue());
                    }
                    if (elementName.equals("Service")) {
                        Attribute id = startElement.getAttributeByName(new QName("ServiceId"));
                        services.add(new Service(Integer.parseInt(id.getValue())));
                    }
                    if (elementName.equals("ServiceNbr")) {
                        Attribute name = startElement.getAttributeByName(new QName("Name"));
                        services.get(services.size()-1).setName(name.getValue());
                    }
                    if (elementName.equals("Stop")) {
                        Attribute i = startElement.getAttributeByName(new QName("Ix"));
                        Attribute stationId = startElement.getAttributeByName(new QName("StationId"));
                        Attribute arrival = startElement.getAttributeByName(new QName("Arrival"));
                        Stop s = new Stop(Integer.parseInt(i.getValue()), Integer.parseInt(stationId.getValue()), Integer.parseInt(arrival.getValue()));
                        services.get(services.size()-1).addStop(s);
                    }
                }
            }
            System.out.println(d);
            for (Service s: services) {
                System.out.println(s.getId() + " stops: " + s.getStops().size());
            }
        }
    }
}
