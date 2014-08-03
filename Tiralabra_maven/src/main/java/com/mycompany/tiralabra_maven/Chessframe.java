package com.mycompany.tiralabra_maven;

import javax.swing.JFrame;

import chesspresso.Chess;
import chesspresso.game.Game;
import chesspresso.game.view.GameBrowser;
import chesspresso.move.IllegalMoveException;
import chesspresso.move.Move;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Chessframe extends JFrame {
    
    private Game chessGame = new Game();
    private GameBrowser gameBrowser = new GameBrowser(chessGame); 
    	private static final Map<Short, String> unicodePiece;
	static {
		Map<Short, String> aMap = new HashMap<Short, String>();
		aMap.put(Chess.WHITE_KING, "\u2654");
		aMap.put(Chess.WHITE_QUEEN, "\u2655");
		aMap.put(Chess.WHITE_ROOK, "\u2656");
		aMap.put(Chess.WHITE_BISHOP, "\u2657");
		aMap.put(Chess.WHITE_KNIGHT, "\u2658");
		aMap.put(Chess.WHITE_PAWN, "\u2659");

		aMap.put(Chess.BLACK_KING, "\u265A");
		aMap.put(Chess.BLACK_QUEEN, "\u265B");
		aMap.put(Chess.BLACK_ROOK, "\u265C");
		aMap.put(Chess.BLACK_BISHOP, "\u265D");
		aMap.put(Chess.BLACK_KNIGHT, "\u265E");
		aMap.put(Chess.BLACK_PAWN, "\u265F");

		unicodePiece = Collections.unmodifiableMap(aMap);
	}
    
    public Chessframe(){
        setTitle("Chesspresso testi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gameBrowser);
        pack();
        addMove(Chess.E2, Chess.E4, false, null);
        addMove(Chess.E7, Chess.E5, false, null);
        addMove(Chess.G1, Chess.F3, false, null);
        addMove(Chess.B8, Chess.C6, false, null);
        addMove(Chess.F1, Chess.B5, false, null);
    }
    
   private void addMove(int fromSquareIndex, int toSquareIndex,
            boolean isCapturingMove, String comment){
        try {
            short move = Move.getRegularMove(fromSquareIndex, toSquareIndex,
                    isCapturingMove);
            if (Move.isValid(move)) {
                chessGame.getPosition().doMove(move);
                if (comment != null && comment.length() > 0)
                    chessGame.addComment(comment);
            }
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
    }
 
}
