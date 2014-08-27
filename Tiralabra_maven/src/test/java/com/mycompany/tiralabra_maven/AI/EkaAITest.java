
package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.App;
import com.mycompany.tiralabra_maven.PelaajaTyyppi;
import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.PeliOhjain;
import com.mycompany.tiralabra_maven.Pelilauta;
import com.mycompany.tiralabra_maven.Siirto;
import com.mycompany.tiralabra_maven.gui.Kayttoliittyma;
import com.mycompany.tiralabra_maven.gui.Paivitettava;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class EkaAITest {
    private PeliOhjain peliOhjain;
    private EkaAI AI;
    
    
    public EkaAITest() {
    }
    
    @Before
    public void setUp() {
        peliOhjain = new PeliOhjain();
        peliOhjain.uusiPeli(PelaajaTyyppi.EKAAI, PelaajaTyyppi.EKAAI);
        AI = (EkaAI) peliOhjain.getPeli().getValkoinen();
    }
    
//    @Test
//    public void seuraavaSiirtoPalauttaaOikeanSiirronPelinAlussa() {
//        Siirto siirto = AI.seuraavaSiirto(peliOhjain.getSallitutSiirrot());
//        assertEquals(4, siirto.getLoppuRivi());
//        assertEquals(1, siirto.getLoppuSarake());
//    }


}
