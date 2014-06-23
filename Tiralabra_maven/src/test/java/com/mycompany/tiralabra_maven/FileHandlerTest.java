/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.Laatikko;
import com.mycompany.tiralabra_maven.domain.Laatikkotyyppi;
import com.mycompany.tiralabra_maven.domain.Sijainti;
import com.mycompany.tiralabra_maven.structures.List;
import com.mycompany.tiralabra_maven.tools.FileHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author szetk
 */
public class FileHandlerTest {

    public FileHandlerTest() {
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
    public void testLueLaatikotLukeeOikeatMaarat() {
        List<Laatikkotyyppi> list = null;
        try {
            list = FileHandler.lueLaatikot(new File("listat/uno"));
        } catch (IOException ex) {
            Logger.getLogger(FileHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(3, list.size());

        Laatikkotyyppi l = list.get(0);

        assertEquals(9, l.getLaatikoita());

        l = list.get(1);
        assertEquals(9, l.getLaatikoita());

        l = list.get(2);
        assertEquals(8, l.getLaatikoita());

    }

    @Test
    public void testLueLaatikotLukeeOikeatLaatikot() {
        List<Laatikkotyyppi> list = null;
        try {
            list = FileHandler.lueLaatikot(new File("listat/uno"));
        } catch (IOException ex) {
            Logger.getLogger(FileHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Laatikkotyyppi l = list.get(0);

        assertEquals(14, l.getX());
        assertEquals(79, l.getY());
        assertEquals(102, l.getZ());

        l = list.get(1);
        assertEquals(81, l.getX());
        assertEquals(69, l.getY());
        assertEquals(58, l.getZ());

        l = list.get(2);
        assertEquals(100, l.getX());
        assertEquals(40, l.getY());
        assertEquals(42, l.getZ());

    }

    @Test
    public void testKirjoitaStringKirjoittaaOikein() throws FileNotFoundException, IOException {
        File file = new File("testi");
        FileHandler.kirjoitaString(file, "eversti");

        BufferedReader br = new BufferedReader(new FileReader("testi"));
        assertEquals("eversti", br.readLine());
        br.close();

    }

    @Test
    public void testKirjoitaLaatikotToimii() throws FileNotFoundException, IOException {
        File file = new File("testi");
        Laatikkotyyppi lt = new Laatikkotyyppi(23, 23, 23);
        Laatikko l = new Laatikko(lt, new Sijainti(), 3);
        lt.setLaatikoita(1);
        List<Laatikkotyyppi> list = new List<Laatikkotyyppi>();
        list.add(lt);

        FileHandler.kirjoitaLaatikot(file, list);

        BufferedReader br = new BufferedReader(new FileReader("testi"));
        assertEquals("23 23 23 1", br.readLine());
        br.close();
    }

}
