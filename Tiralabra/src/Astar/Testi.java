package Astar;

import java.io.*;
import java.util.*;

/**
 * Luokka sisältää pääohjelman, joka tekee parametritarkistukset, sekä pyytää
 * tulkin, ja reitinhaku-luokan palveluita.
 *
 * @param args[] Komentoriviparametrit, 1. = tiedosto, jossa on labyrintti jota
 * aletaan tulkitsemaan. 2. = tiedosto, jonne ratkaistu labyrintti talletetaan.
 * 
* @author Ilkka Maristo
 */
public class Testi {

    public static void main(String args[]) throws FileNotFoundException {

        // Skanneri, joka tulkitsee käyttäjän syötteet ohjelman ajon aikana.
        Scanner lukija = new Scanner(System.in);
        boolean jatketaanko = true;
        // Parametrina annettava tiedosto, jossa haluttu labyrintti sijaitsee. 'Tulkittava tiedosto'.
        File tulTd = null;
        // Parametrina annettava tiedostonimi, jonne ratkaistu labyrintti sijoitetaan. 'Tulostus tiedosto'.
        File tulosTd = null;
        Tulkki tulkki = null;
        // Alku- ja loppuaika, jolla tarkastellaan kauanko
        // ratkaisemiseen on kulunut aikaa.	
        long aikaAlku;
        long aikaLoppu;

        // Jos käyttäjä on antanut virheellisen määrän parametreja, ilmoitetaan siita.
        // Ensimäisella kerralla käyttäjä antaa mahdollisesti 0 parametria -> ohjeet tulostetaan.
        if (args.length < 1 || args.length > 2) {
            System.out.println();
            System.out.println("**************************************************************************");
            System.out.println("Komentoriviparametreja tulee olla yksi tai kaksi kappaletta.");
            System.out.println();
            System.out.println("1. parametri: Labyrintti, jonka lyhimman reitin haluat ratkaista.");
            System.out.println("2. parametri: Tiedostonnimi, johon ratkaistu labyrintti tulostuu.");
            System.out.println();
            System.out.println("Jattamalla 2. parametrin tyhjaksi, tulostuu ratkaistu labyrintti naytolle.");
            System.out.println();


            /*
             * Listataan kaikki '.txt' tiedostot, jotka ovat samassa kansiossa.
             * Nämä kelpaavat 1. parametriksi:
             */

            // missä sijaitsee
            String polku = "";
            try {
                polku = new java.io.File(".").getCanonicalPath();
            } catch (IOException e) {
                System.out.println("Ei löytynyt polkua : " + e.getMessage());
            }

            File tiedosto = new File(polku);
            File[] tiedostot = tiedosto.listFiles();
            String tiedoston_nimi;
            String tiedostopaate;
            String tekstitiedosto = ".txt";

            // listataan tiedostot esille
            System.out.println("1. parametriksi kelpaavat '.txt' tiedostot: ");
            for (int lista = 0; lista < tiedostot.length; lista++) {
                tiedoston_nimi = tiedostot[lista].getName();
                // halutaan vain viimeiset 4 merkkia
                tiedostopaate = tiedoston_nimi.substring(tiedoston_nimi.length() - 3, tiedoston_nimi.length());

                // testataan onko kyseisen tiedoston pääte '.txt'
                if (tiedostopaate.equals(tekstitiedosto)) {
                    System.out.println(tiedoston_nimi);
                }
            }

            // alaraami ohjeille
            System.out.println("**************************************************************************");

            jatketaanko = false;

        }
        // jos parametrien lukumäärä ok:
        if (jatketaanko) {

            // tulkittava tiedosto saa arvokseen parametrina annetun tiedoston
            tulTd = new File(args[0]);
            tulkki = new Tulkki();

            // Yritetään tulkita käyttäjän syottämää labyrinttia, ja varaudutaan
            // siihen, että labyrintti ei ole kelvollinen.
            // Ilmoitetaan myos, mikä labyrintin tulkinnassa on mennyt pieleen.
            try {
                if (!tulkki.tulkitseLabyrintti(tulTd)) {
                    System.out.println();
                    System.out.println("Parametrina antamasi labyrintin ulkoasu ei kelpaa.");
                    System.out.println("Ole hyva, ja tarkasta, etta kaikki rivit ovat yhtapitkia");
                    System.out.println("ja sisaltavat vain merkkeja '.' ja '#'. Labyrintissa saa olla");
                    System.out.println("vain yksi lahtoruutu ('A') ja maaliruutu ('M').");
                    System.out.println("Tarkista myos, etta alussa ei ole tyhjia riveja ja etta");
                    System.out.println("viimeisen rivin jalkeen ei ole rivinvaihtoja.");
                    jatketaanko = false;
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("Parametrina antamaasi tekstitiedostoa ei loydy.");
                System.out.println("Ole hyva, ja tarkista syottamasi parametri.");
                jatketaanko = false;
            }
        }
        // Jos labyrintti on onnistuttu tulkitsemaan, voidaan aloittaa
        // itse a*-algoritmin suorittaminen.
        if (jatketaanko) {
            // Merkataan algoritminsuorituksen aloitusaika
            aikaAlku = System.currentTimeMillis();
            // Luodaan reitinhaku olio, jolla labyrinttia aletaan tulkitsemaan
            AtahtiReitinhaku hakuri = new AtahtiReitinhaku(tulkki.getLabyrintti());
            hakuri.setLahto(tulkki.getLahtoY(), tulkki.getLahtoX());
            hakuri.setMaali(tulkki.getMaaliY(), tulkki.getMaaliX());
            // aTahtiAlgoritmi-metodi palauttaa labyrintin ratkaistuna, mikäli reitti loytyy
            char[][] merkittyLabyrintti = hakuri.aTahtiAlgoritmi(tulkki.getKorkeus(), tulkki.getLeveys());
            // Merkataan lopetusaika
            aikaLoppu = System.currentTimeMillis();
            // jos ei loydetty reittiä, ei siirrytä tulostukseen.
            if (merkittyLabyrintti == null) {
                jatketaanko = false;
            }
            System.out.println();

            // jos toinen parametri on annettu, tarkoittaa se sitä,
            // että merkattu labyrintti tulee sijoittaa parametrina annettuun tiedostoon.
            if (args.length == 2 && jatketaanko) {
                tulosTd = new File(args[1]);
                if (tulosTd.exists()) {
                    System.out.println("Tiedosto '" + args[1] + "' on jo olemassa!");
                    System.out.println("Haluatko korvata sen uudella (enter = ei)?");
                    if (lukija.nextLine().length() == 0) {
                        System.out.println("Vanhaa tiedostoa ei tuhottu.");
                        jatketaanko = false;
                    }
                }
                // jos parametri on kunnossa, siirrytään itse tiedostoon tallettamiseen. 
                if (jatketaanko) {
                    // talletetaan kaksiulotteinen merkkijonotaulukko tiedostoon.
                    PrintWriter tulos = new PrintWriter(tulosTd);
                    for (int i = 0; i < tulkki.getKorkeus(); i++) {
                        for (int j = 0; j < tulkki.getLeveys(); j++) {
                            tulos.print(merkittyLabyrintti[i][j]);
                        }
                        tulos.println();
                    }
                    System.out.println("Hommat hoitui ajassa: " + (aikaLoppu - aikaAlku) + "ms");
                    System.out.println("Syottamasi labyrintin lyhin reitti loytyy tiedostosta '" + args[1] + "'.");
                    tulos.close();
                }
            }
            // jos parametreja on vain 1, tulostetaan merkittylabyrintti naytölle.
            if (args.length == 1 && jatketaanko) {
                System.out.println("Hommat hoitui ajassa: " + (aikaLoppu - aikaAlku) + "ms");
                System.out.println("Syottamasi labyrintin lyhin reitti:");
                for (int i = 0; i < tulkki.getKorkeus(); i++) {
                    for (int j = 0; j < tulkki.getLeveys(); j++) {
                        System.out.print(merkittyLabyrintti[i][j]);
                    }
                    System.out.println();
                }
            }

        }

    }
}
