package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class App {

//    private static int intti;
    /**
     * Main-metodi
     *
     * @param args
     */
    public static void main(String[] args) {
//        intti = 1;
        Kontti kontti = new Kontti(1000, 1000, 200);
        ArrayList<Laatikko> laatikot = generoiLaatikoita(100, 1);
        PakkausSuunnitelma pakkausSuunnitelma = pakkaaKontti(kontti, laatikot, 10000);
        HashMap<Laatikko, Sijainti> boksit = pakkausSuunnitelma.getLaatikot();
//        if (boksit == null){
//            System.out.println("lsdkfjsdlfkjsd");
//        }
        int i = 0;
        for (Entry<Laatikko, Sijainti> entry : boksit.entrySet()) {
            Laatikko key = entry.getKey();
            Sijainti value = entry.getValue();
            System.out.println("Laatikko " + i + ", x: " + value.getPosX() + ", y: " + value.getPosY() + ", z: " + value.getPosZ());
            i++;
        }
    }

    /**
     * Algoritmin ydin, joka pakkaa konttiin annetut laatikot.
     *
     * @param kontti Määritelty Kontti, johon laatikot pakataan
     * @param laatikot Pakattavat laatikot
     * @param aikaraja Aikaraja, jota käytetään rajoittamaan haun syvyyttä.
     * @return Paras löydetty PakkausSuunnitelma
     */
    public static PakkausSuunnitelma pakkaaKontti(Kontti kontti, ArrayList<Laatikko> laatikot, long aikaraja) {
        long t = System.currentTimeMillis();

        PakkausSuunnitelma paras = new PakkausSuunnitelma();
        ArrayList<Palkki> palkit = generoiListaPalkeista(laatikot); // lista generoidaan nykyisen tilan mukaan

        int haunTarkkuus = 1; // määrittelee haun tarkkuuden iteraatiossa
        // tehdään uusia pakkaussuunnitelmia kunnes aikaraja umpeutuu
        int i = 0;
        while (i < 1000) {
            Tila tila = new Tila(kontti, laatikot); // aloitetaan tyhjästä kontista
            while (tila.getVapaatTilapalkit().size() > 0) {
                Tilapalkki tilapalkki = tila.getVapaatTilapalkit().pop();
                tila.getVapaatTilapalkit().push(tilapalkki);
                System.out.println("hää");
                Palkki palkki = haeParasPalkki(tila, tilapalkki, palkit, haunTarkkuus);
                if (palkki != null) {
                    lisaaPalkki(tila, palkki, tilapalkki.getSijainti());
                    lisaaTilapalkit(tila, kontti, palkki, tilapalkki.getSijainti());
//        poista laatikot vapaista
//        poista laatikot blokeista?

                }
            }
//            if (parempi(tila.getPakkausSuunnitelma(), paras)) {
            paras = tila.getPakkausSuunnitelma(); // update search effort for next iteration

//            }
            haunTarkkuus *= 2;
            i++;
        }

        return paras;
    }

    /**
     * Tämä metodi generoi randomisti laatikoita pakattavaksi, annettujen
     * parametrien puitteissa.
     *
     * @param laatikoidenMaara Haluttu laatikoiden määrä
     * @param tyyppienMaara Haluttu laatikoiden määrä
     * @return Lista generoiduista laatikoista
     */
    public static ArrayList<Laatikko> generoiLaatikoita(int laatikoidenMaara, int tyyppienMaara) {
        Random random = new Random();
        ArrayList<Laatikko> laatikot = new ArrayList<Laatikko>();
        Laatikko laatikko = null;
        for (int i = 0; i < tyyppienMaara; i++) {
            laatikko = new Laatikko(random.nextInt(99) + 1, random.nextInt(99) + 1, random.nextInt(99) + 1);
            for (int j = 0; j < laatikoidenMaara / tyyppienMaara; j++) {
                laatikot.add(laatikko);
            }
        }

        return laatikot;
    }
    
    


    /**
     * Hakee parametrinä saatuun tilapalkkiin parhaiten sopivan palkin
     *
     * @param tila Pakkauksen tämänhetkinen tila
     * @param tilapalkki Tilapalkki, joka halutaan täyttää
     * @param palkit Lista palkeista, joista paras palkki etsitään
     * @param haunTarkkuus Parametri, jota käytetään haun syvyyden
     * määrittelemiseen
     * @return Paras löydetty palkki
     */
    private static Palkki haeParasPalkki(Tila tila, Tilapalkki tilapalkki, ArrayList<Palkki> palkit, int haunTarkkuus) {
        Palkki paras = null;

        for (Palkki palkki : palkit) {
            if (mahtuu(tilapalkki, palkki)) {
                paras = palkki;
            }
        }

        return paras;
    }

    /**
     * Selvittää mahtuuko palkki tilapalkkiin vai ei. True tarkoittaa mahtumista
     *
     * @param tilapalkki Tilapalkki, johon palkki yritetään mahduttaa
     * @param palkki Palkki, joka yritetään mahduttaa tilapalkkiin
     * @return Totuusarvo mahtumisesta, true tarkoittaa mahtumista
     */
    private static boolean mahtuu(Tilapalkki tilapalkki, Palkki palkki) {
        return tilapalkki.getX() >= palkki.getX() && tilapalkki.getY() >= palkki.getY() && tilapalkki.getZ() >= palkki.getZ();
    }

    /**
     * Generoi listan mahdollisista palkeista annetun laatikkolistan mukaan
     *
     * @param laatikot Lista laatikoita, joista palkit muodostetaan
     * @return Lista muodostettuja Palkkeja
     */
    private static ArrayList<Palkki> generoiListaPalkeista(ArrayList<Laatikko> laatikot) {
        ArrayList<Palkki> palkit = new ArrayList<Palkki>();

        for (Laatikko laatikko : laatikot) {

            Palkki palkki = new Palkki(laatikko.getX(), laatikko.getY(), laatikko.getZ());

            HashMap<Laatikko, Sijainti> boksit = new HashMap<Laatikko, Sijainti>();
            boksit.put(laatikko, new Sijainti(0, 0, 0));

            palkki.setLaatikot(boksit);
            palkit.add(palkki);

        }
        return palkit;
    }

    /**
     * Lisää palkin tämänhetkiseen pakkaussuunnitelmaan.
     *
     * @param tila Tila, josta selviää tämänhetkinen tilanne
     * @param palkki Palkki joka lisätään tilan pakkaussuunnitelmaan
     * @param sijainti Sijainti, johon palkki asetetaan
     */
    private static void lisaaPalkki(Tila tila, Palkki palkki, Sijainti sijainti) {
        PakkausSuunnitelma pakkausSuunnitelma = tila.getPakkausSuunnitelma();
        HashMap<Laatikko, Sijainti> laatikot = palkki.getLaatikot();
        for (Entry<Laatikko, Sijainti> entry : laatikot.entrySet()) {
            Laatikko key = entry.getKey();
            Sijainti value = entry.getValue();
            sijainti.plus(value);
            pakkausSuunnitelma.getLaatikot().put(key, sijainti);
        }

    }

    /**
     * Poistaa tilan vapaiden laatikoiden listasta palkkiin kuuluvat laatikot
     *
     * @param tila Tila, jonka vapaiden laatikoiden listaa muutetaan
     * @param palkki Palkki, jonka sisältämät laatikot poistetaan
     */
    private static void poistaPalkinLaatikot(Tila tila, Palkki palkki) {
        HashMap<Laatikko, Sijainti> laatikot = palkki.getLaatikot();
        for (Entry<Laatikko, Sijainti> entry : laatikot.entrySet()) {
            Laatikko key = entry.getKey();
            Sijainti value = entry.getValue();
//            pakkausSuunnitelma.getLaatikot().put(key, sijainti);
        }
    }

    /**
     * Tämä lisää tilaan uudet tilapalkit sen jälkeen kun pakkaussuunnitelmaan
     * on lisätty uusi palkki.
     *
     * @param tila Tila, josta selviää ämänhetkinen tilanne
     * @param kontti Kontti, jota pakataan
     * @param palkki Viimeksi pakattu palkki, jonka ympärille tilapalkit luodaan
     * @param sijainti Palkin sijainti
     */
    private static void lisaaTilapalkit(Tila tila, Kontti kontti, Palkki palkki, Sijainti sijainti) {
        tila.getVapaatTilapalkit().add(new Tilapalkki(palkki.getX(), palkki.getY(), kontti.getZ() - palkki.getZ() - sijainti.getPosZ(), sijainti.getPosX(), sijainti.getPosY(), sijainti.getPosZ() + palkki.getZ()));
        tila.getVapaatTilapalkit().add(new Tilapalkki(kontti.getX() - palkki.getX() - sijainti.getPosX(), palkki.getY(), kontti.getZ() - sijainti.getPosZ(), sijainti.getPosX() + palkki.getX(), sijainti.getPosY(), sijainti.getPosZ()));
        tila.getVapaatTilapalkit().add(new Tilapalkki(kontti.getX() - sijainti.getPosX(), kontti.getY() - palkki.getY() - sijainti.getPosY(), kontti.getZ() - sijainti.getPosZ(), sijainti.getPosX(), sijainti.getPosY() + palkki.getY(), sijainti.getPosZ()));

    }

    /**
     * Selvittää kumpi parametrien pakkaussuunnitelmista on optimaalisempi.
     * Palautetaan true, mikäli ensimmäinen parametri on parempi.
     *
     * @param pakkausSuunnitelma Ensimmäinen pakkaussuunnitelma
     * @param paras Toinen pakkaussuunnitelma
     * @return True, mikäli ensimmäinen on parempi
     */
    private static boolean parempi(PakkausSuunnitelma pakkausSuunnitelma, PakkausSuunnitelma paras) {
        return true;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
