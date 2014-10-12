/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.logiikka.LyhinReitti;
import labyrintti.logiikka.Maapala;
import labyrintti.logiikka.Maapalarekisteri;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class LyhinReitinTestaajaTest {
    
    public LyhinReitinTestaajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void palauttaaOikeanNaapurin(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(3, 1, 1, 2, 2);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        lyhinReitti.siirraNaapuritAvoimelleListalle(maapalarekisteri.getAlku());
        lyhinReitti.siirraMaapalaSuljetulleListalle(maapalarekisteri.getAlku());
        
        assertTrue(lyhinReitti.etsiMaapalaJollaPieninKokonaisArvo().getHArvo() == 1);
    }
    
    @Test
    public void palauttaaOikeanNaapurin1(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(4, 1, 1, 3, 3);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        lyhinReitti.siirraNaapuritAvoimelleListalle(maapalarekisteri.getAlku());
        lyhinReitti.siirraMaapalaSuljetulleListalle(maapalarekisteri.getAlku());
        
        assertTrue(lyhinReitti.etsiMaapalaJollaPieninKokonaisArvo().getHArvo() == 3);
    }
    
    @Test
    public void aluksiLoppuaEiOleLoytynyt(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(4, 1, 1, 3, 3);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        assertFalse(lyhinReitti.onkoLoppuLoytynyt());
    }
    
    @Test
    public void oikeaArvoKunLoppuLoytyy(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 0, 1, 1, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        lyhinReitti.siirraNaapuritAvoimelleListalle(maapalarekisteri.getAlku());
        lyhinReitti.siirraMaapalaSuljetulleListalle(maapalarekisteri.getAlku());
        lyhinReitti.etsiMaapalaJollaPieninKokonaisArvo();
        
        
        assertTrue(lyhinReitti.onkoLoppuLoytynyt());
    }
    
    @Test
    public void oikeaMaaraNaapureitaSiirtyyAvoimelleListalle(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 0, 0, 1, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        lyhinReitti.siirraNaapuritAvoimelleListalle(maapalarekisteri.getAlku());
        
        assertTrue(lyhinReitti.getAvoinLista().getKoko() == 3);
    }
    
    @Test
    public void oikeaMaaraNaapureitaSiirtyyAvoimelleListalle1(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 0, 0, 1, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        Maapala maapala = maapalarekisteri.getMaapala(1, 0);
        maapala.asetaSeinaksi();
        
        lyhinReitti.siirraNaapuritAvoimelleListalle(maapalarekisteri.getAlku());
        
        assertTrue(lyhinReitti.getAvoinLista().getKoko() == 2);
    }
    
    @Test
    public void maapalaSiirtyySuljetulleListalleOikein(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 0, 0, 1, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        Maapala maapala = maapalarekisteri.getAlku();
        
        assertTrue(lyhinReitti.getAvoinLista().getKoko() == 1);
        assertTrue(maapala.onkoAvoimellaListalla());
        assertFalse(maapala.onkoSuljetullaListalla());
        
        lyhinReitti.siirraMaapalaSuljetulleListalle(maapala);
        
        assertTrue(lyhinReitti.getAvoinLista().getKoko() == 0);
        assertTrue(maapala.onkoSuljetullaListalla());
        assertFalse(maapala.onkoAvoimellaListalla());
        
    }
    
    @Test
    public void maapalaJollaPieninHArvoPalautuuOikein(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 0, 0, 1, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        Maapala maapala = lyhinReitti.etsiMaapalaJollaPieninKokonaisArvo();
        
        assertTrue(maapala.getHArvo() == maapalarekisteri.getAlku().getHArvo());
        
        
    }
    
    @Test
    public void maapalaJollaPieninHArvoPalautuuOikein1(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 0, 0, 0, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        lyhinReitti.siirraNaapuritAvoimelleListalle(maapalarekisteri.getAlku());
        lyhinReitti.siirraMaapalaSuljetulleListalle(maapalarekisteri.getAlku());
        
        assertTrue(lyhinReitti.etsiMaapalaJollaPieninKokonaisArvo().getHArvo() == 0);
        
        
    }
    
    @Test
    public void avoimenListanKokoOnAluksiOikea(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 0, 0, 0, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        assertTrue(lyhinReitti.getAvoinLista().getKoko() == 1);
        
        lyhinReitti.siirraMaapalaSuljetulleListalle(maapalarekisteri.getAlku());
        
        assertTrue(lyhinReitti.getAvoinLista().getKoko() == 0);
    }
    
}
