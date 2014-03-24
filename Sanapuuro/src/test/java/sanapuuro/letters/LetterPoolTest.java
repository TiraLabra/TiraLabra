/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.letters;

import sanapuuro.letters.Letters;
import sanapuuro.letters.LetterPool;
import sanapuuro.letters.Letter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.grid.LetterContainer;

/**
 *
 * @author skaipio
 */
public class LetterPoolTest {

    private LetterPool letterPool;

    public LetterPoolTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.letterPool = new LetterPool(new LettersForTest());
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfSelectionIndexTooSmall() {
        this.letterPool.setCurrentSelection(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfSelectionIndexTooLarge() {
        this.letterPool.setCurrentSelection(8);
    }
    
    @Test
    public void indicesSetAsUsedWhenPickingLetters() {
        this.letterPool.useLetter();
        this.letterPool.setCurrentSelection(1);
        this.letterPool.useLetter();
        
        assertTrue(this.letterPool.isIndexUsed(0));
        assertTrue(this.letterPool.isIndexUsed(1));
    }
    
    @Test
    public void returnsNullIfSelectionAlreadyUsed() {
        LetterContainer container = this.letterPool.useLetter();
        assertNotNull(container);
        container = this.letterPool.useLetter();
        assertNull(container);
        this.letterPool.setCurrentSelection(1);
        container = this.letterPool.useLetter();
        assertNotNull(container);
    }
    
    @Test
    public void freesUsedIndicesOnReplacingLetters() {
        this.letterPool.useLetter();
        this.letterPool.setCurrentSelection(1);
        this.letterPool.useLetter();
        this.letterPool.clearLetterPicks();
        
        assertFalse(this.letterPool.isIndexUsed(0));
        assertFalse(this.letterPool.isIndexUsed(1));
    }

    private class LettersForTest implements Letters {

        List<Letter> letters = new ArrayList<>();

        private int i = 0;

        public LettersForTest() {
            this.letters.add(new Letter('t', 1, 0));
            this.letters.add(new Letter('o', 2, 0));
            this.letters.add(new Letter('e', 3, 0));
            this.letters.add(new Letter('t', 1, 0));
            this.letters.add(new Letter('o', 2, 0));
            this.letters.add(new Letter('e', 3, 0));
            this.letters.add(new Letter('t', 1, 0));
            this.letters.add(new Letter('o', 2, 0));
            this.letters.add(new Letter('e', 3, 0));
            this.letters.add(new Letter('t', 1, 0));
            this.letters.add(new Letter('o', 2, 0));
            this.letters.add(new Letter('e', 3, 0));
            this.letters.add(new Letter('t', 1, 0));
            this.letters.add(new Letter('o', 2, 0));
            this.letters.add(new Letter('e', 3, 0));
        }

        @Override
        public Letter getLetterMatchingCharacter(char c) {
            return new Letter(' ', 0, 0);
        }

        @Override
        public Letter getRandomLetter() {
            i++;
            return this.letters.get(i - 1);
        }
    }
}
