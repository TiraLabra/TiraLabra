package com.mycompany.tiralabra_maven.player;

import java.io.File;

/**
 * Bot uses ai to play. 
 * @author Joel Nummelin
 */
public class Bot {

    private Ai ai;

    public Bot(int type) {
        if (type == 0) {
            ai = new AdvancedAi();
        } else {
            ai = new SimpleAi();
        }
    }

    public int makeAMove() {
        return ai.determineMove();
    }

    public void upDateAi(int result) {
        ai.update(result);
    }

    public void loadProfile(File file) {
    }
}