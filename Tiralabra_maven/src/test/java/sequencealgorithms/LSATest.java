/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

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
public class LSATest extends TestFileOperations {

    LSA identicalSeqs, simpleSeqs;

    public LSATest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        writeNewTestSequenceFile("actg\nactg");
        identicalSeqs = new LSA(TESTFILENAME);
        identicalSeqs.calculateAlignment();
        identicalSeqs.findSolution();

        writeNewTestSequenceFile("aaatgccc\nccatgaa");
        simpleSeqs = new LSA(TESTFILENAME);
        simpleSeqs.calculateAlignment();
        simpleSeqs.findSolution();
    }

    @Test
    public void identicalSequenceGivesCorrectScore() {
        assertEquals(8, identicalSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void identicalSequenceGivesCorrectSolution() {
        char[] correctSolution = {'a', 'c', 't', 'g'};
        assertEquals(correctSolution.length, identicalSeqs.getSolution()[0].length);
        assertEquals(correctSolution.length, identicalSeqs.getSolution()[1].length);
        for (int i = 0; i < correctSolution.length; i++) {
            assertEquals(correctSolution[i], identicalSeqs.getSolution()[0][i]);
            assertEquals(correctSolution[i], identicalSeqs.getSolution()[1][i]);
        }
    }

    @Test
    public void simpleSequenceGivesCorrectScore() {
        assertEquals(6, simpleSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void simpleSequenceGivesCorrectSolution() {
        char[] correctSolution = {'a', 't', 'g'};
        assertEquals(correctSolution.length, simpleSeqs.getSolution()[0].length);
        assertEquals(correctSolution.length, simpleSeqs.getSolution()[1].length);
        for (int i = 0; i < correctSolution.length; i++) {
            assertEquals(correctSolution[i], simpleSeqs.getSolution()[0][i]);
            assertEquals(correctSolution[i], simpleSeqs.getSolution()[1][i]);
        }
    }

}
