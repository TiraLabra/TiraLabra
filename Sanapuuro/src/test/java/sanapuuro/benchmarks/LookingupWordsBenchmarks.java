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
import sanapuuro.hashfunctions.DJB2ForStrings;
import sanapuuro.hashfunctions.FNVOneForStrings;
import sanapuuro.hashfunctions.GeneralHashFuncForStrings;
import sanapuuro.hashfunctions.GeneralHashFuncForStrings2;
import sanapuuro.hashfunctions.MurmurHash3ForStrings;
import sanapuuro.datastructures.MyHashSet;
import sanapuuro.fileio.FileIO;
import sanapuuro.utils.Util;

/**
 *
 * @author skaipio
 */
public class LookingupWordsBenchmarks {
    private final List<String> words;
    private final MyHashSet<String> genHash1Set;
    private final MyHashSet<String> genHash2Set;
    private final MyHashSet<String> djb2Set;
    private final MyHashSet<String> fnvoneSet;
    private final MyHashSet<String> murmur3Set;
    
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();
    
    public LookingupWordsBenchmarks() {
        FileIO io = new FileIO();
        // test only with words of length 8 or less
        this.words = io.readInWordsFromFile("words/english_words", 8);
        this.genHash1Set = Util.convertListToMyHashSet(words, new GeneralHashFuncForStrings());
        this.genHash2Set = Util.convertListToMyHashSet(words, new GeneralHashFuncForStrings2());
        this.djb2Set = Util.convertListToMyHashSet(words, new DJB2ForStrings());
        this.fnvoneSet = Util.convertListToMyHashSet(words, new FNVOneForStrings());
        this.murmur3Set = Util.convertListToMyHashSet(words, new MurmurHash3ForStrings());
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
    public void lookingupWordsSpeedTestWithGeneralHashWithBigInts() {
        lookupWords(words, genHash2Set);
    }
    
    @Test
    public void lookingupWordsSpeedTestWithGeneralHashWithLongs() {
        lookupWords(words, genHash1Set);
    }

    @Test
    public void lookingupWordsSpeedTestWithDJB2() {
        lookupWords(words, djb2Set);
    }
    
    @Test
    public void lookingupWordsSpeedTestWithFNVOne() {
        lookupWords(words, fnvoneSet);
    }
    
    @Test
    public void lookingupSpeedTestWithMurmurHash3() {
        lookupWords(words, murmur3Set);
    }
    
    private void lookupWords(List<String> words, MyHashSet<String> set){
        for (String word : words) {
            set.contains(word);
        }
    }
}
