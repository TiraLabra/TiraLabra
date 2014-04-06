/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.letters;

import sanapuuro.letters.Letters;
import sanapuuro.letters.PlayerLetterPool;
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

    private PlayerLetterPool letterPool;

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
        this.letterPool = new PlayerLetterPool(new LettersForTest());
    }

    @After
    public void tearDown() {
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

        @Override
        public List<Letter> getAllLetters() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
