/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

import java.util.ArrayList;
import java.util.Arrays;
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
public class MyWriterTest {
    
    MyWriter writer;
    
    public MyWriterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        writer = new MyWriter();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void stringToCharByteTest() {
        
    }
    @Test
    public void divideTest() {
        ArrayList<String> lista3 = writer.divide("111111100000001111111000");
        ArrayList<String> lista = writer.divide("1011100110");
//        System.out.println(lista3);
//        System.out.println(lista);
        ArrayList<String> lista2 = new ArrayList<String>();
        lista2.add("101110");
        lista2.add("0110");
        assertEquals(lista, lista2);
    }
    @Test
    public void StringTobyteCharTest() {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("101110");
        lista.add("0110");
        byte[] b = writer.stringToByteChar(lista);
        byte[] b2 = new byte[2];
        b2[0] = (byte) Integer.parseInt("1101110",2);
        b2[1] = (byte) Integer.parseInt("10110",2);
        
        ArrayList<String> lista3 = writer.divide("111111000000111111010");;
        byte[] b3 = writer.stringToByteChar(lista3);

        byte[] b4 = new byte[4];
        b4[0] = (byte) Integer.parseInt("1111111",2);
        b4[1] = (byte) Integer.parseInt("1000000",2);
        b4[2] = (byte) Integer.parseInt("1111111",2);
        b4[3] = (byte) Integer.parseInt("1010",2);

        assertEquals(true, Arrays.equals(b3, b4));
    }
    
}
