package Toteutus;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanPuuTest {
    private HuffmanPuu puu;
    private BittiEsitykset esitykset;
    
    @Before
    public void setUp() {
        this.puu = new HuffmanPuu();
        puu.muodostaHuffmanPuu(alustaEsiintymat());
        esitykset = new BittiEsitykset();
        esitykset.muodostaMerkeilleBittiEsitykset(puu.getKeko().getSolmut()[0], "");
    }

    private HashMap<String, Integer> alustaEsiintymat() {
        HashMap<String, Integer> esiintymat = new HashMap<String, Integer>();
        esiintymat.put("A", 8);
        esiintymat.put("B", 2); // b = g
        esiintymat.put("C", 4);
        esiintymat.put("D", 1);
        esiintymat.put("E", 5); // e = h
        esiintymat.put("F", 3);
        esiintymat.put("G", 2);
        esiintymat.put("H", 5); // yht 8 kpl
        
        return esiintymat;
    }
    
    @Test
    public void merkillaALyhinBinaariEsitys() {
        String verr = esitykset.getEsitykset().get("A");
        String A = esitykset.getEsitykset().get("A");
       
        
        for (String merkki : esitykset.getEsitykset().keySet()) {
            if (merkki != null) {
                String bittiesitys = esitykset.getEsitykset().get(merkki);
                
                if (bittiesitys.length() < verr.length()) {
                    verr = bittiesitys;
                }
            }
        }
        
        assertTrue(A.length() == verr.length());
    }
    
    @Test
    public void merkillaDPisinBinaariEsitys() {
        String verr = esitykset.getEsitykset().get("D");
        String D = esitykset.getEsitykset().get("D");
        
        for (String merkki : esitykset.getEsitykset().keySet()) {
            if (merkki != null) {
                String bittiesitys = esitykset.getEsitykset().get(merkki);
                
                if (bittiesitys.length() > verr.length()) {
                    verr = bittiesitys;
                }
            }
        }
        
        assertTrue(D.length() == verr.length());
    }
    
    @Test
    public void solmuillaEriBinaariEsitykset() {
        boolean patee = true;
        String[] bin = new String[esitykset.getEsitykset().size()];
        
        int i = 0;
        for (String arvo : esitykset.getEsitykset().values()) {
            bin[i] = arvo;
            i++;
        }
        
        for (int j = 0; j < bin.length; j++) {
            for (int k = 0; k < bin.length; k++) {
                if (j != k && bin[j].equals(bin[k])) {
                    patee = false;
                    break;
                }
            }
        }
        assertTrue(patee);
    }
}
