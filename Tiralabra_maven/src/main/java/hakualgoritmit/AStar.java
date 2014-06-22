package hakualgoritmit;

import heuristiikat.Heuristiikka;
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
    
    /**
     * Kokonaisajan tallentava muuttuja.
     */
    private long kokonaisaika;
    
    /**
     * Määritellään, onko diagonaalinen eteneminen sallittu.
     */
    private boolean diagonaalinenSallittu = true;
    
    public AStar(Heuristiikka heuristiikka) {
        kaydyt = new ArrayListOma();
        
        kaymatta = new Keko();
        
        this.heuristiikka = heuristiikka;
        
        this.kuljettuReitti = new ArrayListOma();
        
    }
    
    /**
     * Varsinaisen AStar-haun toteuttava metodi.
     * @param a
     * @param alku
     * @param loppu 
     * @return kertoo löytyikö reitti.
     */
    public boolean AStarHaku(Alue a, Node alku, Node loppu) {
        
        if (!hakuKelvollinen(a, alku, loppu))
            return false;
        
        long alkuaika = System.currentTimeMillis();
        
        // Alustetaan
        alustus(alku, loppu);
        
        while (!kaymatta.onTyhja()) {
            Node tarkastettava = kaymatta.poistaPienin();
            askelia++;
            
            if (tarkastettava == loppu) {               // Jos päädyttiin loppualkioon, poistutaan.
                rakennaReitti(loppu);
                kokonaisaika = System.currentTimeMillis() - alkuaika;
                return true;
            }
            
            kaydyt.lisaa(tarkastettava);                // Nykyinen lisätään käytyjen joukkoon.

            // Selvitetään tarkasteltavan noden naapurit:
            ArrayListOma naapurit = selvitaNaapurit(a, tarkastettava);
            
            // Käydään läpi tarkasteltavan kaikki naapurit:
            for (int i = 0; i < naapurit.koko(); i++ ) {
                kasitteleNaapuri( (Node) naapurit.palautaKohdasta(i), tarkastettava);
            }
        }
        return false;
    }
    
    /**
     * Tarkastelee Astar-haun käsittelyssä naapurin.
     * Päivittää etäisyyslaskennat, lisää sen tarvittaessa 
     * @param naapuri
     * @param tarkastettava 
     */
    private void kasitteleNaapuri(Node naapuri, Node tarkastettava) {
        naapuri.setLisattyNaapureihin(true);    // Debug-tarkoitukseen tietoa onko nodea otettu naapureihin mukaan..
        
        int uusiG = tarkastettava.getEtaisyysAlusta() + laskeKustannus(tarkastettava, naapuri);     // Uusi mahdollinen kustannus tähän naapuriin
        
        if (kaydyt.sisaltaako(naapuri) || kaymatta.sisaltaa(naapuri)) {
            // Jos naapuri on jo käydyissä
            if (uusiG < naapuri.getEtaisyysAlusta()) {      // ... katsotaan onko uutta kautta pienempi kustannus.
                paivitaLyhyinYhteys(naapuri, tarkastettava, uusiG);
            }
            return;
        }
        if (!kaymatta.sisaltaa(naapuri)) {
            paivitaLyhyinYhteys(naapuri, tarkastettava, uusiG);
            kaymatta.lisaa(naapuri);
        }
    }
    
    /**
     * Suorittaa haun alkutoimenpiteet.
     * Päivittää alun ja lopun tiedot luokkamuuttujiin, ja asettaa alkunoden tiedot oikeiksi.
     * @param alku
     * @param loppu 
     */
    private void alustus(Node alku, Node loppu) {
        this.alkunode = alku;
        this.loppunode = loppu;
        alku.setEtaisyysAlusta(0);
        alku.setEtaisyysMaaliin(0 + heuristiikka.laskeArvio(alku, loppu));
        kaymatta.lisaa(alku);
    }
    
    /**
     * Päivittää halutulle nodelle tiedon lyhimmästä matkasta.
     * Parametrina annetaan myös naapuri, jonka kautta lyhin matka toteutuu.
     * @param n
     * @param edellinen
     * @param matka 
     */
    private void paivitaLyhyinYhteys(Node n, Node edellinen, int matka) {
        n.setEtaisyysAlusta(matka);
        n.setEdellinen(edellinen);
        if (n.getEtaisyysMaaliin() == 0 )
            n.setEtaisyysMaaliin(heuristiikka.laskeArvio(n, loppunode));
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
    
    private boolean hakuKelvollinen(Alue a, Node alku, Node loppu) {
        
        // Tarkastetaan että sekä alku- että loppusolmu ovat kuljettavissa.
        if (!alku.kuljettavissa() || !loppu.kuljettavissa())
            return false;
        
        // Tarkastetaan että alku ja loppu ovat alueen rajojen sisällä.
        if (alku.getRivi() > a.getKorkeus() ||
                alku.getSarake() > a.getLeveys() ||
                loppu.getRivi() > a.getKorkeus() ||
                loppu.getSarake() > a.getLeveys())
            return false;
        
        return true;
    }
    
}
