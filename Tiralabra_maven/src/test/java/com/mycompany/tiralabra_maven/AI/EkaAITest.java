
package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.App;
import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.Siirto;
import com.mycompany.tiralabra_maven.gui.Kayttoliittyma;
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
    private EkaAI AI;
    private Peli peli;
    
    public EkaAITest() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(peli);
        try {
            SwingUtilities.invokeAndWait(kayttoliittyma);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        peli.setPiirtoalusta(kayttoliittyma.getPiirtoalusta());
        peli.uusiPeli();
        AI = new EkaAI(peli);
    }

    @Test
    public void seuraavaSiirtoPalauttaaPelinEnsimmaisenSiirron() {
        assertEquals(0,AI.seuraavaSiirto().getAlkuSarake());
        assertEquals(1, AI.seuraavaSiirto().getLoppuSarake());
    }
    
}
