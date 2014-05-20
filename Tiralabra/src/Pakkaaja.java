
import java.util.Map;


public class Pakkaaja {
    
    public Pakkaaja() {
    }
    
    public String pakkaa(String teksti) {
        int[] merkit = new int[256];
        
        for (int i = 0; i < teksti.length(); i++) {
            merkit[teksti.charAt(i)]++;
        }
        
        HuffmanKoodi h = new HuffmanKoodi();
        
        Map<Character, String> koodisanat = h.muodostaKoodit(h.muodostaPuu(merkit));
        
        h.tulosta();
        
        String pakattu = "";
        
        for (int i = 0; i < teksti.length(); i++) {
            if (koodisanat.containsKey(teksti.charAt(i))) {
                pakattu += koodisanat.get(teksti.charAt(i));
            }
        }
        
        return pakattu;
        
    }
    
}
