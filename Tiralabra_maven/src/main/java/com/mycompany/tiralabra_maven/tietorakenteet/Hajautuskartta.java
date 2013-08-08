
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Ylivuotolistoja sekä jakojäännösmenetelmään perustuvaa hajautusfunktiota
 * hyödyntävä hajautuskartta. Hajautusfunktion yksinkertaistamiseksi ja tämän
 * projektin tarpeita ajatellen hajautuskartan avaimet voivat olla vain tyyppiä
 * <b>char</b>.
 * 
 * @author John Lång
 * @param <V> Hajautuskartan tietoalkioiden tyyppi.
 */
public final class Hajautuskartta<V> {
    
    private final AvainArvoJono<Character, V>[] YLIVUOTOLISTAT;

    /**
     * Palauttaa luokan uuden instanssin.
     *
     * @param HAJAUTUSTAULUN_PITUS Hajautustaulun pituus.
     */
    public Hajautuskartta(final int HAJAUTUSTAULUN_PITUS) {
        this.YLIVUOTOLISTAT = new AvainArvoJono[HAJAUTUSTAULUN_PITUS];
        for (int i = 0; i < HAJAUTUSTAULUN_PITUS; i++) {
            YLIVUOTOLISTAT[i] = new AvainArvoJono<Character, V>();
        }
    }

    /**
     * Lisää hajautuskarttaan annetun avain-arvoparin.
     *
     * @param avain Lisättävä avain.
     * @param arvo  Lisättävä arvo.
     */
    public void lisaa(char avain, V arvo) {
        YLIVUOTOLISTAT[hajauta(avain)].lisaa(avain, arvo);
    }
    
    private int hajauta(char avain) {
        return (int) avain % YLIVUOTOLISTAT.length;
    }
    
    /**
     * Hakee hajautuskartasta arvon annetulla avaimella.
     *
     * @param avain Haettava avain.
     * @return      Avainta vastaava arvo tai <tt>null</tt> jos sellaista ei ole.
     */
    public V hae(char avain) {
        return YLIVUOTOLISTAT[hajauta(avain)].hae(avain);
    }
    
    @Override
    public String toString() {
        StringBuilder mjr = new StringBuilder();
        mjr.append('{');
        for (AvainArvoJono<Character, V> avainArvoJono : YLIVUOTOLISTAT) {
            mjr.append(avainArvoJono.toString());
            mjr.append(',');
        }
        mjr.delete(mjr.length() - 1, mjr.length());
        mjr.append('}');
        return mjr.toString();
    }
    
}
