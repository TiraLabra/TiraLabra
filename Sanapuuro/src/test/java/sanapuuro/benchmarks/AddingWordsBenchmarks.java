/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.benchmarks;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import sanapuuro.datastructures.DJB2ForStrings;
import sanapuuro.datastructures.FNVOneForStrings;
import sanapuuro.datastructures.GeneralHashFuncForStrings;
import sanapuuro.datastructures.GeneralHashFuncForStrings2;
import sanapuuro.datastructures.MurmurHash3ForStrings;
import sanapuuro.datastructures.MyHashSet;
import sanapuuro.fileio.FileIO;
import sanapuuro.utils.Util;

/**
 *
 * @author skaipio
 */
public class AddingWordsBenchmarks {
    private final List<String> words;
    
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();
    
    public AddingWordsBenchmarks() {
        FileIO io = new FileIO();
        // test only with words of length 8 or less
        this.words = io.readInWordsFromFile("words/english_words", 8);
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
    public void addingWordsSpeedTestWithGeneralHashWithBigInts() {
        MyHashSet<String> set = Util.convertListToMyHashSet(words, new GeneralHashFuncForStrings2());
    }
    
    @Test
    public void addingWordsSpeedTestWithGeneralHashWithLongs() {
        MyHashSet<String> set = Util.convertListToMyHashSet(words, new GeneralHashFuncForStrings());
    }

    @Test
    public void addingWordsSpeedTestWithDJB2() {
        MyHashSet<String> set = Util.convertListToMyHashSet(words, new DJB2ForStrings());
    }
    
    @Test
    public void addingWordsSpeedTestWithFNVOne() {
        MyHashSet<String> set = Util.convertListToMyHashSet(words, new FNVOneForStrings());
    }
    
    @Test
    public void addingWordsSpeedTestWithMurmurHash3() {
        MyHashSet<String> set = Util.convertListToMyHashSet(words, new MurmurHash3ForStrings());
    }
}
