package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.Stack;

/**
 *
 * @author Joel Nummelin
 */
public class SimpleAi implements Ai {

    private int LastMove;
    private int[] opponentsMoves;

    public SimpleAi() {
        this.LastMove = -2;
        this.opponentsMoves = new int[3];
    }

    @Override
    public int determineMove() {
        double[] ds = new double[3];

        for (int i = 0; i < 3; i++) {
            ds[i] = (Math.random() * (opponentsMoves[i] + 3));
        }

        int x = 0;

        for (int i = 1; i < 3; i++) {
            if (ds[i] > ds[x]) {
                x = i;
            }
        }
        LastMove = x;
        return x;
    }

    @Override
    public void update(int result) {
        if (result == 0) {
            opponentsMoves[LastMove]++;
        }

        if (LastMove == 0) {
            if (result == -1) {
                opponentsMoves[1]++;
            }
            if (result == 1) {
                opponentsMoves[2]++;
            }
        } else if (LastMove == 1) {
            if (result == 1) {
                opponentsMoves[0]++;
            }
            if (result == -1){
                opponentsMoves[2]++;
            }
        } else if (LastMove == 2) {
            if (result == 1){
                opponentsMoves[1]++;
            }
            if (result == -1){
                opponentsMoves[0]++;
            }
        }
    }

    @Override
    public void loadProfile(Stack s) {
    }

    @Override
    public void showData() {
    }
}
