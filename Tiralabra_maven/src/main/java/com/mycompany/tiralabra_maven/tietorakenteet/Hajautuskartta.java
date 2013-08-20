
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Ylivuotolistoja sekä jakojäännösmenetelmään perustuvaa hajautusfunktiota
 * hyödyntävä hajautuskartta. Hajautusfunktion yksinkertaistamiseksi ja tämän
 * projektin tarpeita ajatellen hajautuskartan avaimet voivat olla vain tyyppiä
 * <b>char</b>. Hajautuskartta tukee myös uudelleenhajautusta mutta ei tee sitä
 * automaattisesti. Lisäksi usein käytettävät operaatiot <tt>lisaa</tt> ja
 * <tt>hae</tt> eivät tarkasta syötettä. Olen pyrkinyt minimoimaan näillä
 * toimilla tietorakenteen kompleksisuutta.
 * 
 * @author John Lång
 * @param <V> Hajautuskartan tietoalkioiden tyyppi.
 */
public final class Hajautuskartta<V> {
    
    private AvainArvoJono<Character, V>[] ylivuotolistat;
    private int alkioita;
    
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
    
    private void tarkastaHajautustaulunPituus(final int HAJAUTUSTAULUN_PITUUS)
            throws IllegalArgumentException {
        // Ylärajaa ei tarvitse tarkastaa sillä int pyörähtää ympäri
        // negatiiviseksi ylivuodon sattuessa.
        if (HAJAUTUSTAULUN_PITUUS <= 0) {
            throw new IllegalArgumentException("Hajautustaulun pituuden pitää "
                    + "olla positiivinen!");
        }
    }

    private AvainArvoJono<Character, V>[] alustaYlivuotolistat(
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
     * @param avain Hajautettava <b>char</b>-arvo.
     * @return <b>int</b>-tyyppiseksi muutettu hajautettu avain.
     */
    private int hajauta(char avain) {
        // Tämä metodi voisi periaatteessa olla julkinen ja ehkä jopa static.
        return avain % ylivuotolistat.length;
    }

    /**
     * Lisää hajautuskarttaan annetun avain-arvoparin.
     *
     * @param avain Lisättävä avain.
     * @param arvo  Lisättävä arvo.
     */
    public void lisaa(char avain, V arvo) {
        ylivuotolistat[hajauta(avain)].lisaa(avain, arvo);
        alkioita++;
    }
    
    /**
     * Hakee hajautuskartasta arvon annetulla avaimella.
     *
     * @param avain Haettava avain.
     * @return      Avainta vastaava arvo tai <tt>null</tt> jos sellaista ei ole.
     */
    public V hae(char avain) {
        return ylivuotolistat[hajauta(avain)].hae(avain);
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
    
    /**
     * Uudelleenhajauttaa hajautustaulun. <b>Hajautuskartta</b> ei suorita
     * uudelleenhajautusta automaattisesti vaan käyttäjän vastuulla on päättää
     * milloin (jos ollenkaan) uudelleenhajautus toteutetaan.
     * 
     * @see Hajautuskartta#tayttosuhde
     */
    public void uudelleenhajauta(final int HAJAUTUSTAULUN_PITUS) {
        tarkastaHajautustaulunPituus(HAJAUTUSTAULUN_PITUS);
        AvainArvoJono<Character, V>[] vanhatYlivuotolistat = ylivuotolistat;
        ylivuotolistat = alustaYlivuotolistat(HAJAUTUSTAULUN_PITUS);
        int i = 0;
        Character avain;
        V arvo;
        Jono<Character> avainjono;
        Jono<V> arvojono;
        for (AvainArvoJono<Character, V> avainArvoJono : vanhatYlivuotolistat) {
            avainjono   = avainArvoJono.avainjono();
            arvojono    = avainArvoJono.arvojono();
            while (!avainjono.onTyhja()) {
                avain   = avainjono.poista();
                arvo    = arvojono.poista();
                lisaa(avain, arvo);
            }
            i++;
        }
    }
    
    /**
     * Palauttaa hajautuskartan täyttösuhteen. Täyttösuhde on ylivuotoketjuissa
     * olevien tietoalkioiden lukumäärä jaettuna ylivuotoketjujen määrällä.
     * Täyttösuhdetta ei tallenneta muuttujaan vaan lasketaan erikseen tätä
     * metodia kutsuttaessa.
     * 
     * @return Hajautuskartan täyttösuhde.
     */
    public float tayttosuhde() {
        return (float) alkioita / ylivuotolistat.length;
    }
    
}
