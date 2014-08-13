package tira.tiralabra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author joonaslaakkonen
 */
public class Mapper {
    
    Scanner map;
    HashMap<String, ArrayList<Target>> sources; 

    public Mapper(Scanner reader) {
        this.map = reader;
        this.sources = new HashMap<String, ArrayList<Target>>();
    }
    
    public void initialize() {
        while (this.map.hasNextLine()) {
            String line = this.map.nextLine();
            String[] parts = line.split("-");
            String start = parts[0];
            String finish = parts[1];
            int dist = Integer.parseInt(parts[2]);
            this.manageLine(start, finish, dist);
        }
        map.close();
    }
    
    public void print() {
        System.out.println(this.sources.keySet());
    }
    
    private void manageLine(String start, String destination, int distance) {
        if (!this.sources.containsKey(start) && !this.sources.containsKey(destination)) {
            ArrayList<Target> startTargets = new ArrayList<Target>();
            ArrayList<Target> destinationTargets = new ArrayList<Target>();
            startTargets.add(new Target(destination, distance));
            destinationTargets.add(new Target(start, distance));
            
            this.sources.put(start, startTargets);
            this.sources.put(destination, destinationTargets);
        }
        if (!this.sources.containsKey(destination)) {
            ArrayList<Target> destinationTargets = new ArrayList<Target>();
            this.sources.get(start).add(new Target(destination, distance));
            destinationTargets.add(new Target(start, distance));

            this.sources.put(destination, destinationTargets);
        }
        if (!this.sources.containsKey(start)) {
            ArrayList<Target> startTargets = new ArrayList<Target>();
            startTargets.add(new Target(destination, distance));
            this.sources.get(destination).add(new Target(start, distance));

            this.sources.put(start, startTargets);
        }     
        
    }
    
}