

package OhjelmaLogiikka;

import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.OmaList;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Pakkaaja {

    
    void pakkaa(String tiedosto, String ulosTiedosto) {
        TiedostoLukija lukija = new TiedostoLukija(tiedosto);
        try {
            OmaList<Byte> luetutTavut = lukija.lueTiedosto(); 
        
        }
        catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynynt: " + ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println("IO-virhe: " + ex.getMessage());
        }
        
        // lue tiedostosta tavut
        // tiedost hash mappiin
        // laske tavujen esiintymistiheys
        // esiintymistiheyden pohjalta minimijono
        // muodosta puu minimijonosta
        // muodosta hajautustaulu puusta
        // korvaa tiedostoon tavut koodeilla
        // tallenna tiedostoon
    }
}
