
package com.mycompany.tiralabra_maven.kayttoliittymat;

import com.mycompany.tiralabra_maven.rajapinnat.Kayttoliittyma;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Tämän luokka toteuttaa rajapinnan <b>Kayttoliittyma</b> ja sen vastuulla on
 * syötteiden pyytäminen käyttäjältä sekä viestien tulostaminen terminaalissa.
 *
 * @author John Lång
 * @see Kayttoliittyma
 */
public final class Tekstikayttoliittyma implements Kayttoliittyma {
    
    private final Scanner LUKIJA;
    
    /**
     * Palauttaa luokan uuden instanssin.
     *
     * @param syote Merkkijono, jota käyttöliittymä käsittelee kuten käyttäjän
     * antamaa syötettä. Jos tämä parametri annetaan, ei
     * <b>Tekstikäyttöliittymä</b> kuuntele standardisisääntuloa. Tämä parametri
     * on tarkoitettu automaattisten yksikkötestien käyttöön.
     */
    public Tekstikayttoliittyma(String syote) {
        LUKIJA = new Scanner(syote);
    }
    
    /**
     * Palauttaa luokan uuden instanssin. Ilman parametria kutsuttu konstruktori
     * luo standardisisääntuloa kuuntelevan instanssin.
     */
    public Tekstikayttoliittyma() {
        LUKIJA = new Scanner(System.in);
    }

    /**
     * Tulostaa standardiulostuloon annetun merkkijonon.
     *
     * @param viesti Tulostettava viesti.
     * @see Kayttoliittyma#tulosta(java.lang.String)
     */
    public void tulosta(String viesti) {
        System.out.println(viesti);
    }

    /**
     * Tulostaa standardiulostuloon parametrina annetun merkkijonon ja lukee
     * sisääntulosta seuraavan rivin. Sisääntulo on määritelty luokan
     * konstruktorissa.
     *
     * @param viesti Tulostettava viesti.
     * @return Luettu syöte.
     * @see Tekstikayttoliittyma#Tekstikayttoliittyma() 
     * @see Tekstikayttoliittyma#Tekstikayttoliittyma(java.lang.String) 
     * @see Kayttoliittyma#pyydaSyote(java.lang.String) 
     */
    public String pyydaSyote(String viesti) {
        tulosta(viesti);
        System.out.print("> ");
        return LUKIJA.nextLine();
    }

}
