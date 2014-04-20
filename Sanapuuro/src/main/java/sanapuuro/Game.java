/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import sanapuuro.datastructures.MyHashSet;
import sanapuuro.datastructures.FNVOneForString;
import sanapuuro.datastructures.HashFuncWithBigIntsForString;
import sanapuuro.fileio.FileIO;
import sanapuuro.letters.GameLetters;
import sanapuuro.letters.Letter;
import sanapuuro.letters.LetterContainer;
import sanapuuro.letters.LetterPool;
import sanapuuro.letters.PlayerLetterPool;
import sanapuuro.ui.ConsoleView;
import sanapuuro.ui.HumanConsoleController;

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

        List<String> words = fileIO.readInWordsFromFile("words/english_words");
        MyHashSet<String> wordSet = sanapuuro.utils.Util.convertListToMyHashSet(words, new FNVOneForString());
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
        
//        LetterContainer[] containers = new LetterContainer[]{
//            new LetterContainer(new Letter('a', 0, 0)),
//            new LetterContainer(new Letter('b', 0, 0)),
//            new LetterContainer(new Letter('c', 0, 0))};
//        controllerTwo.tryPermutations(containers, 0);
    }
}
