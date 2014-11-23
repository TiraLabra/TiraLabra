package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;
import com.mycompany.tira.KasiLista;
import com.mycompany.tira.Kasipari;
import com.mycompany.tira.ListaSolmu;

/**
 * Luokka pitää yllä pelattuja pelikierroksia ja yrittää sen tiedon
 * avulla selvittää pelaajan seuraavaksi pelaaman käden
 */
public class Heuristiikka {

    private KasiLista kasiLista;
    private static final int KASILISTANKOKO = 20;
    private int pelaaja;
    private int kone;
    private int voitto;

    /**
     * Konstruktori alustaa luokkamuuttujat
     */
    public Heuristiikka() {
        this.kasiLista = new KasiLista(KASILISTANKOKO);
    }

    /**
     * Asettaa pelaajan käden heuristiikkaa varten
     * 
     * @param pelaajanKasi Pelaajan pelaama käsi 
     */
    public void setPelaajanKasi(Kasi pelaajanKasi) {
        this.pelaaja = muunnaKasiNumeroksi(pelaajanKasi);
    }

    /**
     * Asettaa tekoälyn käden heuristiikkaa varten
     * 
     * @param koneenKasi Tekoälyn pelaama käsi 
     */
    public void setTietokoneenKasi(Kasi koneenKasi) {
        this.kone = muunnaKasiNumeroksi(koneenKasi);
    }

    /**
     * Asettaa voittotilanteen heuristiikkaa varten
     * Voittoa kuvaa kokonaisluku:
     * <ul>
     * <li> 1 = Pelaaja voitti
     * <li> 0 = Tasapeli
     * <li> -1 = Pelaaja hävisi
     * </ul>
     * 
     * @param voitto Voittoa kuvaava kokonaisluku 
     */
    public void setVoitto(int voitto) {
        this.voitto = voitto;
    }

    /**
     * Päivittää heuristiikan settereissä annettujen käsien perusteella
     */
    public void updateKasilista() {
        Kasipari pari = new Kasipari(this.pelaaja, this.kone, this.voitto);
        this.kasiLista.lisaaKasipari(pari);
    }

    /**
     * Muuntaa paramentrina annetun käden kokonaisluvuksi.
     * Luvut kuvaavat käsiä:
     * <ul>
     * <li> 0 = Kivi
     * <li> 1 = Paperi
     * <li> 2 = Sakset
     * <li> 3 = Lisko
     * <li> 4 = Spock
     * </ul>
     * 
     * @param muunnettavaKasi Käsi joka muunnetaan luvuksi
     * @return Kättä vastaava kokonaisluku
     */
    private int muunnaKasiNumeroksi(Kasi muunnettavaKasi) {
        if (muunnettavaKasi.getNimi().equals("KIVI")) {
            return 0;
        } else if (muunnettavaKasi.getNimi().equals("PAPERI")) {
            return 1;
        } else if (muunnettavaKasi.getNimi().equals("SAKSET")) {
            return 2;
        } else if (muunnettavaKasi.getNimi().equals("LISKO")) {
            return 3;
        } else {
            return 4;
        }
    }
    
    /**
     * Heuristiikka seuraa pelin historiaa ja yrittää sen perusteella
     * määritellä minkä käden pelaaja tulee seuraavana pelaamaan
     * 
     * @return Käsi jonka pelaaja todennäköisimmin pelaa 
     */
    public Kasi pelaajaTuleePelaamaan() {
        // Käy lista läpi ja etsi tilanteet, joissa samat kädet kuin
        // luokkamuuttujat
        int pelaajanKadet[] = new int[5];
        ListaSolmu s = this.kasiLista.getEnsimmainenSolmu();
        while (true) {
            Kasipari k = s.getKasipari();
            if (k.getPelaajanKasi() == this.pelaaja) {
                if (k.getKoneenKasi() == this.kone) {
                    // kädet ovat samat, päivitä taulukko
                    pelaajanKadet[s.getSeuraavaListaSolmu().getKasipari().getPelaajanKasi()]++;
                }
            }
            if (s.getSeuraavaListaSolmu() == null) {
                break;
            } else {
                s = s.getSeuraavaListaSolmu();
            }
        }
        return pelaajanTodennakoisinKasi(pelaajanKadet);
    }

    /**
     * Laskee annetusta taulukosta suurimman arvon saaneen solun arvoa
     * vastaavan käden ja palauttaa sen
     * 
     * @param kadet int[] tyyppinen taulukko käsistä
     * @return Käsi joka vastaa suurinta argumentin taulukon alkiota
     */
    private Kasi pelaajanTodennakoisinKasi(int[] kadet) {
        int suurin = Integer.MIN_VALUE;
        int kasiIndeksi = -1;
        for (int i = 0; i < 4; i++) {
            if (kadet[i] > suurin) {
                kasiIndeksi = i;
                suurin = kadet[i];
            }
        }

        if (kasiIndeksi == 0) {
            return new Kasi("KIVI");
        } else if (kasiIndeksi == 1) {
            return new Kasi("PAPERI");
        } else if (kasiIndeksi == 2) {
            return new Kasi("SAKSET");
        } else if (kasiIndeksi == 3) {
            return new Kasi("LISKO");
        } else {
            return new Kasi("SPOCK");
        }
    }
}
