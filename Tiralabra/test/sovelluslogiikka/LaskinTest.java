package sovelluslogiikka;

import ohjelma.sovelluslogiikka.Laskin;
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
        assertEquals(-12, laskin.ratkaiseLaskutoimitus("(36+2)-(57-3)+4"));
        assertEquals(-18, laskin.ratkaiseLaskutoimitus("36+2-57-3+4"));
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
        assertEquals(15018, laskin.ratkaiseLaskutoimitus("((945-6)*8-3)*2"));
        assertEquals(891, laskin.ratkaiseLaskutoimitus("945-6*8-3*2"));
    }

    @Test
    public void vahennyslaskuJaJakolaskuToimivatSulkeillaJaIlman() {
        assertEquals(-3, laskin.ratkaiseLaskutoimitus("(140-20)/(10-20)/4"));
        assertEquals(133, laskin.ratkaiseLaskutoimitus("140-20/10-20/4"));
    }

    @Test
    public void kertolaskuJaJakolaskuToimivatSulkeillaJaIlman() {
        assertEquals(7, laskin.ratkaiseLaskutoimitus("24*7/(4/2*12)"));
        assertEquals(252, laskin.ratkaiseLaskutoimitus("24*7/4/2*12"));
    }

    @Test
    public void kaikkiLaskuoperaatiotToimivatSulkeillaJaIlman() {
        assertEquals(-308, laskin.ratkaiseLaskutoimitus("((3453-23)*(98+3))/(4-11*(25+1000)/10)"));
        assertEquals(1024, laskin.ratkaiseLaskutoimitus("3453-23*98+3/4-11*25+1000/10"));
    }

    @Test
    public void syoteEiKelpaaKunSeSisaltaaEiSallitunMerkinTaiValilyonnin() {
        assertFalse(laskin.tarkastaSyote("3a+4"));
        assertFalse(laskin.tarkastaSyote("3+ 4"));
    }

    @Test
    public void syoteEiKelpaaKunSenSulutOvatEpatasapainossa() {
        assertFalse(laskin.tarkastaSyote("(45-3)/(2*9"));
        assertFalse(laskin.tarkastaSyote("((45-3)/(2*9)"));
    }

    @Test
    public void syotteeksiEiKelpaaTyhjaMerkkijono() {
        assertFalse(laskin.tarkastaSyote(""));
    }

    @Test
    public void syoteEiKelpaaKunSeSisaltaaSulutJoidenValissaEiOleMerkkia() {
        assertFalse(laskin.tarkastaSyote("5*21+()-34/8"));
        assertFalse(laskin.tarkastaSyote("(5*21)(34/8)"));
    }

    @Test
    public void syoteEiKelpaaKunSenEnsimmainenMerkkiOnOperandiTaiLoppusulku() {
        assertFalse(laskin.tarkastaSyote("*1000-75"));
        assertFalse(laskin.tarkastaSyote(")1000-75"));
    }

    @Test
    public void syoteEiKelpaaKunSenViimeinenMerkkiOnOperandiTaiAlkusulku() {
        assertFalse(laskin.tarkastaSyote("56/2+"));
        assertFalse(laskin.tarkastaSyote("56/2("));
    }

    @Test
    public void syoteEiKelpaaKunSeSisaltaaLaskuoperaationNegatiivisillaLuvuilla() {
        assertFalse(laskin.tarkastaSyote("4*-3"));
        assertFalse(laskin.tarkastaSyote("4*(-3+2)"));
    }

    @Test
    public void syoteEiKelpaaKunSeSisaltaaPerakkaisetOperanditJoidenValissaEiOleLukua() {
        assertFalse(laskin.tarkastaSyote("5024//2"));
        assertFalse(laskin.tarkastaSyote("5024+*2"));
    }
}