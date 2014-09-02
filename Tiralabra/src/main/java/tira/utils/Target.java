package tira.utils;

/**
 *
 * @author joonaslaakkonen
 * Luokan oliot ovat tiedostosta luetun kartan kaupunkien kohteita. Oliolla on nimi, sijanti ja etäisyys.
 */
public class Target {
    
    private String name;
    private int distance;
    private int x;
    private int y;
    
    public Target(String location, int lenght, int locX, int locY) {
        this.name = location;
        this.distance = lenght;
        this.x = locX;
        this.y = locY;
    }
    
    /**
     * 
     * Luokan gettereitä.
     */   
    public String getName() {
        return this.name;
    }
    
    public int getDistance() {
        return this.distance;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
}