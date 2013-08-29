/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tiivistys;

import tiralabra.tiedostonkasittely.TiedostonKirjoittaja;
import tiralabra.tiedostonkasittely.TiedostonLukija;
import tiralabra.tietorakenteet.Jono;
import tiralabra.tietorakenteet.Node;
import tiralabra.tietorakenteet.Puu;

/**
 * Purkaa tiedoston
 * @author joonaslongi
 */
public class TiedostonPurkaja {

    private TiedostonLukija lukija;
    private TiedostonKirjoittaja kirjoittaja;
    private Puu puu;
    private Jono bittiJono;
    private int eriMerkit;
    private int kaikkiMerkit;

    /**
     * Konstruktori alustaa jonon, lukijna, kirjoittajan, sekä käskee 
     * lukemaan kuinka monta eri merkkiä ja kuinka monta merkkiä tiedostossa on,
     * sekä merkkien reitit joilla puu luodaan.
     * @param tiedosto purettava tiedosto
     * @param uusiTiedosto nimi uudelle tiedostolle
     */ 
    
    public TiedostonPurkaja(String tiedosto, String uusiTiedosto) {
        bittiJono = new Jono();
        lukija = new TiedostonLukija(tiedosto);
        kirjoittaja = new TiedostonKirjoittaja(uusiTiedosto);
    }
    
    public void pura(){
        eriMerkit = lueEriMerkit();
        kaikkiMerkit = lueKaikkiMerkit();
        this.puu = new Puu(lueReitit());
        puu.kokoaReiteista();
        lueBitit();
    }

    /**
     * Lukee tiedoston alusta kuinka monta eri merkkiä tiedosto sisältää.
     * raja > 0 estää ohjelman ikuisen loopin, jos lukija palauttaa virheen
     * sattuessa -1.
     * @return 
     */
    
    public int lueEriMerkit() {
        int raja = lukija.lue();
        String eriMerkkienMaara = "";
        while (raja != 47 && raja > 0) {
            eriMerkkienMaara += (char) raja;
            raja = lukija.lue();
        }
        return Integer.parseInt(eriMerkkienMaara);
    }
    
    /**
     * Lukee tiedoston alusta, montako merkkiä se sisältää.
     * raja > 0 estää ohjelman ikuisen loopin, jos lukija palauttaa virheen
     * sattuessa -1.
     * @return 
     */
    
    public int lueKaikkiMerkit() {
        int raja = lukija.lue();
        String merkitSumma = "";
        while (raja != 47 && raja > 0) {
            merkitSumma += (char)raja;
            raja = lukija.lue();
        }
        return Integer.parseInt(merkitSumma);
    }
    
    /**
     * Lukee tiedoston alusta jokaisen käytetyn merkin reitin
     * raja > 0 estää ohjelman ikuisen loopin, jos lukija palauttaa virheen
     * sattuessa -1.
     * puussa.
     * @return 
     */
    
    public String[] lueReitit() {
        String[] reitit = new String[256];
        for (int i = 0; i < eriMerkit; i++) {
            String reitti = "";
            int merkki = lukija.lue();
            int raja = lukija.lue();
            while (raja != 47 && raja > 0) {
                reitti += (char) raja;
                raja = lukija.lue();
            }
            reitit[merkki] = reitti;
        }
        return reitit;
    }
    
    /**
     * Purkaa lopun tiedoston takaisin tekstiksi
     * jonon avulla.
     */
    
    public void lueBitit() {
        while (lukija.vapaana() > 1) {
            lueTavu();
            while (bittiJono.getKoko() >= 256) {
                kirjoittaja.kirjoita(merkinReitti());
            }
        }
        int roskaa = lukija.lue() - 48;
        while (bittiJono.getKoko() > roskaa) {
            kirjoittaja.kirjoita(merkinReitti());
        }
    }
    
    /**
     * Lukee yhden tavun ja muuntaa sen biteiksi ja lisää jonoon.
     */
    
    public void lueTavu() {
        int input = lukija.lue();
        boolean[] bitit = muunnaBiteiksi(input);
        for (int i = 0; i < bitit.length; i++) {
            bittiJono.lisaa(bitit[i]);
        }
    }
    
    /**
     * Muuntaa tavun biteiksi
     * @param input
     * @return 
     */
    
    public boolean[] muunnaBiteiksi(int input) {
        boolean[] bitit = new boolean[8];
        for (int i = 0; i < 8; i++) {
            bitit[i] = ((input & (1 << (7 - i))) != 0);
        }
        return bitit;
    }
    
    /**
     * Etsii puusta merkkiä vastaavan reitin.
     * @return 
     */
    
    public int merkinReitti() {
        Node node = puu.getRoot();
        while (true) {
          if (node.getVasen() == null && node.getOikea() == null) {
              return node.getMerkki();
          }
          boolean bit = bittiJono.ota();
          if (!bit) {
             node = node.getVasen();
          } else {
              node = node.getOikea();
          }
        }
    }
    
    /**
     * Puu getter.
     * @return 
     */
    
    public Puu getPuu(){
        return this.puu;
    }
    
    /**
     * Eri merkit getter.
     * @return 
     */
    
    public int getEriMerkit(){
        return this.eriMerkit;
    }
    
    /**
     * Kaikki merkit getter.
     * @return 
     */
    
    public int getKaikkiMerkit(){
        return this.kaikkiMerkit;
    }
    
    /**
     * Bittijono getter.
     * @return 
     */
    
    public Jono getJono(){
        return this.bittiJono;
    }

}
