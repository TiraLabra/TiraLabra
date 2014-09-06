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
public class BreadthFirstAlgoritmiTest {

    private AlgoritmiTest testi;

    @Before
    public void setUp() {
        testi = new AlgoritmiTest(AlgoritmiTyyppi.BREADTH_FIRST);
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
