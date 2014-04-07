/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author riha
 */
public class LCSTest extends TestFileOperations {

    LCS identicalSeqs, shortSeqs, longSeqs;

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
        writeNewTestSequenceFile("actg\nactg");
        identicalSeqs = new LCS(TESTFILENAME);
        identicalSeqs.calculateAlignment();
        identicalSeqs.findSolution();

        writeNewTestSequenceFile("agcga\ncagatagag");
        shortSeqs = new LCS(TESTFILENAME);
        shortSeqs.calculateAlignment();
        shortSeqs.findSolution();

    }

    @Test
    public void identicalSequenceGivesCorrectScore() {
        assertEquals(4, identicalSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void identicalSequenceSolutionIsTrueSubsequence() {
        char[] solution = identicalSeqs.getSolution()[0];
        char[] seq = {'a', 'c', 't', 'g'};
        assertTrue(isSubseq(seq, solution));
    }

    @Test
    public void shortSequenceGivesCorrectScore() {
        assertEquals(4, shortSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void shortSequenceSolutionIsTrueSubsequence() {
        char[] solution = shortSeqs.getSolution()[0];
        char[] seq1 = {'a','g','c','g','a'};
        char[] seq2 = {'c','a','g','a','t','a','g','a','g'};
        
        assertTrue(isSubseq(seq1, solution) && isSubseq(seq2, solution));
    }
    
    @Test
    public void longSequenceGivesCorrectScore() {
        
    }

    private boolean isSubseq(char[] seq, char[] sub) {
        int k = -1;
        for (int i = 0; i < sub.length; i++) {
            do {
                k++;
            } while (k < seq.length && sub[i] != seq[k]);
        }
        if (k < seq.length) {
            System.out.println("returning false");
            return true;
        }
        System.out.println("returning true");
        return false;
    }

}
