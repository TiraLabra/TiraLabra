/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.letters;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author skaipio
 */
public class PlayerLetterPoolTest {
    private PlayerLetterPool letterPool;

    public PlayerLetterPoolTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.letterPool = new PlayerLetterPool(new LettersForTest());
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void lettersInPoolAreAvailable(){
        assertTrue(this.letterPool.letterIsFree('c'));
        assertTrue(this.letterPool.letterIsFree('a'));
        assertTrue(this.letterPool.letterIsFree('t'));
        assertTrue(this.letterPool.letterIsFree('i'));
        assertTrue(this.letterPool.letterIsFree('n'));
        assertTrue(this.letterPool.letterIsFree('b'));
        assertTrue(this.letterPool.letterIsFree('o'));
        assertTrue(this.letterPool.letterIsFree('x'));
    }
    
    @Test
    public void usingLetterMakesItUnavailable(){
        this.letterPool.useLetter('x');
        assertFalse(this.letterPool.letterIsFree('x'));
    }
    
    @Test
    public void letterThatIsPickedAndUnpickedIsAvailable(){
        this.letterPool.useLetter('x');       
        this.letterPool.unpickLetter('x');
        assertTrue(this.letterPool.letterIsFree('x'));
    }
    
    @Test
    public void usedLettersAreReplacedWhenReplaceMethodIsCalled(){
        this.letterPool.useLetter('c');       
        this.letterPool.useLetter('a');
        this.letterPool.useLetter('t');
        this.letterPool.replacePickedLetters();
        assertFalse(this.letterPool.letterIsFree('c'));
        assertFalse(this.letterPool.letterIsFree('a'));
        assertFalse(this.letterPool.letterIsFree('t'));
        assertTrue(this.letterPool.letterIsFree('j'));
        assertTrue(this.letterPool.letterIsFree('k'));
        assertTrue(this.letterPool.letterIsFree('l'));
    }
    
    @Test
    public void clearingLetterPicksMakesThemAvailableAgain(){
        this.letterPool.useLetter('c');       
        this.letterPool.useLetter('a');
        this.letterPool.useLetter('t');
        assertFalse(this.letterPool.letterIsFree('c'));
        assertFalse(this.letterPool.letterIsFree('a'));
        assertFalse(this.letterPool.letterIsFree('t'));
        this.letterPool.clearLetterPicks();
        assertTrue(this.letterPool.letterIsFree('c'));
        assertTrue(this.letterPool.letterIsFree('a'));
        assertTrue(this.letterPool.letterIsFree('t'));
    }

    private class LettersForTest implements Letters {

        List<Letter> letters = new ArrayList<>();

        private int i = 0;

        public LettersForTest() {
            this.letters.add(new Letter('c', 1, 0));
            this.letters.add(new Letter('a', 2, 0));
            this.letters.add(new Letter('t', 3, 0));
            this.letters.add(new Letter('i', 1, 0));
            this.letters.add(new Letter('n', 2, 0));
            this.letters.add(new Letter('b', 3, 0));
            this.letters.add(new Letter('o', 1, 0));
            this.letters.add(new Letter('x', 2, 0));
            this.letters.add(new Letter('j', 3, 0));
            this.letters.add(new Letter('k', 1, 0));
            this.letters.add(new Letter('l', 2, 0));
//            this.letters.add(new Letter('e', 3, 0));
//            this.letters.add(new Letter('t', 1, 0));
//            this.letters.add(new Letter('o', 2, 0));
//            this.letters.add(new Letter('e', 3, 0));
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

        @Override
        public List<Letter> getAllLetters() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
