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
import pacman.peli.Lista;

/**
 *
 * @author hhkopper@cs
 */
public class ListaTest {

    private Lista list;

    public ListaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        list = new Lista();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luoOikeanKokoisenAluksi() {
        assertEquals(0, list.koko());
    }

    @Test
    public void lisaaOikeinYhden() {
        list.lisaa(new Peliruutu(9, 9));
        assertEquals(1, list.koko());
        assertEquals("(9,9)", list.getAlkio(0).toString());
    }
    
    @Test
    public void lisaaOikeinUseamman() {
        list.lisaa(new Peliruutu(9, 9));
        list.lisaa(new Peliruutu(10, 9));
        list.lisaa(new Peliruutu(8, 9));
        list.lisaa(new Peliruutu(1, 9));
        list.lisaa(new Peliruutu(9, 3));
        list.lisaa(new Peliruutu(9, 9));
        list.lisaa(new Peliruutu(10, 9));
        list.lisaa(new Peliruutu(8, 9));
        list.lisaa(new Peliruutu(1, 9));
        list.lisaa(new Peliruutu(9, 3));
        
        assertEquals(10, list.koko());
        assertEquals("(9,9)", list.getAlkio(0).toString());
        assertEquals("(10,9)", list.getAlkio(1).toString());
        assertEquals("(1,9)", list.getAlkio(3).toString());
        assertEquals("(9,3)", list.getAlkio(4).toString());
        assertEquals("(8,9)", list.getAlkio(7).toString());
    }
    
    @Test
    public void katsooOikeinSisaltaakoRuudun() {
        list.lisaa(new Peliruutu(9, 9));
        list.lisaa(new Peliruutu(10, 9));
        list.lisaa(new Peliruutu(8, 9));
        
        Peliruutu ruutu = new Peliruutu(10, 9);
        
        assertEquals(true, list.sisaltaa(ruutu));
    }
    
    @Test
    public void katsooOikeinEttaEiSisallaRuutu() {
        list.lisaa(new Peliruutu(9, 9));
        list.lisaa(new Peliruutu(10, 9));
        list.lisaa(new Peliruutu(8, 9));
        
        Peliruutu ruutu = new Peliruutu(11, 11);
        assertEquals(false, list.sisaltaa(ruutu));
    }
    
    

}
