
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.kayttoliittymat.Tekstikayttoliittyma;
import com.mycompany.tiralabra_maven.rajapinnat.Kayttoliittyma;
import com.mycompany.tiralabra_maven.tietorakenteet.Jono;

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
        
        String syote = k.pyydaSyote("Kaava:              ");
        int kaavanArvo;
        long aloitusaika = System.nanoTime(), tulkinAika, laskimenAika;
        Jono<String> kaava = t.tulkitseMerkkijono(syote);
        tulkinAika = System.nanoTime() - aloitusaika;
        k.tulosta("RPN-kaava:          " + kaava.toString() + '\n');
        aloitusaika = System.nanoTime();
        kaavanArvo = l.laske(kaava);
        laskimenAika = System.nanoTime() - aloitusaika;
        k.tulosta("Kaavan lukuarvo:    " + kaavanArvo + '\n');
        k.tulosta("Tulkkauksen kesto:  " + tulkinAika + " ns.\n");
        k.tulosta("Laskennan kesto:    " + laskimenAika + " ns.\n");
        System.out.printf("JVM:n viive:        %-1.1f ns.\n",
                testaaAjanotonViive());
    }
    
    private static double testaaAjanotonViive() {
        // En tiedä voiko tämän perusteella oikeasti tehdä mitään johtopäätöksiä
        long a, b, c = 0;
        for (int i = 0; i < 10; i++) {
            a = System.nanoTime();
            b = System.nanoTime() - a;
            c += b;
        }
        return c / 10.0;
    }
}
