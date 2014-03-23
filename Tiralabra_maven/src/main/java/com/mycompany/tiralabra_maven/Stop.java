
package com.mycompany.tiralabra_maven;

public class Stop {
    private int index;
    private int arrival;
    private int station;

    public Stop(int i, int station, int arrival) {
        this.index = i;
        this.arrival = arrival;
        this.station = station;
    }

    @Override
    public String toString() {
        return "index: " + index + ", station: " + station + ", arrival: "+ arrival;
    }

}
