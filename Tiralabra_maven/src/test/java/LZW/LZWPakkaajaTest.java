package LZW;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class LZWPakkaajaTest {
    private LZWPakkaaja pakkaaja;
    private final String n = (char) 0 + "";
    private final String y = (char) 1 + "";
    private final String ilmanEtuNollia = n+y+y+n+y+n+y+y+n+y+y+n+n+y;
    
    @Before
    public void setUp() {
        this.pakkaaja = new LZWPakkaaja();
    }
    
    @Test
    public void pakattuTeksti() {
        String pakattuna = (char) 26 + "" + (char) 217;
        assertEquals((char) 2 + pakattuna, pakkaaja.kirjoitettava(ilmanEtuNollia));
    }
    
    @Test
    public void pakattuna() {
        assertEquals((char) 26 + "" + (char) 217, pakkaaja.pakattuna(ilmanEtuNollia));
    }
}
