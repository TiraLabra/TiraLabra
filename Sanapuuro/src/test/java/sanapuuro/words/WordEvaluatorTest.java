/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.words;

import sanapuuro.WordEvaluator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.fileio.FileIO;
import sanapuuro.Grid;
import sanapuuro.letters.LetterContainer;
import sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class WordEvaluatorTest {

    private Grid grid;
    private final Set<String> words;
    private WordEvaluator wordEvaluator;

    public WordEvaluatorTest() {
        FileIO fileIO = new FileIO();
        this.words = fileIO.readInWordsFromFile("words/english_words");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.grid = new Grid(8, 8);
        this.wordEvaluator = new WordEvaluator(this.words);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void correctValidationOfHorizontalWords() {
        List<LetterContainer> validHorizontalWord = new ArrayList<>();
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('u', 2, 1));
        this.grid.setContainerAt(container, 1, 0);
        validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('b', 3, 1));
        this.grid.setContainerAt(container, 2, 0);
        validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('y', 4, 1));
        this.grid.setContainerAt(container, 3, 0);
        validHorizontalWord.add(container);
        assertTrue(this.wordEvaluator.evalute(new WordEvaluator.Submission(validHorizontalWord, 1, 0)).succeeded);
    }

    @Test
    public void correctValidationOfVerticalWords() {
        List<LetterContainer> validVerticalWord = new ArrayList<>();
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        validVerticalWord.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.setContainerAt(container, 0, 1);
        validVerticalWord.add(container);
        container = new LetterContainer(new Letter('b', 2, 1));
        this.grid.setContainerAt(container, 0, 2);
        validVerticalWord.add(container);
        container = new LetterContainer(new Letter('y', 1, 1));
        this.grid.setContainerAt(container, 0, 3);
        validVerticalWord.add(container);
        assertTrue(this.wordEvaluator.evalute(new WordEvaluator.Submission(validVerticalWord, 0, 1)).succeeded);
    }

    @Test
    public void doesNotValidateWordWithGaps() {
        List<LetterContainer> wordWithGaps = new ArrayList<>();
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        wordWithGaps.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.setContainerAt(container, 0, 1);
        wordWithGaps.add(container);
        container = new LetterContainer(new Letter('b', 2, 1));
        this.grid.setContainerAt(container, 0, 2);
        wordWithGaps.add(container);
        container = new LetterContainer(new Letter('y', 1, 1));
        this.grid.setContainerAt(container, 0, 4);
        wordWithGaps.add(container);
        assertFalse(this.wordEvaluator.evalute(new WordEvaluator.Submission(wordWithGaps, 1, 0)).succeeded);
    }

    @Test
    public void doesNotValidateWordNotOnSameRowOrColumn() {
        List<LetterContainer> wordNotOnSameRow = new ArrayList<>();
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.setContainerAt(container, 1, 1);
        wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('b', 2, 1));
        this.grid.setContainerAt(container, 0, 2);
        wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('y', 1, 1));
        this.grid.setContainerAt(container, 0, 3);
        wordNotOnSameRow.add(container);
        assertFalse(this.wordEvaluator.evalute(new WordEvaluator.Submission(wordNotOnSameRow, 1, 0)).succeeded);
    }

    @Test
    public void wordsTooShortNotValid() {
        List<LetterContainer> wordTooShort = new ArrayList<>();
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        wordTooShort.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.setContainerAt(container, 0, 1);
        wordTooShort.add(container);
        assertFalse(this.wordEvaluator.evalute(new WordEvaluator.Submission(wordTooShort, 1, 0)).succeeded);
    }

    @Test
    public void returnCorrectScoreForLetters() {
        List<LetterContainer> validHorizontalWord = new ArrayList<>();
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('u', 2, 1));
        this.grid.setContainerAt(container, 1, 0);
        validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('b', 3, 1));
        this.grid.setContainerAt(container, 2, 0);
        validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('y', 4, 1));
        this.grid.setContainerAt(container, 3, 0);
        validHorizontalWord.add(container);
        int score = this.wordEvaluator.evalute(new WordEvaluator.Submission(validHorizontalWord, 1, 0)).getScore();
        assertEquals(1 + 2 + 3 + 4, score);
    }
}
