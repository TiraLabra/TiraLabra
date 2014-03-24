/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro;

/**
 * Listener to GameTimer.
 * @author skaipio
 */
public interface GameTimerListener {
    /**
     * A method for handling the time out event.
     */
    void notifyTimeOut();
}
