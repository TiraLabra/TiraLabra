/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import haku.Reitti;

/**
 *
 * Reittioppaaseen erikoistunut prioriteettijono. Lisäys & poll pitäisi olla
 * lähellä O(1):tä Tehokkuus riippuu jonon tiheydestä. Tilavaatimus saattaa olla
 * huomattava.
 * Toteutus mukaelma tästä:
 * http://en.wikipedia.org/wiki/Priority_queue#Specialized_heaps
 *
 * @author E
 */
public class PrioriteettiJonoListalla {

    /////////////////////////
    // (WIP) TOIMII? -> EHKÄ
    // aikavaatimus vaikuttaisi olevan samalla tasolla kuin javan omassa priorityqueuessa
    // toimii ainakin tässä asiayhteydessä (ei siis yleinen ratkaisu)
    // reittiLaskimen palauttamat kustannukset ja arvioidut kustannukset 
    // vaikuttavat toimintaan huomattavasti: mitä enemmän desimaaleja kustannuksissa, sitä enemmän
    // tarvittaisiin tarkkuutta
    // O((?????)) ?? ??
    //
    ////////////////////////////
    
    /**
     * Taulukon maksimikoko 2^31-1
     */
    public static final int ARRAY_MAXSIZE = 2147483639;
    /**
     * Toteutustapana taulukko, jossa prioriteetilla p on vastaava linkitetty
     * lista
     */
    private Jono<Reitti>[] jono; // WIP: tästä taitaa tulla melko harva taulukko
    /**
     * Parhaan prioriteetin osoitin
     */
    private int head;
    /** Paras prioriteetti
     * 
     */
    private int headPrioriteetti;
    /**
     * Jäsenten lkm
     */
    private int size;
    /**
     * Taulukon koko
     */
    public int maxSize;
    /**
     * Kasvatuskerroin taulukolle
     */
    private int growFactor;
    /**
     * Tarkkuus: reitin kustannus on double, kustannus kerrotaan tällä; saatu
     * kokonaisluku on saman arvioidun prioriteetin paikkana jonossa
     */
    private final int TARKKUUS;
    /**
     * Aloituskerroin tarkkuudelle: saadaan taulukon koko
     */
    private final int N;
    
    

    ///////////////////
    // Konstruktorit //
    ///////////////////
    
    /**
     * Oletuskonstruktori
     */
    public PrioriteettiJonoListalla() {
        TARKKUUS = 100; // tarkkuus desimaalina 1/tarkkuus --> vaikuttaa suoritusaikaan
        N = 45;   // esim: jos kustannus aika minuuteissa, N on maksimiminuuttimäärä... 
        // (yli ensimmäisen heuristisen arvion, johon normalisoidaan)

        head = 0;
        headPrioriteetti = Integer.MAX_VALUE;
        size = 0;
        growFactor = 2;
        maxSize = N * TARKKUUS; // vastaa N minuuttia, jos reitti laskee kustannukset minuuteissa
        jono = new Jono[maxSize];
    }
    // WIP: debug
    
    /**
     * Kuinka monta kertaa tallennetaan reitti kustannuksella joka on alle jonon ensimmäisen
     */
    private int count = 0;
    public int lastIndexInUse = 0;
    /**
     * Lisätään oikealle paikalle arvo
     *
     * @param e Lisättävä arvo
     */
    public void add(Reitti e) {
        int prioriteetti = (int) (this.TARKKUUS * (e.getArvioituKustannus() + e.getKustannus())); // erottelutarkkuus 1/tarkkuus
        if (prioriteetti < headPrioriteetti) {
            headPrioriteetti = prioriteetti;
            // JOS heuristiikka toimii, ei pitäisi tapahtua kuin alussa
            if ( count>=1 ) {
                System.out.println("ERRORERROR!!!!");
                System.out.println("g="+e.getKustannus()+"+h="+e.getArvioituKustannus());
            }
            count++;
            
        }
        // TÄSSÄ (ei yleisesti!) voi tehdä näin: reittien kustannukset ovat kasvavia jos heuristiikka toimii
        prioriteetti-=headPrioriteetti; // normalisoidaan päähän
        if ( lastIndexInUse < prioriteetti ) {
            lastIndexInUse = prioriteetti;
        }
        // prioriteetti-=headPrioriteetti; // normalisoidaan päähän
        if (prioriteetti >= ARRAY_MAXSIZE) {
            System.out.println("HUPS! Kurja prioriteetti");
            prioriteetti = ARRAY_MAXSIZE - 1; // sinne vaan!
        }
        if (prioriteetti >= maxSize-1) { // O(maxSize) <- kopioidaan arvot uuteen
            kasvata(prioriteetti);
        }

        if (this.jono[prioriteetti] == null) {
            this.jono[prioriteetti] = new Jono<Reitti>();
        }
        this.jono[prioriteetti].add(e); // oikeaan listaan O(1)
        size++;
    }

    /**
     * Poistetaan ja palautetaan korkeimman prioriteetin arvo
     *
     * @return
     */
    public Reitti poll() {
        if (head < 0 || head >= maxSize || this.isEmpty()) {
            return null;
        }
        // int prioriteetti = head;
        if (this.jono[head] == null || this.jono[head].isEmpty()) {
            head = seuraava(); // max O(N), paljon tihemmässä pitäisi olla kuitenkin
            // headPrioriteetti += head;
            // headPrioriteetti = headPrioriteetti+head;
        }
        Reitti reitti = this.jono[head].poll(); // O(1)
        size--;
        return reitti;
    }

    /**
     * Tarkistaa, onko jonossa vielä jäseniä
     *
     * @return true, jos tyhjä
     */
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Jonon koko
     *
     * @return Jonon koko
     */
    public int size() {
        return size;
    }

    ////////////////////////
    // yksityiset metodit //
    ////////////////////////
    /**
     * Kasvattaa jonon kokoa jotta uusi arvo mahtuu siihen
     *
     * @param lisattavaPrioriteetti
     */
    private void kasvata(int lisattavaPrioriteetti) {
        int factor = this.growFactor;
        int oldSize = this.maxSize;
        while (maxSize * factor < lisattavaPrioriteetti) {
            factor *= 2;
        }
        if (factor * this.maxSize >= ARRAY_MAXSIZE) {
            // HUPS!
            System.out.println("HUHHUH, ei pyde");
            this.maxSize = ARRAY_MAXSIZE;
        } else {
            this.maxSize = factor * this.maxSize;
        }
        Jono<Reitti>[] uusiValues = new Jono[this.maxSize];
        System.arraycopy(this.jono, 0, uusiValues, 0, oldSize);
        this.jono = uusiValues;
    }

    /**
     * Seuraava arvon (epätyhjä lista) sisältävän taulukon indeksi
     *
     * @return
     */
    private int seuraava() {
        int index = this.head;
        for (int i = index; i < maxSize; i++) {
            Jono<Reitti> lista = this.jono[i];
            if (lista != null && !lista.isEmpty()) {
                return i;
            }
        }

        return -1; // ei ollut seuraavaa
    }
}
