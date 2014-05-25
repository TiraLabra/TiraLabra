
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *Luokka sisältää Huffmanin algoritmin.
 */
public class HuffmanKoodi {
    /**
     * Huffmanin algoritmin tuloksena muodostuvat koodisanat.
     */
    private HashMap<Character, String> koodisanat;

    /**
     *
     */
    public HuffmanKoodi() {
        this.koodisanat = new HashMap();
    }

    /**
     *Muodostetaan Huffman-puu annetusta merkeistä minimikeon avulla.
     * @param merkit Merkistön eri merkkien esiintymiskerrat tiedostossa.
     * @return Viite Huffman-puun juureen.
     */
    public HuffmanSolmu muodostaPuu(int[] merkit) {
      Minimikeko keko = merkitJarjestykseen(merkit);

        while (keko.koko() > 1) {
            HuffmanSolmu eka = keko.pienin();
            HuffmanSolmu toka = keko.pienin();

            //TODO: taikanumero korjattava...
            HuffmanSolmu juuri = new HuffmanSolmu(-1, eka.getMaara() + toka.getMaara(), eka, toka);

            keko.lisaa(juuri);
        }

        return keko.pienin();
    }
    
    /**
     *Luo solmut annetuista merkeistä ja lisää nämä minimikekoon.
     * @param merkit Merkistön eri merkkien esiintymiskerrat tiedostossa.
     * @return Minimikeko, joka sisältää tiedoston eri merkit solmuiksi muutettuina.
     */
    private Minimikeko merkitJarjestykseen(int[] merkit) {
        Minimikeko keko = new Minimikeko();
        
        for (int i = 0; i < merkit.length; i++) {
            if (merkit[i] > 0) {
                //merkki, esiintymiskerrat, vasen, oikea
                HuffmanSolmu s = new HuffmanSolmu(i, merkit[i], null, null);
                
                keko.lisaa(s);
                
            }
        }
        return keko;
    }

    /**
     *Kutsuu rekursiivista koodisanat()-metodia ja palauttaa muodostetut koodisanat.
     * @param juuri Viite Huffmanin puun juuren.
     * @return Merkit ja niitä vastaavat koodisanat.
     */
    public Map muodostaKoodit(HuffmanSolmu juuri) {
           if (juuri == null) {
               return null;
           }
           
           if (juuri.getVasen() == null && juuri.getOikea() == null) {
               koodisanat("0", juuri);
               return koodisanat;
           }

           koodisanat("", juuri);
           
           return koodisanat;
    
    }
    
    /**
     * Kulkee Huffmanin puuta pitkin rekursiivisesti etsien lehdet ja muodostaen samalla lehdessä olevia
     * merkkejä vastaavat koodisanat.
     * @param bitit Merkkiä vastaava rakenteilla oleva koodisana.
     * @param solmu Solmu, jonka kohdalla ollaan puussa.
     */
    private void koodisanat(String bitit, HuffmanSolmu solmu) {

        if (solmu.getVasen() == null && solmu.getOikea() == null) {
            koodisanat.put((char) solmu.getMerkki(), bitit);
            return;
        }
        
        koodisanat(bitit + "0", solmu.getVasen());
        koodisanat(bitit + "1", solmu.getOikea());

    }
    
    /**
     *Palauttaa muodostetut koodisanat merkkijonona.
     * @return Merkkijonoesitys koodisanoista muodossa "merkki - koodisana"
     */
    public String palautaKoodisanat() {
        String merkkijono = "";
        
        for (char merkki : koodisanat.keySet()) {
            merkkijono += merkki + " - " + koodisanat.get(merkki) + "\n";
        }
        
        return merkkijono;
    }

}
