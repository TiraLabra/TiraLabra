package hakualgoritmit;

import heuristiikat.Heuristiikka;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.SortedSet;
import tietorakenteet.Alue;
import tietorakenteet.ArrayListOma;
import tietorakenteet.Keko;
import tietorakenteet.Node;
//import tietorakenteet.NodeComparator;

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
    //private PriorityQueue<Node> kaymatta;
    private Keko kaymatta;
    
    //tilapäiskokeilua...
    //private SortedSet<Node> kaymatta2;
    
    /**
     * Tietorakenne, johon tallennetaan haun löytämä optimaalisin reitti.
     */
    //private ArrayList<Node> kuljettuReitti;     // Javan oma ArrayList
    private ArrayListOma kuljettuReitti;
    
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
    
    private long kokonaisaika;
    
    /**
     * Määritellään, onko diagonaalinen eteneminen sallittu.
     */
    private boolean diagonaalinenSallittu = false;
    
    /**
     * boolean-muuttuja, jolla kontrolloidaan debug-tarkoitukseen tehtäviä tulostuksia.
     */
    private final static boolean debug = false;

    public AStar(Heuristiikka heuristiikka) {
        //kaydyt = new ArrayList();     // Javan oma ArrayList
        kaydyt = new ArrayListOma();
        
        //Comparator<Node> comparator = new NodeComparator();
        //kaymatta = new PriorityQueue<Node>(10, comparator);  // Javan oma
        kaymatta = new Keko();
        
        this.heuristiikka = heuristiikka;
        
        //this.kuljettuReitti = new ArrayList<Node>();        // Javan oma ArrayList
        this.kuljettuReitti = new ArrayListOma();
        
    }
    
    /**
     * Varsinaisen AStar-haun toteuttava metodi.
     * EI TOIMI, VASTA ALUSTAVA RUNKO
     * @param a
     * @param alku
     * @param loppu 
     */
    public void AStarHaku(Alue a, Node alku, Node loppu) {
        
        long alkuaika = System.currentTimeMillis();
        
        // Alustetaan 
        //kaymatta.add(alku);                   // Javan oma
        kaymatta.lisaa(alku);
        
        alku.setEtaisyysAlusta(0);
        alku.setEtaisyysMaaliin(0 + heuristiikka.laskeArvio(alku, loppu));
        
        
        // HUOM!!
        // Tätä ei ehditty vielä siistimään, logiikkaongelman vuoksi
        // tämän kimpussa meni kauemmin kuin oletin... Jatkossa koodi        
        // tulee refaktoroitua selkeämmäksi. Nyt vielä mukana debuggausta
        // helpottavia tulostuksiakin, jotka lähtevät pois.
        //
        
        //while (!kaymatta.isEmpty()) {         // Javan oma
        while (!kaymatta.onTyhja()) {
            //Node tarkastettava = kaymatta.poll();     // Javan oma
            Node tarkastettava = (Node)kaymatta.poistaPienin();
            askelia++;
            
            //Debug-tulostusta
            if (debug)
                System.out.println("Tark: " + tarkastettava.toString());
            
            // Jos löydettiin, poistutaan;
            if (tarkastettava == loppu) {
                rakennaReitti(loppu);
                
                long loppuaika = System.currentTimeMillis();
                kokonaisaika = loppuaika - alkuaika;
                return;
            }
            
            //kaymatta.remove(tarkastettava);   // Javan oma
            
            //kaydyt.add(tarkastettava);  // Javan oma ArrayList
            kaydyt.lisaa(tarkastettava);
            
            // Nykyisen naapurien päivitys:
            //for (Node naapuri : selvitaNaapurit(a, tarkastettava)) { // Iteraattorilla..
            ArrayListOma naapurit = selvitaNaapurit(a, tarkastettava);
            for (int i = 0; i < naapurit.koko(); i++ ) {
                Node naapuri = (Node)naapurit.palautaKohdasta(i);
                if (debug)
                    System.out.println("  Naapuri: " + naapuri);
                //Lasketaan naapurin etäisyys tätä tarkastelukautta
                int uusiG = tarkastettava.getEtaisyysAlusta() + laskeKustannus(tarkastettava, naapuri);
                
                //if (kaydyt.contains(naapuri)) {   // Javan oma ArrayList
                if (kaydyt.sisaltaako(naapuri)) {
                    
                    if (debug)
                        System.out.println("  on jo käyty, ei lisätä.");
                    continue;
                }
                
                //if (!kaymatta.contains(naapuri) || uusiG < naapuri.getEtaisyysAlusta()) {       // Javan oma
                if (!kaymatta.sisaltaa(naapuri) || uusiG < naapuri.getEtaisyysAlusta()) {
                    naapuri.setEdellinen(tarkastettava);
                    naapuri.setEtaisyysAlusta(uusiG);
                    naapuri.setEtaisyysMaaliin(uusiG + heuristiikka.laskeArvio(naapuri, loppu));
                    //if (!kaymatta.contains(naapuri)) {                    // Javan oma
                    if (!kaymatta.sisaltaa(naapuri)) {
                        kaymatta.lisaa(naapuri);
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
    //public ArrayList<Node> selvitaNaapurit(Alue a, Node n) {  // Javan oma
    //    ArrayList<Node> naapurit = new ArrayList<Node>();     // Javan oma
    public ArrayListOma selvitaNaapurit(Alue a, Node n) {
        ArrayListOma naapurit = new ArrayListOma();
        
        for (int i = n.getRivi()-1; i <= n.getRivi()+1; i++) {
            for (int j = n.getSarake()-1; j <= n.getSarake()+1; j++) {
                if ( (i >= 0 && j >= 0 && i < a.getKorkeus() && j < a.getLeveys())  // tarkastukset ettei taulukon ulkopuolelta
                        && !(i==n.getRivi() && j==n.getSarake()) ) {                // tarkastus ettei lisätä tarkasteltavaa
                    // Tarkastellaan, ettei lisätä diagonalisia, jos ei ole sallittu
                    if ( (diagonaalinenSallittu || Math.abs((i-n.getRivi())+(j-n.getSarake())) <= 1 ) &&
                            a.getnode(i, j).kuljettavissa())
                        naapurit.lisaa(a.getnode(i, j));
                    
                    if (debug)
                        System.out.println(n.getRivi()+", "+ n.getSarake() + "naapuri: " + i + "," + j);
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
        
        // Jos on diagonaalinen siirtymä:
        if (tarkastettava.getRivi() != naapuri.getRivi() && tarkastettava.getSarake() != naapuri.getSarake() ) {
            return 14;          // pyöristetty arvo sqrt(2);
        } else {
            return 10;
        }
           
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
        //ArrayList<Node> invert = new ArrayList<Node>();       // Javan oma ArrayList
        ArrayListOma invert = new ArrayListOma();
        Node n = loppu;
        while (n.getEdellinen() != null) {
            invert.lisaa(n);
            n = n.getEdellinen();
            n.setOnReitilla(true);
        }
        
        //Käännetään 
        for (int i = invert.koko(); i>0; i--) {
            kuljettuReitti.lisaa(invert.palautaKohdasta(i-1));
        }
    }
    
    /**
     * Palauttaa ratkaisun jälkeen löydetyn reitin.
     * Palauttaa null, jos reittiä ei löytynyt tai kutsutaan ennen hakua.
     * @return 
     */
    public ArrayListOma kerroKuljettuReitti() {
        return this.kuljettuReitti;
    }
    
    /**
     * Palauttaa yhteenvetotietoja hausta tulostettavaksi.
     * @return 
     */
    public String yhteenveto() {
        String yv = "";
        
        yv += "Laskentaan kulunut aika: " + kokonaisaika + " ms\n";
        yv += "Laskennassa tutkittuja askelia: " + askelia + "\n";
        yv += "Toteutuneen polun pituus: " + this.kuljettuReitti.koko();
        
        return yv;
    }
    
}
