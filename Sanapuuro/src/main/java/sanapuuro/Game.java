/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.Random;
import java.util.Scanner;
import sanapuuro.fileio.LetterReader;
import sanapuuro.fileio.WordReader;
import sanapuuro.grid.Grid;
import sanapuuro.letters.LetterPool;
import sanapuuro.words.WordEvaluator;
import sanapuuro.words.WordList;

/**
 *
 * @author skaipio
 */
public class Game {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        Random rnd = new Random();
        WordList words = new WordReader();
        WordEvaluator evaluator = new WordEvaluator(words);
        LetterReader letterReader = new LetterReader(rnd);
        LetterPool letterPoolOne = new LetterPool(letterReader);
        LetterPool letterPoolTwo = new LetterPool(letterReader);
        
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
