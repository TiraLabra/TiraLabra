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

/**
 * A Star -reitinhaun luokka.
 */
public class AStar {
    
    // A-starin allustavia rakenteita, tyypit tulevat muuttumaan
    /**
     * Tietorakenne joka sisältää haun kaikki jo käymät Nodet.
     */
    private ArrayListOma kaydyt;
    
    /**
     * Tietorakenne, joka sisältää haussa käymättä olevat Nodet.
     */
    private Keko kaymatta;
    
    /**
     * Tietorakenne, johon tallennetaan haun löytämä optimaalisin reitti.
     */
    private ArrayListOma kuljettuReitti;
    
    /**
     * Heuristiikka, jota haun optimoinnissa käytetään.
     */
    private Heuristiikka heuristiikka;
    
    private Node alkunode;
    private Node loppunode;
    
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
    private boolean diagonaalinenSallittu = true;
    
    /**
     * boolean-muuttuja, jolla kontrolloidaan debug-tarkoitukseen tehtäviä tulostuksia.
     */
    private final static boolean debug = true;

    public AStar(Heuristiikka heuristiikka) {
        kaydyt = new ArrayListOma();
        
        kaymatta = new Keko();
        
        this.heuristiikka = heuristiikka;
        
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
        
        if (debug) System.out.println("Alkunode: " + alku + "\n" + "Loppunode: " + loppu);
        long alkuaika = System.currentTimeMillis();
        
        // Alustetaan
        this.alkunode = alku;
        this.loppunode = loppu;
        kaymatta.lisaa(alku);
        
        alku.setEtaisyysAlusta(0);
        alku.setEtaisyysMaaliin(0 + heuristiikka.laskeArvio(alku, loppu));
        
        // HUOM!!
        // Tätä ei ehditty vielä siistimään, logiikkaongelman vuoksi
        // tämän kimpussa meni kauemmin kuin oletin... Jatkossa koodi        
        // tulee refaktoroitua selkeämmäksi. Nyt vielä mukana debuggausta
        // helpottavia tulostuksiakin, jotka lähtevät pois.
        //
        
        while (!kaymatta.onTyhja()) {
            Node tarkastettava = kaymatta.poistaPienin();
            askelia++;
            
            if (debug) System.out.println("---------\nTark: " + tarkastettava.toString());
            
            if (tarkastettava == loppu) {               // Jos päädyttiin loppualkioon, poistutaan.
                rakennaReitti(loppu);
                kokonaisaika = System.currentTimeMillis() - alkuaika;
                return;
            }
            
            kaydyt.lisaa(tarkastettava);                // Nykyinen lisätään käytyjen joukkoon.
            if (debug) System.out.println("Käytyjä tähän asti:" + kaydyt.koko());
            
            // Selvitetään tarkasteltavan noden naapurit:
            ArrayListOma naapurit = selvitaNaapurit(a, tarkastettava);
            if (debug) System.out.println("Naapureita " + naapurit.koko() + " kpl.");
            
            // Käydään läpi tarkasteltavan kaikki naapurit:
            for (int i = 0; i < naapurit.koko(); i++ ) {
                kasitteleNaapuri( (Node) naapurit.palautaKohdasta(i), tarkastettava);
            }
        }
    }

