
package chess.game;

import chess.domain.CheckMate;
import java.io.FileNotFoundException;
import java.io.IOException;

/** 
 * Logiikaa kontrolloiva luokka, tämän luokan avulla GUI pyörittää kaikkea pelilogiikkaa.
 */ 
public class Chess {    
    /**
     * Olio Chessboard shakkilaudan toimintoja varten.
     */    
    private Chessboard board;    
    /**
     * Integer turns, määrittää vuorojen määrän.
     */     
    private int turns;     
    /**
     * Olio CheckMate shakkimatti tilanteiden tarkistusta varten.
     */     
    private CheckMate cm;    
   /**
    * Kaksiulotteinen integer array cb joka kuvaa shakkilaudan tilannetta.
    */   
    private int[][] cb;  
    /**
     * Integer info, gui näkee info muuttujan avulla shakkimatti tilanteet.
     */    
    private int info;    
    /**
     * Olio Menu menun kontrolointia varten.
     */    
    private Menu menu;

    
    /**
     * Luokan konstruktori, luodaan kaikki luokassa tarvittavat oliot ja attribuutit.
     */    
    public Chess() { 
        this.board = new Chessboard();
        this.cm = new CheckMate();
        this.cb =  new int[8][8];
        this.turns = 0;         
        this.info = 0;
        this.menu = new Menu();

    }
    
    /** 
     * Aloittaa uuden pelin.
     */    
    public void newGame () {
        this.info = 0;
        this.turns = 1;
        this.board.newBoard();
        this.board.addPieces();
    }
 
     /** 
     * Kopio pelilaudan GUIta varten.
     */     
    public void copyBoard() {
        this.cb = board.getBoard();
    }
    
     /** 
     * Palauttaa pelilaudan, käytettään GUIsta.
     */     
    public int[][] getBoard() {
        return cb;
    }  
    
    
    /** 
     * Mustan vuoro. Jos siirto ei ole mahdollinen vuorojen laskuri ei kasva. 
     * Tarkistettaan joka siirron jälkeen syntyykö shakki tai matti tilanne.
     * 
     * @param sx siirettävän nappulan x - alkukoordinaatti.
     * @param sy siirettävän nappulan y - alkukoordinaatti.
     * @param ex siirettävän nappulan x - loppukoordinaatti.
     * @param ey siirettävän nappulan y - loppukoordinaatti.
     */     
    public void blackTurn(int sx, int sy, int ex, int ey) { 
        int piece = board.getPiece(sx, sy);        
        board.blackTurn(sx, sy, ex, ey);
        
        if (board.getMoveB()) { 
            setInfo(0);
            turns++;
            
            if (cm.canAttackKing(board.getBoard(), piece, ex, ey)) {
                setInfo(1);
                cm.addSquares();
                
                if (!cm.canKingMove() && !cm.canTakeDownAttacker(ex, ey)) {
                    setInfo(3);
                }
            }            
        }  
        
        if (board.getWhite(12) == 99) {
            setInfo(3);
        }         
    }
    
    /** 
     * Valkoisen vuoro.Jos siirto ei ole mahdollinen vuorojen laskuri ei kasva. 
     * Tarkistettaan joka siirron jälkeen syntyykö shakki tai matti tilanne.
     * 
     * @param sx siirettävän nappulan x - alkukoordinaatti.
     * @param sy siirettävän nappulan y - alkukoordinaatti.
     * @param ex siirettävän nappulan x - loppukoordinaatti.
     * @param ey siirettävän nappulan y - loppukoordinaatti.
     */ 
    public void whiteTurn(int sx, int sy, int ex, int ey) {  
        int piece = board.getPiece(sx, sy);
        board.whiteTurn(sx, sy, ex, ey);  
        
        if (board.getMoveW()) {
            setInfo(0);            
            turns++;
            
            if (cm.canAttackKing(board.getBoard(), piece, ex, ey)) {
                setInfo(2);
                cm.addSquares();
                
                if (!cm.canKingMove() && !cm.canTakeDownAttacker(ex, ey)) {
                    setInfo(4);
                }            
            }            
        }  
        
        if (board.getBlack(4) == 99) {
            setInfo(4);
        }          
    } 
    
    public int getTurn() {
        return this.turns;
    }

     /**       * 
     * Asetetaan infolle arvo, infoa luetaan GUIsta, jos aika riittää katson voinko
     * todeuttaa tämän enumilla.
     * 
     * @param info
     */     
    public void setInfo(int info) {
        this.info = info;
    } 
   
    public int getInfo() {
        return this.info;
    }
    
     /**       * 
     * Ilmoittaa menu oliolle että pelitilanne(shakkilauta, vuorot, info) 
     * pitäisi tallentaa.
     *
     */
    public void saveGame() throws IOException {
        menu.save(board.getBoard(), turns, info);
    }
    
     /**       * 
     * Ilmoittaa menu oliolle että pelitilanne(shakkilauta, vuorot, info) 
     * pitäisi lataa. Kutsutaan myös addPieces metodia että saataisin oikeat
     * nappuloiden sijannit arrayihin.
     *
     */    
    public void loadGame() throws FileNotFoundException {
        int [][] cb = menu.loadBoard();
        board.setBoard(cb);
        setInfo(menu.loadInfo());
        this.turns = menu.loadTurns();
        this.board.addPieces();        
    }
    
}
