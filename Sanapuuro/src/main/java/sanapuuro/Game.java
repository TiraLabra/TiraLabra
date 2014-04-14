/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.Iterator;
import sanapuuro.ui.GameView;
import sanapuuro.ui.ConsoleController;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import sanapuuro.datastructures.MyHashSet;
import sanapuuro.datastructures.StringHashFuncs;
import sanapuuro.datastructures.Util;
import sanapuuro.fileio.FileIO;
import sanapuuro.letters.GameLetters;
import sanapuuro.letters.PlayerLetterPool;

/**
 *
 * @author skaipio
 */
public class Game {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        FileIO fileIO = new FileIO();
        Random rnd = new Random();
        
        Set<String> words = fileIO.readInWordsFromFile("words/english_words");
        WordEvaluator evaluator = new WordEvaluator(words);
        GameLetters letterReader = new GameLetters(rnd, fileIO.readInLettersFromFile("letters/english_letters"));
        PlayerLetterPool letterPoolOne = new PlayerLetterPool(letterReader);
        PlayerLetterPool letterPoolTwo = new PlayerLetterPool(letterReader);
        
        Scanner scanner = new Scanner(System.in);
        ConsoleController controllerOne = new ConsoleController(scanner, 8, 8);
        ConsoleController controllerTwo = new ConsoleController(scanner, 8, 8);
        
        Grid grid = new Grid(8, 8);
        
        HumanPlayer playerOne = new HumanPlayer(controllerOne, letterPoolOne, grid, "Hessu");
        AiPlayer playerTwo = new AiPlayer(letterPoolTwo, grid, "Mikki", words);
        
        GameView view = new GameView(grid, playerOne, playerTwo, letterReader);
        controllerOne.addListener(view);
        controllerTwo.addListener(view);
        
        TwoPlayerGame game = new TwoPlayerGame(grid, playerOne, playerTwo, letterReader, evaluator, view);
        game.startGame();
    }
}
