/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.letters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.fileio.FileIO;

/**
 *
 * @author skaipio
 */
public class GameLettersTest {
    private List<Letter> letters = new ArrayList<>();
    private Set<Character> englishLetters;
    private GameLetters letterReader;
    
    public GameLettersTest() {
        FileIO fileIO = new FileIO();
        this.letters = fileIO.readInLettersFromFile("letters/english_letters");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.letterReader = new GameLetters(new Random(1), this.letters);
        this.englishLetters = new HashSet<>();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < letters.length(); i++){
            this.englishLetters.add(letters.charAt(i));
        }
    }
    
    @After
    public void tearDown() {
    }
     
         
    @Test
     public void correctlyMatchesLetters() {
         Letter letter = this.letterReader.getLetterMatchingCharacter('a');
         assertEquals('a', letter.character);
         assertEquals(0.0812f, letter.frequency, 0.0000001f);
         assertEquals(1, letter.score);
         letter = this.letterReader.getLetterMatchingCharacter('å');
         assertNull(letter);
         letter = this.letterReader.getLetterMatchingCharacter('z');
         assertEquals('z', letter.character);
         assertEquals(0.0007f, letter.frequency, 0.0000001f);
         assertEquals(10, letter.score);
     }
    
     @Test
     public void allLettersAreProduced() {
         for (int i = 0; i < 1000; i++){
             Letter letter = this.letterReader.getRandomLetter();
             this.englishLetters.remove(letter.character);
             if (this.englishLetters.isEmpty()) break;
         }
         StringBuilder lettersNotProduced = new StringBuilder();
         if (!this.englishLetters.isEmpty()){
             for(char c : this.englishLetters){
                 lettersNotProduced.append(c + " ");
             }
         }
         assertTrue(this.englishLetters.size() + " letters were not produced (" + lettersNotProduced + ")", this.englishLetters.isEmpty());
     }
}
