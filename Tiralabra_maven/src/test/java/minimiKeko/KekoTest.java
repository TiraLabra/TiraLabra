package minimiKeko;

import junit.framework.TestCase;

/**
 *
 * @author Ilkimys
 */
public class KekoTest extends TestCase {

    Keko<Object> k;

    @Override
    protected void setUp() throws Exception {
        k = new Keko<Object>(200);
    }

    /**
     * Test of lisaaKekoon method, of class Keko.
     */
    public void testLisaaKekoon() {
        taytaKekoHitaasti();
        assertEquals(200, k.getKoko());
    }

    /**
     * Test of laskeAvaimenArvoa method, of class Keko.
     */
    public void testLaskeAvaimenArvoa() {
        taytaKekoNopeasti();
        k.laskeAvaimenArvoa(199, 0);
        assertEquals(0, k.poll().getKey());
    }

    /**
     * Test of dataIndeksi method, of class Keko.
     */
    public void testDataIndeksi() {
        Object o = new Object();
        k.lisaaKekoon(10, o);
        assertEquals(1, k.dataIndeksi(o));
    }

    /**
     * Test of dataaVastaavaKey method, of class Keko.
     */
    public void testDataaVastaavaKey() {
                Object o = new Object();
        k.lisaaKekoon(10, o);
        assertEquals(10, k.dataaVastaavaKey(o));
    }

    /**
     * Test of poll method, of class Keko.
     */
    public void testPoll() {
        taytaKekoNopeasti();
         Solmu tulos = k.poll();
         assertEquals(199, k.getKoko());
         assertEquals(1, tulos.getKey());
    }

    /**
     * Test of isEmpty method, of class Keko.
     */
    public void testIsEmpty() {
        assertTrue(k.isEmpty());
    }

    private void taytaKekoHitaasti() {
        for (int i = 200; i >= 0; i++) {
            k.lisaaKekoon(i, new Object());
        }
    }

    private void taytaKekoNopeasti() {
        for (int i = 1; i <= 200; i++) {
            k.lisaaKekoon(i, new Object());
        }
    }
}
