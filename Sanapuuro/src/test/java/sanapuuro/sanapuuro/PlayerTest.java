/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.sanapuuro.grid.Grid;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.gui.TimerWrapper;
import sanapuuro.sanapuuro.letters.Letter;
import sanapuuro.sanapuuro.letters.LetterPool;
import sanapuuro.sanapuuro.letters.Letters;
import sanapuuro.sanapuuro.words.WordEvaluator;

/**
 *
 * @author skaipio
 */
public class PlayerTest {

    private Player player;
    private LetterPool letterPool;
    private Grid grid;
    private final Evaluation evaluation = new Evaluation();
    private final WordEvaluator evaluator = new WordEvaluator();

    public PlayerTest() {

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
        Letters letters = new LettersForTest();
        this.player = new Player(grid, evaluation, letters);
        this.evaluation.registerPlayer(player, new TimerWrapper());
        this.letterPool = this.player.getLetterPool();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void letterIsAddedToCellIfCellEmpty() {
        boolean letterWasSet = this.player.addLetterTo(0, 0);
        assertTrue(letterWasSet);
        assertTrue(this.grid.hasContainerAt(0, 0));
        assertFalse(this.player.getAddedContainers().isEmpty());
    }

    @Test
    public void letterIsNotAddedToCellIfCellNotEmptyAndIsStillAvailableInLetterPool() {
        Letter letter = new Letter('a', 0, 0);
        this.grid.setContainerAt(new LetterContainer(letter), 0, 0);
        LetterContainer container = this.grid.getContainerAt(0, 0);
        boolean letterWasSet = this.player.addLetterTo(0, 0);
        assertFalse(letterWasSet);
        assertSame(this.grid.getContainerAt(0, 0), container);
        assertTrue(this.player.getAddedContainers().isEmpty());
        assertFalse(this.letterPool.isIndexUsed(0));
    }

    @Test
    public void letterIsSelectedIfCellIsNotSelected() {
        Letter letter = new Letter('a', 0, 0);
        this.grid.setContainerAt(new LetterContainer(letter), 0, 0);
        boolean letterWasSelected = this.player.selectLetterAt(0, 0);
        assertTrue(letterWasSelected);
        assertTrue(this.player.getAddedContainers().isEmpty());
        assertFalse(this.player.getSelectedContainers().isEmpty());
    }

    @Test
    public void letterIsNotSelectedIfCellIsSelected() {
        Letter letter = new Letter('a', 0, 0);
        this.grid.setContainerAt(new LetterContainer(letter), 0, 0);
        boolean letterWasSelected = this.player.selectLetterAt(0, 0);
        assertTrue(letterWasSelected);
        assertTrue(this.player.getAddedContainers().isEmpty());
        assertFalse(this.player.getSelectedContainers().isEmpty());
        letterWasSelected = this.player.selectLetterAt(0, 0);
        assertFalse(letterWasSelected);
        assertFalse(this.player.getSelectedContainers().size() > 1);
    }

    @Test
    public void lettersAreNotSelectedOrAddedIfControlsAreNotEnabled() {
        this.player.setControlsEnabled(false);

        boolean letterWasSet = this.player.addLetterTo(0, 0);
        assertFalse(letterWasSet);
        assertFalse(this.grid.hasContainerAt(0, 0));
        assertTrue(this.player.getAddedContainers().isEmpty());

        Letter letter = new Letter('a', 0, 0);
        this.grid.setContainerAt(new LetterContainer(letter), 0, 0);
        boolean letterWasSelected = this.player.selectLetterAt(0, 0);
        assertFalse(letterWasSelected);
        assertTrue(this.player.getSelectedContainers().isEmpty());
    }

    @Test
    public void playerGetsScoreForSubmittedWordWhenAllAddedByPlayer() {
        this.player.addLetterTo(0, 0);
        this.letterPool.setCurrentSelection(1);
        this.player.addLetterTo(0, 1);
        this.letterPool.setCurrentSelection(2);
        this.player.addLetterTo(0, 2);

        this.player.selectLetterAt(0, 0);
        this.player.selectLetterAt(0, 1);
        this.player.selectLetterAt(0, 2);

        boolean submission = player.submitSelectedLetters();
        assertTrue(submission);
        assertEquals(6, player.getScore());
    }

    @Test
    public void playerGetsScoreForSubmittedWordWhenAllPreAddedAndThenSelected() {
        this.grid.setContainerAt(new LetterContainer(new Letter('t', 1, 0)), 0, 0);
        this.grid.setContainerAt(new LetterContainer(new Letter('o', 2, 0)), 0, 1);
        this.grid.setContainerAt(new LetterContainer(new Letter('e', 3, 0)), 0, 2);

        this.player.selectLetterAt(0, 0);
        this.player.selectLetterAt(0, 1);
        this.player.selectLetterAt(0, 2);

        boolean submission = player.submitSelectedLetters();
        assertTrue(submission);
        assertEquals(6, player.getScore());
    }

    @Test
    public void submissionFailsIfControlsNotEnabled() {
        this.grid.setContainerAt(new LetterContainer(new Letter('t', 1, 0)), 0, 0);
        this.grid.setContainerAt(new LetterContainer(new Letter('o', 2, 0)), 0, 1);
        this.grid.setContainerAt(new LetterContainer(new Letter('e', 3, 0)), 0, 2);

        this.player.selectLetterAt(0, 0);
        this.player.selectLetterAt(0, 1);
        this.player.selectLetterAt(0, 2);

        this.player.setControlsEnabled(false);
        boolean submission = player.submitSelectedLetters();
        assertFalse(submission);
        assertEquals(0, player.getScore());
    }

    @Test
    public void addedLettersAreReplacedOnSuccessfulSubmission() {
        this.player.addLetterTo(0, 0);
        this.letterPool.setCurrentSelection(1);
        this.player.addLetterTo(0, 1);
        this.letterPool.setCurrentSelection(2);
        this.player.addLetterTo(0, 2);
        this.player.selectLetterAt(0, 0);
        this.player.selectLetterAt(0, 1);
        this.player.selectLetterAt(0, 2);

        List<LetterContainer> addedContainers = this.player.getAddedContainers();

        this.player.submitSelectedLetters();

        for (LetterContainer poolContainer : this.letterPool.getLetters()) {
            for (LetterContainer addedContainer : addedContainers) {
                assertNotSame(addedContainer, poolContainer);
            }
        }
    }

    @Test
    public void getsLastSelectionIfSelectedContainersIsNotEmpty() {
        this.grid.setContainerAt(new LetterContainer(new Letter('t', 1, 0)), 0, 0);
        this.grid.setContainerAt(new LetterContainer(new Letter('o', 2, 0)), 0, 1);

        this.player.selectLetterAt(0, 0);
        this.player.selectLetterAt(0, 1);

        LetterContainer container = this.grid.getContainerAt(0, 1);
        assertEquals(container, player.getLastSelection());

        this.player.addLetterTo(0, 2);
        this.player.selectLetterAt(0, 2);
        container = this.grid.getContainerAt(0, 2);
        assertEquals(container, player.getLastSelection());
    }

    @Test
    public void getsFirstSelectionIfSelectedContainersIsNotEmpty() {
        this.grid.setContainerAt(new LetterContainer(new Letter('t', 1, 0)), 0, 0);
        this.grid.setContainerAt(new LetterContainer(new Letter('o', 2, 0)), 0, 1);

        this.player.selectLetterAt(0, 0);
        this.player.selectLetterAt(0, 1);

        LetterContainer container = this.grid.getContainerAt(0, 0);
        assertEquals(container, player.getFirstSelection());

        this.player.addLetterTo(0, 2);
        this.player.selectLetterAt(0, 2);
        assertEquals(container, player.getFirstSelection());
    }

    @Test
    public void firstAndLastSelectionNullIfSelectedContainersIsEmpty() {
        LetterContainer container = this.player.getFirstSelection();
        assertNull(container);

        container = this.player.getLastSelection();
        assertNull(container);
    }

    @Test
    public void lastPickedLetterRemovedFromSelectedContainersOnRemoveLastSelection() {
        this.grid.setContainerAt(new LetterContainer(new Letter('t', 1, 0)), 0, 0);
        this.grid.setContainerAt(new LetterContainer(new Letter('o', 2, 0)), 0, 1);

        this.player.selectLetterAt(0, 0);
        this.player.selectLetterAt(0, 1);

        this.player.removeLastSelection();
        assertTrue(this.grid.hasContainerAt(0, 1));
        assertFalse(this.player.getSelectedContainers().contains(this.grid.getContainerAt(0, 1)));

        this.player.removeLastSelection();
        assertTrue(this.grid.hasContainerAt(0, 0));
        assertFalse(this.player.getSelectedContainers().contains(this.grid.getContainerAt(0, 0)));
    }

    @Test
    public void lastPickedLetterRemovedFromSelectedContainersAndAddedContainersIfIsAddedByPlayer() {
        this.player.addLetterTo(0, 0);
        this.letterPool.setCurrentSelection(1);
        this.player.addLetterTo(0, 1);
        this.player.selectLetterAt(0, 0);
        this.player.selectLetterAt(0, 1);

        LetterContainer l1 = this.grid.getContainerAt(0, 0);
        LetterContainer l2 = this.grid.getContainerAt(0, 1);

        this.player.removeLastSelection();
        assertFalse(this.grid.hasContainerAt(0, 1));
        assertFalse(this.player.getSelectedContainers().contains(l2));
        assertFalse(this.player.getAddedContainers().contains(l2));

        this.player.removeLastSelection();
        assertFalse(this.grid.hasContainerAt(0, 0));
        assertFalse(this.player.getSelectedContainers().contains(l1));
        assertFalse(this.player.getAddedContainers().contains(l1));
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
