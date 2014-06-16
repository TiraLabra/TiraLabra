package Huffman;

import Tietorakenteet.HajTaulu;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TiedostonPakkaajaTest {
    private TiedostonPakkaaja pakkaaja;
    
    @Before
    public void setUp() {
        this.pakkaaja = new TiedostonPakkaaja();
    }
    
    @Test
    public void lisaaTekstiToimii() {
        StringBuilder kirjoitettava = new StringBuilder();
        BittiEsitykset esitykset = new BittiEsitykset(testattavatBittiEsitykset());

        StringBuilder teksti = new StringBuilder();
        for (String avain : esitykset.getEsitykset().getAvaimet()) {
            teksti.append(avain);
            teksti.append(esitykset.getEsitykset().getArvo(avain));     // a101 b00 c01 (127)(127)
        }
        
        teksti.append((char) 127);
        teksti.append((char) 127);
        teksti.append((char) 0);
        teksti.append("1010001");
        
        pakkaaja.lisaaTeksti(kirjoitettava, "1010001", esitykset);
        assertEquals(teksti.toString(), kirjoitettava.toString());
    }
    
    private HajTaulu testattavatBittiEsitykset() {
        String n = (char) 0 + "";
        String y = (char) 1 + "";
        
        HajTaulu esitykset = new HajTaulu();
        esitykset.lisaa("a", y+n+y);
        esitykset.lisaa("b", n+n);
        esitykset.lisaa("c", n+y);
        esitykset.lisaa("d", y+n+n);
        esitykset.lisaa("e", y+y);
        
        return esitykset;
    }
    
    @Test
    public void pakatuksiTekstiksi() {
        String pakattuna = pakkaaja.tekstiPakattuna(testattavatBittiEsitykset(), "cbe");
        assertEquals((char) 19 + "", pakattuna);
    }
}