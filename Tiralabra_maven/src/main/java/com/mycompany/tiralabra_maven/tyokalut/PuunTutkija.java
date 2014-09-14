
package com.mycompany.tiralabra_maven.tyokalut;
import com.mycompany.tiralabra_maven.tietorakenteet.*;

/**
 *  Puiden suorituskyvyn analysointiin liittyviä toimintoja sisältävä luokka
 * @author Markus
 */
public class PuunTutkija {
    
    final private Sekuntikello kello;

    public PuunTutkija() {
        this.kello = new Sekuntikello();
    }
    
     /**
     *  Palauttaa puun lisäysoperaatioon kuluvan ajan
     * @param puu Puu josta alkiota haetaan
     * @param lisattava   Lisättävän alkion arvo
     * @return  Lisäykseen kulunut aika
     */
    public long lisaysaika(Hakupuu puu, int lisattava){
        kello.aloita();
        puu.lisaa(lisattava);
        return kello.lopeta();
    }

    /**
     *  Mittaa kaikkien lisättävien arvojen lisäysajat ja palauttaa näistä tilastoja sisältävän olion
     * @param puu   Puu johon arvot lisätään
     * @param lisattavat    Lisattavat arvot sisältävä taulukko
     * @return  Mittaustulos -olio, joka sisältää tietoa mitatuista ajoista
     */
    public Mittaustulos lisaysaikaTulokset(Hakupuu puu, int[] lisattavat){
        Mittaustulos tulokset = new Mittaustulos();
        for (int i : lisattavat) {
            tulokset.lisaaAika(lisaysaika(puu, i));
        }
        return tulokset;
    }
    
    /**
     *  Palauttaa puun hakuoperaatioon kuluvan ajan
     * @param puu Puu josta alkiota haetaan
     * @param haettava  Haettavan alkion arvo
     * @return  Hakuun kulunut aika
     */
    public long hakuaika(Hakupuu puu, int haettava){
        kello.aloita();
        puu.hae(haettava);
        return kello.lopeta();
    }
    
    /**
     *  Palauttaa puun poisto-operaatioon kuluvan ajan
     * @param puu
     * @param poistettava
     * @return
     */
    public long poistoaika(Hakupuu puu, int poistettava){
        kello.aloita();
        puu.poista(poistettava);
        return kello.lopeta();
    }
}
