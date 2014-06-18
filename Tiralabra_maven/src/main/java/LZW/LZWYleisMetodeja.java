package LZW;

import Tietorakenteet.HajautusTaulu;

public class LZWYleisMetodeja {
    
    protected int  merkkienPituus(HajautusTaulu ascii, HajautusTaulu laaj) {
        int i = 8;
        
        while (true) {
            double potenssi = Math.pow(2, i);
            
            if (potenssi < arvoja(ascii, laaj)) {
                i++;
                continue;
            }
            return i;
        }
    }
    
    protected int arvoja(HajautusTaulu ascii, HajautusTaulu laaj) {
        return ascii.getKoko() + laaj.getKoko();
    }
    
    protected String bittijonona(String arvo, HajautusTaulu ascii, HajautusTaulu laaj) {
        StringBuilder builder = new StringBuilder();
        int merkkienPituus = new LZWYleisMetodeja().merkkienPituus(ascii, laaj);
        
        while (builder.toString().length() + arvo.length() < merkkienPituus) {
            builder.append((char) 0);
        }
        
        builder.append(arvo);
        return builder.toString();
    }
}
