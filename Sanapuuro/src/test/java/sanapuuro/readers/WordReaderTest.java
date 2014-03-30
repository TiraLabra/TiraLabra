/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.readers;

import sanapuuro.fileio.WordReader;
import java.util.Set;
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
        Set<String> words = this.reader.getWords();
        assertTrue(words.contains("aa"));
        assertTrue(words.contains("zyzzyvas"));
    }
}
