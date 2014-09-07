/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.AlgoritmiTyyppi;
import org.junit.Before;
import org.junit.Test;

public class DijkstraAlgoritmiTest {

    private AlgoritmiTest testi;

    @Before
    public void setUp() {
        testi = new AlgoritmiTest(AlgoritmiTyyppi.DIJKSTRA);
    }

    @Test
    public void algoritmiLoytaaSuoranReitinPerille() {
        testi.algoritmiLoytaaSuoranReitinPerille();
    }

    @Test
    public void algoritmiLoytaaReitinPerilleNopeahkosti() {
        testi.algoritmiLoytaaReitinPerilleNopeasti(210, 2000);
    }

}
