package LZW;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class LZWPakkaajaTest {
    private LZWPakkaaja pakkaaja;
    
    @Before
    public void setUp() {
        this.pakkaaja = new LZWPakkaaja();
    }
    
    @Test
    public void pakattuna() {
        String n = (char) 0 + "";
        String y = (char) 1 + "";
        
        String ilmanEtuNollia = n+y+y+n+y+n+y+y+n+y+y+n+n+y;
        assertEquals((char) 26 + "" + (char) 217, pakkaaja.pakattuna(ilmanEtuNollia));
    }
 
// LWZPakkaajaTest: abccabcbaa    
    
//Hajautustauluun lis‰tt‰v‰t uudet avaimet ja bin‰‰riesitykset:
//-------------------------------------------------------------
//256 - ab
//257 - bc
//258 - cc
//259 - ca
//260 - abc
//261 - cb
//262 - ba
//263 - aa
//
//
//a + b + c + c + ab + c + b + a + a
    
}
