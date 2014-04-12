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
import pacman.alusta.Peliruutu;
import pacman.hahmot.Man;
import pacman.hahmot.Suunta;
import pacman.peli.HaamujenKasittelija;
import pacman.peli.Pacman;

/**
 *
 * @author Hanna
 */
public class HaamujenKasittelijaTest {

    private HaamujenKasittelija kasittelija;
    private Pacman peli;

    public HaamujenKasittelijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        peli = new Pacman();
        kasittelija = peli.getKasittelija();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void asetetaankoHaamuHeikoksi() {
        kasittelija.heikennaHaamut();
        assertEquals("heikko", kasittelija.getCyan().getTyyppi());
        assertEquals("heikko", kasittelija.getGreen().getTyyppi());
        assertEquals("heikko", kasittelija.getMagenta().getTyyppi());
        assertEquals("heikko", kasittelija.getRed().getTyyppi());

    }

    @Test
    public void asetetaanHaamuVahvaksi() {
        kasittelija.heikennaHaamut();
        kasittelija.getGreen().setHeikkous(0);
        kasittelija.getCyan().setHeikkous(0);
        kasittelija.getMagenta().setHeikkous(0);
        kasittelija.getRed().setHeikkous(0);
        kasittelija.liikutaHaamut();
        assertEquals("vahva", kasittelija.getCyan().getTyyppi());
        assertEquals("vahva", kasittelija.getGreen().getTyyppi());
        assertEquals("vahva", kasittelija.getMagenta().getTyyppi());
        assertEquals("vahva", kasittelija.getRed().getTyyppi());
    }

    @Test
    public void kertooOikeinHeikkojenMaaran() {
        kasittelija.heikennaHaamut();
        assertEquals(4, kasittelija.tarkistaOnkoHeikkoja());
        kasittelija.getGreen().setTyyppi("vahva");
        assertEquals(3, kasittelija.tarkistaOnkoHeikkoja());
    }

    @Test
    public void liikuttaaOikeinVahvaRed() {
        liikutaRed();

    }

    private void liikutaRed() {
        assertEquals(9, kasittelija.getRed().getX());
        assertEquals(7, kasittelija.getRed().getY());
        assertTrue(peli.getAlusta().getPeliruutu(9, 7).getOnkoHaamu());
        kasittelija.liikutaHaamut();
        assertEquals(8, kasittelija.getRed().getX());
        assertEquals(7, kasittelija.getRed().getY());
        assertTrue(peli.getAlusta().getPeliruutu(8, 7).getOnkoHaamu());
    }

    @Test
    public void liikuttaaOikeinVahvaGreen() {
        liikutaGreen();
    }

    private void liikutaGreen() {
        assertEquals(10, kasittelija.getGreen().getX());
        assertEquals(9, kasittelija.getGreen().getY());
        assertTrue(peli.getAlusta().getPeliruutu(10, 9).getOnkoHaamu());
        kasittelija.liikutaHaamut();
        assertEquals(9, kasittelija.getGreen().getX());
        assertEquals(9, kasittelija.getGreen().getY());
        assertTrue(peli.getAlusta().getPeliruutu(9, 9).getOnkoHaamu());
    }

    @Test
    public void liikuttaaOikeinVahvaCyan() {
        liikutaCyan();
    }

    private void liikutaCyan() {
        assertEquals(8, kasittelija.getCyan().getX());
        assertEquals(9, kasittelija.getCyan().getY());
        assertTrue(peli.getAlusta().getPeliruutu(10, 9).getOnkoHaamu());
        kasittelija.liikutaHaamut();
        assertEquals(9, kasittelija.getCyan().getX());
        assertEquals(9, kasittelija.getCyan().getY());
        assertTrue(peli.getAlusta().getPeliruutu(9, 9).getOnkoHaamu());
    }

    @Test
    public void liikuttaaOikeinVahvaMagenta() {
        liikutaMagenta();
    }

    private void liikutaMagenta() {
        kasittelija.setMagentaMaali(peli.getAlusta().getPeliruutu(11, 7));
        assertEquals(9, kasittelija.getMagenta().getX());
        assertEquals(9, kasittelija.getMagenta().getY());
        assertTrue(peli.getAlusta().getPeliruutu(10, 9).getOnkoHaamu());
        kasittelija.liikutaHaamut();
        assertEquals(9, kasittelija.getMagenta().getX());
        assertEquals(8, kasittelija.getMagenta().getY());
        assertTrue(peli.getAlusta().getPeliruutu(9, 8).getOnkoHaamu());
        assertTrue(peli.getAlusta().getPeliruutu(9, 9).getOnkoHaamu()); // tarkistetaan, että myös ruutu josta liikkuu pois jää true, vaikka muuttaa falseksi, koska toinen haamu liikkuu siihen
    }

    @Test
    public void liikuttaaOikeinHeikkoRed() {
        kasittelija.heikennaHaamut();
        liikutaRed();

    }

    @Test
    public void liikuttaaOikeinHeikkoGreen() {
        kasittelija.heikennaHaamut();
        liikutaGreen();
    }

    @Test
    public void liikuttaaOikeinHeikkoMagenta() {
        kasittelija.heikennaHaamut();
        liikutaMagenta();
    }

    @Test
    public void liikuttaaOikeinHeikkoCyan() {
        kasittelija.heikennaHaamut();
        liikutaCyan();
    }
    
    @Test
    public void selvittaaOikeanMaalinHeikolleCyanille() {
        peli.setMan(new Man(14, 13, Suunta.OIKEA, peli.getAlusta()));
        Peliruutu maali = kasittelija.selvitaCyanMaaliHeikkona();
        assertEquals(peli.getAlusta().getPeliruutu(1, 13), maali);
    }
    
    @Test
    public void selvittaaOikeanMaalinHeikolleCyanille2() {
        peli.setMan(new Man(14, 13, Suunta.OIKEA, peli.getAlusta()));
        kasittelija.getCyan().setX(1);
        kasittelija.getCyan().setY(13);
        
        Peliruutu maali = kasittelija.selvitaCyanMaaliHeikkona();
        assertEquals(peli.getAlusta().getPeliruutu(14, 13), maali);
    }
    
    @Test
    public void selvittaaOikeanMaalinHeikolleRed() {
        peli.setMan(new Man(14, 13, Suunta.OIKEA, peli.getAlusta()));
        Peliruutu maali = kasittelija.maaliHeikolleRed();
        assertEquals(peli.getAlusta().getPeliruutu(4, 13), maali);
    }
    
    @Test
    public void selvittaaOikeanMaalinHeikolleRed2() {
        kasittelija.getRed().setX(4);
        kasittelija.getRed().setY(13);
        peli.setMan(new Man(14, 13, Suunta.OIKEA, peli.getAlusta()));
        Peliruutu maali = kasittelija.maaliHeikolleRed();
        assertEquals(peli.getAlusta().getPeliruutu(14, 13), maali);
    }
}
