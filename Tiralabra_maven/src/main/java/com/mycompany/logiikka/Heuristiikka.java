package com.mycompany.logiikka;

import com.mycompany.tira.KasiLista;
import com.mycompany.tira.Kasipari;
import com.mycompany.tira.ListaSolmu;

/**
 * Luokka pitää yllä pelattuja pelikierroksia ja yrittää sen tiedon avulla
 * selvittää pelaajan seuraavaksi pelaaman käden
 */
public class Heuristiikka {

    private KasiLista kasiLista;
    private static final int KASILISTANKOKO = 20;

    /**
     * Konstruktori alustaa käsilistan
     */
    public Heuristiikka() {
        this.kasiLista = new KasiLista(KASILISTANKOKO);
    }
    
    /**
     * Metodi lisää heuristiikalle uuden käsiparin, joka toimii
     * heuristiikan historiadatana
     * 
     * @param pKasi pelaajan käsi 
     * @param kKasi tietokoneen käsi
     */

    public void paivitaHeuristiikka(int pKasi, int kKasi) {
        Kasipari pari = new Kasipari(pKasi, kKasi);
        this.kasiLista.lisaaKasipari(pari);
    }
    
    /**
     * Palauttaa viimeisimmäksi pelatun käden
     * 
     * @return viimeisin käsi 
     */

    public Kasipari getViimeisinKasipari() {
        return this.kasiLista.getViimeisinPari();
    }

    /**
     * Heuristiikka seuraa pelin historiaa ja yrittää sen perusteella määritellä
     * minkä käden pelaaja tulee seuraavana pelaamaan
     *
     * @return Käsi jonka pelaaja todennäköisimmin pelaa
     */
    public int pelaajaTuleePelaamaan() {
        int pelaajanKadet[] = new int[5];
        ListaSolmu seuraaja = this.kasiLista.getEkaSolmu();
        ListaSolmu edeltaja = seuraaja.getSeuraavaListaSolmu();
        ListaSolmu verrattava = this.kasiLista.getEkaSolmu();
        while (edeltaja != null) {
            Kasipari kseur = seuraaja.getKasipari();
            Kasipari kedel = edeltaja.getKasipari();
            Kasipari kverr = verrattava.getKasipari();
            if (kedel.getPelaajanKasi() == kverr.getPelaajanKasi()) {
                if (kedel.getKoneenKasi() == kverr.getKoneenKasi()) {
                    // kädet ovat samat, päivitä taulukko
                    pelaajanKadet[kseur.getPelaajanKasi()]++;
                }
            }
            seuraaja = seuraaja.getSeuraavaListaSolmu();
            edeltaja = edeltaja.getSeuraavaListaSolmu();
        }
        return pelaajanTodennakoisinKasi(pelaajanKadet);
    }

    /**
     * Laskee annetusta taulukosta suurimman arvon saaneen solun arvoa vastaavan
     * käden ja palauttaa sen
     *
     * @param kadet int[] tyyppinen taulukko käsistä
     * @return Käsi joka vastaa suurinta argumentin taulukon alkiota
     */
    private int pelaajanTodennakoisinKasi(int[] kadet) {
        int suurin = Integer.MIN_VALUE;
        int kasiIndeksi = -1;
        for (int i = 0; i < 4; i++) {
            if (kadet[i] > suurin) {
                kasiIndeksi = i;
                suurin = kadet[i];
            }
        }
        return kasiIndeksi;
    }
}
