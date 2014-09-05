/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.AlgoritmiTyyppi;
import org.junit.*;

/**
 *
 * @author mikko
 */
public class AStarAlgoritmiTest {
    private AlgoritmiTest testi;
    
    @Before
    public void setUp() {
        testi = new AlgoritmiTest(AlgoritmiTyyppi.A_STAR);
    }

    @Test
    public void algoritmiLoytaaSuoranReitinPerille() {
        testi.algoritmiLoytaaSuoranReitinPerille();
    }

    @Test
    public void algoritmiLoytaaReitinPerilleNopeasti() {
        testi.algoritmiLoytaaReitinPerilleNopeasti(210, 1500);
    }

    @Test
    public void algoritmiOsaaKiertaaSeinan() {
        testi.algoritmiOsaaKiertaaSeinan();
    }

    @Test
    public void algoritmiLoytaaLyhimmanReitinEikaTutkiVaaraanSuuntaan() {
        testi.algoritmiLoytaaLyhimmanReitinEikaTutkiVaaraanSuuntaan();
    }

}
