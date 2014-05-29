package Toteutus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Luokka jotka käytetään (teksti)tiedoston lukemiseen ja samanaikaisesti eri merkkien
 * esiintymismäärien kirjaamiseen.
 */

public class TekstinLukija {
    private HashMap<String, Integer> esiintymat;
    private String teksti;
    
    public TekstinLukija() {
        this.esiintymat = new HashMap<String, Integer>();
        this.teksti = "";
    }
    
    public HashMap<String, Integer> getEsiintymat() {
        return this.esiintymat;
    }
    
    public String getTeksti() {
        return this.teksti;
    }
    
    /**
     * Metodi jota kutsutaan lukemaan tiedosto ja laskemaan siinä esiintyneet merkit.
     * @param polku - tiedoston polku
     * @throws FileNotFoundException - mikäli polku on virheellinen, heittää ko. poikkeuksen
     */
    
    public void lueTiedosto(String polku)  throws FileNotFoundException {
        Scanner lukija = new Scanner(new File(polku));
        
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            lisaaRivi(rivi);

            if (lukija.hasNextLine()) {
                lisaaMerkki("\n");
            }
        }
    }
    
    /**
     * Lisää seuraavan rivin merkit "tekstiin" ja kasvatetaan taas siinä olevien merkkien esiintymismääriä.
     * @param rivi
     */
    
    protected void lisaaRivi(String rivi) {
        for (int i = 0; i < rivi.length(); i++) {
            lisaaMerkki(rivi.charAt(i) + "");
        }
    }
    
    /**
     * Lisää merkin "tekstin" päähän ja kasvattaa ko. merkin esiintymismääriä haj.taulussa.
     * @param merkki 
     */
    
    protected void lisaaMerkki(String merkki) {
        int maara = 1;
        
        if (esiintymat.containsKey(merkki)) {
            maara += esiintymat.get(merkki);
        }
        
        esiintymat.put(merkki, maara);
        teksti += merkki;
    }
}
