package ohjelma;

import java.util.HashSet;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kkivikat
 */
public class Verkko {

    private HashSet<Kaari> kaaret;
    private iHashMap<Integer, Solmu> solmut;

    public Verkko(iHashMap solmut, HashSet kaaret) {
        this.solmut = solmut;
        this.kaaret = kaaret;

    }

    /**
    * Alustaa verkon luomalla solmut, kaaret ja niiden yhteydet.
    **/
    public void teeVerkko() {
        System.out.println("Monta solmua verkossa on?");
        Scanner lukija = new Scanner(System.in);
        int solmumaara = lukija.nextInt();

        for (int i = 1; i < solmumaara + 1; i++) {
            solmut.put(i, new Solmu(i));
        }

        System.out.println("Monta kaarta verkossa on?");
        int kaarimaara = lukija.nextInt();

        for (int i = 1; i < kaarimaara + 1; i++) {
            System.out.println("Anna kaaren " + i + " lähtösolmu (1-" + solmumaara + ")");
            int lahto = lukija.nextInt();
            System.out.println("Anna kaaren " + i + " kohdesolmu (1-" + solmumaara + ")");
            int kohde = lukija.nextInt();
            System.out.println("Anna kaaren " + i + " pituus");
            int pituus = lukija.nextInt();

            kaaret.add(new Kaari(solmut.get(lahto).getSolmu(), solmut.get(kohde).getSolmu(), pituus));
        }
    }

    /**
    * Palauttaa kaikki verkkoon kuuluvat kaaret.
    **/
    public HashSet<Kaari> getKaaret() {
        return kaaret;
    }
    
    /**
    * Palauttaa kaikki verkkoon kuuluvat solmut).
    **/
    public iHashMap<Integer, Solmu> getSolmut() {
        return solmut;
    }
    
    
}
