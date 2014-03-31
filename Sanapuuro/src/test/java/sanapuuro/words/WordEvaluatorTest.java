/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.words;

import sanapuuro.words.WordEvaluator;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.fileio.WordReader;
import sanapuuro.grid.Grid;
import sanapuuro.grid.LetterContainer;
import sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class WordEvaluatorTest {

    private Grid grid;
    private final WordEvaluator wordEvaluator = new WordEvaluator(new WordReader());
    private final List<LetterContainer> validHorizontalWord = new ArrayList<>();
    private final List<LetterContainer> validVerticalWord = new ArrayList<>();
    private final List<LetterContainer> wordWithGaps = new ArrayList<>();
    private final List<LetterContainer> wordNotOnSameRow = new ArrayList<>();
    private final List<LetterContainer> wordTooShort = new ArrayList<>();

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.grid = new Grid(8, 8);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void correctValidationOfHorizontalWords() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('u', 2, 1));
        this.grid.setContainerAt(container, 1, 0);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('b', 3, 1));
        this.grid.setContainerAt(container, 2, 0);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('y', 4, 1));
        this.grid.setContainerAt(container, 3, 0);
        this.validHorizontalWord.add(container);
        assertTrue(this.wordEvaluator.evalute(validHorizontalWord).succeeded);
    }

    @Test
    public void correctValidationOfVerticalWords() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        this.validVerticalWord.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.setContainerAt(container, 0, 1);
        this.validVerticalWord.add(container);
        container = new LetterContainer(new Letter('b', 2, 1));
        this.grid.setContainerAt(container, 0, 2);
        this.validVerticalWord.add(container);
        container = new LetterContainer(new Letter('y', 1, 1));
        this.grid.setContainerAt(container, 0, 3);
        this.validVerticalWord.add(container);
        assertTrue(this.wordEvaluator.evalute(validVerticalWord).succeeded);
    }

    @Test
    public void doesNotValidateWordWithGaps() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        this.wordWithGaps.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.setContainerAt(container, 0, 1);
        this.wordWithGaps.add(container);
        container = new LetterContainer(new Letter('b', 2, 1));
        this.grid.setContainerAt(container, 0, 2);
        this.wordWithGaps.add(container);
        container = new LetterContainer(new Letter('y', 1, 1));
        this.grid.setContainerAt(container, 0, 4);
        this.wordWithGaps.add(container);
        assertFalse(this.wordEvaluator.evalute(wordWithGaps).succeeded);
    }

    @Test
    public void doesNotValidateWordNotOnSameRowOrColumn() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        this.wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.setContainerAt(container, 1, 1);
        this.wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('b', 2, 1));
        this.grid.setContainerAt(container, 0, 2);
        this.wordNotOnSameRow.add(container);
        container = new LetterContainer(new Letter('y', 1, 1));
        this.grid.setContainerAt(container, 0, 3);
        this.wordNotOnSameRow.add(container);
        assertFalse(this.wordEvaluator.evalute(wordNotOnSameRow).succeeded);
    }

    @Test
    public void wordsTooShortNotValid() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        this.wordTooShort.add(container);
        container = new LetterContainer(new Letter('u', 1, 1));
        this.grid.setContainerAt(container, 0, 1);
        this.wordTooShort.add(container);
        assertFalse(this.wordEvaluator.evalute(wordTooShort).succeeded);
    }

    @Test
    public void returnCorrectScoreForLetters() {
        LetterContainer container = new LetterContainer(new Letter('r', 1, 1));
        this.grid.setContainerAt(container, 0, 0);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('u', 2, 1));
        this.grid.setContainerAt(container, 1, 0);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('b', 3, 1));
        this.grid.setContainerAt(container, 2, 0);
        this.validHorizontalWord.add(container);
        container = new LetterContainer(new Letter('y', 4, 1));
        this.grid.setContainerAt(container, 3, 0);
        this.validHorizontalWord.add(container);
        int score = this.wordEvaluator.evalute(validHorizontalWord).getScore();
        assertEquals(1 + 2 + 3 + 4, score);
    }
}
