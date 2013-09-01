
package com.mycompany.tiralabra_maven.tietorakenteet;

import com.mycompany.tiralabra_maven.automaatit.Tila;

/**
 * Perusominaisuuksiltaan kuten <b>AvainArvoJono</b>, mutta muokattu luokan
 * <b>Tila</b> tarpeita ajatellen.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 * @see AvainArvoJono
 * @see Tila
 */
public final class Tilasiirtymajono extends AvainArvoJono<Character,Tila> {
    
    /**
     * Palauttaa merkkijonoesityksen käytettäväksi tekstikäyttöliittymässä.
     *
     * @param SISENNYS  Jokaisen rivin eteen lisättävä merkkijono.
     * @return          Sisennetty merkkijo.
     */
    public String sisennettyMerkkijono(final String SISENNYS) {        
        if (ensimmainen == null) {
            return "\u2205";
        }
        
        int alkioita = 0;
        StringBuilder mjr = new StringBuilder();
        AvainArvoSolmu<Character, Tila> solmu = ensimmainen;
        mjr.append("(");        
        mjr.append('\n');
        mjr.append(SISENNYS);
        while (solmu != null) {
            if (alkioita == 5) {
                mjr.append('.');
                mjr.append('.');
                mjr.append('.');
                mjr.append(',');
                mjr.append('\n');
                mjr.append(SISENNYS);
                break;
            }
            if (solmu.AVAIN != '\u03b5') {
                mjr.append('\'');
                mjr.append(solmu.AVAIN);
                mjr.append('\'');
            } else {
                mjr.append(' ');
                mjr.append('\u03b5');
                mjr.append(' ');
            }
            mjr.append("\u21A6");
            mjr.append(solmu.ARVO.sisennettyMerkkijono(SISENNYS));
            mjr.append(',');
            mjr.append('\n');
            mjr.append(SISENNYS);
            solmu = solmu.seuraaja;
            alkioita++;
        }
        mjr.delete(mjr.length() - (SISENNYS.length() + 2), mjr.length());
        
        mjr.append('\n');
        mjr.append(SISENNYS);
        mjr.append(")");
        
        return mjr.toString();
    }

    @Override
    public void lisaa(final Character AVAIN, final Tila ARVO) {
        super.lisaa(AVAIN, ARVO);
    }
    
    /**
     * Palauttaa jonon solmujen avaimista.
     * 
     * @return  Uusi <b>Jono</b>-instanssi joka sisältää <b>AvainArvoJono</b>:n
     *          tietoalkioiden avaimet.
     * @see     Hajautuskartta#uudelleenHajauta() 
     */
    Jono<Character> avainjono() {
        if (ensimmainen == null) {
            return null;
        }
        Jono<Character> paluuarvo = new Jono<>();
        AvainArvoSolmu<Character, Tila> solmu = ensimmainen;
        while (solmu != null) {
            paluuarvo.lisaa(solmu.AVAIN);
            solmu = solmu.seuraaja;
        }
        return paluuarvo;
    }
    
    /**
     * Palauttaa jonon solmujen arvoista.
     * 
     * @return  Uusi <b>Jono</b>-instanssi joka sisältää <b>AvainArvoJono</b>:n
     *          tietoalkioiden arvot.
     * @see     Hajautuskartta#uudelleenHajauta() 
     */
    Jono<Tila> arvojono() {
        if (ensimmainen == null) {
            return null;
        }
        Jono<Tila> paluuarvo = new Jono<>();
        AvainArvoSolmu<Character, Tila> solmu = ensimmainen;
        while (solmu != null) {
            paluuarvo.lisaa(solmu.ARVO);
            solmu = solmu.seuraaja;
        }
        return paluuarvo;
    }
    
    /**
     * Palauttaa kaikki annettua arvoa vastaavat avaimet uutena <b>Jono</b>
     * instanssina. Mallintaa tilan kaikkien mahdollisten tilasiirtymien joukkoa
     * annetulla merkillä.
     * 
     * @param AVAIN Merkki, jolla haetaan tilasiirtymiä.
     * @return <b>Jono</b> kaikista merkkiin liitetyistä tilasiirtymistä tai
     * <i>null</i> jos sellaisia ei ole.
     */
    public Jono<Tila> haeKaikki(final char AVAIN) {
        if (hae(AVAIN) == null) {
            return null;
        }
        
        final Jono<Tila> ARVOJONO   = arvojono();        
        
        if (ARVOJONO.pituus() == 1) {
            return ARVOJONO;
        }
        
        final Jono<Tila> PALUUARVO      = new Jono<>();
        final Jono<Character> AVAINJONO = avainjono();
        
        Character avain;
        Tila arvo;
        // Operaation hidas osuus (jonot pitäisi ehkä järjestää tai korvata
        // keoilla mutta ne eivät ole tavallisissa olosuhteissa kovin pitkiä):
        while (!AVAINJONO.onTyhja()) {
            // Käydään avain- ja arvojonoja läpi samanaikaisesti ja lisätään
            // arvo ulostuloon jos avaimet täsmäävät (jonot ovat yhtä pitkät).
            avain   = AVAINJONO.poista();
            arvo    = ARVOJONO.poista();
            if (avain.equals(AVAIN)) {
                PALUUARVO.lisaa(arvo);
            }
        }
        
        return PALUUARVO;
    }
}
