package Toteutus.Huffman;

import Tietorakenteet.MinKeko;
import Tietorakenteet.Solmu;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HuffmanPuuTest {
    private HuffmanPuu puu;
    private HuffmanPuu puu2;
    private HashMap<String, Integer> esiintymat;
    private BittiEsitykset esitykset;
    
    @Before
    public void setUp() {
        this.puu = new HuffmanPuu();
        puu.muodostaHuffmanPuu(alustaEsiintymat());
        esitykset = new BittiEsitykset();
        esitykset.muodostaMerkeilleBittiEsitykset(puu.getKeko().getSolmut()[0], "");
    }

    private HashMap<String, Integer> alustaEsiintymat() {
        HashMap<String, Integer> esiintymat = new HashMap<>();
        esiintymat.put("A", 8);
        esiintymat.put("B", 2); // b = g
        esiintymat.put("C", 4);
        esiintymat.put("D", 1);
        esiintymat.put("E", 2);
        
        return esiintymat;
    }
    
    @Test
    public void merkillaALyhinBinaariEsitys() {
        String verr = esitykset.getEsitykset().get("A");
        String A = esitykset.getEsitykset().get("A");
        String nul = (char) 0 + "";
        
        for (String merkki : esitykset.getEsitykset().keySet()) {
            if (! merkki.equals(nul)) {
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
        String nul = "" + (char) 0;
        
        for (String merkki : esitykset.getEsitykset().keySet()) {
            if (! merkki.equals(nul)) {
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
    
    @Test
    public void luoKekoLuoMinKeon() {
        puu2 = new HuffmanPuu();
        esiintymat = new HashMap<>();
        esiintymat.put(("a"), 1);
        puu2.luoKeko(esiintymat);
        
        assertTrue(puu2.getKeko().getClass() == MinKeko.class);
    }
    
    @Test
    public void luodussaKeossaOnSinneLaitettavatAvaimet() {
        muodostaTestiPuuJaKeko();
        
        assertEquals("b", puu2.getKeko().getSolmut()[0].getAvain());
        assertEquals("a", puu2.getKeko().getSolmut()[1].getAvain());
    }    
    
    @Test
    public void yhdistetynSolmunEsiintymienMaaraLapsienEsiintymienSumma() {
        muodostaTestiPuuJaKeko();
        
        puu2.yhdistaKeonSolmutPuuksi();
        assertEquals(puu2.getKeko().getSolmut()[0].getEsiintymat(), 5);
    }
    
    @Test
    public void solmujenLinkitysOnnistuu() {
        Solmu vanh = new Solmu("a", 2);
        Solmu vasen = new Solmu("b", 3);
        Solmu oikea = new Solmu("c", 4);
        puu.linkitaSolmut(vanh, vasen, oikea);
        
        assertEquals(vasen, vanh.getVasen());
        assertEquals(oikea, vanh.getOikea());
        assertEquals(vanh, vasen.getVanh());
        assertEquals(vanh, oikea.getVanh());
    }
    
    @Test
    public void puunTekstiEsitys() {
        puu2 = new HuffmanPuu();
        esiintymat = new HashMap<>();
        puu2.luoKeko(esiintymat);
        tyhjaKekoPalauttaaNull();
        
        luoTestiEsiintymat();
        puu2.muodostaHuffmanPuu(esiintymat);
        
        char nul = (char) 0;
        puu2.puunTekstiEsitys();
        assertEquals(nul + "ab", puu2.puunTekstiEsitys());
        assertEquals(nul + "" + nul + "A" + nul + "C" + nul + "B" + "E" + "D", puu.puunTekstiEsitys());
    }
    
    private void tyhjaKekoPalauttaaNull() {
        assertNull(puu2.puunTekstiEsitys());
    }
    
    private void muodostaTestiPuuJaKeko() {
        luoTyhjaPuu();
        luoTestiEsiintymat();
        puu2.luoKeko(esiintymat);
    }
    
    private void luoTyhjaPuu() {
        puu2 = new HuffmanPuu();
    }
    
    private void luoTestiEsiintymat() {
        esiintymat = new HashMap<>();
        esiintymat.put("a", 3);
        esiintymat.put("b", 2);
    }
}
