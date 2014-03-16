package pacman.hahmot.test;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pacman.alusta.Pelialusta;
import pacman.hahmot.Man;
import pacman.hahmot.Suunta;
import pacman.peli.Pacman;

public class ManTest {

    private Pelialusta alusta;
    private Man man;
    private Pacman peli;

    public ManTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        alusta = new Pelialusta(19, 21);
        alusta.luoPelialusta();
        man = new Man(9, 11, Suunta.ALAS, alusta);
        man.luoManAlustalle();
        peli = new Pacman();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void manLuodaanOikein() {
        assertEquals(9, man.getX());
        assertEquals(11, man.getY());
        assertEquals(Suunta.ALAS, man.getSuunta());
        assertEquals(3, man.getElamat());
    }

    @Test
    public void manEiLiikuAlasSeinanLapi() {
        man.liiku(false, peli);
        assertEquals(9, man.getX());
        assertEquals(11, man.getY());
        assertTrue(alusta.getPeliruutu(9, 11).getOnkoMan());
        assertFalse(alusta.getPeliruutu(9, 12).getOnkoMan());
    }

    @Test
    public void manEiLiikuYlosSeinanLapi() {
        man.setSuunta(Suunta.YLOS);
        man.liiku(false, peli);
        assertEquals(9, man.getX());
        assertEquals(11, man.getY());
    }

    @Test
    public void manEiLiikuVasenSeinanLapi() {
        man = new Man(6, 11, Suunta.VASEN, alusta);
        man.liiku(false, peli);
        assertEquals(6, man.getX());
        assertEquals(11, man.getY());
    }

    @Test
    public void manEiLiikuOikeaSeinanLapi() {
        man = new Man(12, 11, Suunta.OIKEA, alusta);
        man.liiku(false, peli);
        assertEquals(12, man.getX());
        assertEquals(11, man.getY());
    }

    @Test
    public void manLiikkuuOikeinVasen() {
        man.setSuunta(Suunta.VASEN);
        man.liiku(false, peli);
        assertEquals(8, man.getX());
        assertEquals(11, man.getY());
        assertTrue(alusta.getPeliruutu(8, 11).getOnkoMan());
        assertFalse(alusta.getPeliruutu(9, 11).getOnkoMan());
    }

    @Test
    public void manLiikkuuOikeinOikea() {
        man.setSuunta(Suunta.OIKEA);
        man.liiku(false, peli);
        assertEquals(10, man.getX());
        assertEquals(11, man.getY());
        assertTrue(alusta.getPeliruutu(10, 11).getOnkoMan());
        assertFalse(alusta.getPeliruutu(9, 11).getOnkoMan());
    }

    @Test
    public void manLiikkuuOikeinAlas() {
        man = new Man(6, 11, Suunta.ALAS, alusta);
        man.liiku(false, peli);
        assertEquals(6, man.getX());
        assertEquals(12, man.getY());
        assertTrue(alusta.getPeliruutu(6, 12).getOnkoMan());
        assertFalse(alusta.getPeliruutu(6, 11).getOnkoMan());
    }

    @Test
    public void manLiikkuuOikeinYlos() {
        man = new Man(6, 11, Suunta.YLOS, alusta);
        man.liiku(false, peli);
        assertEquals(6, man.getX());
        assertEquals(10, man.getY());
        assertTrue(alusta.getPeliruutu(6, 10).getOnkoMan());
        assertFalse(alusta.getPeliruutu(6, 11).getOnkoMan());
    }
    
    @Test
    public void manPalaaAlkuunOikein() {
        man = new Man(1, 1, Suunta.YLOS, alusta);
        man.palaaAlkuun();
        assertEquals(9, man.getX());
        assertEquals(11, man.getY());
    }
    
    @Test
    public void vahennetaanElamatOikein() {
        man.vahennaElama();
        assertEquals(2, man.getElamat());
    }
    
    @Test
    public void liikkuuOikeinAlasHeikkoSeina() {
        man.liiku(true, peli);
        assertEquals(9, man.getX());
        assertEquals(11, man.getY());
    }
    
