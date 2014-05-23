
import java.util.Map;


public class Pakkaaja {
    
    public Pakkaaja() {
    }
    
    public String pakkaa(String teksti) {
        HuffmanKoodi h = new HuffmanKoodi();
        
        int[] merkit = merkkienEsiintymiskerrat(teksti);
        
        Map<Character, String> koodisanat = h.muodostaKoodit(h.muodostaPuu(merkit));
        
        //h.tulosta();
        
        return enkoodaa(teksti, koodisanat);
        
    }

    private String enkoodaa(String teksti, Map<Character, String> koodisanat) {
        String pakattu = "";
        
        for (int i = 0; i < teksti.length(); i++) {
            if (koodisanat.containsKey(teksti.charAt(i))) {
                pakattu += koodisanat.get(teksti.charAt(i));
            }
        }
        
        return pakattu;
    }

    private int[] merkkienEsiintymiskerrat(String teksti) {
        int[] merkit = new int[256];
        for (int i = 0; i < teksti.length(); i++) {
            merkit[teksti.charAt(i)]++;
        }
        return merkit;
    }
    
}
