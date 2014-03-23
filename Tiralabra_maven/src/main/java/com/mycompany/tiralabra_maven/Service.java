package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

public class Service {
    private int id;
    private String name;
    private ArrayList<Stop> stops;

    public Service(int ID) {
        id = ID;
        stops = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public void addStop(Stop stop) {
        stops.add(stop);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
