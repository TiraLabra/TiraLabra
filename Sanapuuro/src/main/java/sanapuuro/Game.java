/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.Random;
import java.util.Scanner;
import sanapuuro.filereaders.LetterReader;
import sanapuuro.grid.Grid;
import sanapuuro.letters.LetterPool;

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
        LetterReader letterReader = new LetterReader(rnd);
        LetterPool letterPoolOne = new LetterPool(letterReader);
        LetterPool letterPoolTwo = new LetterPool(letterReader);
        
        Scanner scanner = new Scanner(System.in);
        ConsoleController controllerOne = new ConsoleController(scanner, 8, 8);
        ConsoleController controllerTwo = new ConsoleController(scanner, 8, 8);
        
        Grid grid = new Grid(8, 8);
        
        HumanPlayer humanPlayerOne = new HumanPlayer(controllerOne, letterPoolOne, grid, "Hessu");
        HumanPlayer humanPlayerTwo = new HumanPlayer(controllerTwo, letterPoolTwo, grid, "Mikki");
        
        TwoPlayerGame game = new TwoPlayerGame(grid, humanPlayerOne, humanPlayerTwo, letterReader);
        game.startGame();
    }
}
