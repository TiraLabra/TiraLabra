package tira.utils;

import tira.list.LinkedList;

/**
 *
 * @author joonaslaakkonen
 */
public class Location {
    
    private LinkedList<Target> targets;
    private String name;
    
    public Location(String called) {
        this.name = called;
        this.targets = new LinkedList<Target>();
    }
    
    public void add(Target t) {
        this.targets.add(t);
    }
    
    public String toString() {
        return this.name;
    }
    
    public LinkedList getTargets() {
        return this.targets;
    }
    
}
