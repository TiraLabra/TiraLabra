package tira.main;

/**
 *
 * @author joonaslaakkonen
 * Luokan oliot ovat tiedostosta luetun kartan kaupunkien kohteita
 */
public class Target {
    
    private String name;
    private int distance;
    
    public Target(String location, int lenght) {
        this.name = location;
        this.distance = lenght;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getDistance() {
        return this.distance;
    }

}