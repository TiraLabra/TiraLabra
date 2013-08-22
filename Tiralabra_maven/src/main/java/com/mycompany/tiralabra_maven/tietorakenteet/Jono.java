
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Jonotietorakenne joka toteuttaa operaatiot <tt>lisaa</tt>, <tt>poista</tt>,
 * <tt>koko</tt>, <tt>onTyhja</tt> ja <tt>tyhjenna</tt>. Debuggauksen
 * helpottamiseksi olen myös ylikirjoittanut metodin <tt>toString</tt>. Jono on
 * toteutettu sillä oletuksella, ettei siihen yritetä lisätä yli
 * <i>2 147 483 647</i> alkiota. Jonoon lisättyjen muuttujien viite voidaan
 * asettaa vain kerran.
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
     * Lisää alkion jonon viimeiseksi.
     *
     * @param arvo Jonoon lisättävä tietoalkio.
     */
    public void lisaa(T arvo) {
//        if (pituus == Integer.MAX_VALUE) {
//            throw new IndexOutOfBoundsException("Jonoon tuli täyteen!");
//        }
        Solmu seuraava = new Solmu<>(arvo);
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
            // Muutin toStringiä tilapäisesti jotta voisin leikkiä hienomman
            // näköisellä tulosteella.
//            mjr.append('(');        
            while (solmu != null) {
                mjr.append(solmu.ARVO);
//                mjr.append(',');
                mjr.append(' ');
                solmu = solmu.seuraaja;
            }
//            mjr.delete(mjr.length() - 1, mjr.length());
//            mjr.append(')');
        }
        
        return mjr.toString();
    }

}