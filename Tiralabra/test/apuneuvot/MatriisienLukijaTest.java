package apuneuvot;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatriisienLukijaTest {
    
    double[][] I;
    MatriisienLukija lukija;
    
    @Before
    public void setUp() {
        lukija = new MatriisienLukija();
        
        I = new double[][]{
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
    }
    
    @Test
    public void lukijaToimii() {
        double[][] I2 = lukija.lue("testi lukijalle.txt");
        assertTrue(Arrays.deepEquals(I, I2));
    }
    
    @Test
    public void lukijaToimii2() {
        double[][] tyhja = lukija.lue("eiolemassa.txt");
        assertTrue(tyhja == null);
    }
}