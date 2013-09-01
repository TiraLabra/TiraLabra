/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.LaskinModel;
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
    
    private LaskinModel laskin;
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
    private double[][] matriisi5 = {{3, 5, 7, 11, 13}, 
                                    {5, 7, 11, 13, 17}, 
                                    {7, 11, 13, 17, 19}, 
                                    {11, 13, 17, 19, 23}, 
                                    {13, 17, 19, 23, 29}};
 
    
    public LaskinTest() {
        laskin = new LaskinModel();
    
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
   
    @Test (expected = Exception.class)
   public void matriisinKertominenMatriisillaEriTyyppi() throws Exception {	               
            laskin.kerroMatriisit(matriisi3, matriisi1);
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
   
   @Test (expected = Exception.class)
    public void matriisiLUDekompositioDoolittleEiNeliomatriisi() throws Exception{
            double[][] matriisi      = {{ 2,-1,-2},
                                        {-2, 4,-1}};
               laskin.luDekompositioDoolittle(matriisi);
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
   
    @Test  (expected = Exception.class)
    public void matriisiLUDekompositioDoolittleYlempiKolmiomatriisiEiNeliomatriisi() throws Exception{
         double[][] matriisi      = {{ 2,-1,-2},
                                     {-2, 4,-1}};      
            laskin.luDekompositioDoolittleYlempiKolmiomatriisi(matriisi);
    }
   
   @Test
    public void matriisinDeterminantti(){
        try {
            double laskettuTulos = laskin.laskeDeterminantti(matriisi4);
            double odotettuTulos = 24;
            assertTrue((Math.abs(laskettuTulos) - Math.abs(odotettuTulos) < virhemarginaali));
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }
    }
   
   @Test
    public void matriisinDeterminanttiYksikkoMatriisi(){
        try {
            double[][] matriisi = {{ 1, 0,0},
                                    {0, 1,0},
                                    {0,0,1}};
            double laskettuTulos = laskin.laskeDeterminantti(matriisi);
            double odotettuTulos = 1;
            assertTrue((Math.abs(laskettuTulos) - Math.abs(odotettuTulos) < virhemarginaali));
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }
    }
   
   @Test
    public void matriisinDeterminanttiNollaMatriisi(){
        try {
            double[][] matriisi = {{ 0, 0, 0},
                                    {0, 0, 0},
                                    {0, 0, 0}};
            double laskettuTulos = laskin.laskeDeterminantti(matriisi);
            double odotettuTulos = 0;
            assertTrue("Laskettu tulos oli: " + laskettuTulos,(Math.abs(laskettuTulos) - Math.abs(odotettuTulos) < virhemarginaali));
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }
    }
   
    @Test (expected = Exception.class)
   public void matriisinDeterminanttiEiNelioMatriisi() throws Exception {	
            double[][] matriisi = {{ 0, 0, 0},
                                    {0, 0, 0},}; 
        laskin.laskeDeterminantti(matriisi);
   }
    
    @Test (expected = Exception.class)
   public void matriisinDeterminanttiNullArvolla() throws Exception{	
        laskin.laskeDeterminantti(null);
   }
    
    @Test
    public void matriisinDeterminanttiSuorituskykyAlleNeliollinen() throws Exception {
      long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            laskin.laskeDeterminantti(matriisi4);
        }
      long endTime = System.currentTimeMillis();
      long difference1 = endTime-startTime;

      double kerroin1 = difference1 / (matriisi4.length*matriisi4.length);
      startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            laskin.laskeDeterminantti(matriisi5);
        }
      endTime = System.currentTimeMillis();  
       long difference2 = endTime-startTime;
      double kerroin2 = difference2 / (matriisi5.length*matriisi5.length); 
        
      assertTrue(kerroin2 < (kerroin1*kerroin1));
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