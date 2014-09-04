package tira.utils;

import tira.common.Node;
import tira.list.LinkedList;

/**
 *
 * @author joonaslaakkonen
 * Luokka sisältää algoritmien käyttämiä yhteisiä metodeja.
 */
public class Helper {
    
    private LinkedList<Node> list;

    public Helper(LinkedList<Node> cells) {
        this.list = cells;
    }
    /**
     * 
     * @param searched haettava Node.
     * @return palauttaa viitteen haettuun Nodeen tai null jos Nodea ei löydy.
     */
    public Node search(String searched) {
        for (Node helper : this.list) {
            if (helper.toString().equals(searched)) {
                return helper;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param goalCell maalisolmu.
     * @return palauttaa merkkijonon, jossa on reitti maalisolmuun lähtösolmusta.
     */
    public String getRoute(Node goalCell) {
        String polku = "";
        for (Node vertex = goalCell; vertex != null; vertex = vertex.getPrevious())
            polku = "-" + vertex.toString() + "-" + polku;
        return polku;
    }    
}