
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanKoodi {

    private HashMap<Character, String> koodisanat;

    public HuffmanKoodi() {
        this.koodisanat = new HashMap();
    }

    public HuffmanSolmu muodostaPuu(int[] merkit) {
      Minimikeko keko = merkitJarjestykseen(merkit);

        //System.out.println(keko.koko());

        while (keko.koko() > 1) {
            HuffmanSolmu eka = keko.pienin();
            HuffmanSolmu toka = keko.pienin();

            HuffmanSolmu juuri = new HuffmanSolmu(-1, eka.getMaara() + toka.getMaara(), eka, toka);

            keko.lisaa(juuri);
        }

        return keko.pienin();
    }

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

    public Map muodostaKoodit(HuffmanSolmu juuri) {
           if (juuri == null) {
               return null;
           }

           koodisanat("", juuri);
           
           return koodisanat;
    
    }
    
    private void koodisanat(String bitit, HuffmanSolmu solmu) {

        if (solmu.getVasen() == null && solmu.getOikea() == null) {
            koodisanat.put((char) solmu.getMerkki(), bitit);
            return;
        }
        
        koodisanat(bitit + "0", solmu.getVasen());
        koodisanat(bitit + "1", solmu.getOikea());

    }
    
    public void tulosta () {
        for (char merkki : koodisanat.keySet()) {
            System.out.println(merkki + " - " + koodisanat.get(merkki));
        }
    }

}
