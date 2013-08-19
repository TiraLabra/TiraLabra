
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Ylivuotolistoja sekä jakojäännösmenetelmään perustuvaa hajautusfunktiota
 * hyödyntävä hajautuskartta. Hajautusfunktion yksinkertaistamiseksi ja tämän
 * projektin tarpeita ajatellen hajautuskartan avaimet voivat olla vain tyyppiä
 * <b>char</b>. Hajautuskartta tukee myös uudelleenhajautusta mutta ei tee sitä
 * automaattisesti.
 * 
 * @author John Lång
 * @param <V> Hajautuskartan tietoalkioiden tyyppi.
 */
public final class Hajautuskartta<V> {
    
    private AvainArvoJono<Character, V>[] ylivuotolistat;
    /**
     * Palauttaa luokan uuden instanssin.
     *
     * @param HAJAUTUSTAULUN_PITUS Hajautustaulun pituus.
     */
    public Hajautuskartta(final int HAJAUTUSTAULUN_PITUS) {
        if (HAJAUTUSTAULUN_PITUS <= 0) {
            throw new IllegalArgumentException("Hajautustaulun pituuden pitää "
                    + "olla positiivinen kokonaisluku!");
        }
        this.ylivuotolistat = alustaYlivuotolistat(HAJAUTUSTAULUN_PITUS);
    }
    
    /**
     * Palauttaa luokan uuden instanssin. Hajautustaulun pituudeksi tulee 3.
     */
    public Hajautuskartta() {
        this.ylivuotolistat = alustaYlivuotolistat(3);
    }

    /**
     * Lisää hajautuskarttaan annetun avain-arvoparin.
     *
     * @param avain Lisättävä avain.
     * @param arvo  Lisättävä arvo.
     */
    public void lisaa(char avain, V arvo) {
        ylivuotolistat[hajauta(avain)].lisaa(avain, arvo);
    }
    
    private int hajauta(char avain) {
        return (int) avain % ylivuotolistat.length;
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
        int pituuksienSumma = 0;
        
        for (AvainArvoJono<Character, V> avainArvoJono : ylivuotolistat) {
            pituuksienSumma += avainArvoJono.pituus();
        }
        
        // Normaali float on aivan tarpeeksi tarkka täyttösuhteelle.
        return (float) pituuksienSumma / ylivuotolistat.length;
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
    
}
