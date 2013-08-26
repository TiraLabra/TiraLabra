/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.tietorakenteet;

import com.mycompany.tiralabra_maven.automaatit.Tila;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class Tilasiirtymajono extends AvainArvoJono<Character,Tila> {
   
    public String sisennettyMerkkijono(final String SISENNYS) {        
         if (ensimmainen == null) {
             return "\u2205";
         }
        
        StringBuilder mjr = new StringBuilder();
        AvainArvoSolmu<Character, Tila> solmu = ensimmainen;
//        mjr.append(SISENNYS);
        
        if (ensimmainen.seuraaja == null) {
            mjr.append("(");
            mjr.append(solmu.AVAIN);
            mjr.append("\u21A6");
            mjr.append(solmu.ARVO.sisennettyMerkkijono(SISENNYS));
            mjr.append(")");
        } else {
            mjr.append('(');
            mjr.append('\n');
            mjr.append(SISENNYS);
            while (solmu != null) {
                mjr.append(solmu.AVAIN);
                mjr.append("\u21A6");
                mjr.append(solmu.ARVO.sisennettyMerkkijono(SISENNYS));
                mjr.append(',');
                mjr.append('\n');
                mjr.append(SISENNYS);
                solmu = solmu.seuraaja;
            }
            mjr.delete(mjr.length() - (SISENNYS.length() + 2), mjr.length());
            mjr.delete(1, SISENNYS.length() + 2);
            mjr.append(")");
        }
        
        return mjr.toString();
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
}
