package Tiralabra.vertailija;

import Tiralabra.domain.KaksiKolme;
import Tiralabra.domain.Punamusta;
import Tiralabra.domain.Puu;
import Tiralabra.domain.Threaded;
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
    public Vertailija() {
        syote = 100;
    }

    /**
     * Asettaa syötteen koon.
     *
     * @param syote syötteen koko
     */
    public void setSyote(int syote) {
        this.syote = syote;
    }

    public String vertaile(Puu pRand, Puu pJarj) {
        Random r = new Random();
        String tulos = "";
        alkiot = new ALista();

        tulos += insert(pRand, pJarj, r, alkiot);

        tulos += search(pRand, pJarj, r, alkiot);

        tulos += tulostus(pRand);

        tulos += delete(pRand, pJarj, r, alkiot);

        return tulos;
    }

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

        return "\n" + syote + " alkion lisääminen puuhun sattumanvaraisessa järjestyksessä kesti "
                + ((insertRandom / 1000) / 1000) + " millisekuntia. \n " + syote + " alkion lisääminen"
                + " puuhun järjestyksessä kesti " + ((insertJarj / 1000) / 1000) + " millisekuntia.\n";
    }
    
    private String search(Puu pR, Puu pJ, Random r, ALista l){
        
    } 
}