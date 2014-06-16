package LZW;

import java.io.IOException;

public class Pakkaaja {
    
    public void pakkaa(String polku) throws IOException {
        LZWLukija lukija = new LZWLukija();
        lukija.lue(polku);
        
        String teksti = lukija.getTeksti();
        System.out.println(teksti);
    }
}
