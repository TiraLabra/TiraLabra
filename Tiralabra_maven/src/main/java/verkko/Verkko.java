/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import verkko.esimerkki.LinjaJSON;
import verkko.esimerkki.Pysakkiverkko;

/**
 * Verkko pitää kirjaa solmujen naapureista ja niiden välisistä kaarista
 *
 * @author E
 */
public class Verkko {
    /*
     HUOM! useaan paikkaan tallennettu data nopeuttaa hakuja, mutta hidastaa verkon luomista ja varsinkin muokkaamista
     Kannattanee kuitenkin, jotta haut mahd. nopeita
     */
    
    private Pysakki[] pysakit;
    private Linja[] linjat;
    // hakuja nopeuttamaan
    private HashMap<String, Pysakki> pysakinKoodit;
    private HashMap<String, Linja> linjanKoodit;
    // WIP: valmiita tietorakenteita käytössä tässä
    // Tarvitseeko tässä olioviitteitä 
    // WIP KATSO MITEN VAIKUTTAA SUORITUSAIKAAN JOS AVAIMET STRING
    private HashMap<Pysakki, List<Linja>> pysakiltaKulkevatLinjat;
    private HashMap<Pysakki, List<Pysakki>> naapurit;   // max V*V
    private HashMap<Pysakki, HashMap<Pysakki, List<Kaari>>> reititNaapureihin; // max V*V*E
    // Kannattaako ennemmin kävellä pysäkiltä toiselle? Saattaa jäädä pois lopullisesta versiosta
    // private double[][]  pysakkienValisetEtaisyydet;

    /**
     * Pysäkkien ohitusajat. ( Tieto vuorojen tiheydestä linjassa ), pysakin
     * koodi-linjan koodi-ohitusaika
     */
    private HashMap<String, HashMap<String, Double>> pysakkiAikataulut;

    /**
     * Luo verkon käyttäen apuna pysäkkiverkko-oliota
     */
    public Verkko() {
        // apuolioita hakujen nopeuttamiseen
        linjanKoodit = new HashMap();
        pysakinKoodit = new HashMap();
        naapurit = new HashMap();
        reititNaapureihin = new HashMap();
        pysakiltaKulkevatLinjat = new HashMap();
        pysakkiAikataulut = new HashMap();
        // luetaan JSON-datasta pysäkit ja linjat
        Pysakkiverkko pysakkiverkko = new Pysakkiverkko();
        pysakkiverkko.create("verkko.json", "linjat.json");
        // rakennetaan JSON-olioista tarkoituksenmukainen verkko
        // pysakit
        pysakit = new Pysakki[pysakkiverkko.getPysakit().length];
        for (int i = 0; i < pysakit.length; i++) {
            pysakit[i] = new Pysakki(pysakkiverkko.getPysakit()[i]);
            pysakinKoodit.put(pysakit[i].getKoodi(), pysakit[i]);
        }
        // linjat
        linjat = new Linja[pysakkiverkko.getLinjat().length];
        for (int i = 0; i < linjat.length; i++) {
            LinjaJSON linja = pysakkiverkko.getLinjat()[i];
            linjat[i] = new Linja(linja);
            linjat[i].setTyyppi(Linja.TYYPPI_RATIKKA);
            // linjan reitin tallentaminen: kaaret linkitettyyn listaan
            LinkedList<Kaari> linjanReitti = new LinkedList();
            for (int j = 0; j < linja.getPsKoodit().length; j++) {
                // lisätään pysähtymistieto pysäkkiaikatauluun
                this.lisaaPysakkiAikataulut(linja.getPsKoodit()[j], linja.getPsAjat()[j], linja.getKoodi());
                if (j >= linja.getPsKoodit().length - 1) {
                    break;
                }
                // luodaan kaaret ja niiden määräämät yhteydet
                Kaari kaari = new Kaari();
                kaari.setAlkuSolmu(linja.getPsKoodit()[j]);
                kaari.setLoppuSolmu(linja.getPsKoodit()[j + 1]);
                kaari.setKustannus(linja.getPsAjat()[j + 1] - linja.getPsAjat()[j]); // aika pysäkiltä toiselle
                kaari.setEtaisyys(Math.pow(
                        Math.pow(linja.getX()[j + 1] - linja.getX()[j], 2) + Math.pow(linja.getY()[j + 1] - linja.getY()[j], 2),
                        0.5
                )); // euklidinen etäisyys R^2
                kaari.setLinjanNimi(linjat[i].getKoodi());
                linjanReitti.add(kaari);
                // päivitetään verkkoa:
                // linjaa pitkin pääsee alkupysäkiltä loppupysäkille. päivitetään siis naapureita
                this.lisaaNaapuri(kaari);
                // ... ja naapureihin johtavia reittejä
                this.lisaaReittiNaapuriin(kaari);
                // lisätään linja pysäkin kautta kulkeviin kulkeviin
                this.lisaaPysakille(kaari);
                
            }
            linjat[i].setReitti(linjanReitti);
            linjanKoodit.put(linjat[i].getKoodi(), linjat[i]);
            
        }
        
    }
    /*
     Konstruktorin apumetodit
     */

