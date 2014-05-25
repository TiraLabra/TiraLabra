package tietorakenteet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A Star -reitinhaun luokka.
 */
public class AStar {
    
    // A-starin allustavia rakenteita, tyypit tulevat muuttumaan
    private ArrayList kaydyt;
    
    private PriorityQueue<Node> kaymatta;
    private SortedSet<Node> kaymatta2;
    
    private Node[] kuljettuReitti;

    public AStar() {
        kaydyt = new ArrayList();
        
        Comparator<Node> comparator = new NodeComparator();
        kaymatta = new PriorityQueue<Node>(10, comparator);
    }
    
    /**
     * Varsinaisen AStar-haun toteuttava metodi.
     * EI TOIMI, VASTA ALUSTAVA RUNKO
     * @param a
     * @param alku
     * @param loppu 
     */
    public void AStarHaku(Alue a, Node alku, Node loppu) {
        
        // Alustetaan 
        kaymatta.add(alku);
        
        while (!kaymatta.isEmpty()) {
            Node tarkastettava = kaymatta.poll();
            
            // Jos löydettiin, poistutaan;
            if (tarkastettava == loppu) {
                return;
            }
            
            kaymatta.remove(tarkastettava);
            kaydyt.add(tarkastettava);
            
            // Nykyisen naapurien päivitys:
            for (Node naapuri : selvitaNaapurit(a, tarkastettava)) {
                
                //TODO: Oikea laskenta:
                int uusiG = 999;
                
                if (kaydyt.contains(naapuri) && uusiG < naapuri.getEtaisyysAlusta()) {
                    naapuri.setEtaisyysAlusta(uusiG);
                    naapuri.setEdellinen(tarkastettava);
                }
                if (!kaymatta.contains(naapuri) || uusiG < naapuri.getEtaisyysAlusta()) {
                    naapuri.setEdellinen(tarkastettava);
                    naapuri.setEtaisyysAlusta(uusiG);
                    naapuri.setEtaisyysMaaliin(uusiG + heuristiikkaArvio(naapuri, loppu));
                    if (!kaymatta.contains(naapuri))
                        kaymatta.add(naapuri);
                }
                    
                
                if ( !kaymatta.contains(naapuri) ) {
                    naapuri.setEdellinen(tarkastettava);
                }
            }
        }
    }
    
    //KESKEN
    /**
     * Selvittää annetun noden naapurit.
     * Käytännössä siis myös diagonaaliset siirtymät ruudukossa.
     * @param a
     * @param n
     * @return 
     */
    public ArrayList<Node> selvitaNaapurit(Alue a, Node n) {
        ArrayList<Node> naapurit = new ArrayList<Node>();
        
        for (int i = n.getX()-1; i <= n.getX()+1; i++) {
            for (int j = n.getY()-1; j <= n.getY()+1; j++) {
                if (i >= 0 && j >= 0) {
                    naapurit.add(a.getnode(j, j));
                }
            }
        }
        return naapurit;
    }

    /**
     * Tilapäinen heuristiikkametodi, todennäköisesti voi tulla omaksi
     * luokakseen ja palauttamaan järkeviä arvoja joka tapauksessa.
     * @param naapuri
     * @param loppu
     * @return 
     */
    private int heuristiikkaArvio(Node naapuri, Node loppu) {
        
        //TODO, palauttaa vain Dijkstran mukaisen nollan
        return 0;
    }
}
