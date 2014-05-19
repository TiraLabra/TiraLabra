package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class TiedostonPakkaaja {
    private HashMap<String, Integer> hash;
    private String teksti;
    
    public TiedostonPakkaaja() {
        this.hash = new HashMap<String, Integer>();
        this.teksti = "";
    }
    
    public void pakkaa(String polku) {
        
        try {
            haeTeksti(polku);
        }
        
        catch (FileNotFoundException e) {
            System.out.print("Tiedoston luku ei onnistunut. Annoit sen polun väärin.\nOhjelma suljetaan.");
            return;
        }
        
        
    }
    
    public void haeTeksti(String polku) throws FileNotFoundException {
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
    
    private void lisaaMerkki(String lisattava) {
        int maara = 1;
        
        if (hash.containsKey(lisattava)) {
            maara += hash.get(lisattava);
        }
        
        hash.put(lisattava, maara);
        teksti += lisattava;
    }
}
