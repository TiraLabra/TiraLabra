package viidensuora.ai;

import viidensuora.logiikka.Koordinaatti;

/**
 * Etsintämetodeista palautettava hakutulos, joka sisältää parhaaan siirron ja
 * hakuun liittynyttä metrikkaa.
 *
 * @author juha
 */
public class Hakutulos {

    /**
     * Parhaan löydetyn siirron koordinaatti.
     */
    public final Koordinaatti parasSiirto;

    /**
     * Siirrolle loytynyt arvo. Suurempi kuin 0 tarkoittaa ristin etua. Pienemi
     * kuin 0 tarkoittaa nollan etua.
     */
    public final int siirronArvo;

    /**
     * Hakupuussa vierailtujen solmujen lukumäärä.
     */
    public final int avattujaNodeja;

    /**
     * Täydellisen hakupuun solmujen lukumäärä.
     */
    public final int puussaNodeja;

    /**
     * Kuinka syvältä tulos löydettiin hakupuusta.
     */
    public final int hakusyvyys;

    /**
     * Kuinka monta millisekuntia hakuun käytettiin aikaa.
     */
    public final long hakuaika;

    public Hakutulos(Koordinaatti parasSiirto, int siirronArvo, int hakusyvyys,
            long hakuaika, int avattujaNodeja, int puussaNodeja) {
        this.parasSiirto = parasSiirto;
        this.siirronArvo = siirronArvo;
        this.hakusyvyys = hakusyvyys;
        this.avattujaNodeja = avattujaNodeja;
        this.puussaNodeja = puussaNodeja;
        this.hakuaika = hakuaika;
    }

    /**
     *
     * @return Merkkijonoesitys hakutuloksesta
     */
    @Override
    public String toString() {
        String s = "Paras siirto: (" + parasSiirto.x + ", " + parasSiirto.y + ")";
        s += "\nSiirron arvo: " + siirronArvo;
        s += "\nHaun syvyys: " + hakusyvyys;
        s += "\nHakuaika: " + (hakuaika / 1000.0) + " sekuntia";
        s += "\nNodeja: " + avattujaNodeja + "/" + puussaNodeja;
        return s + "\n";
    }

}
