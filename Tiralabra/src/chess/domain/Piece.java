
package chess.domain;

/**
 * Enum luokka nappuloita varten.
 */

public enum Piece {
    WHITEPAWN(1), WHITEROOK(3), WHITEKNIGHT(5), WHITEBISHOP(7), WHITEQUEEN(9), WHITEKING(11),
    BLACKPAWN(2), BLACKROOK(4), BLACKKNIGHT(6), BLACKBISHOP(8), BLACKQUEEN(10), BLACKKING(12); 
    
     private int piece; 
    
     /**
     * Luokan konstruktori.
     * 
     * @param piece
     */      
    private Piece(int piece) {
        this.piece = piece;
    }
    
    public int getPiece() {
        return this.piece;
    }
    
}
