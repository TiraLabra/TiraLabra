package Toteutus.Huffman;

import Apuvalineet.TekstinLukija;
import Tietorakenteet.Solmu;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BittiEsityksetTest {
    private BittiEsitykset esitykset;
    private BittiEsitykset esitykset2;
    private Solmu solmu;
    
    @Before
    public void setUp() {
        alustaBittiEsitykset();
    }
    
    private void alustaBittiEsitykset() {
        esitykset = new BittiEsitykset();
        
        this.solmu = new Solmu(6);
        solmu.setVasen(new Solmu("a", 3));
        solmu.setOikea(new Solmu(3));
        
        Solmu oikea = solmu.getOikea();
        oikea.setVasen(new Solmu("b", 2));
        oikea.setOikea(new Solmu("c", 1));
        
        esitykset.muodostaMerkeilleBittiEsitykset(solmu, "");
    }
    

    @Test
    public void esityksetTasmaavatPuussa1() {
        assertEquals(esitykset.getEsitykset().getArvo("a"), (char) 0 + "");
        assertEquals(esitykset.getEsitykset().getArvo("b"), (char) 1 + "" + (char) 0 + "");
        assertEquals(esitykset.getEsitykset().getArvo("c"), (char) 1 + "" + (char) 1 + "");
    }
    

    @Test
    public void huffmanPuunTekstiEsitys() {
        StringBuilder teksti = new StringBuilder();
        for (String avain : esitykset.getEsitykset().getAvaimet()) {
            teksti.append(avain);
            teksti.append(esitykset.getEsitykset().getArvo(avain));
        }
        
        teksti.append((char) 127);
        teksti.append((char) 127);
        
        assertEquals(teksti.toString(), esitykset.huffmanPuunTekstiEsitys());
        assertEquals("a" + (char) 0 + "" + "b" + (char) 1 + "" + (char) 0 + "" + "c" + (char) 1 + "" + (char) 1 + "" + (char) 127 + "" + (char) 127 + "",
                    esitykset.huffmanPuunTekstiEsitys());
    }
    
    @Test
    public void bittijonoSamanPituinen() throws FileNotFoundException {
        TekstinLukija lukija = new TekstinLukija();
        lukija.lueTiedosto("HuffmanCodingtest.txt");
        
        HuffmanPuu puu = new HuffmanPuu();
        puu.muodostaHuffmanPuu(lukija.getEsiintymat());
        
        
        esitykset2 = new BittiEsitykset();
        esitykset2.muodostaMerkeilleBittiEsitykset(puu.getKeko().getSolmut()[0], "");
        
        String[] avaimet = esitykset2.getEsitykset().getAvaimet();
        int maara = 0;
        
        for (String avain : avaimet) {
            maara += Integer.parseInt(lukija.getEsiintymat().getArvo(avain)) * esitykset2.getEsitykset().getArvo(avain).length();
            
        }
        
        assertEquals(135, maara);
    }
}