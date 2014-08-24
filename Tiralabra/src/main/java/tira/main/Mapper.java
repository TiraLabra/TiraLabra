package tira.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author joonaslaakkonen
 * Luokka luo HashMapin, jossa on tallenettuna avaimiksi kukin kartan kaupunki kerran ja avaimeen
 * liitetään kaupungista lähtevät Target oliot eli kohteet, joihin kaupungista pääsee.
 */
public class Mapper {
    
    private Scanner map;
    private HashMap<String, ArrayList<Target>> sources; 

    public Mapper(Scanner reader) {
        this.map = reader;
        this.sources = new HashMap<String, ArrayList<Target>>();
    }
    
    /**
     * Metodi lukee karttatiedoston ja luo oliot. Apuna käytetty javan String luokan split metodia, joka
     * tekee taulukon merkkijonosta erottimen perusteella.
     */
    public void initialize() {
        while (this.map.hasNextLine()) {
            String line = this.map.nextLine();
            String[] parts = line.split("-");
            String start = parts[0];
            int startX = Integer.parseInt(parts[1]);
            int startY= Integer.parseInt(parts[2]);
            String finish = parts[3];
            int finishX = Integer.parseInt(parts[4]);
            int finishY = Integer.parseInt(parts[5]);
            int dist = Integer.parseInt(parts[6]);
            this.manageLine(start, startX, startY, finish, finishX, finishY, dist);
        }
        map.close();
    }
    
    /**
     * Tulostetaan kaupungit.
     */
    public void print() {
        for (String apu : this.sources.keySet()) {
            System.out.println(apu);
        }
    }
    
    /**
     * 
     * @param start
     * @param end
     * @return 
     * Metodi tarkistaa onko käyttäjän syöte kunnossa ja palauttaa tiedon siitä.
     */
    
    public boolean validKeys(String start, String end) {
        if (this.sources.containsKey(start) && this.sources.containsKey(end)) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi palauttaa kartan.
     * @return 
     */
    
    public HashMap getGrid() {
        return this.sources;
    }
    
    /**
     * 
     * @param start
     * @param destination
     * @param distance
     * Metodi luo HashMapiin avaimet ja lisää niille kohteita, joilla on tietty etäisyys lähtökaupungista.
     */
    private void manageLine(String start, int sx, int sy, String destination, int dx, int dy, int distance) {
        if (!this.sources.containsKey(start) && !this.sources.containsKey(destination)) {
            ArrayList<Target> startTargets = new ArrayList<Target>();
            ArrayList<Target> destinationTargets = new ArrayList<Target>();
            startTargets.add(new Target(destination, distance, sx, sy));
            destinationTargets.add(new Target(start, distance, dx, dy));
            
            this.sources.put(start, startTargets);
            this.sources.put(destination, destinationTargets);
            
        } else if (!this.sources.containsKey(destination) && this.sources.containsKey(start)) {
            ArrayList<Target> destinationTargets = new ArrayList<Target>();
            this.sources.get(start).add(new Target(destination, distance, dx, dy));
            destinationTargets.add(new Target(start, distance, sx, sy));

            this.sources.put(destination, destinationTargets);
            
        } else if (!this.sources.containsKey(start) && this.sources.containsKey(destination)) {
            ArrayList<Target> startTargets = new ArrayList<Target>();
            startTargets.add(new Target(destination, distance, dx, dy));
            this.sources.get(destination).add(new Target(start, distance, sx, sy));

            this.sources.put(start, startTargets);
        } else {
            this.sources.get(start).add(new Target(destination, distance, dx, dy));
            this.sources.get(destination).add(new Target(start, distance, sx, sy));
        }    
        
    }
    
}