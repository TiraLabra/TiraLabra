package tira.tiralabra;

/**
 *
 * @author joonaslaakkonen
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