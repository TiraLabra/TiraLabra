package com.mycompany.tiralabra_maven.player;

import java.io.File;
import java.io.IOException;

/**
 * Bot uses ai to play. 
 * @author Joel Nummelin
 */
public class Bot {

    private Ai ai;
    private File file;

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

    public void updateAi(int result) throws IOException {
        int x = ai.update(result);
        if (file != null){
            new FileHandler(file).saveLine(x, result);
        }
    }

    public void loadProfile(File file) throws IOException {
        this.file = file;
        ai.setStack(new FileHandler(file).getLines());
    }
}