
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanKoodi {

    private HashMap<Character, String> koodit;

    public HuffmanKoodi() {
        this.koodit = new HashMap();
    }

    public HuffmanSolmu muodostaPuu(int[] merkit) {
      Minimikeko keko = merkitJarjestykseen(merkit);

        System.out.println(keko.koko());

        while (keko.koko() > 1) {
            HuffmanSolmu eka = keko.pienin();
            HuffmanSolmu toka = keko.pienin();

            HuffmanSolmu juuri = new HuffmanSolmu(-1, eka.getMaara() + toka.getMaara(), eka, toka);

            keko.lisaa(juuri);
        }

        return keko.pienin();
    }

    private Minimikeko merkitJarjestykseen(int[] merkit) {
        Minimikeko q = new Minimikeko();
        
        for (int i = 0; i < merkit.length; i++) {
            if (merkit[i] > 0) {
                //merkki, esiintymiskerrat, vasen, oikea
                HuffmanSolmu s = new HuffmanSolmu(i, merkit[i], null, null);
                
                q.lisaa(s);
                
            }
        }
        return q;
    }

    public Map muodostaKoodit(HuffmanSolmu juuri) {
           if (juuri == null || (juuri.getVasen() == null && juuri.getOikea() == null)) {
               return null;
           }
           
           String bitit = "";
           
           koodit(bitit, juuri);
           
           return koodit;
    
    }
    
    public void koodit(String bitit, HuffmanSolmu solmu) {

        if (solmu.getVasen() == null && solmu.getOikea() == null) {
            koodit.put((char) solmu.getMerkki(), bitit);
            return;
        }
        
        koodit(bitit + "0", solmu.getVasen());
        koodit(bitit + "1", solmu.getOikea());

    }
    
    public void tulosta () {
        for (char merkki : koodit.keySet()) {
            System.out.println(merkki + " - " + koodit.get(merkki));
        }
    }

}
