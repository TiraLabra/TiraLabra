/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.AlgoritmiTyyppi;
import com.mycompany.tiralabra_maven.HeuristiikkaTyyppi;
import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
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

    @Test
    public void lopetaReitinEtsiminenLopettaaAlgoritminSuorituksen() {
        simulaatio.setHidaste(1);
        simulaatio.etsiReitti();
        simulaatio.lopetaReitinEtsiminen();
        assertFalse(simulaatio.onkoSimulaatioKaynnissa());
    }

    @Test
    public void algoritminTyypinAsettaminenToimii() {
        simulaatio.asetaAlgoritmi(AlgoritmiTyyppi.DIJKSTRA);
        assertEquals(AlgoritmiTyyppi.DIJKSTRA, simulaatio.getAlgoritmiTyyppi());
    }

    @Test
    public void hidasteenAsettaminenToimii() {
        simulaatio.setHidaste(303);
        assertEquals(303, simulaatio.getHidaste());
    }

    @Test
    public void alkuPisteenAsettaminenToimii() {
        simulaatio.setAlkuPiste(new Koordinaatit(1, 1));
        assertEquals(new Koordinaatit(1, 1), simulaatio.getAlkuPiste());
    }

    @Test
    public void maaliPisteenAsettaminenToimii() {
        simulaatio.setMaali(new Koordinaatit(1, 1));
        assertEquals(new Koordinaatit(1, 1), simulaatio.getMaali());
    }
    
    @Test
    public void ruudunAsettaminenToimii() {
        simulaatio.setRuutu(3, 5, Ruutu.RUOHO);
        assertEquals(Ruutu.RUOHO, simulaatio.getMaailmaRuutu(3, 5));
        assertEquals(Ruutu.RUOHO, simulaatio.getRuudukko()[5][3]);
    }
    
    @Test
    public void ruudunTilatOvatNullJosAlgoritmiEiOleKaynnissa() {
        assertNull(simulaatio.getTilaRuutu(0, 0));
    }
    
    @Test
    public void algoritminAsettamatRuutujenTilatToimivatOikein() {
        simulaatio.asetaAlgoritmi(AlgoritmiTyyppi.GREEDY_BEST_FIRST);
        simulaatio.setHidaste(0);
        simulaatio.setAlkuPiste(new Koordinaatit(3, 3));
        simulaatio.setMaali(new Koordinaatit(6, 3));
        simulaatio.etsiReitti();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }
        assertEquals(RuudunTila.REITTI, simulaatio.getTilaRuutu(3, 3));
    }
    
    @Test
    public void vinottainLiikkumisenAsettaminenSallituksiToimii() {
        simulaatio.asetaVinottainLiikkuminenSallituksi(true);
        assertTrue(simulaatio.saakoLiikkuaVinottain());
        simulaatio.asetaVinottainLiikkuminenSallituksi(false);
        assertFalse(simulaatio.saakoLiikkuaVinottain());
    }
    
    @Test
    public void heuristiikanAsettaminenToimii() {
        simulaatio.asetaHeuristiikka(HeuristiikkaTyyppi.DIAGONAALINEN);
        assertEquals(HeuristiikkaTyyppi.DIAGONAALINEN, simulaatio.getHeuristiikkaTyyppi());
    }
    

}
