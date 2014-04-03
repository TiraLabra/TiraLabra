/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author riha
 */
public class LCSTest {

    LCS lcs;
    final private String TESTFILENAME = "testfile";

    public LCSTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String filename = "testsequence";
        char[] alphabet = {'a', 't', 'g', 'c'};
        lcs = new LCS(filename, alphabet);
    }

    @After
    public void tearDown() {
        // delete TESTFILENAME
    }

    @Test
    public void simpleDnaSequence() {
        writeNewTestSequenceFile("aaacgt\ntgcaaa");
        lcs = new LCS(TESTFILENAME, new char[]{'a', 't', 'g', 'c'});
        lcs.calculateAlignment();
        lcs.findSolution();
        StringBuilder s = new StringBuilder();
        String solution = s.append(lcs.getSolution()[0]).toString().trim();
        assertEquals("aaa", solution);
    }

    @Test
    public void longDnaSequence() {
        writeNewTestSequenceFile("AAACCGTGAGTTATTCGTTCTAGAA\n"
                + "CACCCCTAAGGTACCTTTGGTTC");
        lcs = new LCS(TESTFILENAME, new char[]{'a', 't', 'g', 'c'});
        lcs.calculateAlignment();
        lcs.findSolution();
        StringBuilder s = new StringBuilder();
        String solution = s.append(lcs.getSolution()[0]).toString().trim();
        assertEquals("acctggttttgttc", solution);
    }

    private void writeNewTestSequenceFile(String input) {
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("testfile"), "utf-8"));
            writer.write(input);
        } catch (IOException ex) {
            System.out.println("Virhe testissä writeFile " + ex);
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

//    @Test
//    public void correctSequence() {
//        String correct = "ACCTAGTACTTTG";
//        String result = lcs.getSolution()[0].toString();
//        assertEquals(correct, result);
//    }
}
