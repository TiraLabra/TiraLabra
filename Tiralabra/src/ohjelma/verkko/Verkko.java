package ohjelma.verkko;


import java.util.HashSet;
import java.util.Scanner;
import ohjelma.tietorakenteet.iHashMap;

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
        
//        Solmu eka = new Solmu(1);
//        Solmu toka = new Solmu(2);
//        Solmu kol = new Solmu(3);
//        Solmu nel = new Solmu(4);
//        Solmu viis = new Solmu(5);
//        Solmu kuus = new Solmu(6);
//        Solmu seitt = new Solmu(7);
//        Solmu kasi = new Solmu(8);
//        Solmu ysi = new Solmu(9);
//        Solmu kyba = new Solmu(10);
//        
//        solmut.put(1, eka);
//        solmut.put(2, toka);
//        solmut.put(3, kol);
//        solmut.put(4, nel);
//        solmut.put(5, viis);
//        solmut.put(6, kuus);
//        solmut.put(7, seitt);
//        solmut.put(8, kasi);
//        solmut.put(9, ysi);
//        solmut.put(10, kyba);
//        
//        Kaari kaari1 = new Kaari(eka, toka, 1);
//        Kaari kaari2 = new Kaari(eka, kol, 3);
//        Kaari kaari3 = new Kaari(kol, toka, 1);
//        Kaari kaari4 = new Kaari(nel, toka, 2);
//        Kaari kaari5 = new Kaari(nel, kuus, 2);
//        Kaari kaari6 = new Kaari(kuus, kasi, 2);
//        Kaari kaari7 = new Kaari(kasi, kyba, 10);
//        Kaari kaari8 = new Kaari(kol, viis, 2);
//        Kaari kaari9 = new Kaari(viis, seitt, 1);
//        Kaari kaari10 = new Kaari(seitt, ysi, 7);
//        Kaari kaari11 = new Kaari(ysi, kyba, 7);
//        Kaari kaari12 = new Kaari(viis, nel, 4);
//        Kaari kaari13 = new Kaari(viis, kuus, 2);
//        Kaari kaari14 = new Kaari(kuus, seitt, 3);
//        Kaari kaari15 = new Kaari(ysi, kuus, 3);
//        Kaari kaari16 = new Kaari(kasi, ysi, 2);
//        
//        kaaret.add(kaari1);
//        kaaret.add(kaari2);
//        kaaret.add(kaari3);
//        kaaret.add(kaari4);
//        kaaret.add(kaari5);
//        kaaret.add(kaari6);
//        kaaret.add(kaari7);
//        kaaret.add(kaari8);
//        kaaret.add(kaari9);
//        kaaret.add(kaari10);
//        kaaret.add(kaari11);
//        kaaret.add(kaari12);
//        kaaret.add(kaari13);
//        kaaret.add(kaari14);
//        kaaret.add(kaari15);
//        kaaret.add(kaari16);
        
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
