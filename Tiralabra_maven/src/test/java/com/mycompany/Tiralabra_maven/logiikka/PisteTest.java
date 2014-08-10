package com.mycompany.Tiralabra_maven.logiikka;

import com.mycompany.Tiralabra_maven.logiikka.Piste;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hannu
 */
public class PisteTest {

    Piste piste;

    public PisteTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        piste = new Piste(2, -3);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void toimiikoPisteenLuominen() {
        piste = new Piste(2, -3);
//        jakolasku.setOperandi2(new KokonaisLukuOperandi(7));
//        Tehtava jakolaskuTehtava = new Tehtava(jakolasku);
        assertEquals(-3, piste.y);
    }

}
