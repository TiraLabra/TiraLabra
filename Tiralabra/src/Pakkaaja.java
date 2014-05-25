
import java.util.Map;

/**
 *Pakkaaja tiivistää halutun tiedoston.
 */
public class Pakkaaja {
    /**
     * Tiedostossa käytetyn merkiston koko.
     */
    private static final int MERKISTON_KOKO = 256;
    
    /**
     * 
     */
    public Pakkaaja() {
    }
    
    /**
     *Metodi palauttaa Huffmanin koodauksen avulla tiivistetyn tekstin.
     * @param teksti Teksti joka halutaan tiviistää.
     * @return Teksti tiivistetyssä muodossa.
     */
    public String pakkaa(String teksti) {
        if (teksti.isEmpty()) {
            return  null;
        }
        
        HuffmanKoodi h = new HuffmanKoodi();
        
        int[] merkit = merkkienEsiintymiskerrat(teksti);
        
        Map<Character, String> koodisanat = h.muodostaKoodit(h.muodostaPuu(merkit));
        
        System.out.println(h.palautaKoodisanat());
        
        return enkoodaa(teksti, koodisanat);
        
    }
    /**
     * Metodi luo enkoodatun tekstin.
     * @param teksti Teksti joka halutaan tiivistää.
     * @param koodisanat Tekstissä olevat merkit ja niitä vastaavat Huffmanin koodauksella luodut koodisanat.
     * @return Teksti tiivistetyssä muodossa. 
     */
    private String enkoodaa(String teksti, Map<Character, String> koodisanat) {
        String pakattu = "";
        
        for (int i = 0; i < teksti.length(); i++) {
            if (koodisanat.containsKey(teksti.charAt(i))) {
                pakattu += koodisanat.get(teksti.charAt(i));
            }
        }
        
        return pakattu;
    }
    
    /**
     * Metodi käy tekstin (tiedoston) läpi ja laskee merkistöön kuuluvien merkkien esiintymiskerrat tekstissä.
     * @param teksti Teksti joka halutaan tiivistää.
     * @return Tiedoston eri merkkien esiintymiskerrat taulukossa.
     **/
    private int[] merkkienEsiintymiskerrat(String teksti) {
        int[] merkit = new int[MERKISTON_KOKO];
        for (int i = 0; i < teksti.length(); i++) {
            merkit[teksti.charAt(i)]++;
        }
        return merkit;
    }
    
}
