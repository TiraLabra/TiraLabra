
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Jonotietorakenne joka toteuttaa operaatiot <tt>lisaa</tt>, <tt>poista</tt>,
 * <tt>koko</tt>, <tt>onTyhja</tt> ja <tt>tyhjenna</tt>. Debuggauksen
 * helpottamiseksi olen myös ylikirjoittanut metodin <tt>toString</tt>. Jono on
 * toteutettu sillä oletuksella, ettei siihen yritetä lisätä yli
 * <i>2 147 483 647</i> alkiota. Jonon lisaa-metodi sallii duplikaatit,
 * mukaanlukien useamman <i>null</i>-arvon lisäys.
 *
 * @param <T> Jonoon säilöttävien tietoalkioiden tyyppi.
 * @author John Lång
 */
public final class Jono<T> {
    
    private Solmu<T>    ensimmainen, viimeinen;
    private int         pituus;
    
    /**
     * Palauttaa luokan uuden instanssin.
     */
    public Jono() {
        this.pituus = 0;
    }
    
    /**
     * Palauttaa luokan uuden instanssin, johon on lisätty syötteenä annetut
     * alkiot.
     */    
    public Jono(final T... ALKIOT) {
        this.pituus = 0;
        for (T alkio : ALKIOT) {
            lisaa(alkio);
        }
    }

    /**
     * Lisää alkion jonon viimeiseksi.
     *
     * @param ARVO Jonoon lisättävä tietoalkio.
     */
    public void lisaa(final T ARVO) {
//        if (pituus == Integer.MAX_VALUE) {
//            throw new IndexOutOfBoundsException("Jonoon tuli täyteen!");
//        }
        Solmu seuraava = new Solmu<>(ARVO);
        if (ensimmainen == null) {
            ensimmainen = seuraava;
            viimeinen   = seuraava;
            pituus      = 1;
        } else {
            viimeinen.seuraaja = seuraava;
            viimeinen = seuraava;
            pituus++;
        }
    }

    /**
     * Poistaa ja palauttaa jonon ensimmäisen alkion.
     *
     * @return Jonon ensimmäinen alkio tai <tt>null</tt> jos jono on tyhjä.
     */
    public T poista() {
        if (ensimmainen == null) {
            return null;
        }
        Solmu<T> solmu = ensimmainen;
        ensimmainen = solmu.seuraaja;
        pituus--;
        return solmu.ARVO;
    }

    /**
     * Palauttaa jonon ensimmäisen alkion.
     *
     * @return Jonon ensimmäinen alkio tai <tt>null</tt> jos jono on tyhjä.
     */
    public T kurkista() {
        if (ensimmainen == null) {
            return null;
        }
        return ensimmainen.ARVO;
    }
    
    /**
     *
     * @return Jonon alkioiden määrä.
     */
    public int pituus() {
        return pituus;
    }

    /**
     *
     * @return Tosi jos jono on tyhjä; epätosi muuten.
     */
    public boolean onTyhja() {
        return pituus == 0;
    }

    /**
     * Poistaa jonosta kaikki alkiot.
     */
    public void tyhjenna() {
        ensimmainen = null;
        viimeinen   = null;
        pituus      = 0;
    }
    
    /**
     * Lisää syötteenä annetun <b>Jono</b>-instanssin solmut metodin suorittavan
     * instanssin loppuun. Operaation lopputulos on sama kuin jos jokainen alkio
     * lisättäisiin erikseen metodilla <tt>lisaa</tt>, mutta <tt>yhdista</tt> on
     * nopeampi suorittaa, sillä sen aikavaativuus on luokkaa <i>O</i>(1). Jos
     * syöte on <i>null</i>, ei metodi tee mitään.
     * 
     * @param JONO 
     */
    public void yhdista(final Jono<T> JONO) {
        if (JONO == null) {
            return;
        }
        
        if (ensimmainen == null) {
            this.ensimmainen    = JONO.ensimmainen;
            this.viimeinen      = JONO.viimeinen;
            this.pituus         = JONO.pituus;
        } else {
            this.viimeinen.seuraaja = JONO.ensimmainen;
            this.viimeinen          = JONO.viimeinen;
            this.pituus             += JONO.pituus;
        }
    }
    
    @Override
    public String toString() {
        
         if (onTyhja()) {
             return "\u2205";
         }
        
        StringBuilder mjr = new StringBuilder();
        Solmu<T> solmu = ensimmainen;
        
        if (ensimmainen.seuraaja == null) {
            mjr.append("{");
            mjr.append(solmu.ARVO);
            mjr.append("}");
        } else {
            mjr.append('(');        
            while (solmu != null) {
                mjr.append(solmu.ARVO);
                mjr.append(',');
                solmu = solmu.seuraaja;
            }
            mjr.delete(mjr.length() - 1, mjr.length());
            mjr.append(')');
        }
        
        return mjr.toString();
    }
    
    /**
     * Muuttaa jonon käyttöliittymässä tulostettavaan merkkijonomuotoon.
     * Tarkemmin ilmaistuna metodi palauttaa merkkijonon jokaisesta alkiosta 
     * (<b>String</b>:ksi muutettuna) eroteltuna välilyönneillä.
     * 
     * @return Jonon alkioista muodostettu <b>String</b>.
     */
    public String tuloste() {        
        StringBuilder mjr = new StringBuilder();
        Solmu<T> solmu = ensimmainen;
        
        while (solmu != null) {
            mjr.append(solmu.ARVO);
            mjr.append(' ');
            solmu = solmu.seuraaja;
        }
        
        return mjr.toString();
    }

}