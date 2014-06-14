package Toteutus.Huffman;

import Tietorakenteet.HajTaulu;
import Tietorakenteet.MinKeko;
import Tietorakenteet.Solmu;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HuffmanPuuTest {
    private HuffmanPuu puu;
    private HuffmanPuu puu2;
    private HajTaulu esiintymat;
    private BittiEsitykset esitykset;
    
    @Before
    public void setUp() {
        this.puu = new HuffmanPuu();
        puu.muodostaHuffmanPuu(alustaEsiintymat());
        esitykset = new BittiEsitykset();
        esitykset.muodostaMerkeilleBittiEsitykset(puu.getKeko().getSolmut()[0], "");
    }

    private HajTaulu alustaEsiintymat() {
        HajTaulu puunEsiintymat = new HajTaulu();
        puunEsiintymat.lisaa("A", Integer.toString(8));
        puunEsiintymat.lisaa("B", Integer.toString(2)); // b = g
        puunEsiintymat.lisaa("C", Integer.toString(4));
        puunEsiintymat.lisaa("D", Integer.toString(1));
        puunEsiintymat.lisaa("E", Integer.toString(2));
        
        return puunEsiintymat;
    }
    
    @Test
    public void merkillaALyhinBinaariEsitys() {
        String verr = esitykset.getEsitykset().getArvo("A");
        String A = esitykset.getEsitykset().getArvo("A");
        String nul = (char) 0 + "";
        
        for (String merkki : esitykset.getEsitykset().getAvaimet()) {
            if (! merkki.equals(nul)) {
                String bittiesitys = esitykset.getEsitykset().getArvo(merkki);
                
                if (bittiesitys.length() < verr.length()) {
                    verr = bittiesitys;
                }
            }
        }
        
        assertTrue(A.length() == verr.length());
    }
    
    @Test
    public void merkillaDPisinBinaariEsitys() {
        String verr = esitykset.getEsitykset().getArvo("D");
        String D = esitykset.getEsitykset().getArvo("D");
        String nul = "" + (char) 0;
        
        for (String merkki : esitykset.getEsitykset().getAvaimet()) {
            if (! merkki.equals(nul)) {
                String bittiesitys = esitykset.getEsitykset().getArvo(merkki);
                
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
        String[] bin = new String[esitykset.getEsitykset().getAvaimet().length];
        
        int i = 0;
        for (String arvo : esitykset.getEsitykset().getArvot()) {
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
        esiintymat = new HajTaulu();
        esiintymat.lisaa(("a"), Integer.toString(1));
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
    
    private void muodostaTestiPuuJaKeko() {
        luoTyhjaPuu();
        luoTestiEsiintymat();
        puu2.luoKeko(esiintymat);
    }
    
    private void luoTyhjaPuu() {
        puu2 = new HuffmanPuu();
    }
    
    private void luoTestiEsiintymat() {
        esiintymat = new HajTaulu();
        esiintymat.lisaa("a", Integer.toString(3));
        esiintymat.lisaa("b", Integer.toString(2));
    }
}
