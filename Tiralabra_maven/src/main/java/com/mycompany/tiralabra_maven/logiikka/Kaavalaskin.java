
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.kayttoliittymat.Tekstikayttoliittyma;
import com.mycompany.tiralabra_maven.rajapinnat.Kayttoliittyma;
import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import java.util.Queue;

/**
 * Ohjelman pääluokka, joka vastaa kolmen pääkomponentin, <b>Kayttoliittyma</b>, 
 * <b>Tulkki</b> ja <b>Laskin</b>, käynnistämisestä sekä ohjelman suorituksen
 * päättämisestä. Lisäksi luokka käsittelee mahdolliset käyttäjän antamat
 * käynnistysparametrit.
 *
 * @author John Lång
 */
public final class Kaavalaskin {

    /**
     * Ohjelman suoritus alkaa ja päättyy tässä staattisessa metodissa.
     *
     * @param args Mahdolliset käynnistysparametrit.
     */
    public static void main(String[] args) {
        Tulkki t = new Tulkki();
        Laskin l = new Laskin();
        Kayttoliittyma k = new Tekstikayttoliittyma();
        
//        Queue<String> kaava = t.tulkitseMerkkijono("(7 + 3) * 5");
//        System.out.println(kaava.toString());
//        System.out.println("=");
//        System.out.println(l.laske(kaava));
//        
//        kaava = t.tulkitseMerkkijono("7 + 3 * 5");
//        System.out.println(kaava.toString());
//        System.out.println("=");
//        System.out.println(l.laske(kaava));
        
        String syote = k.pyydaSyote("Kaava:");
        Jono<String> kaava = t.tulkitseMerkkijono(syote);
        k.tulosta("RPN-kaavan symbolit: " + kaava);
        k.tulosta("Kaavan lukuarvo:     " + l.laske(kaava));
    }
}
