package Toteutus.Huffman;

import Tietorakenteet.Solmu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BittiEsityksetTest {
    private BittiEsitykset esitykset;
    private Solmu solmu;
    
    @Before
    public void setUp() {
        this.esitykset = new BittiEsitykset();
        alustaBittiEsitykset();
    }
    
    private void alustaBittiEsitykset() {
        this.solmu = new Solmu(6);
        solmu.setVasen(new Solmu("a", 3));
        solmu.setOikea(new Solmu(3));
        
        Solmu oikea = solmu.getOikea();
        oikea.setVasen(new Solmu("b", 2));
        oikea.setOikea(new Solmu("c", 1));
        
        esitykset.muodostaMerkeilleBittiEsitykset(solmu, "");
    }
    
    @Test
    public void esityksetTasmaavatPuussa() {
        assertEquals(esitykset.getEsitykset().get("a"), (char) 0 + "");
        assertEquals(esitykset.getEsitykset().get("b"), (char) 1 + "" + (char) 0);
        assertEquals(esitykset.getEsitykset().get("c"), (char) 1 + "" + (char) 1);
    }
    
    @Test
    public void huffmanPuunTekstiEsitys() {
        StringBuilder teksti = new StringBuilder();
        for (String avain : esitykset.getEsitykset().keySet()) {
            teksti.append(avain);
            teksti.append(esitykset.getEsitykset().get(avain));
        }
        
        teksti.append((char) 127);
        teksti.append((char) 127);
        
        assertEquals(teksti.toString(), esitykset.huffmanPuunTekstiEsitys());
    }
}
