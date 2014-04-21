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

//        writeNewTestSequenceFile("agcga\ncagatagag");
        writeNewTestSequenceFile("actgactg\naaccttgg");
        shortSeqs = new LCS(TESTFILENAME);
        shortSeqs.calculateAlignment();
        shortSeqs.findSolution();

        writeNewTestSequenceFile("gcgcgtgcgcggaaggagccaaggtgaagttgtagcagtgtgtcagaagaggtgcgtggcaccatgctgtcccccgaggcggagcgggtgctgcggtacctggtcgaagtagaggagttg\n"
                + "gacttgtggaacctacttcctgaaaataaccttctgtcctccgagctctccgcacccgtggatgacctgctcccgtacacagatgttgccacctggctggatgaatgtccgaatgaagcg");
        longSeqs = new LCS(TESTFILENAME);
        longSeqs.calculateAlignment();
        longSeqs.findSolution();

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
        assertEquals(5, shortSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void shortSequenceSolutionIsTrueSubsequence() {
        char[] solution = shortSeqs.getSolution()[0];
        char[] seq1 = ("actgactg").toCharArray();
        char[] seq2 = ("aaccttgg").toCharArray();
//        char[] seq1 = {'a','g','c','g','a'};
//        char[] seq2 = {'c','a','g','a','t','a','g','a','g'};

        assertTrue(isSubseq(seq1, solution) && isSubseq(seq2, solution));
    }

    @Test
    public void longSequenceGivesCorrectScore() {
        assertEquals(75, longSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void longSequenceSolutionIsTrueSubsequence() {
        char[] solution = longSeqs.getSolution()[0];
        char[] seq1 = ("gcgcgtgcgcggaaggagccaaggtgaagttgtagcagtgtgtcagaagaggtgcgtggcaccatgctgtcccccgaggcggagcgggtgctgcggtacctggtcgaagtagaggagttg").toCharArray();
        char[] seq2 = ("gacttgtggaacctacttcctgaaaataaccttctgtcctccgagctctccgcacccgtggatgacctgctcccgtacacagatgttgccacctggctggatgaatgtccgaatgaagcg").toCharArray();
        assertTrue(isSubseq(seq1, solution) && isSubseq(seq2, solution));
    }

    private boolean isSubseq(char[] seq, char[] sub) {
        int k = -1;
        for (int i = 0; i < sub.length; i++) {
            do {
                k++;
            } while (k < seq.length && sub[i] != seq[k]);
        }
        if (k < seq.length) {
            return true;
        }
        return false;
    }

}
