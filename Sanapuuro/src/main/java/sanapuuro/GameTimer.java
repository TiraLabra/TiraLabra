/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro;

/**
 * A timer counting down to zero.
 * @author skaipio
 */
public interface GameTimer {
    /**
     * Add listener to be notified of on time out.
     * @param listener Listener.
     */
    void addListener(GameTimerListener listener);
    /**
     * Set time to start count down from.
     * @param seconds Start time in seconds.
     */
    void startCountdownFrom(int seconds);
    /**
     * Speed count down by taking off seconds.
     * @param seconds Time to take off.
     */
    void decreaseTime(int seconds);
    /**
     * Put more time into the count down.
     * @param seconds Time to increase by in seconds.
     */
    void addTime(int seconds);
}
