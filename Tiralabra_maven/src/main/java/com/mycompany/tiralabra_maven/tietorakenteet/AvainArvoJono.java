
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Jono, jonka on tarkoitus toimia luokan <b>Hajautuskartta</b> komponenttina.
 * Tällä luokalla on vain avain-arvoparien lisäys- ja hakutoiminnot. Sama avain-
 * arvopari voi esiintyä jonossa useaan kertaan. On kuitenkin syytä huomata,
 * että metodi <tt>haeEnsimmainen</tt> palauttaa aina vain ensimmäiseen avaimeen
 * liitetyn arvon sillä luokalla ei ole metodia joka poistaisi alkioita jonosta.
 *
 * @author John Lång
 */
class AvainArvoJono<K, V> {
    
    protected AvainArvoSolmu<K, V> ensimmainen, viimeinen;

    AvainArvoJono() {
//        pituus = 0;
    }
    
    /**
     * Lisää jonoon uuden avain-arvoparin.
     * 
     * @param avain Lisättävä avain.
     * @param arvo  Lisättävä arvo.
     */
    void lisaa(K avain, V arvo) {
        AvainArvoSolmu<K, V> solmu = new AvainArvoSolmu<>(avain, arvo);
        if (ensimmainen == null) {
            ensimmainen = solmu;
            viimeinen = solmu;
        } else {
            viimeinen.seuraaja = solmu;
            viimeinen = solmu;
        }
//        pituus++;
    }
    
    /**
     * Hakee jonosta arvon annetulla avaimella.
     * 
     * @param avain Haettava avain.
     * @return      Avainta vastaava arvo tai <tt>null</tt> jos sellaista ei ole.
     */
    V hae(K avain) {
        V paluuarvo = null;
        AvainArvoSolmu<K, V> solmu = ensimmainen;
        
        while (solmu != null) {            
            if (solmu.AVAIN.equals(avain)) {
                paluuarvo = solmu.ARVO;
                break;
            }
            solmu = solmu.seuraaja;
        }
        
        return paluuarvo;
    }
    
    @Override
    public String toString() {        
         if (ensimmainen == null) {
             return "\u2205";
         }
        
        StringBuilder mjr = new StringBuilder();
        AvainArvoSolmu solmu = ensimmainen;
        
        if (ensimmainen.seuraaja == null) {
            mjr.append("{");
            mjr.append(solmu.AVAIN);
            mjr.append("\u21A6");
            mjr.append(solmu.ARVO);
            mjr.append("}");
        } else {
            mjr.append('(');        
            while (solmu != null) {
                mjr.append(solmu.AVAIN);
                mjr.append("\u21A6");
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
