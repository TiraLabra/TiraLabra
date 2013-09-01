package suorituskyky;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import osat.Laatikko;
import osat.Nelikulmio;
import tyokalut.AVLkasittelija;

/**
 *
 * @author albis
 */
public class AVLTest {
    
    public AVLTest() {
    }
    
    @Test
    public void lisaa10() {
        Random arpoja = new Random();
        AVLkasittelija puu = new AVLkasittelija();
        
        for (int i = 0; i < 999999; i++) {
            puu.AVLlisays(new Laatikko(1, 1, 1, arpoja.nextLong()), new int[1][1], new Nelikulmio(2, 2, 2));
        }
        
        puu.AVLlisays(new Laatikko(1, 1, 1, Long.MAX_VALUE), new int[1][1], new Nelikulmio(2, 2, 2));
        
        puu.etsi(puu.getJuuri(), Long.MAX_VALUE);
    }
}
