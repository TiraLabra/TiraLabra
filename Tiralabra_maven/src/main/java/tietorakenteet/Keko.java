package tietorakenteet;

/**
 * Oma versio Keko-tietorakenteen toteutuksesta.
 */
public class Keko {

    /**
     * Uuden luodun ilmentymän oletuskoko.
     */
    private static final int OLETUSKOKO = 10;

    private Comparable[] sisalto;
    
    private int koko;
    
    private int taulukonKoko;

    public Keko() {
        this(OLETUSKOKO);
    }

    public Keko(int koko) {
        this.sisalto = new Comparable[koko];
        this.taulukonKoko = koko;
        this.koko = 0;
    }
    
    /**
     * Konstruktori joka luo annetusta taulukosta keon.
     * TESTIKÄYTTÖÄ VARTEN!
     * HUOM! Ei toistaiseksi tee mitään taulukolle, olettaa että se 
     * täyttää valmiiksi kekoehdot.
     * @param kokoSisalto 
     */
    public Keko(Comparable[] kokoSisalto) {
        sisalto = new Comparable[kokoSisalto.length+10];
        for ( int i = 0; i < kokoSisalto.length; i++ ) {
            sisalto[i] = kokoSisalto[i];
        }
        this.taulukonKoko = kokoSisalto.length;
        this.koko = kokoSisalto.length;
    }
    
    public void lisaa(Comparable c) {
        if (koko >= taulukonKoko)
            kasvataTaulukkoa();
        sisalto[koko] = c;
        koko++;
        tasapainota(koko-1);
    }
    
    /**
     * Palauttaa pienimmän alkion.
     * Huom, tämä ei poista.
     * @return 
     */
    public Comparable kerroPienin() {
        return sisalto[0];
    }
    
    /**
     * Palauttaa pienimmän alkion ja poistaa sen keosta.
     * @return 
     */
    public Comparable poistaPienin() {
        Comparable palautus = sisalto[0];
        sisalto[0] = sisalto[koko-1];
        koko--;
        minHeapify(0);
        
        return palautus;
    }
    
    /**
     * Kertoo, kuuluuko haluttu alkio jo kekoon.
     * @param o
     * @return 
     */
    public boolean sisaltaa(Object o) {
        boolean sisaltaa = false;
        for (int i = 0; i < this.koko; i++) {
            if (o == this.sisalto[i])
                return true;
        }
        return sisaltaa;
    }
    
    protected void tasapainota(int k) {
        System.out.println("Tasapainotetaan kohta "+ k);
        Comparable tarkasteltava = sisalto[k];
        int vanhempi = vanhempi(k);
        while ( k > 0 && tarkasteltava.compareTo(sisalto[vanhempi]) < 0) {
            vaihda(k, vanhempi(k));
            k = vanhempi(k);
            tarkasteltava = sisalto[k];
            vanhempi = vanhempi(k);
        
        
        }
    }
    
    /**
     * Heapify-metodi, joka pitää huolta että pienin alkio on ylimpänä.
     * @param k 
     */
    protected void minHeapify(int k) {

        int pienin = etsiPienin(k, vasenLapsi(k), oikeaLapsi(k));

        if (pienin != k) {
            vaihda(k, pienin);
            minHeapify(pienin);
        }
        
    }
    
    /**
     * Apumetodi pienimmän alkion indeksin selvittämiseen annetusta kolmikosta.
     * @param k
     * @param vasen
     * @param oikea
     * @return 
     */
    protected int etsiPienin(int k, int vasen, int oikea) {
        int pienin = k;
        if (vasen > 0 && sisalto[k].compareTo(sisalto[vasen]) > 0)
            pienin = vasen;
        if (oikea > 0 && sisalto[pienin].compareTo(sisalto[oikea]) > 0 )
            pienin = oikea;
        
        return pienin;
    }
    
    /**
     * Palauttaa noden vasemman lapsen indeksin.
     * Jos ei ole, tuloksena -1.
     * @param k
     * @return 
     */
    protected int vasenLapsi(int k) {
        int kohde = 2*k+1;
        if (kohde < koko)
            return kohde;
        else
            return -1;
    }
    
    /**
     * Palauttaa noden oikean lapsen indeksin.
     * Jos ei ole, tuloksena -1.
     * @param k
     * @return 
     */
    protected int oikeaLapsi(int k) {
        int kohde = 2*k+2;
        if (kohde < koko)
            return kohde;
        else
            return -1;
    }
    
    /**
     * Palauttaa noden vanhemman.
     * @param k
     * @return 
     */
    protected int vanhempi(int k) {
        int kohde;
        if (k>0) {
            kohde = (int)Math.floor(k/2);
        }
        else
            kohde = -1;
         
        return kohde;
    }
    
    /**
     * Sisäinen apumetodi, joka tuplaa taulukon koon.
     */
    private void kasvataTaulukkoa() {
        
        Comparable[] uusiTaulukko = new Comparable[2*taulukonKoko];
        for (int i = 0; i < koko; i++) {
            uusiTaulukko[i] = sisalto[i];
        }
        sisalto = uusiTaulukko;
        taulukonKoko = 2*taulukonKoko;
    }
    
    /**
     * Kertoo onko keko tyhjä.
     * @return 
     */
    public boolean onTyhja() {
        if (koko == 0)
            return true;
        else
            return false;
    }
    
    /**
     * Kertoo keon koon.
     * @return 
     */
    public int koko() {
        return this.koko;
    }

    @Override
    public String toString() {
        String t = "";
        for ( int i = 0; i < this.koko; i++) {
            t = t + i + ": " + sisalto[i].toString() + "\n";
        }
        return t;
    }
    
    //Kokeilua...
//    public String tulosta() {
//        String t = "";
//        
//        for (int i = 0; i < this.koko; i++) {
//            
//        }
//        return t;
//    }

    /**
     * Apumetodi joka vaihtaa kahden alkion paikan.
     * @param eka
     * @param toka 
     */
    private void vaihda(int eka, int toka) {
        Comparable temp = sisalto[eka];
        sisalto[eka] = sisalto[toka];
        sisalto[toka] = temp;
    }
    
    /**
     * Palauttaa keon taulukkosisällön, testausta varten.
     * @return 
     */
    protected Comparable[] sisalto() {
        return this.sisalto;
    }

    
}