    /**
     * Päivittää pysäkkiaikatauluja
     *
     * @param pysakki
     * @param aika
     * @param linja
     */
    private void lisaaPysakkiAikataulut(String p, double aika, String l) {
        
        if (!pysakkiAikataulut.containsKey(p)) {
            pysakkiAikataulut.put(p, new HashMap());
        }
        pysakkiAikataulut.get(p).put(l, aika);
    }

    /**
     * Verkon luomisessa käytetty apumetodi. Luo yhteyden solmujen välille.
     *
     * @param kaari Parametrin välittämien solmuihin lisätään yhteys
     */
    private void lisaaPysakille(Kaari kaari) {
        Pysakki alku = pysakinKoodit.get(kaari.getAlkuSolmu());
        Pysakki loppu = pysakinKoodit.get(kaari.getLoppuSolmu());
        if (!pysakiltaKulkevatLinjat.containsKey(alku)) {
            pysakiltaKulkevatLinjat.put(alku, new ArrayList());
        }
        if (!pysakiltaKulkevatLinjat.containsKey(loppu)) {
            pysakiltaKulkevatLinjat.put(loppu, new ArrayList());
        }
        Linja linja = linjanKoodit.get(kaari.getLinjanNimi());
        if (!pysakiltaKulkevatLinjat.get(alku).contains(linja)) {
            pysakiltaKulkevatLinjat.get(alku).add(linja);
        }
        if (!pysakiltaKulkevatLinjat.get(loppu).contains(linja)) {
            pysakiltaKulkevatLinjat.get(loppu).add(linja);
        }
    }

    /**
     * Verkon luomisessa käytetty apumetodi. Luo yhteyden solmujen välille.
     *
     * @param kaari Parametrin välittämien solmujen vällille luodaan yhteys
     */
    private void lisaaNaapuri(Kaari kaari) {
        Pysakki alku = pysakinKoodit.get(kaari.getAlkuSolmu());
        Pysakki loppu = pysakinKoodit.get(kaari.getLoppuSolmu());
        if (!this.naapurit.containsKey(alku)) {
            List<Pysakki> pysakinNaapurit = new ArrayList();
            pysakinNaapurit.add(loppu);
            this.naapurit.put(alku, pysakinNaapurit);
        } else {
            if (!this.naapurit.get(alku).contains(loppu)) {
                this.naapurit.get(alku).add(loppu);
            }
        }
    }

    /**
     * Verkon luomisessa käytetty apumetodi. Lisää kaaren solmujen välille.
     *
     * @param kaari Parametrin välittämien solmujen vällille asetetaan kaari
     */
    private void lisaaReittiNaapuriin(Kaari kaari) {
        Pysakki alku = pysakinKoodit.get(kaari.getAlkuSolmu());
        Pysakki loppu = pysakinKoodit.get(kaari.getLoppuSolmu());
        if (!this.reititNaapureihin.containsKey(alku)) {
            this.reititNaapureihin.put(alku, new HashMap());
        }
        if (!this.reititNaapureihin.get(alku).containsKey(loppu)) {
            this.reititNaapureihin.get(alku).put(loppu, new ArrayList());
        }
        this.reititNaapureihin.get(alku).get(loppu).add(kaari);
    }

    // JULKISET METODIT
    /**
     * Pysäkkien ja linjojen tulostaminen
     */
    public void debugPrint() {
        for (Pysakki pysakki : this.getPysakit()) {
            System.out.println("" + pysakki);
        }
        for (Linja linja : this.getLinjat()) {
            System.out.println("" + linja);
        }
    }

    /**
     * Palauttaa alku- ja loppusolmujen väliset kaaret (voi olla useita)
     *
     * @param alku
     * @param loppu
     * @return Kaaret alku- ja loppusolmun välillä
     */
    public List<Kaari> getKaaret(Pysakki alku, Pysakki loppu) {
        return this.reititNaapureihin.get(alku).get(loppu);
    }

    /**
     * Palauttaa solmun naapurit
     *
     * @param solmu
     * @return
     */
    public List<Pysakki> getNaapurit(Pysakki solmu) {
        return this.naapurit.get(solmu);
    }

    /**
     * Palauttaa seuraavan linjan ohitusajan pysäkiltä (pysäkkiaikataulu)
     *
     * @param aika
     * @param pysakki
     * @param linja
     * @return
     */
    public double getOdotusAika(double aika, String pysakki, String linja) {
        
        double ohitusAika = pysakkiAikataulut.get(pysakki).get(linja);
        // WIP: vuorovälit linjojen ominaisuutena
        // WIP: ajan esitys toisenlaisena
        double vuorovali = 10; // = l.getVuoroVali( aika );

        double odotusaika = ohitusAika % vuorovali - aika % vuorovali;
        if (odotusaika < 0) {
            odotusaika += vuorovali;
        }
        return odotusaika;
    }

    
    // automaattiset getterit
    public Pysakki[] getPysakit() {
        return pysakit;
    }
    public Linja[] getLinjat() {
        return linjat;
    }
    
}
