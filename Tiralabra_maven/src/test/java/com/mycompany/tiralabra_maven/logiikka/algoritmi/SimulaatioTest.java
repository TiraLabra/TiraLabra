/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.logiikka.Simulaatio;
import com.mycompany.tiralabra_maven.Koordinaatit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 *
 * @author mikko
 */
public class SimulaatioTest {

    private Simulaatio simulaatio;

    public SimulaatioTest() {

    }

    @Before
    public void setUp() throws Exception {
        this.simulaatio = new Simulaatio();
    }

    @Test
    public void teeUusiRuudukkoToimii() {
        simulaatio.teeUusiRuudukko(15, 10);
        assertEquals(15, simulaatio.getLeveys());
        assertEquals(10, simulaatio.getKorkeus());
    }

    @Test
    public void getReittiAlussaNull() {
        assertNull(simulaatio.getReitti());
    }

    @Test
    public void algoritminSuorittamisenJalkeenGetReittiPalauttaaReitin() {
        simulaatio.setHidaste(0);
        simulaatio.etsiReitti();
        int laskuri = 0;
        while (!simulaatio.onkoValmis()) {
            if (laskuri > 80) {
                fail("Kesti liian kauan, jäikö simulaatio jumiin?");
            }
            laskuri++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimulaatioTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        assertTrue(simulaatio.getReitti() != null);
    }

}
