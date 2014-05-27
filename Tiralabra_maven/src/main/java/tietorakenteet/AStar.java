package tietorakenteet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.SortedSet;

/**
 * A Star -reitinhaun luokka.
 */
public class AStar {
    
    // A-starin allustavia rakenteita, tyypit tulevat muuttumaan
    /**
     * Tietorakenne joka sisältää haun kaikki jo käymät Nodet.
     */
    private ArrayList kaydyt;
    
    /**
     * Tietorakenne, joka sisältää haussa käymättä olevat Nodet.
     */
    private PriorityQueue<Node> kaymatta;
    
    //tilapäiskokeilua...
    private SortedSet<Node> kaymatta2;
    
    /**
     * Tietorakenne, johon tallennetaan haun löytämä optimaalisin reitti.
     */
    private ArrayList<Node> kuljettuReitti;

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
        
        alku.setEtaisyysAlusta(0);
        alku.setEtaisyysMaaliin(0 + heuristiikkaArvio(alku, loppu));
        
        
        while (!kaymatta.isEmpty()) {
            Node tarkastettava = kaymatta.poll();
            
            //Debug-tulostusta
            System.out.println(tarkastettava.toString());
            
            // Jos löydettiin, poistutaan;
            if (tarkastettava == loppu) {
                return;
            }
            
            kaymatta.remove(tarkastettava);
            kaydyt.add(tarkastettava);
            
            // Nykyisen naapurien päivitys:
            for (Node naapuri : selvitaNaapurit(a, tarkastettava)) {
                
                //Lasketaan naapurin etäisyys tätä tarkastelukautta
                int uusiG = tarkastettava.getEtaisyysAlusta() + laskeKustannus(tarkastettava, naapuri);
                
                if (kaydyt.contains(naapuri) && uusiG < naapuri.getEtaisyysAlusta()) {
                    naapuri.setEtaisyysAlusta(uusiG);
                    naapuri.setEdellinen(tarkastettava);
                }
                else if (!kaymatta.contains(naapuri) || uusiG < naapuri.getEtaisyysAlusta()) {
                    naapuri.setEdellinen(tarkastettava);
                    naapuri.setEtaisyysAlusta(uusiG);
                    naapuri.setEtaisyysMaaliin(uusiG + heuristiikkaArvio(naapuri, loppu));
                    if (!kaymatta.contains(naapuri))
                        kaymatta.add(naapuri);
                }
                    
                
                //if ( !kaymatta.contains(naapuri) ) {
                //    naapuri.setEdellinen(tarkastettava);
                //}
            }
        }
    }
    
    /**
     * Selvittää annetun noden naapurit.
     * Käytännössä siis myös diagonaaliset siirtymät ruudukossa.
     * TODO: Ei huomioi läpipääsemättömiksi merkittyjä nodeja vielä...
     * @param a
     * @param n
     * @return 
     */
    public ArrayList<Node> selvitaNaapurit(Alue a, Node n) {
        ArrayList<Node> naapurit = new ArrayList<Node>();
        
        for (int i = n.getX()-1; i <= n.getX()+1; i++) {
            for (int j = n.getY()-1; j <= n.getY()+1; j++) {
                if ( (i >= 0 && j >= 0) && !(i==n.getX() && j==n.getY()) ) {
                    
                    naapurit.add(a.getnode(i, j));
                    //System.out.println(n.getX()+", "+ n.getY() + "naapuri: " + i + "," + j);
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
    
    /**
     * Metodi, joka laskee kahden noden välisen kustannuksen.
     * TODO: Palauttaa toistaiseksi vain aina ykkösen... naapurikäyttöä ajatellen.
     * Otettava huomioon Noden kustannusarvo...
     * @param tarkastettava
     * @param naapuri
     * @return 
     */
    private int laskeKustannus(Node tarkastettava, Node naapuri) {
        return 1;
    }
}
