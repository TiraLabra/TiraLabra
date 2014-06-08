package Toteutus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Luokka jota k‰ytet‰‰n (teksti)tiedoston lukemiseen ja samanaikaisesti eri merkkien
 * esiintymism‰‰rien kirjaamiseen.
 */

public class TekstinLukija {
    private HashMap<String, Integer> esiintymat;
    private StringBuilder teksti;
    private boolean keraaEsiintymat;
    
    public TekstinLukija() {
        this.esiintymat = new HashMap<>();
        this.teksti = new StringBuilder();
        this.keraaEsiintymat = true;
    }
    
    public TekstinLukija(boolean keraaEsiintymat) {
        this.teksti = new StringBuilder();
        this.keraaEsiintymat = keraaEsiintymat;
    }
    
    public HashMap<String, Integer> getEsiintymat() {
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
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Annoit tiedoston polun v‰‰rin.\nOhjelma suljetaan..");
        }
    }
    
    protected void lue(Scanner lukija) {
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            lisaaRivi(rivi);

            if (lukija.hasNextLine()) {
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
        if (keraaEsiintymat) {
            lisaaEsiintyma(merkki);
        }
        teksti.append(merkki);
    }
    
    protected void lisaaEsiintyma(String merkki) {
        int maara = 1;
            
        if (esiintymat.containsKey(merkki)) {
            maara += esiintymat.get(merkki);
        }
        
        esiintymat.put(merkki, maara); 
    }
}
