package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;

/**
 * Luokka huolehtii pelin voittologiikasta. Syottämällä luokalle
 * pelaajan ja tekoälyn kädet luokka tarkistaa kuka voittaa
 * 
 */
public class Logiikka {
    
    private Kasi pelaaja;
    private Kasi tekoAly;
    private Heuristiikka heuristiikka;
    
    /**
     * Konstruktori alustaa luokkamuuttujat
     */
    public Logiikka(Heuristiikka h) {
        this.pelaaja = null;
        this.tekoAly = null;
        this.heuristiikka = h;
    }
    
    /**
     * Palauttaa viimeisimmän voittovertailun pelaajan käden
     * @return pelaajan käsi
     */
    public Kasi pelaajanViimeisinKasi() {
        return this.pelaaja;
    }
    
    /**
     * Asettaa pelaajan käden vertailua varten
     * @param pelaajanKasi Käsi
     */
    public void setPelaajanKasi(Kasi pelaajanKasi) {
        this.pelaaja = pelaajanKasi;
        this.heuristiikka.setPelaajanKasi(pelaajanKasi);
    }
    
    /**
     * Asettaa tekoälyn käden vertailua varten
     * @param tekoalynKasi Käsi
     */
    public void setTekoalynKasi(Kasi tekoalynKasi) {
        this.tekoAly = tekoalynKasi;
        this.heuristiikka.setTietokoneenKasi(tekoalynKasi);
    }
    
    /**
     * Vertailee asetettuja pelaajan ja tekoälyn käsiä ja
     * palauttaa vertailua kuvaavan luvun.
     * <p>
     * Luvun merkitykset
     * <ul>
     * <li> 1 = Pelaaja voittaa
     * <li> 0 = Tasapeli
     * <li> -1 = Tekoäly voittaa
     * </ul>
     * 
     * @return luku
     */
    public int pelaajaVoittaaKierroksen() {
        if(this.pelaaja.getNimi().equals("KIVI")) {
            if (this.tekoAly.getNimi().equals("KIVI")) {
                return 0;
            } else if (this.tekoAly.getNimi().equals("PAPERI")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("SAKSET")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("LISKO")) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.pelaaja.getNimi().equals("PAPERI")) {
            if (this.tekoAly.getNimi().equals("KIVI")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("PAPERI")) {
                return 0;
            } else if (this.tekoAly.getNimi().equals("SAKSET")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("LISKO")) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.pelaaja.getNimi().equals("SAKSET")) {
            if (this.tekoAly.getNimi().equals("KIVI")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("PAPERI")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("SAKSET")) {
                return 0;
            } else if (this.tekoAly.getNimi().equals("LISKO")) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.pelaaja.getNimi().equals("LISKO")) {
            if (this.tekoAly.getNimi().equals("KIVI")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("PAPERI")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("SAKSET")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("LISKO")) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (this.tekoAly.getNimi().equals("KIVI")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("PAPERI")) {
                return -1;
            } else if (this.tekoAly.getNimi().equals("SAKSET")) {
                return 1;
            } else if (this.tekoAly.getNimi().equals("LISKO")) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
