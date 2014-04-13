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
public class GSAwGapTest extends TestFileOperations {

    private GSA identicalSeqs, shortSeqs, longSeqs;

    public GSAwGapTest() {

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
        identicalSeqs.setUpScoring(10, -3, -1, -7);
        identicalSeqs.calculateAlignment();
        identicalSeqs.findSolution();

        writeNewTestSequenceFile("actgactg\naaccttgg");
        shortSeqs = new GSA(TESTFILENAME);
        shortSeqs.setUpScoring(10, -3, -1, -7);
        shortSeqs.calculateAlignment();
        shortSeqs.findSolution();

        writeNewTestSequenceFile("gcgcgtgcgcggaaggagccaaggtgaagttgtagcagtgtgtcagaagaggtgcgtggcaccatgctgtcccccgaggcggagcgggtgctgcggtacctggtcgaagtagaggagttg\n"
                + "gacttgtggaacctacttcctgaaaataaccttctgtcctccgagctctccgcacccgtggatgacctgctcccgtacacagatgttgccacctggctggatgaatgtccgaatgaagcg");
        longSeqs = new GSA(TESTFILENAME);
        longSeqs.setUpScoring(10, -3, -1, -7);
        longSeqs.calculateAlignment();
        longSeqs.findSolution();
    }

    @Test
    public void identicalSequencesGiveCorrectScore() {
        assertEquals(40, identicalSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void identicalSequenceSolutionMatchesScore() {
        double score = checkSolutionScore(identicalSeqs);
        assertEquals(40, score, 0.00001);
    }

    @Test
    public void shortSequenceGivesCorrectScore() {
        assertEquals(44, shortSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void shortSequenceSolutionMatchesScore() {
        double score = checkSolutionScore(shortSeqs);
        assertEquals(44, score, 0.00001);
    }

    @Test
    public void longSequenceGivesCorrectScore() {
        assertEquals(660, longSeqs.getAlignmentScore(), 0.00001);
    }

    @Test
    public void longSequenceSolutionMatchesScore() {
        double score = checkSolutionScore(longSeqs);
        assertEquals(660, score, 0.00001);
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
