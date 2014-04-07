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
public class GSATest extends TestFileOperations {

    private GSA identicalSeqs, shortSeqs, longSeqs;

    public GSATest() {
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
        identicalSeqs = new GSA(TESTFILENAME);
        identicalSeqs.setUpScoring(5,-3,-1,0);
        identicalSeqs.calculateAlignment();
        identicalSeqs.findSolution();

        writeNewTestSequenceFile("actgactg\naaccttgg");
        shortSeqs = new GSA(TESTFILENAME);
        shortSeqs.setUpScoring(5,-3,-1,0);
        shortSeqs.calculateAlignment();
        shortSeqs.findSolution();

        writeNewTestSequenceFile("gcgcgtgcgcggaaggagccaaggtgaagttgtagcagtgtgtcagaagaggtgcgtggcaccatgctgtcccccgaggcggagcgggtgctgcggtacctggtcgaagtagaggagttg\n"
                + "gacttgtggaacctacttcctgaaaataaccttctgtcctccgagctctccgcacccgtggatgacctgctcccgtacacagatgttgccacctggctggatgaatgtccgaatgaagcg");
        longSeqs = new GSA(TESTFILENAME);
        longSeqs.setUpScoring(5,-3,-1,0);
        longSeqs.calculateAlignment();
        longSeqs.findSolution();
    }

    @Test
    public void identicalSequencesGiveCorrectScore() {
        assertEquals(20, identicalSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void identicalSequenceSolutionMatchesScore() {
        double score = checkSolutionScore(identicalSeqs);
        assertEquals(20, score, 0.00001);
    }

    @Test
    public void shortSequenceGivesCorrectScore() {
        System.out.println("GAS: " + shortSeqs.getAlignmentScore());
        assertEquals(19, shortSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void shortSequenceSolutionMatchesScore() {
        double score = checkSolutionScore(shortSeqs);
        assertEquals(19, score, 0.00001);
    }

    @Test
    public void longSequenceGivesCorrectScore() {
        assertEquals(285, longSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void longSequenceSolutionMatchesScore() {
        double score = checkSolutionScore(longSeqs);
        assertEquals(285, score, 0.00001);
    }

    private double checkSolutionScore(GSA gsa) {
        double score = 0;
        char[][] solution = gsa.getSolution();
        for (int i = 0; i < solution[0].length; i++) {
            score += gsa.s.getScore(solution[0][i], solution[1][i]);
        }
        return score;
    }

}
