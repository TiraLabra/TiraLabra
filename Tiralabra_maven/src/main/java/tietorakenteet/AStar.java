package tietorakenteet;

import heuristiikat.Heuristiikka;
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
    //private ArrayList kaydyt;
    private ArrayListOma kaydyt;
    
    /**
     * Tietorakenne, joka sisältää haussa käymättä olevat Nodet.
     */
    private PriorityQueue<Node> kaymatta;
    
    //tilapäiskokeilua...
    //private SortedSet<Node> kaymatta2;
    
    /**
     * Tietorakenne, johon tallennetaan haun löytämä optimaalisin reitti.
     */
    private ArrayList<Node> kuljettuReitti;
    //private ArrayListOma kuljettuReitti;
    
    /**
     * Heuristiikka, jota haun optimoinnissa käytetään.
     */
    private Heuristiikka heuristiikka;
    
    /**
     * Tieto, kuinka monta askelta haussa otettiin.
     * Käytössä lähinnä algoritmin toiminnan tarkastelua varten.
     * Ei ole ratkaisun askelmäärä, vaan tarkasteltujen nodejen määrä.
     */
    private int askelia;

    public AStar(Heuristiikka heuristiikka) {
        //kaydyt = new ArrayList();     // Javan oma ArrayList
        kaydyt = new ArrayListOma();
        
        Comparator<Node> comparator = new NodeComparator();
        kaymatta = new PriorityQueue<Node>(10, comparator);
        
        this.heuristiikka = heuristiikka;
        
        this.kuljettuReitti = new ArrayList<Node>();
        //this.kuljettuReitti = new ArrayListOma();
        
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
        alku.setEtaisyysMaaliin(0 + heuristiikka.laskeArvio(alku, loppu));
        
        
        // HUOM!!
        // Tätä ei ehditty vielä siistimään, logiikkaongelman vuoksi
        // tämän kimpussa meni kauemmin kuin oletin... Jatkossa koodi        // tulee refaktoroitua selkeämmäksi. Nyt vielä mukana debuggausta
        // helpottavia tulostuksiakin, jotka lähtevät pois.
        //
        
        while (!kaymatta.isEmpty()) {
            Node tarkastettava = kaymatta.poll();
            askelia++;
            
            //Debug-tulostusta
            System.out.println("Tark: " + tarkastettava.toString());
            
            // Jos löydettiin, poistutaan;
            if (tarkastettava == loppu) {
                rakennaReitti(loppu);
                return;
            }
            
            kaymatta.remove(tarkastettava);
            //kaydyt.add(tarkastettava);  // Javan oma ArrayList
            kaydyt.lisaa(tarkastettava);
            
            // Nykyisen naapurien päivitys:
            for (Node naapuri : selvitaNaapurit(a, tarkastettava)) {
                
                System.out.println("  Naapuri: " + naapuri);
                //Lasketaan naapurin etäisyys tätä tarkastelukautta
                int uusiG = tarkastettava.getEtaisyysAlusta() + laskeKustannus(tarkastettava, naapuri);
                
                //if (kaydyt.contains(naapuri)) {   // Javan oma ArrayList
                if (kaydyt.sisaltaako(naapuri)) {
                    
                    System.out.println("  on jo käyty, ei lisätä.");
                    continue;
                }
                
                if (!kaymatta.contains(naapuri) || uusiG < naapuri.getEtaisyysAlusta()) {
                    naapuri.setEdellinen(tarkastettava);
                    naapuri.setEtaisyysAlusta(uusiG);
                    naapuri.setEtaisyysMaaliin(uusiG + heuristiikka.laskeArvio(naapuri, loppu));
                    if (!kaymatta.contains(naapuri)) {
                        kaymatta.add(naapuri);
                    }
                }
                
                /*
                if (kaydyt.contains(naapuri) && uusiG < naapuri.getEtaisyysAlusta()) {
                    System.out.println("    löytyi naapureista, " + uusiG + " < " + naapuri.getEtaisyysAlusta());
                    naapuri.setEtaisyysAlusta(uusiG);
                    naapuri.setEdellinen(tarkastettava);
                }
                else if (!kaymatta.contains(naapuri) || uusiG < naapuri.getEtaisyysAlusta()) {
                    System.out.println("    ei vielä openlistissa");
                    naapuri.setEdellinen(tarkastettava);
                    naapuri.setEtaisyysAlusta(uusiG);
                    
                    // TODO: Heuristiikan valinta järkevämmäksi
                    //naapuri.setEtaisyysMaaliin(uusiG + heuristiikkaArvio(naapuri, loppu));
                    naapuri.setEtaisyysMaaliin(uusiG + heuristiikka.laskeArvio(naapuri, loppu));
                    
                    if (!kaymatta.contains(naapuri))
                        kaymatta.add(naapuri);
                        System.out.println("      lisätään openlistiin");
                }
                */
                    
                
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
        
        for (int i = n.getRivi()-1; i <= n.getRivi()+1; i++) {
            for (int j = n.getSarake()-1; j <= n.getSarake()+1; j++) {
                if ( (i >= 0 && j >= 0 && i < a.getKorkeus() && j < a.getLeveys()) && !(i==n.getRivi() && j==n.getSarake()) ) {
                    if (a.getnode(i, j).kuljettavissa())
                        naapurit.add(a.getnode(i, j));
                    //System.out.println(n.getRivi()+", "+ n.getSarake() + "naapuri: " + i + "," + j);
                }
            }
        }
        return naapurit;
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

    public int getAskelia() {
        return askelia;
    }
    
    /**
     * Reitin rakentava metodi.
     * Nopea alustava toteutus, pitäisi tutkia voiko tehdä fiksummin ja siistiä tätä vielä.
     * @param loppu 
     */
    private void rakennaReitti(Node loppu) {
        ArrayList<Node> invert = new ArrayList<Node>();
        Node n = loppu;
        while (n.getEdellinen() != null) {
            invert.add(n);
            n = n.getEdellinen();
        }
        
        //Käännetään 
        for (int i = invert.size(); i>0; i--) {
            kuljettuReitti.add(invert.get(i-1));
        }
    }
    
    /**
     * Palauttaa ratkaisun jälkeen löydetyn reitin.
     * Palauttaa null, jos reittiä ei löytynyt tai kutsutaan ennen hakua.
     * @return 
     */
    public ArrayList<Node> kerroKuljettuReitti() {
        return this.kuljettuReitti;
    }
    
}
