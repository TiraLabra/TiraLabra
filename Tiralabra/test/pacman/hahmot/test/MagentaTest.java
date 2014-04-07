/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.hahmot.test;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.hahmot.Green;
import pacman.hahmot.Magenta;
import pacman.hahmot.Suunta;

/**
 *
 * @author Hanna
 */
public class MagentaTest {

    private Magenta magenta;
    private Pelialusta alusta;

    public MagentaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        alusta = new Pelialusta(19, 21);
        alusta.luoPelialusta();
        this.magenta = new Magenta(9, 8, Suunta.ALAS, "green", alusta);
        this.magenta.luoHaamuAlustalle();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void liikkuuOikein() {
        Peliruutu maali = magenta.selvitaMaaliMagenta(new Random());
        assertTrue(maali.getRuudunTyyppi() != 0);
        assertFalse(magenta.getKielletyt().sisaltaa(maali));
    }
}
