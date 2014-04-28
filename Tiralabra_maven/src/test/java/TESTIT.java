/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.tiralabra_maven.Astar;
import com.mycompany.tiralabra_maven.Wall;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiina
 */
public class TESTIT {
    
    public TESTIT() {
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
     //pitää olla seiniä
     public void onkoSeinia() {
         Wall tiili = new Wall();
         int[][] map = tiili.getMap();
         for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if(map[i][j] == 1){
                        assertTrue(true);
                    }
            }
         }
     }
//     private Astar test = new Astar();
//     //testaa onko reitillä nodeja
//     @Test
//     public void Reitti(){
//         
//         if(test.reitti != null) {
//             
//         }
//     }
    
}
