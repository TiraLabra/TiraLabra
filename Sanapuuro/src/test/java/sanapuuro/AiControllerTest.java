/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sanapuuro.letters.Letter;
import sanapuuro.letters.LetterContainer;
import sanapuuro.letters.LetterPool;
import sanapuuro.utils.Util;

/**
 *
 * @author skaipio
 */
public class AiControllerTest {
    private Player player;
    private AiController ai;
    private final Set<String> words = new HashSet<>();

    public AiControllerTest() {
        this.words.add("nipsuli");
        this.words.add("nipdsul");
        this.words.add("nihdsulx");
        this.words.add("hipsu");
        this.words.add("nipsu");
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
        this.player = new Player(lpstub , grid, "Hessu");
        this.ai = new AiController(lpstub, grid, "Hessu", this.words);
        this.ai.setControlled(player);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void submitsBestWord() {
        this.ai.makeMove();
        List<LetterContainer> submission = this.player.getSubmission();
        assertEquals("hipsu", Util.stringFromLetterContainers(submission));      
    }
    
    @Test
    public void submitsBestWordWithLettersAlreadyInGrid1() {
        LetterPool lpstub = new LetterPoolStub();
        Grid grid = new Grid(8, 8);
        LetterContainer lc = new LetterContainer(new Letter('d', 10, 0));
        grid.setContainerAt(lc, 3, 3);
        lc.setToGridPermanently();       
        this.player = new Player(lpstub , grid, "Hessu");
        this.ai = new AiController(lpstub, grid, "Hessu", this.words);
        this.ai.setControlled(player);
        this.ai.makeMove();
        List<LetterContainer> submission = this.player.getSubmission();
        assertEquals("nipdsul", Util.stringFromLetterContainers(submission));      
    }
    
    @Test
    public void submitsBestWordWithLettersAlreadyInGrid2() {
        LetterPool lpstub = new LetterPoolStub();
        Grid grid = new Grid(8, 8);
        LetterContainer lc = new LetterContainer(new Letter('d', 10, 0));
        grid.setContainerAt(lc, 3, 3);
        lc.setToGridPermanently();    
        LetterContainer lc1 = new LetterContainer(new Letter('h', 10, 0));
        grid.setContainerAt(lc1, 3, 3);
        lc1.setToGridPermanently();       
        this.player = new Player(lpstub , grid, "Hessu");
        this.ai = new AiController(lpstub, grid, "Hessu", this.words);
        this.ai.setControlled(player);
        this.ai.makeMove();
        List<LetterContainer> submission = this.player.getSubmission();
        assertEquals("nipdsul", Util.stringFromLetterContainers(submission));      
    }
    
    @Test
    public void submitsBestWordWithLettersAlreadyInGrid3() {
        LetterPool lpstub = new LetterPoolStub();
        Grid grid = new Grid(8, 8);
        LetterContainer lc = new LetterContainer(new Letter('d', 10, 0));
        grid.setContainerAt(lc, 3, 3);
        lc.setToGridPermanently();    
        LetterContainer lc1 = new LetterContainer(new Letter('h', 1, 0));
        grid.setContainerAt(lc1, 3, 3);
        lc1.setToGridPermanently();    
        LetterContainer lc2 = new LetterContainer(new Letter('x', 5, 0));
        grid.setContainerAt(lc2, 7, 3);
        lc2.setToGridPermanently();       
        this.player = new Player(lpstub , grid, "Hessu");
        this.ai = new AiController(lpstub, grid, "Hessu", this.words);
        this.ai.setControlled(player);
        this.ai.makeMove();
        List<LetterContainer> submission = this.player.getSubmission();
        assertEquals("nihdsulx", Util.stringFromLetterContainers(submission));      
    }
    
    public static class LetterPoolStub implements LetterPool {

        private final LetterContainer[] containers = new LetterContainer[8];
        private final boolean[] containersFree = new boolean[8];

        public LetterPoolStub() {
            this.containers[0] = new LetterContainer(new Letter('n', 5, 1));
            this.containers[1] = new LetterContainer(new Letter('i', 1, 1));
            this.containers[2] = new LetterContainer(new Letter('p', 1, 1));
            this.containers[3] = new LetterContainer(new Letter('s', 1, 1));
            this.containers[4] = new LetterContainer(new Letter('u', 1, 1));
            this.containers[5] = new LetterContainer(new Letter('l', 1, 1));
            this.containers[6] = new LetterContainer(new Letter('i', 1, 1));
            this.containers[7] = new LetterContainer(new Letter('h', 10, 1));
            for (int i = 0; i < containersFree.length; i++){
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

        @Override
        public boolean letterAtIndexIsFree(int i) {
            return this.containersFree[i];
        }
    }
    
    
}
