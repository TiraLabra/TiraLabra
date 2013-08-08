package tyokalut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Toteutetaan AVL-puun muodossa historia kaikista aiemmin lasketuista tuotteista,
 * jotka aina tallennetaan tekstitiedostoon ja ohjelman käynnistyessä luetaan takaisin
 * AsetteluHistoria-olioksi.
 * 
 * @author albis
 */
public class AsetteluHistoria {
    /**
     * 
     */
    private HashMap<Integer, KasvavaLista> historia = new HashMap<Integer, KasvavaLista>();
    
    public AsetteluHistoria() {
        
    }
    
    /**
     * Metodi, jolla uusia laskettuja asettelumäärityksiä tallennetaan.
     */
    public void lisaa() {
        
    }
    
    /**
     * Metodi, joka avaa vanhat asettelutavat sisältävän tiedoston ja lukee sen tiedot.
     */
    public void avaa() {
        File historiaTiedosto = new File("historia.txt");
        try {
            Scanner lukija = new Scanner(historiaTiedosto);
            
            while (lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                
                String[] osat = rivi.split(":");
                
                int EAN = Integer.parseInt(osat[0]);
                
                KasvavaLista asettelu = new KasvavaLista();
                
                osat = osat[0].split(",");
                
                for (int i = 0; i < osat.length; i++) {
                    asettelu.lisaa(osat[i]);
                }
                
                historia.put(EAN, asettelu);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Virhe tiedostoa avattaessa!" + e.getMessage());
        }
        
        
    }
    
    /**
     * Metodi, joka tallentaa kaikki puun sisältämät asettelutavat tekstitiedostoon
     * ohjelma suljettaessa.
     */
    public void tallenna() {
        try {
            FileWriter kirjoittaja = new FileWriter("historia.txt");
            
            kirjoittaja.write("EAN:suunta,suunta,suunta,suunta\n" );
        } catch (IOException e) {
            System.out.println("Virhe tallennettaessa!" + e.getMessage());
        }
    }
}
