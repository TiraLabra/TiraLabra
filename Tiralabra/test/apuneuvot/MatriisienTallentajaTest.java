package apuneuvot;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatriisienTallentajaTest {

    double[][] testi;
    private MatriisienTallentaja tallentaja;
    private MatriisienLukija lukija;
    
    @Before
    public void setUp() {
        tallentaja = new MatriisienTallentaja();
        lukija = new MatriisienLukija();
        
        testi = new double[][] {
            {1, 0},
            {0, 1}
        };
    }
    
    @Test
    public void tallentajaToimii() {
        tallentaja.tallenna(testi, "testi tallentajalle.txt");
        double[][] I = lukija.lue("testi tallentajalle.txt");
        assertTrue(Arrays.deepEquals(I, testi));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        tallentaja.tallenna(null, "eiole.txt");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii2() {
        tallentaja.tallenna(testi, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii3() {
        tallentaja.tallenna(testi, "");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii4() {
        tallentaja.tallenna(testi, "    ");
    }
}