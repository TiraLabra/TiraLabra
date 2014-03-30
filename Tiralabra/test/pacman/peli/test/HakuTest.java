/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.peli.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.peli.Haku;
import pacman.peli.Pacman;

/**
 *
 * @author Hanna
 */
public class HakuTest {

    private Pacman peli;
    private Haku haku;
    private Pelialusta alusta;

    public HakuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.haku = new Haku();
        alusta = new Pelialusta(19, 21);
        alusta.luoPelialusta();
        this.peli = new Pacman();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void alustaaOikeinEtaisyydet() {
        haku.alustus(this.peli.getAlusta(), this.peli.getAlusta().getPeliruutu(9, 9));
        assertEquals(5, this.peli.getAlusta().getPeliruutu(12, 11).getEtaisyysMaaliin());
        assertEquals(16, this.peli.getAlusta().getPeliruutu(17, 1).getEtaisyysMaaliin());
    }

    @Test
    public void laskeeOikeinEtaisyyden() {
        int tulos = haku.etaisyys(this.peli.getAlusta().getPeliruutu(9, 9), this.peli.getAlusta().getPeliruutu(12, 11));
        assertEquals(5, tulos);
    }

    @Test
    public void selvittaaOikeinRuudun() {
        Peliruutu siirto = haku.aStar(this.peli.getAlusta().getPeliruutu(8, 9), this.peli.getAlusta().getPeliruutu(9, 11), this.peli.getAlusta());
        assertEquals(this.peli.getAlusta().getPeliruutu(9, 9), siirto);
        siirto = haku.aStar(this.peli.getAlusta().getPeliruutu(15, 16), this.peli.getAlusta().getPeliruutu(12, 11), this.peli.getAlusta());
        assertEquals(this.peli.getAlusta().getPeliruutu(15, 17), siirto);
    }

    @Test
    public void oikeaParasSumma() {
        haku.aStar(alusta.getPeliruutu(15, 9), alusta.getPeliruutu(17, 9), alusta);
        assertEquals(2, haku.getParasSumma());
    }

    @Test
    public void oikeaReitti() {
        haku.aStar(alusta.getPeliruutu(14, 13), alusta.getPeliruutu(17, 13), alusta);
        
        Peliruutu[] reitti = haku.getParasReitti();
        assertEquals("(14,13)", reitti[0].toString());
        assertEquals("(15,13)", reitti[1].toString());
        assertEquals("(16,13)", reitti[2].toString());
        assertEquals("(17,13)", reitti[3].toString());
        assertEquals(4, reitti.length);
    }

}
