/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.util.Scanner;
import tiralabra.koodaus.TiedostonPakkaaja;
import tiralabra.koodaus.TiedostonPurkaja;

/**
 * Pakkauksen ja purkamisen "käyttöjärjestelmä".
 * @author joonaslongi
 */

public class Huffman {
    
    private Scanner lukija;
    
    /**
     * Konstruktori alustaa uuden lukijan.
     */
    
    public Huffman(){
        lukija = new Scanner(System.in);
    }
    
    /**
     * Kysyy käyttäjätlä toiminnon ja purkaa, pakkaa tai lopettaa
     * sen mukaisesti.
     */
    
    public void suorita(){
        while (true) {
            tulostaAlkuTiedot();
            String toiminto = lukija.nextLine();
            if (toiminto.equals("1")) {
                pakkaa();
                
            } else if(toiminto.equals("2")) {
                pura();
                
            } else if(toiminto.equals("3")){
                break;
            }
            System.out.println("---------------------------------------");
        }
    }
    
    /**
     * Tulostaa alkuun ohjeet käyttäjälle.
     */
    
    public void tulostaAlkuTiedot(){
        System.out.println("Tervetuloa!");
        System.out.println("Haluatko ");
        System.out.println("1. Pakata tiedoston");
        System.out.println("2. Purkaa tiedoston");
        System.out.println("3. Lopettaa?");
        System.out.println("(kirjoita 1, 2 tai 3)");
    }
    
    /**
     * Kysyy käyttäjätlä purettavan tiedoston, uuden nimen ja suorittaa
     * purkamisen.
     */
    
    private void pura(){
        System.out.println("Anna purettavan tiedoston sijainti juurikansioon nähden");
        System.out.println("(esim src/Tiralabra/tiedostot/esimerkki.txt)");
        String nimi = lukija.nextLine();
        File tiedosto = new File(nimi);
        while (!tiedosto.exists()) {
            System.out.println("--------------------------------------");
            System.out.println("Tiedostoa ei löytynyt, yritä uudelleen");
            System.out.println("Anna purettavan tiedoston sijainti juurikansioon nähden");
            System.out.println("(esim src/Tiralabra/tiedostot/esimerkki.txt)");
            nimi = lukija.nextLine();
            tiedosto = new File(nimi);
        }
        System.out.println("Anna puratulle tiedostolle uusi nimi:");
        String uusinimi = lukija.nextLine();
        uusinimi +=".purettu";
        suoritaPurku(nimi, uusinimi);
        
    }
    
    /**
     * Suorittaa purkamisen ja laskee purkuun kuluneen ajan.
     * @param nimi
     * @param uusinimi 
     */
    
    private void suoritaPurku(String nimi, String uusinimi){
        System.out.println("Odota hetki, puretaan tiedostoa.");
        long purkuAlku = System.currentTimeMillis();
        TiedostonPurkaja purkaja = new TiedostonPurkaja(nimi, uusinimi);
        purkaja.pura();
        long purkuLoppu = System.currentTimeMillis();
        System.out.println("purettu ajassa " + (purkuLoppu - purkuAlku) + " ms"); 
    }
    
    /**
     * Kysyy käyttäjältä pakattavan tiedoston, uuden nimen ja suorittaa
     * pakkauksen.
     */
    
    private void pakkaa(){
        System.out.println("Anna pakattavan tiedoston sijainti juurikansioon nähden");
        System.out.println("(esim src/Tiralabra/tiedostot/esimerkki.txt)");
        String nimi = lukija.nextLine();
        File tiedosto = new File(nimi);
        while (!tiedosto.exists()) {
            System.out.println("--------------------------------------");
            System.out.println("Tiedostoa ei löytynyt, yritä uudelleen");
            System.out.println("Anna pakattavan tiedoston sijainti juurikansioon nähden");
            System.out.println("(esim src/Tiralabra/tiedostot/esimerkki.txt)");
            nimi = lukija.nextLine();
            tiedosto = new File(nimi);
        }
        System.out.println("Anna pakatulle tiedostolle uusi nimi:");
        String uusinimi = lukija.nextLine();
        uusinimi +=".pakattu";
        suoritaPakkaus(nimi, uusinimi);
    }
    
    /**
     * Pakkaa tiedoston ja laskee kuluneen ajan.
     * @param nimi
     * @param uusinimi 
     */
    
    private void suoritaPakkaus(String nimi, String uusinimi){
        System.out.println("Odota hetki, pakataan tiedostoa.");
        long pakkausAlku = System.currentTimeMillis();
        TiedostonPakkaaja pakkaaja = new TiedostonPakkaaja(uusinimi, nimi);
        pakkaaja.pakkaa();
        long pakkausLoppu = System.currentTimeMillis();
        System.out.println("Pakattu ajassa " + (pakkausLoppu - pakkausAlku) + " ms");
    }
    
}
