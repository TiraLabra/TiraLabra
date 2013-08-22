/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.algoritmit;
import java.util.HashSet;
import ohjelma.tietorakenteet.iHashMap;
import ohjelma.verkko.Kaari;
import ohjelma.verkko.Solmu;
import ohjelma.verkko.Verkko;

/**
 *
 * @author kkivikat
 */
public class Dijkstra {

    private iHashMap<Integer, Solmu> solmut;
    private HashSet<Kaari> kaaret;

    public Dijkstra(Verkko verkko) {
        this.solmut = verkko.getSolmut();
        this.kaaret = verkko.getKaaret();
    }

    /**
     * Päämetodi.
     *
     */
    public void Dijkstra() {
        alusta();
        for (int i = 1; i < solmut.getArvojenMaara() + 1; i++) {
            Solmu lahin = etsiLahinSolmu(solmut.get(i));                            // etsi solmut(i) solmun lähin solmu ja tallenna sen [lähin]                                                                                    // Lisätään solmu käydyt joukkoon jottei käydä samaa solmua uudestaan läpi
            Kaari[] vieruskaaret = haeVierussolmut(lahin);                          // etsii [lähin] solmun vierussolmut
            for (int j = 0; vieruskaaret[j] != null; j++) {                         // käy [lähin] solmun vierussolmut läpi kunnes törmätään nulliin
                Relax(vieruskaaret[j]);
            }
        }
        tulostaSolmut();
    }

    /**
     * Palauttaa lähimmän solmun ja asettaa painoja.
     */
    public Solmu etsiLahinSolmu(Solmu solmu) {
        Kaari uusilyhyin = null;
        int pienin = Integer.MAX_VALUE;

        for (Kaari kaari : kaaret) {
            if (kaari.getAlku().equals(solmu)) {                                                        // Huomioidaan vain kaaret joiden alkusolmu on sama kuin parametrin [solmu]
                
                if (kaari.getKohde().getPaino() > kaari.getAlku().getPaino() + kaari.getEtaisyys()) {   // jos kohteen paino > kuin matka tästä solmusta kohteeseen
                    kaari.getKohde().setPaino(kaari.getAlku().getPaino() + kaari.getEtaisyys());        // päivitetään kohteen paino
                    
                    if (kaari.getAlku().getPaino() + kaari.getEtaisyys() < pienin) {
                        pienin = kaari.getAlku().getPaino() + kaari.getEtaisyys();                      // päivitetään muuttujaa [pienin] jos löytyy lyhyempi reitti
                        uusilyhyin = kaari;
                    }
                }
            }
        }
        if (uusilyhyin != null) {
            return uusilyhyin.getKohde();
        } else {
            return null;
        }
    }

    /**
     * Etsii kaaret joiden alkusolmu on sama kuin parametrin [solmu].
     */
    public Kaari[] haeVierussolmut(Solmu solmu) {
        Kaari[] joukko = new Kaari[100];
        int indeksi = 0;
        for (Kaari kaari : kaaret) {                                                // Käy kaikki kaaret läpi
            if (kaari.getAlku().equals(solmu)) {                                    // Haetaan kaaret joiden alkusolmu on sama kuin parametrin [solmu]
                joukko[indeksi] = kaari;
                ++indeksi;
            }
        }
        return joukko;
    }

    /**
     * Apumetodeja Dijkstralle.
     */
    public void alusta() {
        solmut.get(1).setPaino(0);                                                  // ekalle solmulle aina paino 0
        for (int i = 2; i < solmut.getArvojenMaara(); i++) {                                   // lopuille solmuille painoksi 99
            solmut.get(i).setPaino(99);
        }
    }

    public void Relax(Kaari kaari) {                                                // päivittää vierussolmujen painoja jos löytyy lyhyempiä reittejä
        if (kaari.getKohde().getPaino() > ((kaari.getAlku().getPaino() + kaari.getEtaisyys()))) {
            kaari.getKohde().setPaino((kaari.getAlku().getPaino() + kaari.getEtaisyys()));
        }
    }

    /**
     * ******************************************************
     */
    /*             Testaukseen käytettävät metodit           */
    /**
     * ******************************************************
     */
    private void tulostaSolmut() {
        System.out.println("Dijkstra tulokset: ");
        for (int x = 1; x < solmut.getArvojenMaara() + 1; x++) {
            System.out.print(solmut.get(x).getPaino()+", ");
        }
    }
}