    private void kasitteleNaapuri(Node naapuri, Node tarkastettava) {
        naapuri.setLisattyNaapureihin(true);    // Debug-tarkoitukseen tietoa onko nodea otettu naapureihin mukaan..
        if (debug) System.out.println("  Naapuri: " + naapuri);
        
        int uusiG = tarkastettava.getEtaisyysAlusta() + laskeKustannus(tarkastettava, naapuri);     // Uusi mahdollinen kustannus tähän naapuriin
        if (debug) System.out.println("    UusiG = " + uusiG);
        
        if (kaydyt.sisaltaako(naapuri)) {
            // Jos naapuri on jo käydyissä
            if (uusiG < naapuri.getEtaisyysAlusta()) {      // ... katsotaan onko uutta kautta pienempi kustannus.
                naapuri.setEtaisyysAlusta(uusiG);
                naapuri.setEdellinen(tarkastettava);
                if (debug) System.out.println("    Noden kustannusarvoa päivitettiin.");
            } else {
                if (debug) System.out.println("    Vanha kustannusarvo oli pienempi...");
            }
            if (debug) System.out.println("    on jo käyty, ei tehdä mitään.");
            return;
        }
        if (!kaymatta.sisaltaa(naapuri)) {
            if (debug) System.out.print("    Ei ole vielä lisätty käymättä-joukkoon, lisätään uutena: ");
            naapuri.setEdellinen(tarkastettava);
            naapuri.setEtaisyysAlusta(uusiG);
            naapuri.setEtaisyysMaaliin(heuristiikka.laskeArvio(naapuri, loppunode));
            kaymatta.lisaa(naapuri);
            if (debug) System.out.println(naapuri.toString());
        }
    }
    
    /**
     * Selvittää annetun noden naapurit.
     * Diagonalisten naapureiden lisäystä määritellään luokkamuuttujassa diagonaalinenSallittu.
     * @param a
     * @param n
     * @return 
     */
    public ArrayListOma selvitaNaapurit(Alue a, Node n) {
        ArrayListOma naapurit = new ArrayListOma();
        
        for (int i = n.getRivi()-1; i <= n.getRivi()+1; i++) {
            for (int j = n.getSarake()-1; j <= n.getSarake()+1; j++) {
                if ( (i >= 0 && j >= 0 && i < a.getKorkeus() && j < a.getLeveys())  // tarkastukset ettei taulukon ulkopuolelta
                        && !(i==n.getRivi() && j==n.getSarake()) ) {                // tarkastus ettei lisätä tarkasteltavaa
                    // Tarkastellaan, ettei lisätä diagonalisia, jos ei ole sallittu
                    if ( (diagonaalinenSallittu || Math.abs((i-n.getRivi())+(j-n.getSarake())) <= 1 ) &&
                            a.getnode(i, j).kuljettavissa())          // Varmistetaan ettei ole seinä
                        naapurit.lisaa(a.getnode(i, j));
                    
                    //if (debug)
                    //    System.out.println(n.getRivi()+", "+ n.getSarake() + "naapuri: " + i + "," + j);
                }
            }
        }
        return naapurit;
    }
    
    /**
     * Metodi, joka laskee kahden noden välisen kustannuksen.
     * Kustannuskertoimeksi on määritelty mielivaltaiset, ei-lineaariset kertoimet.
     * Näitä voi vielä säätää mielekkääksi jatkossakin...
     * @param tarkastettava
     * @param naapuri
     * @return 
     */
    public int laskeKustannus(Node tarkastettava, Node naapuri) {
        
        double kustannuskerroin;
        if (naapuri.getKustannus() == 0)
            kustannuskerroin = 1.0;
        else if (naapuri.getKustannus() == 1)
            kustannuskerroin = 1.5;
        else if (naapuri.getKustannus() == 2)
            kustannuskerroin = 2.0;
        else if (naapuri.getKustannus() == 3)
            kustannuskerroin = 4.0;
        else if (naapuri.getKustannus() == 4)
            kustannuskerroin = 8.0;
        else if (naapuri.getKustannus() == 5)
            kustannuskerroin = 16.0;
        else
            kustannuskerroin = 100.0;
        
        int kustannus = (int) (10 * kustannuskerroin);
        
        // Jos on diagonaalinen siirtymä:
        if (Math.abs(tarkastettava.getRivi()-naapuri.getRivi() + tarkastettava.getSarake()-naapuri.getSarake()) > 1 ) {
            kustannus = (int)(kustannus * Math.sqrt(2));          // sivuttaissiirtymässä kerroin on neliöjuuri(2)
        }
        
        return kustannus;
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
