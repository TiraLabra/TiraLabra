package tira.utils;

import java.util.Scanner;
import tira.list.LinkedList;

/**
 *
 * @author joonaslaakkonen
 * Luokka luo HashMapin, jossa on tallenettuna avaimiksi jokainen kaupunki kerran ja avaimeen
 * liitetään kaupungista lähtevät Target oliot eli kohteet, joihin kaupungista on suora yhteys.
 * Oliolle annetaan pääohjelman luoma Scanner olio.
 */
public class Mapper {
    
    private Scanner map;
    private LinkedList<Location> locations;

    public Mapper(Scanner reader) {
        this.map = reader;
        this.locations = new LinkedList<Location>();
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
     * Tulostetaan kaupungit käyttäjälle, joista hän valitsee lähtöpaikan ja maalin.
     */
    public void print() {
        for (Location name : this.locations) {
            System.out.println(name.toString());
        }
    }
    
    /**
     * 
     * @param start lähtöpiste
     * @param end maali
     * @return 
     * Metodi tarkistaa onko käyttäjän syöte kunnossa ja palauttaa tiedon siitä.
     */   
    public boolean validKeys(String start, String end) {
        if (this.locations.containsString(start) && this.locations.containsString(end)) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi palauttaa alustetun kartan.
     * @return 
     */   
    public LinkedList getMap() {
        return this.locations;
    }
    
    /**
     * 
     * @param start
     * @param destination
     * @param distance
     * Metodi luo HashMapiin avaimet ja lisää niille kohteita, joilla on tietty etäisyys lähtökaupungista.
     * Kaupungeille annetaan myös tieto niiden x,y-sijainnista.
     */
    private void manageLine(String start, int sx, int sy, String destination, int dx, int dy, int distance) {
        if (!this.locations.containsString(start) && !this.locations.containsString(destination)) {
            
            Location alku = new Location(start);
            Location maali = new Location(destination);
            alku.add(new Target(destination, distance, sx, sy));
            maali.add(new Target(start, distance, dx, dy));
            this.locations.add(alku);
            this.locations.add(maali);
            
            
        } else if (!this.locations.containsString(destination) && this.locations.containsString(start)) {
            
            Location maali = new Location(destination);
            maali.add(new Target(start, distance, sx, sy));
            Location alku = (Location)this.locations.searchWithString(start).getOlio();
            alku.add(new Target(destination, distance, dx, dy));
            this.locations.add(maali);
            
        } else if (!this.locations.containsString(start) && this.locations.containsString(destination)) {
            
            Location alku = new Location(start);
            alku.add(new Target(destination, distance, dx, dy));
            Location maali = (Location)this.locations.searchWithString(destination).getOlio();
            maali.add(new Target(start, distance, sx, sy));
            this.locations.add(alku);
            
        } else {
            
            Location maali = (Location)this.locations.searchWithString(destination).getOlio();
            Location alku = (Location)this.locations.searchWithString(start).getOlio();
            maali.add(new Target(start, distance, sx, sy));
            alku.add(new Target(destination, distance, dx, dy));
        }      
    }  
}