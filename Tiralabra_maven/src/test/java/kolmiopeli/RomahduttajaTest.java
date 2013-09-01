package kolmiopeli;

import java.awt.Color;
import java.util.ArrayList;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.Ruudukko;
import kolmiopeli.logiikka.tiralabraAlgoritmit.Romahduttaja;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class RomahduttajaTest {

    static Kolmio[][] testiRuudukkoRomahtavatKeskella = {{new Kolmio(Color.MAGENTA, 0, 0), new Kolmio(Color.RED, 0, 1), new Kolmio(Color.BLUE, 0, 2), new Kolmio(Color.RED, 0, 3), new Kolmio(Color.RED, 0, 4)},
        {new Kolmio(Color.GREEN, 1, 0), new Kolmio(Color.CYAN, 1, 1), new Kolmio(Color.MAGENTA, 1, 2), new Kolmio(Color.MAGENTA, 1, 3), new Kolmio(Color.RED, 1, 4)},
        {new Kolmio(Color.CYAN, 2, 0), null, null, new Kolmio(Color.MAGENTA, 2, 3), new Kolmio(Color.BLUE, 2, 4)},
        {new Kolmio(Color.CYAN, 3, 0), new Kolmio(Color.MAGENTA, 3, 1), null, new Kolmio(Color.MAGENTA, 3, 3), new Kolmio(Color.CYAN, 3, 4)},};
    static Kolmio[][] testiRuudukkoRomahtavatYlhaalla = {{null, null, null, new Kolmio(Color.RED, 0, 3), new Kolmio(Color.RED, 0, 4)},
        {new Kolmio(Color.GREEN, 1, 0), new Kolmio(Color.CYAN, 1, 1), new Kolmio(Color.MAGENTA, 1, 2), new Kolmio(Color.MAGENTA, 1, 3), new Kolmio(Color.RED, 1, 4)},
        {new Kolmio(Color.CYAN, 2, 0), new Kolmio(Color.CYAN, 2, 0), new Kolmio(Color.CYAN, 2, 0), new Kolmio(Color.MAGENTA, 2, 3), new Kolmio(Color.BLUE, 2, 4)},
        {new Kolmio(Color.CYAN, 3, 0), new Kolmio(Color.MAGENTA, 3, 1), new Kolmio(Color.CYAN, 2, 0), new Kolmio(Color.MAGENTA, 3, 3), new Kolmio(Color.CYAN, 3, 4)},};

    private Ruudukko lauta;
    private Romahduttaja romahduttaja;

    public RomahduttajaTest() {
        lauta = new Ruudukko(4, 5);
    }

    @Before
    public void setUp() {
    }
    
    @Test
    public void alkutesti() {
        lauta.setRuudukko(testiRuudukkoRomahtavatKeskella);
        romahduttaja = new Romahduttaja(lauta.getRuudukko(), true);
        ArrayList<Koordinaatti> romahtavat = new ArrayList<Koordinaatti>();
        romahtavat.add(new Koordinaatti(2, 1));
        Kolmio k1 = lauta.getRuudukko()[1][1];
        romahtavat.add(new Koordinaatti(2, 2));
        Kolmio k2 = lauta.getRuudukko()[1][3];
        romahtavat.add(new Koordinaatti(3, 2));
        Kolmio k3 = lauta.getRuudukko()[2][3];
        romahduttaja.romahduta(romahtavat);
        
        assertTrue("k1 vaarin", lauta.getRuudukko()[2][1] == k1);
        assertTrue("k2 vaarin", lauta.getRuudukko()[2][2] == k2);
        assertTrue("k3 vaarin", lauta.getRuudukko()[3][2] == k3);
        
    }
    
    @Test
    public void romahtajaLuoUusiaYlariville() {
        lauta.setRuudukko(testiRuudukkoRomahtavatYlhaalla);
        romahduttaja = new Romahduttaja(lauta.getRuudukko(), true);
        ArrayList<Koordinaatti> romahtavat = new ArrayList<Koordinaatti>();
        romahtavat.add(new Koordinaatti(0, 0));
        romahtavat.add(new Koordinaatti(0, 1));
        romahtavat.add(new Koordinaatti(0, 2));
        romahduttaja.romahduta(romahtavat);
        
        assertTrue("Ylarivilla ei arvottu uutta", lauta.getRuudukko()[0][0] != null);
        assertTrue("Ylarivilla ei arvottu uutta", lauta.getRuudukko()[0][1] != null);
        assertTrue("Ylarivilla ei arvottu uutta", lauta.getRuudukko()[0][2] != null);
        
    }
    

    
    
}
