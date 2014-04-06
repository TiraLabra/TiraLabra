/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.grid.Grid;
import sanapuuro.grid.LetterContainer;
import sanapuuro.letters.Letter;
import sanapuuro.words.WordEvaluator.Submission;
import sanapuuro.words.WordList;

/**
 *
 * @author skaipio
 */
public class AiPlayerTest {
    private AiPlayer ai;
    private WordList words;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ai = new AiPlayer(new LetterPoolStub(), new Grid(8,8), "Hessu", new WordListStub());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void scoreRaisedOnSuccessfulSubmission() {
        this.ai.successfulSubmission(10);
        assertEquals(10, this.ai.getScore());       
    }
    
    @Test
    public void submitsBestWord() {
        Submission submission = this.ai.getSubmission();
        assertEquals("hipsu", Util.stringFromLetterContainers(submission.letterContainers));      
    }
    
    public static class WordListStub implements WordList{
        List<String> words = new ArrayList<>();
        
        public WordListStub(){
            this.words.add("nipsuli");
            this.words.add("hipsu");
            this.words.add("nipsu");
        }

        @Override
        public boolean hasWord(String word) {
            return this.words.contains(word);
        }
        
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
    }
    
    
}
