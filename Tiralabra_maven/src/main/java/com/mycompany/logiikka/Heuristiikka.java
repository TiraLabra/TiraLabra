package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;
import com.mycompany.tira.KasiLista;
import com.mycompany.tira.Kasipari;
import com.mycompany.tira.ListaSolmu;

public class Heuristiikka {

    private KasiLista kasiLista;
    private static final int KASILISTANKOKO = 20;
    private int pelaaja;
    private int kone;
    private int voitto;

    public Heuristiikka(Statistiikka s) {
        this.kasiLista = new KasiLista(KASILISTANKOKO);
    }

    public void setPelaajanKasi(Kasi k) {
        this.pelaaja = muunnaKasiNumeroksi(k);
    }

    public void setTietokoneenKasi(Kasi k) {
        this.kone = muunnaKasiNumeroksi(k);
    }

    public void setVoitto(int v) {
        this.voitto = v;
    }

    public void updateKasilista() {
        Kasipari pari = new Kasipari(this.pelaaja, this.kone, this.voitto);
        this.kasiLista.lisaaKasipari(pari);
    }

    private int muunnaKasiNumeroksi(Kasi k) {
        if (k.getNimi().equals("KIVI")) {
            return 0;
        } else if (k.getNimi().equals("PAPERI")) {
            return 1;
        } else if (k.getNimi().equals("SAKSET")) {
            return 2;
        } else if (k.getNimi().equals("LISKO")) {
            return 3;
        } else {
            return 4;
        }
    }
    
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
        //Kasi todennakoisin = pelaajanTodennakoisinKasi(pelaajanKadet);
        
        return pelaajanTodennakoisinKasi(pelaajanKadet);
    }
    
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
