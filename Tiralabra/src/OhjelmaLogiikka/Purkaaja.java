

package OhjelmaLogiikka;

import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Purkaaja {
    private final int BLOKIN_KOKO;
    
    public Purkaaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }
    
    public void pura(String pakattu, String kohde) {
        try {
            TiedostoLukija lukija = new TiedostoLukija(pakattu);
            OmaMap<String, OmaList<Byte>> koodit = lataaKoodit(pakattu);
        }
        catch (Exception ex) {
            System.out.println("Jotain meni pieleen: " + ex.getMessage());
        }     
    }

    private OmaMap<String, OmaList<Byte>> lataaKoodit(String pakattu) throws FileNotFoundException, IOException {
        TiedostoLukija headerLukija = new TiedostoLukija(pakattu + ".header");
        OmaList<String> rivit = headerLukija.lueTiedostoRiveittain();
        
        for (int i = 0; i < rivit.size(); ++i) {
            System.out.println("Rivi: " + rivit.get(i));
        }
        
        return null;
    }
}
