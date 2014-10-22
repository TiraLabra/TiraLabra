package viidensuora.ai;

import java.math.BigInteger;
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
     * Evaluoitujen solmujen lukumäärä hakupuussa.
     */
    public final int evaluoitujaTilanteita;

    /**
     * Kuinka syvältä tulos löydettiin hakupuusta.
     */
    public final int hakusyvyys;

    /**
     * Kuinka monta millisekuntia hakuun käytettiin aikaa.
     */
    public final long hakuaika;

    /**
     * Hakupuun pahin mahdolinen koko, jos käytäisiin kaikki haarat läpi
     * hakusyvyydelle asti.
     */
    public final BigInteger hakupuunKoko;

    /**
     * Koko pelipuun mahdollinen koko. (Vapaiden ruutujen lukumäärän kertoma)
     */
    public final BigInteger pelipuunKoko;

    public Hakutulos(Koordinaatti parasSiirto, int siirronArvo, long hakuaika,
            int evaluoitujaTilanteita, int hakusyvyys, int vapaitaRuutuja) {
        this.parasSiirto = parasSiirto;
        this.siirronArvo = siirronArvo;
        this.hakusyvyys = Math.min(hakusyvyys, vapaitaRuutuja);
        this.evaluoitujaTilanteita = evaluoitujaTilanteita;
        this.hakupuunKoko = laskeHakupuunKoko(hakusyvyys, vapaitaRuutuja);
        this.pelipuunKoko = laskeHakupuunKoko(vapaitaRuutuja, vapaitaRuutuja);
        this.hakuaika = hakuaika;
    }

    /**
     * Laskee hakupuun mahdollisen koon hakusyvyyden ja vapaiden ruutujen
     * lukumäärän perusteella.
     *
     * @param syvyys Haun syvyys
     * @param vapaitaRuutuja Pelilaudalla olleiden vapaiden ruutujen määrä
     * @return Puun koko
     */
    private BigInteger laskeHakupuunKoko(int syvyys, int vapaitaRuutuja) {
        int n = Math.min(syvyys, vapaitaRuutuja);
        BigInteger koko = BigInteger.valueOf(vapaitaRuutuja);
        for (int i = 1; i < n; i++) {
            koko = koko.multiply(BigInteger.valueOf(vapaitaRuutuja - i));
        }
        return koko;
    }

    /**
     *
     * @return Merkkijonoesitys hakutuloksesta
     */
    @Override
    public String toString() {
        String s = "Siirto:\t\t(" + parasSiirto.x + ", " + parasSiirto.y + ")";
        s += "\nSiirron arvo:\t\t" + siirronArvo;
        s += "\nHaun syvyys:\t\t" + hakusyvyys;
        s += "\nHakuaika:\t\t" + (hakuaika / 1000.0) + " sekuntia";
        s += "\nEvaluoituja tilanteita:\t" + evaluoitujaTilanteita;
        s += "\nHakupuun potent. koko:\t" + hakupuunKoko;
        s += "\nPelipuun potent. koko:\t" + pelipuunKoko;
        return s + "\n";
    }

}
