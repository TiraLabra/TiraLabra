package LZW;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajautusTaulu;

public class YleisMetodeja {
    
    protected int  merkkienPituus(HajautusTaulu ascii, HajautusTaulu laaj) {
        int i = 8;
        
        while (true) {
            double potenssi = Math.pow(2, i);
            
            if (potenssi <= arvoja(ascii, laaj)) {
                i++;
                continue;
            }
            return i;
        }
    }
    
    protected int arvoja(HajautusTaulu ascii, HajautusTaulu laaj) {
        return ascii.getKoko() + laaj.getKoko();
    }
    
    protected HajautusTaulu alustaAscii() {
        BinaariMuuntaja muuntaja = new BinaariMuuntaja();
        
        HajautusTaulu ascii = new HajautusTaulu();
        for (int i = 0; i < 256; i++) {
            ascii.lisaa((char) i + "", muuntaja.binaariEsitys8Bit(i));
        }
        return ascii;
    }
}
