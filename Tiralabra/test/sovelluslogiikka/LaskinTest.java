package sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaskinTest {

    Laskin laskin;

    @Before
    public void setUp() {
        laskin = new Laskin();
    }

    @Test
    public void yhteenlaskuToimiiSulkeillaJaIlman() {
        assertEquals(1047, laskin.ratkaiseLaskutoimitus("(2+1000)+45"));
        assertEquals(62, laskin.ratkaiseLaskutoimitus("53+9"));
    }

    @Test
    public void vahennyslaskuToimiiSulkeillaJaIlman() {
        assertEquals(11, laskin.ratkaiseLaskutoimitus("4-(7-14)"));
        assertEquals(9999, laskin.ratkaiseLaskutoimitus("10000-1"));
    }

    @Test
    public void kertolaskuToimiiSulkeillaJaIlman() {
        assertEquals(160, laskin.ratkaiseLaskutoimitus("(4*5)*8"));
        assertEquals(1500, laskin.ratkaiseLaskutoimitus("15*100"));
    }

    @Test
    public void jakolaskuToimiiSulkeillaJaIlman() {
        assertEquals(1, laskin.ratkaiseLaskutoimitus("(100/20)/5"));
        assertEquals(228, laskin.ratkaiseLaskutoimitus("456/2"));
    }

    @Test
    public void nollallaJakaminenSyotteessaJaLaskuoperaatioidenSeurauksenaJohtaaYhdellaJakamiseen() {
        assertEquals(30, laskin.ratkaiseLaskutoimitus("30/0"));
        assertEquals(100, laskin.ratkaiseLaskutoimitus("(10*10)/(4-4)"));
    }

    @Test
    public void yhteenlaskuJaVahennyslaskuToimivatSulkeillaJaIlman() {
//        assertEquals(-12, laskin.ratkaiseLaskutoimitus("(36+2)-(57-3)+4"));
        assertEquals(-12, laskin.ratkaiseLaskutoimitus("36+2-57-3+4"));
    }

    @Test
    public void yhteenlaskuJaKertolaskuToimivatSulkeillaJaIlman() {
        assertEquals(2989, laskin.ratkaiseLaskutoimitus("4+5*(562+35)"));
        assertEquals(2849, laskin.ratkaiseLaskutoimitus("4+5*562+35"));
    }

    @Test
    public void yhteenlaskuJaJakolaskuToimivatSulkeillaJaIlman() {
        assertEquals(500, laskin.ratkaiseLaskutoimitus("10000/(4+6)/2"));
        assertEquals(2503, laskin.ratkaiseLaskutoimitus("10000/4+6/2"));
    }

    @Test
    public void vahennyslaskuJaKertolaskuToimivatSulkeillaJaIlman() {
//        assertEquals(, laskin.ratkaiseLaskutoimitus(""));
//        assertEquals(, laskin.ratkaiseLaskutoimitus(""));
    }

    @Test
    public void vahennyslaskuJaJakolaskuToimivatSulkeillaJaIlman() {
//        assertEquals(, laskin.ratkaiseLaskutoimitus(""));
//        assertEquals(, laskin.ratkaiseLaskutoimitus(""));
    }

    @Test
    public void kertolaskuJaJakolaskuToimivatSulkeillaJaIlman() {
//        assertEquals(, laskin.ratkaiseLaskutoimitus(""));
//        assertEquals(, laskin.ratkaiseLaskutoimitus(""));
    }

    @Test
    public void kaikkiLaskuoperaatiotToimivatSulkeillaJaIlman() {
//        assertEquals(, laskin.ratkaiseLaskutoimitus(""));
//        assertEquals(, laskin.ratkaiseLaskutoimitus(""));
    }
}