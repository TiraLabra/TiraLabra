/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman.hahmot.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.hahmot.Cyan;
import pacman.hahmot.Man;
import pacman.hahmot.Suunta;

/**
 *
 * @author Hanna
 */
public class CyanTest {
    private Cyan cyan;
    private Pelialusta alusta;
    private Man man;
    
    public CyanTest() {
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
        this.cyan = new Cyan(9, 8, Suunta.ALAS, "green", alusta);
        this.cyan.luoHaamuAlustalle();
        man = new Man(9, 11, Suunta.OIKEA, alusta);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void selvittaaOikeinMaalinKunRuutuMahdollinen() {
        Peliruutu maali = cyan.selvitaMaaliCyan(man);
        assertEquals("(12,11)", maali.toString());
    }

    @Test
    public void selvittaaOikeinMaalinKunVastassaSeina() {
        man.setX(3);
        man.setY(9);
        man.setSuunta(Suunta.VASEN);
        Peliruutu maali = cyan.selvitaMaaliCyan(man);
        assertEquals("(3,9)", maali.toString());
    }

    @Test
    public void selvittaaOikeinMaalinKunHuonoRuutu() {
        man.setX(17);
        man.setY(9);
        Peliruutu maali = cyan.selvitaMaaliCyan(man);
        assertEquals("(17,9)", maali.toString());
    }

    @Test
    public void selvittaaOikeinMaalinJosHaamuMaaliRuudussa() {
        man.setX(14);
        man.setY(9);
        cyan.setX(17);
        cyan.setY(9);
        Peliruutu maali = cyan.selvitaMaaliCyan(man);
        assertEquals("(16,9)", maali.toString());
    }
}
