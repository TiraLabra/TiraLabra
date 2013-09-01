/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.Controller;
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
public class ControllerTest {

    private Controller controller;
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

    public ControllerTest() {
        controller = new Controller();
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
    public void stringistaDoubletaulukoksiOnnistuu() {
        try {
        String[] testiSyote = {"1,2,3","2,3,4","3,5,6"};
        double[][] odotettuMatriisi = {
                            {1,2,3},
                            {2,3,4},
                            {3,5,6}
        };       
        assertTrue(tarkistaMatriisit(controller.luoMatriisi(testiSyote), odotettuMatriisi));       
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }
    }
    
     @Test
    public void stringistaDoubletaulukoksiOnnistuuYlimaaraisillaMerkeillaLopussa() {
         try {
         String[] testiSyote = {"1,2,3m,.","2,3,4,.-","3,5,6dfsa"};
        double[][] odotettuMatriisi = {
                            {1,2,3},
                            {2,3,4},
                            {3,5,6}
        };       
        assertTrue(tarkistaMatriisit(controller.luoMatriisi(testiSyote), odotettuMatriisi));       
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }
    }
    
@Test
    public void matriisinDeterminanttiController(){
        try {
            String laskettava = "2,-1,-2\n-4,6,3\n-4,-2,8";
            String laskettuTulos = controller.laske(laskettava,null, "4",null);
            String odotettuTulos = "24.0";

            assertEquals(laskettuTulos, odotettuTulos);
        } catch (Exception ex) {
            assertTrue(ex.getMessage(), false);
        }
    }
     
     
     
     
     
    
    private boolean tarkistaMatriisit(double[][] laskettuTulos, double[][] odotettuTulos) {
        for (int i = 0; i < laskettuTulos.length; i++) {
                for (int j = 0; j < laskettuTulos[0].length; j++) {
                    if ((Math.abs(laskettuTulos[i][j]) - Math.abs(odotettuTulos[i][j])) > 0.1){                  
                        return false;
                    } 
                }
            }
        return true;
    }
}
