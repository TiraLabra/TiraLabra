/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.tietorakenteet.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.alusta.Peliruutu;
import pacman.peli.Pacman;
import pacman.tietorakenteet.AStar;
import pacman.tietorakenteet.Lista;

/**
 *
 * @author Hanna
 */
public class AStarTest {

    private AStar haku;
    private Pacman peli;
    private Lista sopivat;

    public AStarTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.haku = new AStar();
        this.peli = new Pacman();
        this.sopivat = new Lista();
        haku.lisaaSopivatSolmut(peli.getAlusta(), sopivat);
        haku.muunnaSopivatListaKaymattomatTaulukoksi(sopivat);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void asettaaOikeatSolmutSopiviksi() {
        assertTrue(sopivat.sisaltaa(peli.getAlusta().getPeliruutu(8, 1)));
        assertFalse(sopivat.sisaltaa(peli.getAlusta().getPeliruutu(7, 6)));
    }

    @Test
    public void kopioiSopivatOikeinKaymattomiin() {
        Peliruutu[] kaymattomat = haku.getKaymattomat();
        
        assertEquals(sopivat.getAlkio(0), kaymattomat[0]);
        assertEquals(sopivat.getAlkio(72), kaymattomat[72]);
        assertEquals(sopivat.koko(), kaymattomat.length);
    }
    
    @Test
    public void laskeeOikeinEtaisyydet() {
        int tulos = haku.etaisyys(this.peli.getAlusta().getPeliruutu(9, 9), this.peli.getAlusta().getPeliruutu(12, 11));
        
        assertEquals(5, tulos);
    }
    
    @Test
    public void alustaaOikeinEtaisyydetMaaliin() {
        haku.alustus(peli.getAlusta().getPeliruutu(9, 9));
        
        assertEquals(5, this.peli.getAlusta().getPeliruutu(12, 11).getEtaisyysMaaliin());
        assertEquals(16, this.peli.getAlusta().getPeliruutu(17, 1).getEtaisyysMaaliin());
    }
    
    @Test
    public void selvittaaOikeinReitin1() {
        haku.astar(peli.getAlusta(), this.peli.getAlusta().getPeliruutu(8, 9), this.peli.getAlusta().getPeliruutu(9, 11));
        Peliruutu[] reitti = haku.getReitti();
        Peliruutu siirto = reitti[reitti.length-1];
        
        assertEquals(peli.getAlusta().getPeliruutu(9, 9), siirto); 
    }
    
    @Test
    public void selvittaaOikeinReitin2() {
        haku.astar(peli.getAlusta(), this.peli.getAlusta().getPeliruutu(15, 16), this.peli.getAlusta().getPeliruutu(12, 11));
        Peliruutu[] reitti = haku.getReitti();
        Peliruutu siirto = reitti[reitti.length-1];
        
        assertEquals(this.peli.getAlusta().getPeliruutu(15, 17), siirto);
    }
    
    @Test
    public void oikeaReittiJaReittiMuodostunutOikein() {
        haku.astar(peli.getAlusta(), peli.getAlusta().getPeliruutu(14, 13), peli.getAlusta().getPeliruutu(17, 13));
        Peliruutu[] reitti = haku.getReitti();
        Lista r = reittiListaksi(reitti);
        
        assertEquals(3, reitti.length);
        assertFalse(r.sisaltaa(peli.getAlusta().getPeliruutu(14, 13))); // maalia ei sisällytetä reittiin
        assertEquals(peli.getAlusta().getPeliruutu(17, 13), reitti[0]);
        assertEquals(peli.getAlusta().getPeliruutu(16, 13), reitti[1]);
        assertEquals(peli.getAlusta().getPeliruutu(15, 13), reitti[2]);
    }
    
     @Test
     public void selvittaaNaapuritOikein() {
         Lista naapurit = new Lista();
         haku.selvitaNaapurit(peli.getAlusta(), peli.getAlusta().getPeliruutu(12, 11), naapurit);
         
         assertEquals(3, naapurit.koko());
     }

    private Lista reittiListaksi(Peliruutu[] reitti) {
        Lista r = new Lista();
        for (Peliruutu reitti1 : reitti) {
            r.lisaa(reitti1);
        }
        return r;
    }
}
