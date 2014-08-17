
package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.App;
import com.mycompany.tiralabra_maven.Peli;
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
    private Peli peli;
    private Pelilauta lauta;
    private EkaAI AI;
    private Paivitettava paivitettava;
    
    
    public EkaAITest() {
    }
    
    @Before
    public void setUp() {
        lauta = new Pelilauta();
        peli = new Peli();
        paivitettava = new Paivitettava() {

            @Override
            public void paivita() {
                
            }

            @Override
            public void naytaViesti(String viesti) {
                
            }
        };
        peli.setPaivitettava(paivitettava);
        AI = new EkaAI(peli);
        

    }
    
    @Test
    public void seuraavaSiirtoPalauttaaOikeanSiirronPelinAlussa() {
        peli.uusiPeli();
        lauta.asetaNappulatAlkuasetelmaan();
        Siirto siirto = AI.seuraavaSiirto();
        assertEquals(4, siirto.getLoppuRivi());
        assertEquals(1, siirto.getLoppuSarake());
    }


}
