/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import tiralabra.Utilities;
import tiralabra.game.Board;

/**
 *
 * @author atte
 */
public class AI {

    private final Board board;
    private final byte team;

    public AI(Board board, byte team) {
        this.team = team;
        this.board = board;
    }

    public static class MoveSorter implements Comparator<Long> {

        private final int team;
        private final HashMap<Long, Integer> piecesOwned;

        public MoveSorter(int team, HashMap<Long, Integer> pieces) {
            this.team = team;
            this.piecesOwned = pieces;
        }

        @Override
        public int compare(Long o1, Long o2) {
            return piecesOwned.get(o2) - piecesOwned.get(o1);
        }

    }

    public long greedyBeginnerMove() {
        ArrayList<Long> moves = board.findLegalMoves(team);
        byte[][] originalBoard = board.getBoard();
        board.setBoard(Utilities.copy2dArray(originalBoard));
        HashMap<Long, Integer> piecesOwned = new HashMap<>();
        
        for (long move : moves) {
            board.put(move, team);
            
            int pieces = board.getNumberOfPieces(team);
            piecesOwned.put(move, pieces);
            
            board.setBoard(Utilities.copy2dArray(originalBoard));
        }
        Collections.sort(moves, new MoveSorter(team, piecesOwned));

        return moves.get(0);
    }

}
