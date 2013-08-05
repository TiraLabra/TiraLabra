
package com.mycompany.tiralabra_maven.logiikka;

import java.util.Queue;

/**
 *
 * @author John Lång
 */
public class Kaavalaskin {

    public static void main(String[] args) {
        Tulkki t = new Tulkki();
        Laskin l = new Laskin();
        
        // Clean codea parhaimmillaan
        Queue<String> kaava = t.tulkitseMerkkijono("(7 + 3) * 5");
        System.out.println(kaava.toString());
        System.out.println("=");
        System.out.println(l.laske(kaava));
        
        kaava = t.tulkitseMerkkijono("7 + 3 * 5");
        System.out.println(kaava.toString());
        System.out.println("=");
        System.out.println(l.laske(kaava));
    }
}
