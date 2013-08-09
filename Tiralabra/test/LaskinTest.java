/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ilkka
 */
public class LaskinTest {
    
    private Laskin laskin;
    private double virhemarginaali = 0.1;
    private double[][] matriisi1 = {{1, 2},
                                    {3, 4}};
    private double[][] matriisi2= {{4, 3},
                                   {2, 1}};
    private double[][] matriisi3= {{4, 3},
                                   {2, 1},
                                   {3, 2}};
    
    private double[][] matriisi4 = {{ 2,-1,-2},
                                    {-4, 6,3},
                                    {-4,-2,8}};
 
    
    public LaskinTest() {
        laskin = new Laskin();
        
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


    
    
    
    
    @Test
    public void matriisinKertominenSkalaarilla(){
        try {
            double[][] laskettuTulos = laskin.kerroSkalaarilla(matriisi1, 2);
            double[][] odotettuTulos = {{2, 4},
                                        {6, 8}};
            assertTrue(tarkistaMatriisit(laskettuTulos, odotettuTulos));
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }       
    }
    
    @Test
    public void matriisienYhteenlaskuSamaTyyppi(){
        try {
            double[][] laskettuTulos = laskin.laskeYhteen(matriisi1, matriisi2);
            double[][] odotettuTulos = {{5, 5},
                                        {5, 5}};
            assertTrue(tarkistaMatriisit(laskettuTulos, odotettuTulos));
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }
    } 
    
    @Test(expected = Exception.class)
   public void matriisienYhteenlaskuEriTyyppi() throws Exception {	
         laskin.laskeYhteen(matriisi1, matriisi3);
   }
    
    
    
   @Test
    public void matriisinKertominenMatriisillaSamaTyyppi(){
        try {
            double[][] laskettuTulos = laskin.kerroMatriisit(matriisi1, matriisi2);
            double[][] odotettuTulos = {{8, 5},
                                        {20, 13}};
            assertTrue(tarkistaMatriisit(laskettuTulos, odotettuTulos));
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }
    } 
   
    @Test(expected = Exception.class)
   public void matriisinKertominenMatriisillaEriTyyppi() throws Exception {	
         laskin.laskeYhteen(matriisi1, matriisi3);
   }
   
   @Test
    public void matriisiLUDekompositioDoolittle(){
        try {
            double[][] laskettuTulos = laskin.luDekompositioDoolittle(matriisi4);
            double[][] odotettuTulos = {{ 2,-1,-2},
                                        {-2, 4,-1},
                                        {-2,-1, 8}};
            assertTrue(tarkistaMatriisit(laskettuTulos, odotettuTulos));
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }
    }
   
   @Test
    public void matriisiLUDekompositioDoolittleYlempiKolmiomatriisi(){
        try {
            double[][] laskettuTulos = laskin.luDekompositioDoolittleYlempiKolmiomatriisi(matriisi4);
            double[][] odotettuTulos = {{ 2,-1,-2},
                                        {0, 4,-1},
                                        {0, 0, 3}};
            assertTrue(tarkistaMatriisit(laskettuTulos, odotettuTulos));
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }
    }
   
    
    private boolean tarkistaMatriisit(double[][] laskettuTulos, double[][] odotettuTulos) {
        for (int i = 0; i < laskettuTulos.length; i++) {
                for (int j = 0; j < laskettuTulos[0].length; j++) {
                    if ((Math.abs(laskettuTulos[i][j]) - Math.abs(odotettuTulos[i][j])) > virhemarginaali){                  
                        return false;
                    } 
                }
            }
        return true;
    }
}