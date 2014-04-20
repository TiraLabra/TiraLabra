/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.datastructures;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sanapuuro.fileio.FileIO;

/**
 *
 * @author skaipio
 */
public class FNVOneForStringTest {

    private List<String> words;
    private Set<Integer> hashDuplicates;
            
            

    public FNVOneForStringTest() {
        FileIO io = new FileIO();
        words = io.readInWordsFromFile("words/english_words");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.hashDuplicates = new HashSet<>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testNumberOfCollisions() {
        int duplicates = 0;
        int m = 600011;
        FNVOneForString fnv = new FNVOneForString();
        for (String string : words) {
            int hash = fnv.getNormalHash(string, m);
            if (this.hashDuplicates.contains(hash))
                duplicates++;
            else this.hashDuplicates.add(hash);
        }
        System.out.println("duplicates: " + duplicates);
    }
}
