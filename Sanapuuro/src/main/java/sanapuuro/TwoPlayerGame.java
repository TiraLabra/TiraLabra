/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.List;
import sanapuuro.grid.Grid;
import sanapuuro.grid.LetterContainer;
import sanapuuro.letters.Letters;
import sanapuuro.words.WordEvaluator;
import sanapuuro.words.WordEvaluator.EvaluationResult;

/**
 *
 * @author skaipio
 */
public class TwoPlayerGame {

    private final WordEvaluator evaluator = new WordEvaluator();
//    private final Player playerOne, playerTwo;
    private Player playerWithTurn;
    private Player playerWaiting;
    private final Grid grid;
    private int successiveSkips = 0;

    private final GameView view;

    public TwoPlayerGame(Grid grid, Player playerOne, Player playerTwo, Letters letters) {
        this.grid = grid;
//        this.playerOne = playerOne;
//        this.playerTwo = playerTwo;
        this.playerWithTurn = playerOne;
        this.playerWaiting = playerTwo;
        this.view = new GameView(grid, playerOne, playerTwo, letters);
    }

    public void startGame() {
        while (true) {
            this.view.printView(this.playerWithTurn.getController());
            this.view.printMessage(this.playerWithTurn + "'s turn: ");
            this.playerWithTurn.makeMove();
            if (this.playerWithTurn.hasMadeASubmission()) {
                this.view.printMessage(this.playerWithTurn + " submitted the word " +
                        this.stringFromLetterContainers(this.playerWithTurn.getContainersForSubmission()).toUpperCase());
                EvaluationResult result = this.evaluator.evalute(playerWithTurn.getContainersForSubmission());
                if (result.succeeded) {
                    this.playerWithTurn.successfulSubmission();
                    this.playerWithTurn.addScore(result.getScore());
                }else{
                    this.playerWithTurn.unsuccessfulSubmission();
                }
                this.swapTurn();
            }
            if (this.playerWithTurn.isSkipping()) {
                swapTurn();
                successiveSkips++;
            }else{
                successiveSkips = 0;
            }
            if (this.grid.isFull() || successiveSkips == 2) {
                break;
            }
        }
    }

    private void swapTurn() {
        Player temp = this.playerWithTurn;
        this.playerWithTurn = this.playerWaiting;
        this.playerWaiting = temp;
    }
    
    private String stringFromLetterContainers(List<LetterContainer> containers){
        StringBuilder letters = new StringBuilder();
        for(LetterContainer container : containers){
            letters.append(container.letter.character);
        }
        return letters.toString();
    }
}
