/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import sanapuuro.utils.Util;
import sanapuuro.ui.View;
import java.util.List;
import sanapuuro.letters.LetterContainer;
import sanapuuro.letters.Letters;
import sanapuuro.WordEvaluator.EvaluationResult;
import sanapuuro.WordEvaluator.Submission;

/**
 *
 * @author skaipio
 */
public class TwoPlayerGame {

    private final WordEvaluator evaluator;
    private Player playerWithTurn;
    private Player playerWaiting;
    private final Grid grid;
    private int successiveSkips = 0;

    private final View view;

    public TwoPlayerGame(Grid grid, Player playerOne, Player playerTwo, Letters letters, WordEvaluator evaluator, View view) {
        this.grid = grid;
        this.playerWithTurn = playerOne;
        this.playerWaiting = playerTwo;
        this.evaluator = evaluator;
        this.view = view;
    }

    public void startGame() {
        while (true) {
            this.view.updateView(this.playerWithTurn.getController());
            this.view.showMessage(this.playerWithTurn + "'s turn: ");
            Submission submission = this.playerWithTurn.getSubmission();
            
            if (!submission.letterContainers.isEmpty()) {
                String submissionStr = Util.stringFromLetterContainers(submission.letterContainers).toUpperCase();
                this.view.showMessage(this.playerWithTurn + " submitted the word " + submissionStr);
                EvaluationResult result = this.evaluator.evalute(submission);
                if (result.succeeded) {
                    System.out.println(this.playerWithTurn + " was awarded " + result.getScore() + " points");
                    this.playerWithTurn.successfulSubmission(result.getScore());
                }else{
                    System.out.println(submissionStr + " is not a valid word");
                    this.playerWithTurn.unsuccessfulSubmission();
                }
                successiveSkips = 0;              
            }else{
                this.view.showMessage(this.playerWithTurn + " skips their turn");
                successiveSkips++;
            }
            
            if (this.grid.isFull() || successiveSkips == 2) {
                break;
            }
            
            this.swapTurn();
        }
    }

    private void swapTurn() {
        Player temp = this.playerWithTurn;
        this.playerWithTurn = this.playerWaiting;
        this.playerWaiting = temp;
    }
}
