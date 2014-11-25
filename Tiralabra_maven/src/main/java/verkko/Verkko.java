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
import tira.DynaaminenLista;
import tira.Lista;
import verkko.esimerkki.LinjaJSON;
import verkko.esimerkki.Pysakkiverkko;
import verkko.rajapinnat.Edge;
import verkko.rajapinnat.Graph;
import verkko.rajapinnat.Value;

/**
 * Verkko pitää kirjaa solmujen naapureista ja niiden välisistä kaarista
 *
 * @author E
 */
public class Verkko implements Graph {
    /*
     HUOM! useaan paikkaan tallennettu data nopeuttaa hakuja, mutta hidastaa verkon luomista ja varsinkin muokkaamista
     Kannattanee kuitenkin, jotta haut mahd. nopeita
     WIP: valmiit tietorakenteet käytössä
     */
    
    /**
     * Pysäkit taulukossa
     */
    private Pysakki[] pysakit;
    /**
     * Linjat taulukossa
     */
    private Linja[] linjat;
    
    ////////////////////////
    // hakuja nopeuttamaan//
    ////////////////////////
    
    /**
     * Hajautustaulu: avaimina pysäkkien koodit, arvoina pysäkki-oliot
     */
    private HashMap<String, Pysakki> pysakinKoodit;
    /**
     * Hajautustaulu: avaimina linjojen koodit, arvoina linja-oliot
     */    
    private HashMap<String, Linja> linjanKoodit;
    
    // WIP: KATSO MITEN VAIKUTTAA SUORITUSAIKAAN JOS AVAIMET STRING
    
    /**
     * Avaimina pysäkit, arvoina lista linjoista jotka kulkevat pysäkiltä
     */
    private HashMap<Pysakki, List<Linja>> pysakiltaKulkevatLinjat;
    /**
     * Avaimina pysäkit, arvoina lista pysäkeistä joihin pysäkiltä pääsee jollain linjalla
     */
    private HashMap<Pysakki, List<Pysakki>> pysakinNaapurit;   // max V*V
    /**
     * Avaimena pysäkit, arvoina hajautustaulu pysäkin naapureista ja lista linjojen kaarista, joilla naapureihin pääsee
     */
    private HashMap<Pysakki, HashMap<Pysakki, List<Kaari>>> reititNaapureihin; // max V*V*E
    
    // WIP: Kannattaako ennemmin kävellä pysäkiltä toiselle?
    // private double[][]  pysakkienValisetEtaisyydet;

    /**
     * Pysäkkien ohitusajat. ( Tieto vuorojen tiheydestä linjassa ), pysakin
     * koodi-linjan koodi-ohitusaika
     */
    private HashMap<String, HashMap<String, Double>> pysakkiAikataulut;

    /**
     * Hivenen pitkä konstruktori. Osa toiminnallisuudesta siirretty yksityisiin metodeihin
     * Luo verkon käyttäen apuna pysäkkiverkko-oliota
     * - JSON-data luetaan Pysakkiverkko-oliolla
     * - JSON-data muokataan verkko-paketin olioiksi ja tallennetaan taulukoihin
     * - Luodaan apukentiksi hakuihin hajautustaulut, käyetään apumetodeja
     * 
     */
    public Verkko() {
        // apuolioita hakujen nopeuttamiseen
        linjanKoodit = new HashMap();
        pysakinKoodit = new HashMap();
        pysakinNaapurit = new HashMap();
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
    //////////////////////////////////////
    //Konstruktorin apumetodit (private)//
    /////////////////////////////////////

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
     * Verkon luomisessa käytetty apumetodi. Luo yhteyden solmujen(pysäkkien) välille.
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
        if (!this.pysakinNaapurit.containsKey(alku)) {
            List<Pysakki> pysakinNaapurit = new ArrayList();
            pysakinNaapurit.add(loppu);
            this.pysakinNaapurit.put(alku, pysakinNaapurit);
        } else {
            if (!this.pysakinNaapurit.get(alku).contains(loppu)) {
                this.pysakinNaapurit.get(alku).add(loppu);
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
    //////////////////////
    ////JULKISET METODIT//
    //////////////////////
    
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
    public Iterable<Kaari> getKaaret(Pysakki alku, Pysakki loppu) {
        return this.reititNaapureihin.get(alku).get(loppu);
    }

    /**
     * Palauttaa solmun naapurit
     *
     * @param solmu
     * @return
     */
    public Iterable<Pysakki> getNaapurit(Pysakki solmu) {
        return this.pysakinNaapurit.get(solmu);
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

    
    /////////////////////////////////////////////
    ///// automaattiset setterit & getterit /////
    /////////////////////////////////////////////  
    public Pysakki[] getPysakit() {
        return pysakit;
    }
    public Linja[] getLinjat() {
        return linjat;
    }

    public Iterable<Edge> getKaaret(Value alku, Value loppu) {
        Iterable<Kaari> ip =  this.getKaaret((Pysakki)alku, (Pysakki)loppu);
        
        Lista<Edge> ledge  = new DynaaminenLista();
        for ( Kaari kaari : ip ) {
            ledge.add(kaari);
        }
        return ledge;
    }

    public Iterable<Value> getNaapurit(Value alku) {
        Iterable<Pysakki> ip =  this.getNaapurit((Pysakki)alku);
        
        Lista<Value>  lvalue  = new DynaaminenLista();
        for ( Pysakki pysakki : ip ) {
            lvalue.add(pysakki);
        }
        return lvalue;
    }

    
}
