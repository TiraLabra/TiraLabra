package Main;

import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.OmaList;

public class Main {

    
    // testailua
    public static void main(String[] args) {
        TiedostoLukija l = new TiedostoLukija("Testitiedostot/keskisuuri.txt", 5);
        try {
            OmaList<OmaList<Byte>> luettuTieto = l.lueTiedosto();
            
            for (int i = 0; i < luettuTieto.size(); ++i) {
                OmaList<Byte> tavut = luettuTieto.get(i);
      
            }
            
            TiedostoKirjoittaja k = new TiedostoKirjoittaja("Testitiedostot/testioutput.txt");
            k.kirjoitaTiedosto(luettuTieto);
            
            
        }   
        catch (Exception e) {
            System.out.println("Jotain meni pieleen: " + e.getMessage());
        }
    }
}
