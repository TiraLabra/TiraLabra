
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Ylivuotolistoja sekä jakojäännösmenetelmään perustuvaa hajautusfunktiota
 * hyödyntävä hajautuskartta jonka avain-arvoparit eivät ole muuttuvia eivätkä
 * uniikkeja. Hajautusfunktion yksinkertaistamiseksi ja tämän projektin tarpeita
 * ajatellen hajautuskartan avaimet voivat olla vain tyyppiä <b>char</b>.
 * Hajautuskartta tukee myös uudelleenhajautusta mutta ei tee sitä
 * automaattisesti. Lisäksi usein käytettävät operaatiot <tt>lisaa</tt> ja
 * <tt>hae</tt> eivät tarkasta syötettä. Olen pyrkinyt minimoimaan näillä
 * toimilla tietorakenteen kompleksisuutta.
 * 
 * @author John Lång
 * @param <V> Hajautuskartan tietoalkioiden tyyppi.
 */
public class Hajautuskartta<V> {
    
    protected AvainArvoJono<Character, V>[] ylivuotolistat;
    protected int alkioita;
    
    /**
     * Palauttaa luokan uuden instanssin.
     *
     * @param HAJAUTUSTAULUN_PITUS Hajautustaulun pituus.
     */
    public Hajautuskartta(final int HAJAUTUSTAULUN_PITUS) {
        tarkastaHajautustaulunPituus(HAJAUTUSTAULUN_PITUS);
        this.ylivuotolistat = alustaYlivuotolistat(HAJAUTUSTAULUN_PITUS);
        this.alkioita = 0;
    }
    
    /**
     * Palauttaa luokan uuden instanssin. Hajautustaulun pituudeksi tulee 3.
     */
    public Hajautuskartta() {
        this.ylivuotolistat = alustaYlivuotolistat(3);
    }
    
    protected void tarkastaHajautustaulunPituus(final int HAJAUTUSTAULUN_PITUUS)
            throws IllegalArgumentException {
        // Ylärajaa ei tarvitse tarkastaa sillä int pyörähtää ympäri
        // negatiiviseksi ylivuodon sattuessa.
        if (HAJAUTUSTAULUN_PITUUS <= 0) {
            throw new IllegalArgumentException("Hajautustaulun pituuden pitää "
                    + "olla positiivinen!");
        }
    }

    protected AvainArvoJono<Character, V>[] alustaYlivuotolistat(
            final int HAJAUTUSTAULUN_PITUS) {
        AvainArvoJono<Character, V>[] paluuarvo
                = new AvainArvoJono[HAJAUTUSTAULUN_PITUS];
        for (int i = 0; i < HAJAUTUSTAULUN_PITUS; i++) {
            paluuarvo[i] = new AvainArvoJono<>();
        }
        return paluuarvo;
    }
    
    /**
     * Metodi mallintaa hajautusfunktiota <i>h</i>&nbsp;:&nbsp;<i>M</i>&nbsp;->
     * &nbsp;<i>I</i> missä <i>M</i> on Javan char tyyppiin mahtuvien
     * luonnollisten lukujen joukko ja <i>I</i> on niiden luonnollisten lukujen
     * <i>i</i> joukko joille pätee 0&nbsp;<=&nbsp;<i>i</i>&nbsp;<=&nbsp;
     * <i>m</i>, missä <i>m</i> on hajautustaulun pituus. Hajautusfunktion
     * kuva-alkion <i>h</i>(<i>n</i>) arvo on <i>n</i>:n jakojäännös
     * nimittäjällä <i>m</i>.
     * 
     * @param AVAIN Hajautettava <b>char</b>-arvo.
     * @return <b>int</b>-tyyppiseksi muutettu hajautettu avain.
     */
    protected int hajauta(final char AVAIN) {
        // Tämä metodi voisi periaatteessa olla julkinen ja ehkä jopa static.
        return AVAIN % ylivuotolistat.length;
    }

    /**
     * Lisää hajautuskarttaan annetun avain-arvoparin.
     *
     * @param AVAIN Lisättävä avain.
     * @param ARVO  Lisättävä arvo.
     */
    public void lisaa(final char AVAIN, final V ARVO) {
        ylivuotolistat[hajauta(AVAIN)].lisaa(AVAIN, ARVO);
        alkioita++;
    }
    
    /**
     * Palauttaa ensimmäisen avaimella löytyneen arvon.
     *
     * @param AVAIN Haettava avain.
     * @return      Avainta vastaava arvo tai <tt>null</tt> jos sellaista ei ole.
     */
    public V haeEnsimmainen(final char AVAIN) {
        return ylivuotolistat[hajauta(AVAIN)].hae(AVAIN);
    }
    
    @Override
    public String toString() {
        StringBuilder mjr = new StringBuilder();
        mjr.append('{');
        for (AvainArvoJono<Character, V> avainArvoJono : ylivuotolistat) {
            mjr.append(avainArvoJono.toString());
            mjr.append(',');
        }
        mjr.delete(mjr.length() - 1, mjr.length());
        mjr.append('}');
        return mjr.toString();
    }
    
}
