package suorituskyky;

import logiikka.Laskuri;
import org.junit.Test;
import static org.junit.Assert.*;
import osat.Laatikko;
import osat.Nelikulmio;

/**
 *
 * @author albis
 */
public class AsettelulaskuriTest {
    
    public AsettelulaskuriTest() {
    }

    @Test
    public void testaa10Laatikko() {
        Laskuri laskuri = new Laskuri();
        
        laskuri.laske(new Laatikko(24, 39, 80, 123456789012L), new Nelikulmio(80, 120, 120));
    }
}
