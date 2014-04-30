/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import sanapuuro.hashfunctions.DJB2ForStrings;
import sanapuuro.datastructures.MyHashSet;
import sanapuuro.fileio.FileIO;
import sanapuuro.letters.GameLetters;
import sanapuuro.letters.LetterPool;
import sanapuuro.letters.PlayerLetterPool;
import sanapuuro.ui.ConsoleView;
import sanapuuro.ui.HumanConsoleController;
import sanapuuro.ui.SanapuuroSwingApp;

/**
 *
 * @author skaipio
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-w")) {
            SanapuuroSwingApp.main(args);
        } else {
            FileIO fileIO = new FileIO();
            Random rnd = new Random();

            List<String> words = fileIO.readInWordsFromFile("words/english_words", 8);
            MyHashSet<String> wordSet = sanapuuro.utils.Util.convertListToMyHashSet(words, new DJB2ForStrings());
            GameLetters letters = new GameLetters(rnd, fileIO.readInLettersFromFile("letters/english_letters"));

            Scanner scanner = new Scanner(System.in);
            Grid grid = new Grid(8, 8);

            LetterPool poolOne = new PlayerLetterPool(letters);
            LetterPool poolTwo = new PlayerLetterPool(letters);
            Player playerOne = new Player(poolOne, grid, "Hessu");
            Player playerTwo = new Player(poolTwo, grid, "Mikki");
            HumanConsoleController controllerOne = new HumanConsoleController(scanner, 8, 8);
            AiController controllerTwo = new AiController(poolTwo, grid, "Mikki", wordSet);
            controllerOne.setControlled(playerOne);
            controllerTwo.setControlled(playerTwo);

            ConsoleView view = new ConsoleView(grid, controllerOne, controllerTwo, letters);
            controllerOne.addConsoleListener(view);

            TwoPlayerGame game = new TwoPlayerGame(grid, controllerOne, controllerTwo, wordSet);
            game.setGameListener(view);
            game.startGame();
        }
    }
}
