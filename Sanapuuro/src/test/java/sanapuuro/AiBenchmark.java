package sanapuuro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import sanapuuro.datastructures.DJB2ForStrings;
import sanapuuro.datastructures.MyHashSet;
import sanapuuro.fileio.FileIO;
import sanapuuro.letters.Letter;
import sanapuuro.letters.LetterContainer;
import sanapuuro.letters.LetterPool;
import sanapuuro.utils.Util;

/**
 *
 * @author skaipio
 */
public class AiBenchmark {

    private Player player;
    private AiController ai;
    private final MyHashSet<String> words;

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    public AiBenchmark() {
        FileIO io = new FileIO();
        List<String> wordsFromFile = io.readInWordsFromFile("words/english_words", 8);
        this.words = Util.convertListToMyHashSet(wordsFromFile, new DJB2ForStrings());
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        LetterPool lpstub = new LetterPoolStub();
        Grid grid = new Grid(8, 8);
        this.player = new Player(lpstub, grid, "Hessu");
        this.ai = new AiController(lpstub, grid, "Hessu", this.words);
        this.ai.setControlled(player);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAiSpeed() {
        this.ai.makeMove();
        List<LetterContainer> submission = this.player.getSubmission();
        assertEquals("xeroxing", Util.stringFromLetterContainers(submission));    
    }

    private static class LetterPoolStub implements LetterPool {

        private final LetterContainer[] containers = new LetterContainer[8];
        private final boolean[] containersFree = new boolean[8];

        public LetterPoolStub() {
            this.containers[0] = new LetterContainer(new Letter('r', 1, 1));
            this.containers[1] = new LetterContainer(new Letter('x', 1, 1));
            this.containers[2] = new LetterContainer(new Letter('i', 1, 1));
            this.containers[3] = new LetterContainer(new Letter('n', 1, 1));
            this.containers[4] = new LetterContainer(new Letter('x', 1, 1));
            this.containers[5] = new LetterContainer(new Letter('o', 1, 1));
            this.containers[6] = new LetterContainer(new Letter('g', 1, 1));
            this.containers[7] = new LetterContainer(new Letter('e', 1, 1));
            for (int i = 0; i < containersFree.length; i++) {
                containersFree[i] = true;
            }
        }

        @Override
        public LetterContainer[] getLetters() {
            return this.containers;
        }

        @Override
        public boolean letterIsFree(char c) {
            for (int i = 0; i < 8; i++) {
                if (this.containersFree[i] && this.containers[i].letter.character == c) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public LetterContainer useLetter(char c) {
            for (int i = 0; i < 8; i++) {
                if (this.containersFree[i] && this.containers[i].letter.character == c) {
                    this.containersFree[i] = false;
                    return this.containers[i];
                }
            }
            return null;
        }

        @Override
        public void unpickLetter(char c) {
        }

        @Override
        public void clearLetterPicks() {

        }

        @Override
        public void replacePickedLetters() {

        }
    }
}
