package tira.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author joonaslaakkonen
 * Luokka sisältää algoritmien käyttämiä yhteisiä metodeja.
 */
public class Helper {
    
    private ArrayList<Node> list;

    public Helper(ArrayList<Node> cells) {
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
     * @return palauttaa listan, jossa on reitti maalisolmuun lähtösolmusta.
     */
    public List<Node> getRoute(Node goalCell) {
        List<Node> path = new ArrayList<Node>();
            for (Node vertex = goalCell; vertex != null; vertex = vertex.getPrevious())
                path.add(vertex);
            Collections.reverse(path);
            return path;
    }    
}