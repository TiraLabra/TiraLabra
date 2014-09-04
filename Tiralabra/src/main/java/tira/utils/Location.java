package tira.utils;

import tira.list.LinkedList;

/**
 * Luokka kuvaa yhtä kartan sijantia. Sijannilla on lista kohteita, jonne siitä pääsee.
 * @author joonaslaakkonen
 */
public class Location {
    
    private LinkedList<Target> targets;
    private String name;
    
    public Location(String called) {
        this.name = called;
        this.targets = new LinkedList<Target>();
    }
    
    /**
     * 
     * @param t lisättävä kohde listaan.
     */
    public void add(Target t) {
        this.targets.add(t);
    }
    
    public String toString() {
        return this.name;
    }
    
    /**
     * 
     * @return lista olioista, joihin sijainnista pääsee.
     */
    public LinkedList getTargets() {
        return this.targets;
    }   
}