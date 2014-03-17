/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.readers;

import sanapuuro.sanapuuro.filereaders.WordReader;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author skaipio
 */
public class WordReaderTest {
    private final WordReader reader;
    
    public WordReaderTest() {
        this.reader = new WordReader();
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
     public void allWordsAreReadIn() {
         List<String> words = this.reader.getWords();
         assertEquals("aa", words.get(0));
         assertEquals("zyzzyvas", words.get(words.size()-1));
     }
}
