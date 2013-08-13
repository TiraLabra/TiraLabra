/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tiedostonkasittely;

import tiralabra.tallennus.Lukija;
import tiralabra.tallennus.Kirjoittaja;
import tiralabra.tietorakenteet.Puu;
import tiralabra.tietorakenteet.Node;
import java.util.LinkedList;

/**
 * Purkaa tiedoston
 * @author joonaslongi
 */
public class Purkaja {

    private Lukija lukija;
    private Kirjoittaja kirjoittaja;
    private Puu puu;
    private LinkedList<Boolean> que;
    private int eriMerkit;
    private int kaikkiMerkit;

    /**
     * Konstruktori alustaa jonon, lukijna, kirjoittajan, sekä käskee 
     * lukemaan kuinka monta eri merkkiä ja kuinka monta merkkiä tiedostossa on,
     * sekä merkkien reitit joilla puu luodaan.
     * @param tiedosto purettava tiedosto
     * @param uusiTiedosto nimi uudelle tiedostolle
     */ 
    
    public Purkaja(String tiedosto, String uusiTiedosto) {
        que = new LinkedList<Boolean>();
        lukija = new Lukija(tiedosto);
        kirjoittaja = new Kirjoittaja(uusiTiedosto);
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
     * @return 
     */
    
    public int lueEriMerkit() {
        int raja = lukija.lue();
        String eriMerkkienMaara = "";
        while (raja != 47) {
            eriMerkkienMaara += (char) raja;
            raja = lukija.lue();
        }
        return Integer.parseInt(eriMerkkienMaara);
    }
    
    /**
     * Lukee tiedoston alusta, montako merkkiä se sisältää.
     * @return 
     */
    
    public int lueKaikkiMerkit() {
        int raja = lukija.lue();
        String merkitSumma = "";
        while (raja != 47) {
            merkitSumma += (char)raja;
            raja = lukija.lue();
        }
        return Integer.parseInt(merkitSumma);
    }
    
    /**
     * Lukee tiedoston alusta jokaisen käytetyn merkin reitin
     * puussa.
     * @return 
     */
    
    public String[] lueReitit() {
        String[] reitit = new String[256];
        for (int i = 0; i < eriMerkit; i++) {
            String reitti = "";
            int merkki = lukija.lue();
            int raja = lukija.lue();
            while (raja != 47) {
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
            while (que.size() >= 256) {
                kirjoittaja.kirjoita(merkinReitti());
            }
        }
        int roskaa = lukija.lue() - 48;
        while (que.size() > roskaa) {
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
            que.add(bitit[i]);
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
          boolean bit = que.pollFirst();
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
}
