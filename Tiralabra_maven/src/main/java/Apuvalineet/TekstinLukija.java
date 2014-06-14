package Apuvalineet;

import Tietorakenteet.HajTaulu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Luokka jota k‰ytet‰‰n (teksti)tiedoston lukemiseen ja samanaikaisesti eri merkkien
 * esiintymism‰‰rien kirjaamiseen.
 */

public class TekstinLukija {
    private HajTaulu esiintymat;
    private StringBuilder teksti;
    
    public TekstinLukija() {
        this.esiintymat = new HajTaulu();
        this.teksti = new StringBuilder();
    }
    
    public HajTaulu getEsiintymat() {
        return this.esiintymat;
    }
    
    public String getTeksti() {
        return this.teksti.toString();
    }
    
    /**
     * Metodi jota kutsutaan lukemaan tiedosto ja laskemaan siin‰ esiintyneet merkit.
     * @param polku - tiedoston polku
     * @throws FileNotFoundException - mik‰li polku on virheellinen, heitt‰‰ ko. poikkeuksen
     */
    
    public void lueTiedosto(String polku) throws FileNotFoundException {
        try {
            Scanner lukija = new Scanner(new File(polku));
            lue(lukija);
            lukija.close();
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Annoit tiedoston polun v‰‰rin.\nOhjelma suljetaan..");
        }
    }
    
    /**
     * Lukee seuravan rivin ja jos rivej‰ on lis‰‰, lis‰‰ rivinvaihdon (\r + \n).
     * @param lukija
     */
    
    protected void lue(Scanner lukija) {
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            lisaaRivi(rivi);

            if (lukija.hasNextLine()) {
                lisaaMerkki("\r");
                lisaaMerkki("\n");
            }
        }
    }
    
    /**
     * Lis‰‰ seuraavan rivin merkit "tekstiin" ja kasvatetaan taas siin‰ olevien merkkien esiintymism‰‰ri‰.
     * @param rivi
     */
    
    protected void lisaaRivi(String rivi) {
        for (int i = 0; i < rivi.length(); i++) {
            lisaaMerkki(rivi.charAt(i) + "");
        }
    }
    
    /**
     * Lis‰‰ merkin "tekstin" p‰‰h‰n ja kasvattaa ko. merkin esiintymism‰‰ri‰ haj.taulussa.
     * @param merkki 
     */
    
    protected void lisaaMerkki(String merkki) {
        lisaaEsiintyma(merkki);
        teksti.append(merkki);
    }
    
    /**
     * Kasvattaa ko. merkin esiintymien m‰‰r‰‰ yhdell‰.
     * @param merkki
     */
    
    protected void lisaaEsiintyma(String merkki) {
        int maara = 1;
            
        if (esiintymat.sisaltaaAvaimen(merkki)) {
            maara += Integer.parseInt(esiintymat.getArvo(merkki));
        }
        
        esiintymat.lisaa(merkki, Integer.toString(maara));
    }
}
