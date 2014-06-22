package LZW;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajautusTaulu;

/**
 * Sis‰lt‰‰ muutamia yleismetodeja joita sek‰ LZWLukija ett‰ LZWPurkaja
 * k‰ytt‰v‰t.
 */

public class YleisMetodeja {
    
    /**
     * Palauttaa luettavien/kirjoitettavien bittijonojen pituuden.
     * @param pituus
     * @param suurimmanArvonPituus
     * @return 
     */
    
    protected int merkkienPituus(int pituus, int suurimmanArvonPituus) {
        return Math.max(pituus, suurimmanArvonPituus);
    }
    
    /**
     * Selvitt‰‰ arvon, joka on seuraava, mik‰ (laaj-) koodistoon lis‰t‰‰n.
     * @param ascii
     * @param laaj
     * @return 
     */
    
    protected String koodistoonLisattavaArvo(HajautusTaulu ascii, HajautusTaulu laaj) {
        int arvoja = arvoja(ascii, laaj);
        return new BinaariMuuntaja().binaariEsitys(arvoja);
    }
    
    /**
     * Palauttaa parametrina annetuissa haj.tauluissa olevien avainten m‰‰r‰n.
     * (Ohjelmassa: 256 + laaj.getKoko()).
     * @param ascii
     * @param laaj
     * @return 
     */
    
    protected int arvoja(HajautusTaulu ascii, HajautusTaulu laaj) {
        return ascii.getKoko() + laaj.getKoko();
    }
    
    /**
     * Alustaa ascii -koodiston lis‰‰m‰ll‰ sinne kaikki tavut ja niit‰ vastaavat
     * 8-bitin esitykset.
     * @return 
     */
    
    protected HajautusTaulu alustaAscii() {
        BinaariMuuntaja muuntaja = new BinaariMuuntaja();
        
        HajautusTaulu ascii = new HajautusTaulu();
        for (int i = 0; i < 256; i++) {
            ascii.lisaa((char) i + "", muuntaja.binaariEsitys8Bit(i));
        }
        return ascii;
    }
}
