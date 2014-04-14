/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.List;
import java.util.Set;
import sanapuuro.utils.Util;
import sanapuuro.letters.Letters;
import sanapuuro.WordEvaluator.EvaluationResult;
import sanapuuro.letters.LetterContainer;
import sanapuuro.letters.LetterPool;
import sanapuuro.letters.PlayerLetterPool;

/**
 *
 * @author skaipio
 */
public class TwoPlayerGame {

    private final WordEvaluator evaluator;
    private Controller controllerWithTurn;
    private Controller controllerWaiting;
    private final Grid grid;
    private int successiveSkips = 0;
    private GameListener listener;

    public TwoPlayerGame(Grid grid, Controller playerOne, Controller playerTwo, Set<String> words) {
        this.grid = grid;
        this.controllerWithTurn = playerOne;
        this.controllerWaiting = playerTwo;
        this.evaluator = new WordEvaluator(words);
    }
    
    public void setGameListener(GameListener listener){
        this.listener = listener;
    }

    public void startGame() {
        while (true) {
            if (this.listener != null){
                this.listener.turnStarted(this.controllerWithTurn);
            }
            this.controllerWithTurn.makeMove();
            Player playerWithTurn = (Player)this.controllerWithTurn.getControlled();
            
            List<LetterContainer> submission = playerWithTurn.getSubmission();
            if (!submission.isEmpty()) {
                String submissionStr = Util.stringFromLetterContainers(submission).toUpperCase();               
                EvaluationResult result = this.evaluator.evalute(submission);
                if (result.succeeded) {
                    playerWithTurn.successfulSubmission(result.getScore());
                }else{
                    playerWithTurn.unsuccessfulSubmission();
                }
                this.listener.wordSubmitted(playerWithTurn, submissionStr, result.succeeded, result.reason, result.getScore());
                successiveSkips = 0;              
            }else{
                this.listener.playerSkipsTurn(playerWithTurn);
                successiveSkips++;
            }
            
            if (this.grid.isFull() || successiveSkips == 2) {
                break;
            }
            
            this.swapTurn();
        }
    }

    private void swapTurn() {
        Controller temp = this.controllerWithTurn;
        this.controllerWithTurn = this.controllerWaiting;
        this.controllerWaiting = temp;
    }
}
