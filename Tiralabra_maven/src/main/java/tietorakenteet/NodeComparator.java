
package tietorakenteet;

import java.util.Comparator;

/**
 * Comparator-tyypin luokka, joka vertailee kahden Noden välistä 
 * kustannusarviota.
 */
public class NodeComparator implements Comparator<Node> {
    
    @Override
    public int compare(Node n1, Node n2) {
        if (n1.getEtaisyysMaaliin() < n2.getEtaisyysMaaliin())
            return -1;
        if (n1.getEtaisyysMaaliin() > n2.getEtaisyysMaaliin())
            return 1;
        return 0;
    }
    

}
