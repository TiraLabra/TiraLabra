
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Jonotietorakenne joka toteuttaa operaatiot <tt>add</tt>, <tt>poll</tt>,
 * <tt>size</tt>, <tt>isEmpty</tt> ja <tt>clear</tt>. Debuggauksen
 * helpottamiseksi olen myös ylikirjoittanut metodin <tt>toString</tt>.
 *
 * @author John Lång
 */
public final class Jono<T> {
    
    private Solmu<T>    ensimmainen, viimeinen;
    private int         pituus;

    public boolean lisaa(T e) {
        Solmu seuraava = new Solmu<T>(e);
        if (ensimmainen == null) {
            ensimmainen = seuraava;
            viimeinen   = seuraava;
            pituus      = 1;
            return true;
        }
        viimeinen.seuraaja = seuraava;
        viimeinen = seuraava;
        pituus++;
        return true;
    }

    public T poista() {
        if (ensimmainen == null) {
            return null;
        }
        Solmu<T> solmu = ensimmainen;
        ensimmainen = solmu.seuraaja;
        pituus--;
        return solmu.ARVO;
    }

    public T kurkista() {
        if (ensimmainen == null) {
            return null;
        }
        return ensimmainen.ARVO;
    }
    
    public int pituus() {
        return pituus;
    }

    public int koko() {
        return pituus;
    }

    public boolean onTyhja() {
        return pituus == 0;
    }

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

}
