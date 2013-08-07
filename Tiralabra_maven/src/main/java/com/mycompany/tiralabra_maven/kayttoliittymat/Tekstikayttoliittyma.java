
package com.mycompany.tiralabra_maven.kayttoliittymat;

import com.mycompany.tiralabra_maven.rajapinnat.Kayttoliittyma;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author John Lång
 */
public final class Tekstikayttoliittyma implements Kayttoliittyma {
    
    private final Scanner LUKIJA;
    
    public Tekstikayttoliittyma(String syote) {
        LUKIJA = new Scanner(syote);
    }
    
    public Tekstikayttoliittyma() {
        LUKIJA = new Scanner(System.in);
    }

    public void tulosta(String viesti) {
        System.out.println(viesti);
    }

    public String pyydaSyote(String viesti) {
        tulosta(viesti);
        System.out.print("> ");
        return LUKIJA.nextLine();
    }

}
