/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.util.Scanner;
import tiralabra.tiedostonkasittely.Pakkaaja;
import tiralabra.tiedostonkasittely.Purkaja;

/**
 *
 * @author joonaslongi
 */
public class Tiralabra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        while (true) {
            System.out.println("Tervetuloa!");
            System.out.println("Haluatko ");
            System.out.println("1. Pakata tiedoston");
            System.out.println("2. Purkaa tiedoston");
            System.out.println("3. Lopettaa?");
            System.out.println("(kirjoita 1, 2 tai 3)");
            String toiminto = lukija.nextLine();
            if (toiminto.equals("1")) {
                System.out.println("Anna pakattavan tiedoston sijainti juurikansioon nähden");
                System.out.println("(esim src/Tiralabra/tiedostot/esimerkki.txt)");
                String nimi = lukija.nextLine();
                File tiedosto = new File(nimi);
                while (!tiedosto.exists()) {
                    System.out.println("Tiedostoa ei löytynyt, yritä uudelleen");
                    System.out.println("Anna pakattavan tiedoston sijainti juurikansioon nähden");
                    System.out.println("(esim src/Tiralabra/tiedostot/esimerkki.txt)");
                    nimi = lukija.nextLine();
                    tiedosto = new File(nimi);
                }
                System.out.println("Anna pakatulle tiedostolle uusi nimi:");
                String uusinimi = lukija.nextLine();

                long pakkausAlku = System.currentTimeMillis();
                Pakkaaja pakkaaja = new Pakkaaja("src/Tiralabra/tiedostot/" + uusinimi, nimi);
                pakkaaja.pakkaa();
                long pakkausLoppu = System.currentTimeMillis();
                System.out.println("Pakattu ajassa " + (pakkausLoppu - pakkausAlku) +" ms");
            } else if(toiminto.equals("2")) {

                System.out.println("Anna purettavan tiedoston sijainti juurikansioon nähden");
                System.out.println("(esim src/Tiralabra/tiedostot/esimerkki.txt)");
                String nimi = lukija.nextLine();
                File tiedosto = new File(nimi);
                while (!tiedosto.exists()) {
                    System.out.println("Tiedostoa ei löytynyt, yritä uudelleen");
                    System.out.println("Anna purettavan tiedoston sijainti juurikansioon nähden");
                    System.out.println("(esim src/Tiralabra/tiedostot/esimerkki.txt)");
                    nimi = lukija.nextLine();
                    tiedosto = new File(nimi);
                }
                System.out.println("Anna puretulle tiedostolle uusi nimi:");
                String uusinimi = lukija.nextLine();
                 long purkuAlku = System.currentTimeMillis();
                Purkaja purkaja = new Purkaja(nimi, "src/Tiralabra/tiedostot/" + uusinimi);
                purkaja.pura();
                long purkuLoppu = System.currentTimeMillis();
                System.out.println("purettu ajassa " + (purkuLoppu - purkuAlku) + " ms");
                
            } else if(toiminto.equals("3")){
                break;
            }
        }
    }
}
