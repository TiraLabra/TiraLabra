package domain;

import ohjelma.domain.Pino;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PinoTest {

    Pino pino;

    @Before
    public void setUp() {
        pino = new Pino(10);
    }

    @Test
    public void konstruktoriAsettaaPinonKoonJaHuipunViitteenOikein() {
        assertEquals(10, pino.getSize());
        assertEquals(-1, pino.getTop());
    }

    @Test
    public void pinoOnTyhjaKunSiihenEiOleLisattyYhtakaanOliota() {
        assertTrue(pino.empty());
    }

    @Test
    public void pinoEiOleTyhjaKunSiihenOnLisattyYksikinOlio() {
        pino.push("lisättävä olio");
        assertFalse(pino.empty());
    }

    @Test
    public void peekPalauttaaPinonPaallimmaisenOlionMutteiPoistaSitaEikaMuutaHuipunViitetta() {
        pino.push("lisättävä olio");
        assertEquals("lisättävä olio", pino.peek());
        assertEquals(1, pino.getOlioidenLkm());
        assertEquals(0, pino.getTop());
    }

    @Test
    public void pinossaOnYksiOlioJaHuipunViiteOnOikeinYhdenPushinJalkeen() {
        pino.push("lisättävä olio");
        assertEquals(1, pino.getOlioidenLkm());
        assertEquals(0, pino.getTop());
    }

    @Test
    public void pinossaOnUseampiaOliotaJaHuipunViiteOnOikeinUseammanPushinJalkeen() {
        pino.push("lisättävä olio 1");
        pino.push("lisättävä olio 2");
        pino.push("lisättävä olio 3");
        assertEquals(3, pino.getOlioidenLkm());
        assertEquals(2, pino.getTop());
    }

    @Test
    public void popPalauttaaPinonPaallimaisenOlionTyhjentaaPinonJaAsettaaHuipunViitteenOikeinYhdenPushinJalkeen() {
        pino.push("lisättävä olio");
        assertEquals("lisättävä olio", pino.pop());
        assertEquals(0, pino.getOlioidenLkm());
        assertEquals(-1, pino.getTop());
    }

    @Test
    public void popPalauttaaPinonPaallimaisenOlionPoistaaSenAsettaaHuipunViitteenJaPinonOlioidenLukumaaranOikeinUseammanPushinJalkeen() {
        pino.push("lisättävä olio 1");
        pino.push("lisättävä olio 2");
        pino.push("lisättävä olio 3");
        assertEquals("lisättävä olio 3", pino.pop());
        assertEquals(1, pino.getTop());
        assertEquals(2, pino.getOlioidenLkm());

    }

    @Test
    public void popPalauttaaPinonPaallimaisenOlionPoistaaSenAsettaaHuipunViitteenJaPinonOlioidenLukumaaranOikeinUseammanPushinJaYhdenPopinJalkeen() {
        pino.push("lisättävä olio 1");
        pino.push("lisättävä olio 2");
        pino.push("lisättävä olio 3");
        pino.pop();
        assertEquals("lisättävä olio 2", pino.pop());
        assertEquals(0, pino.getTop());
        assertEquals(1, pino.getOlioidenLkm());
    }
}