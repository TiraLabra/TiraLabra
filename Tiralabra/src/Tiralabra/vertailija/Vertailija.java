package Tiralabra.vertailija;

import Tiralabra.domain.Puu;
import Tiralabra.util.ALista;
import Tiralabra.util.Listasolmu;
import java.util.Random;

/**
 * Rakentaa halutut tietorakenteet ja vertaa niiden tehokkuutta valituilla
 * algoritmeilla ja syötteillä.
 *
 * @author Pia Pakarinen
 */
public class Vertailija {

    /**
     * Puun sisältämät alkiot.
     */
    private ALista alkiot;
    /**
     * Syötteen koko: 100 = pieni, 10 000 = keskikokoinen, 1 000 000 = suuri;
     */
    private int syote;

    /**
     * Luo uuden vertailijan, oletuksena pieni syöte.
     */
    public Vertailija(int s) {
        syote = s;
    }

    /**
     * Asettaa syötteen koon.
     *
     * @param syote syötteen koko
     */
    public void setSyote(int syote) {
        this.syote = syote;
    }

    /**
     * Suorita puun eri operaatiot ja palauttaa tuloksista tekstiesityksen.
     * @param pRand testattava puu, alkiot lisätään sattumanvaraisessa järjestyksessä
     * @param pJarj testattava puu, alkiot lisätään suuruusjärjestyksessä
     * @return tulokset puun operaatioista
     */
    public String vertaile(Puu pRand, Puu pJarj) {
        Random r = new Random();
        String tulos = "";
        alkiot = new ALista();

        tulos += insert(pRand, pJarj, r, alkiot);

        tulos += search(pRand, alkiot);

        tulos += tulostus(pRand);

        tulos += delete(pRand, alkiot);

        return tulos;
    }

    /** Lisää puuhun noin syötteen verran alkioita.
     * Tietty arvo voidaan sisällyttää puuhun vain kerran.
     * @param p puu johon alkiot lisätään sattumanvaraisesti
     * @param pJarj puu johon alkiot lisätään järjestyksessä
     * @param r luo sattumanvaraisia numeroita
     * @param l lista alkioista
     * @return tulosesitys puuhun alkioiden lisäämisestä mikrosekunteina
     */
    private String insert(Puu p, Puu pJarj, Random r, ALista l) {
        long insertRandom = 0;
        long aloita;
        long lopeta;
        int arvo;
        for (int i = 0; i < syote; i++) {
            arvo = r.nextInt();
            aloita = System.nanoTime();
            p.insert(arvo);
            lopeta = System.nanoTime();
            insertRandom += lopeta - aloita;
            l.lisaa(arvo);
        }

        long insertJarj = 0;
        Listasolmu l2 = l.getLista();
        while (l2 != null) {
            aloita = System.nanoTime();
            pJarj.insert(l.getLista().getArvo());
            lopeta = System.nanoTime();
            insertJarj += lopeta - aloita;
            l2 = l2.getNext();
        }

        return "\nNoin " + syote + " alkion lisääminen puuhun sattumanvaraisessa järjestyksessä kesti "
                + ((insertRandom / 1000) ) + " mikrosekuntia. \nNoin " + syote + " alkion lisääminen"
                + " puuhun järjestyksessä kesti " + ((insertJarj / 1000)) + " mikrosekuntia.\n";
    }

    /**
     * Etsii puusta kymmenen pienintä alkiota.
     * @param pR puu, johon alkiot on lisätty satunnaisjärjestyksessä
     * @param l puun alkiot
     * @return tulosesitys etsinnän kestosta mikrosekunteina
     */
    private String search(Puu pR, ALista l) {
        long searchJarj = 0;
        Listasolmu l2 = l.getLista();

        for (int i = 0; i < l.getKoko(); i++) {
            long aloita = System.nanoTime();
            pR.search(l2.getArvo());
            long lopeta = System.nanoTime();
            searchJarj += lopeta - aloita;
        }
        
        return "\nKaikkien alkioiden löytäminen noin " + syote + " alkioiselta listalta kesti " + ((searchJarj / 1000)) + " mikrosekuntia.\n";
    }

    /** Palauttaa puun alkioiden tulostuksen kestämän ajan.
     * 
     * @param p satunnaisjärjestyksessä oleva puu
     * @return puun alkoiden tulostuksen kestämä aika mikrosekunteina
     */
    private String tulostus(Puu p){
        long aloita = System.nanoTime();
        p.tulostaArvot();
        long lopeta = System.nanoTime();
        
        return "\nAlkioiden tulostus noin " + syote + " alkioisella puulla kesti " + (((lopeta - aloita) / 1000) ) + " mikrosekuntia.\n";
    }
    
    /** Poistaa puusta kaikki sen alkiot ja palauttaa operaatioiden kestämän ajan mikrosekunteina.
     * 
     * @param p puu, jonka alkiot on lisätty satunnaisjärjestyksesä
     * @param l lista puun alkioista
     * @return puun alkioiden poisto-operaatioiden kesto mikrosekunteina 
     */
    private String delete(Puu p, ALista l) {
        Listasolmu l2 = l.getLista();
        long deleteAika = 0;
        long aloita;
        long lopeta;
        
        while (l2 != null) {
            aloita = System.nanoTime();
            p.delete(l2.getArvo());
            lopeta = System.nanoTime();
            deleteAika += (lopeta - aloita);
            l2 = l2.getNext();
        }
        
        return "\nKaikkien alkioiden poisto noin " + syote + " alkioiselta puulta kesti " + ((deleteAika)/1000) + " mikrosekuntia.\n";
    }   
}