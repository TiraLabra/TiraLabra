/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import haku.Reitti;


/**
 *
 * Reittioppaaseen erikoistunut prioriteettijono. Lisäys & poll pitäisi olla lähellä O(1):tä 
 * Tehokkuus riippuu jonon tiheydestä. Tilavaatimus saattaa olla huomattava
 * Toteutus mukaelma tästä: http://en.wikipedia.org/wiki/Priority_queue#Specialized_heaps
 *
 * @author E
 */
public class PrioriteettiJonoListalla {
    
    /////////////////////////
    // (WIP) TOIMII? -> EHKÄ
    // aikavaatimus vaikuttaisi olevan samalla tasolla kuin javan omassa priorityqueuessa
    // ainakin siis tässä asiayhteydessä (ei siis yleinen ratkaisu)
    // O((?????)) ?? ??
    ////////////////////////////

    /**
     * Taulukon maksimikoko 2^31-1
     */
    public static final int ARRAY_MAXSIZE = 2147483639;
    /**
     * Toteutustapana taulukko, jossa prioriteetilla p on vastaava linkitetty
     * lista
     */
    private LinkitettyLista<Reitti>[] jono; // WIP: tästä taitaa tulla melko harva taulukko
    /**
     * Pienimmän prioriteetin tunnus
     */
    private int head;
    /**
     * Jäsenten lkm
     */
    private int size;
    /**
     * Taulukon koko
     */
    private int maxSize;
    /**
     * Kasvatuskerroin taulukolle
     */
    private int growFactor;
    /**
     * Tarkkuus: reitin kustannus on double, kustannus kerrotaan tällä;
     * saatu kokonaisluku on saman arvioidun prioriteetin paikkana jonossa
     */
    private int tarkkuus;

    ///////////////////
    // Konstruktorit //
    ///////////////////
    
    /**
     * Oletuskonstruktori
     */
    public PrioriteettiJonoListalla() {
        head = Integer.MAX_VALUE;
        size = 0;
        tarkkuus = 100; // tarkkuus desimaalina 1/tarkkuus
        growFactor = 2;
        maxSize    = 60*tarkkuus; // vastaa 60 minuuttia, jos reitti laskee kustannukset minuuteissa
        jono       = new LinkitettyLista[maxSize];
    }

    /**
     * Lisätään oikealle paikalle arvo
     *
     * @param e Lisättävä arvo
     */
    public void add(Reitti e) {
        int prioriteetti = (int) (this.tarkkuus * (e.getArvioituKustannus() + e.getKustannus())); // erottelutarkkuus 0.01
        if (prioriteetti < head) {
            head = prioriteetti;
        }
        if (prioriteetti >= ARRAY_MAXSIZE) {
            System.out.println("HUPS! Kurja prioriteetti");
            prioriteetti = ARRAY_MAXSIZE-1; // sinne vaan!
        }
        if (prioriteetti >= maxSize) { // O(maxSize) <- kopioidaan arvot uuteen
            kasvata(prioriteetti);
        }

        if (this.jono[prioriteetti] == null) {
            this.jono[prioriteetti] = new LinkitettyLista<Reitti>();
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
        int prioriteetti = head;
        if (this.jono[prioriteetti] == null || this.jono[prioriteetti].isEmpty()) {
            prioriteetti = seuraava(); // max O(N), paljon tihemmässä pitäisi olla kuitenkin
        }
        Reitti reitti = this.jono[prioriteetti].poll(); // O(1)
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
        while (maxSize * factor < lisattavaPrioriteetti) {
            factor *= 2;
        }
        if (factor * this.maxSize >= ARRAY_MAXSIZE) {
            // HUPS!
            System.out.println("HUHHUH, ei pyde");
            this.maxSize=ARRAY_MAXSIZE;
        } else {
            this.maxSize = factor * this.maxSize;
        }
        LinkitettyLista<Reitti>[] uusiValues = new LinkitettyLista[this.maxSize];
        System.arraycopy(this.jono, 0, uusiValues, 0, this.size);
        this.jono = uusiValues;
    }
    /**
     * Seuraava arvon (epätyhjä lista) sisältävän taulukon indeksi
     * @return 
     */
    private int seuraava() {
        int index = this.head;        
        for (int i = index; i < maxSize; i++) {
            LinkitettyLista<Reitti> lista = this.jono[i];
            if (lista != null && !lista.isEmpty()) {
                return i;
            }
        }

        return -1; // ei ollut seuraavaa
    }
}
