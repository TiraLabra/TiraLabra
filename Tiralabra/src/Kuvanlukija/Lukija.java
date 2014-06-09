/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kuvanlukija;
    import java.io.File;
    import java.awt.image.BufferedImage;
    import Verkko.*;

/**
 *
 * @author eniirane
 */
public class Lukija {
    File tiedosto;
    
    public Lukija(String tiedostonimi) {
        this.tiedosto = new File(tiedostonimi);
    }

    private static Verkko lueKartaksi(BufferedImage kuva) {
        int korkeus = kuva.getHeight();
        int leveys = kuva.getWidth();
        Verkko verkko = new Verkko();

        // käydään kuva läpi ja merkitään 0-arvot läpikuljettaviksi tileiksi.
        for (int x = 0; x < leveys; x++) {
            for (int y = 0; y < korkeus; y++) {
                if (kuva.getRGB(x, y) == 0) {
                    // tässä on algoritmimysteeri --- miten merkata yhteydet naapureihin?
                    // alustavasti voisi ajatella että tarkastetaan iteroidessa x,y-1, x-1,y ja x-1, y-1
                    // koska verkon relaatiot ovat symmetrisiä (ja transitiivisia)
                    verkko.setNoodi(x, y);
                }
         }
      }

      return verkko;
   }

}
