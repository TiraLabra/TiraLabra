package Toteutus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
    
    private void lisaaRivi(String rivi) {
        Scanner rivinLukija = new Scanner(rivi);
        
        while (rivinLukija.hasNext()) {
            String sana = rivinLukija.next();
                
            for (int i = 0; i < sana.length(); i++) {
                lisaaMerkki(sana.charAt(i) + "");
            }
            
            if (rivinLukija.hasNext()) {
                lisaaMerkki(" ");
            }
        }
    }
    
    private void lisaaMerkki(String merkki) {
        int maara = 1;
        
        if (esiintymat.containsKey(merkki)) {
            maara += esiintymat.get(merkki);
        }
        
        esiintymat.put(merkki, maara);
        teksti += merkki;
    }
}
