package ohjelma;

import java.util.HashMap;
import java.util.HashSet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kkivikat
 */
public class Verkko {
    private HashSet<Kaari> kaaret;
    private HashMap<Integer, Solmu> solmut;

    public Verkko(HashMap solmut, HashSet kaaret) {
        this.solmut = solmut;
        this.kaaret = kaaret;
    }
}
