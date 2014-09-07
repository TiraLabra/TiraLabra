package compression;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import datastructures.Paketti;
import compression.Packer;
import datastructures.Node;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joonaskylliainen
 */
public class PackerTest {
    
    Packer packer;
    
    String word1;
    String word2;
    String word3;
    
    public PackerTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        word1 = "kissa";
        word2 = "j'aime aller sur le bord de l'eau les jeudis ou les jours impairs";
        packer = new Packer();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaPakkaajaa() {
        
    }
     /**
     * testaa toimiiko pakkaaja
     */
//    @Test
//     public void doesPackerWork() {
//
//         Paketti paketti = packer.pack(word1);
//         Paketti paketti2 = packer.pack(word2);
//         assertEquals(paketti.getText(), "1011100110");
//         assertEquals(paketti2.getText(),"10111101001001100010101011001001111011100111101000101100110100111001100101100111111101111100011110011001110101000111001110000111001101000101110111100111101000010001111111000011100110100010111111111100110101000100010101101101100110001101010");
//     }

    
}
