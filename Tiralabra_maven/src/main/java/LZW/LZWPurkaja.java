package LZW;

import Apuvalineet.Purkaja;

public class LZWPurkaja extends Purkaja {
    
    public LZWPurkaja() {
        super(".lzw");
    }
    
    @Override
    protected String puretunTiedostonSisalto(String teksti) {
        String tekstiBinaarina = tekstiBinaarina(teksti, 0);
        return kirjoitettavaTeksti(tekstiBinaarina);
    }
    
    protected String kirjoitettavaTeksti(String binaarina) {
        return "";
    }
}
