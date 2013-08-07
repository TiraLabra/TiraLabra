
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.kayttoliittymat.Tekstikayttoliittyma;
import com.mycompany.tiralabra_maven.rajapinnat.Kayttoliittyma;
import java.util.Queue;

/**
 *
 * @author John Lång
 */
public final class Kaavalaskin {

public static void main(String[] args) {
        Tulkki t = new Tulkki();
        Laskin l = new Laskin();
        Kayttoliittyma k = new Tekstikayttoliittyma();
        
//        // Clean codea parhaimmillaan
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
        Queue<String> kaava = t.tulkitseMerkkijono(syote);
        k.tulosta("RPN-kaavan symbolit: " + kaava);
        k.tulosta("Kaavan lukuarvo:     " + l.laske(kaava));
    }
}
