/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro;

/**
 *
 * @author skaipio
 */
public interface GameListener {
    void turnStarted(Controller controllerWithTurn);
    void wordSubmitted(Player playerWithTurn, String submissionStr, boolean succeeded, String reason, int score);
    void playerSkipsTurn(Player playerWithTurn);
}