    @Test
    public void liikkuuOikeinYlosHeikkoSeina() {
        man.setSuunta(Suunta.YLOS);
        man.liiku(true, peli);
        assertEquals(9, man.getX());
        assertEquals(11, man.getY());
    }
    
    @Test
    public void liikkuuOikeinOikeaHeikkoSeina() {
        man.setX(12);
        man.setSuunta(Suunta.OIKEA);
        man.liiku(true, peli);
        
        assertEquals(12, man.getX());
        assertEquals(11, man.getY());
    }
    
    @Test
    public void liikkuuOikeinVasenHeikkoSeina() {
        man.setX(6);
        man.setSuunta(Suunta.VASEN);
        man.liiku(true, peli);
        
        assertEquals(6, man.getX());
        assertEquals(11, man.getY());
    }
    
    @Test
    public void liikuOikeinAlasHeikko() {
        man.setX(4);
        man.setY(10);
        man.liiku(true, peli);
        
        assertEquals(4, man.getX());
        assertEquals(12, man.getY());
    }
    
    @Test
    public void liikuOikeinYlosHeikko() {
        man.setX(6);
        man.setSuunta(Suunta.YLOS);
        man.liiku(true, peli);
        
        assertEquals(6, man.getX());
        assertEquals(9, man.getY());
    }
    
    @Test
    public void liikuOikeinVasenHeikko() {
        man.setSuunta(Suunta.VASEN);
        man.liiku(true, peli);
        
        assertEquals(7, man.getX());
        assertEquals(11, man.getY());
    }
    
    @Test
    public void liikuOikeinOikeaHeikko() {
        man.setSuunta(Suunta.OIKEA);
        man.liiku(true, peli);
        
        assertEquals(11, man.getX());
        assertEquals(11, man.getY());
    }

    @Test
    public void liikuOikeinRisteysJosToinenSeinaAlasHeikko() {
        man.setX(6);
        man.setY(12);
        man.liiku(true, peli);
        
        assertEquals(6, man.getX());
        assertEquals(13, man.getY());
    }
    
    @Test
    public void liikuOikeinRisteysJosToinenSeinaYlosHeikko() {
        man.setX(6);
        man.setY(8);
        man.setSuunta(Suunta.YLOS);
        man.liiku(true, peli);
        
        assertEquals(6, man.getX());
        assertEquals(7, man.getY());
    }
    
    @Test
    public void liikuOikeinRisteysJosToinenSeinaVasenHeikko() {
        man.setX(7);
        man.setSuunta(Suunta.VASEN);
        man.liiku(true, peli);
        
        assertEquals(6, man.getX());
        assertEquals(11, man.getY());
    }
    
    @Test
    public void liikuOikeinRisteysJosToinenSeinaOikeaHeikko() {
        man.setX(11);
        man.setSuunta(Suunta.OIKEA);
        man.liiku(true, peli);
        
        assertEquals(12, man.getX());
        assertEquals(11, man.getY());
    }
    
    @Test
    public void liikuOikeinRisteysAlasHeikko() {
        man.setX(5);
        man.setY(14);
        man.liiku(true, peli);
        
        assertEquals(5, man.getX());
        assertEquals(15, man.getY());
    }
    
    @Test
    public void liikuOikeinRisteysYlosHeikko() {
        man.setX(5);
        man.setY(16);
        man.setSuunta(Suunta.YLOS);
        man.liiku(true, peli);
        
        assertEquals(5, man.getX());
        assertEquals(15, man.getY());
    }
    
    @Test
    public void liikuOikeinRisteysVasenHeikko() {
        man.setX(6);
        man.setY(13);
        man.setSuunta(Suunta.VASEN);
        man.liiku(true, peli);
        
        assertEquals(5, man.getX());
        assertEquals(13, man.getY());
    }
    
    @Test
    public void liikuOikeinRisteysOikeaHeikko() {
        man.setX(3);
        man.setY(13);
        man.setSuunta(Suunta.OIKEA);
        man.liiku(true, peli);
        
        assertEquals(4, man.getX());
        assertEquals(13, man.getY());
    }
}
