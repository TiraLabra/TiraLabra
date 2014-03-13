/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import sanapuuro.sanapuuro.GameTimer;
import sanapuuro.sanapuuro.GameTimerListener;

/**
 *
 * @author skaipio
 */
public class TimerWrapper implements GameTimer, ActionListener {

    private int seconds = 0;
    private int minutes = 0;
    private final List<GameTimerListener> countDownListener;
    private final List<ActionListener> otherListeners;
    private final Timer timer;

    public TimerWrapper() {
        this.countDownListener = new ArrayList<>();
        this.otherListeners = new ArrayList<>();
        this.timer = new Timer(1000, this);
    }

    public int getSeconds() {
        return this.seconds;
    }

    public int getMinutes() {
        return this.minutes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.seconds--;

        if (this.minutes == 0 && this.seconds == 0) {
            for (GameTimerListener listener : countDownListener) {
                listener.notifyTimeOut();
            }
            this.timer.stop();
        } else if (this.seconds < 0) {
            this.minutes--;
            this.seconds = 59;
        }

        for (ActionListener listener : otherListeners) {
            listener.actionPerformed(new ActionEvent(this, 0, ""));
        }
        
    }

    @Override
    public void addListener(GameTimerListener listener) {
        this.countDownListener.add(listener);
    }

    public void addActionListener(ActionListener listener) {
        this.otherListeners.add(listener);
    }

    @Override
    public void startCountdownFrom(int seconds) {
        this.seconds = seconds % 60;
        this.minutes = seconds / 60;
        this.timer.restart();
    }

    @Override
    public void decreaseTime(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("Can't decrease timer by a negative number.");
        }
        if (seconds <= this.seconds) {
            this.seconds -= seconds;
        } else {
            int diff = seconds - this.seconds;
            int minutes = seconds / 60;
            this.minutes -= minutes;
            this.seconds = 60 - diff;
        }
    }

    @Override
    public void addTime(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("Can't add to timer by a negative number.");
        }
        this.seconds += seconds;
        this.minutes += this.seconds / 60;
        this.seconds = this.seconds % 60;
    }
}
