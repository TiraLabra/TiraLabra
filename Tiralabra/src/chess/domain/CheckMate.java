
package chess.domain;

import java.util.ArrayList;

/**
 * Tämä luokka  on shakki ja shakkimatti tilanteiden tarkistamista varten.
 */
public class CheckMate {    
    /**
     * Olio Moves siirtojen lailisuuden tarkistamista varten.
     */
    private Moves moves;    
    /**
     * Integer array whites joka tulee sisältämään valkoisten nappuloiden sijannit.
     */    
    private int [] whites;    
    /**
     * Integer array blacks joka tulee sisältämään mustien nappuloiden sijannit.
     */     
    private int [] blacks;     
    /**
     * Integer x joka on uhattuna olevan kuninkaan x - koordinaatti.
     */     
    private int x;    
    /**
     * Integer x joka on uhattuna olevan kuninkaan y - koordinaatti.
     */       
    private int y;    
    /**
     * Kaksiulotteinen integer array cb joka kuvaa shakkilaudan tilannetta.
     */    
    private int [][] cb;    
    /**
     * Integer ArrayList johon tallennetaan kuninkaan ympärillä olevien tyhjien
     * ruutujen x - koordinaatit.
     */     
    private ArrayList<Integer> aroundKingX;   
    /**
     * Integer ArrayList johon tallennetaan kuninkaan ympärillä olevien tyhjien
     * ruutujen y - koordinaatit.
     */      
    private ArrayList<Integer> aroundKingY;    
    /**
     * Integer ArrayList johon tallennetaan kuninkaan ja hyökkävän nappulan välissä
     * olevien ruutujen x - koordinaatit.
     */    
    private ArrayList<Integer> blockX;    
    /**
     * Integer ArrayList johon tallennetaan kuninkaan ja hyökkävän nappulan välissä
     * olevien ruutujen y - koordinaatit.
     */    
    private ArrayList<Integer> blockY; 
    /**
     * Enum Piece, numerot nappuloiden nimiski.
     */     
    private Piece p;    
    
    /**
     * Luokan konstruktori, luodaan kaikki tarvittavat attribuutit ja Moves olio.
     */       
    public CheckMate() {

        this.moves = new Moves();
        this.whites = new int[16];
        this.blacks = new int[16];
        this.cb = new int[8][8];
        this.x = 0;
        this.y = 0;
        this.aroundKingX = new ArrayList<Integer>();
        this.aroundKingY = new ArrayList<Integer>();   
        this.blockX = new ArrayList<Integer>();
        this.blockY = new ArrayList<Integer>();          
    }
    
   /** 
     * Metodi joka lisää mustien ja valkoisten nappuloiden sijannit blacks ja 
     * whites arrayhin.
     * 
     * @param board tämän hetken shakkilauta
     */       
    public void addPieces(int[][]board) {
        this.cb = board;
        int k = 0;
        int l = 0;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    if (board[i][j] % 2 == 0) {
                        this.blacks[k] = j * 10 + i;
                        k++;                        
                    } else {
                        this.whites[l] = j * 10 + i;
                        l++;
                    }
                }                        
            }
        }
    }    

    /**
     * Tarkistaa voiko nappula "syödä" kuninkaan seuravalla siirolla
     * ensin laitettaan valkoisten ja mustien nappuloiden sijannit arrayihin
     * sitten etitään kuninkaan sijanti
     * lopulta katsotaan voiko annettu nappula shakittaa vastapuolen kuningasta.
     * 
     * @param board tämän hetken shakki lauta
     * @param piece shakkinappulan numero, tämä määrää kumpi puoli on hyökkääjä
     * @param sx hyökkävän nappulan x - koordinaatti
     * @param sy hyökkävän nappulan y - koordinaatti
     * @return palauttaa arvon true jos kuningas on hyökkäyksen kohteena
     */   
    public boolean canAttackKing(int[][] board, int piece, int sx, int sy) {    
        addPieces(board);
        findKing(board, piece);
        
        if (piece % 2 == 0 && checkBlackAttack(board, piece, sx, sy, x, y)) {
            return true;
            }
        
        if (piece % 2 != 0 && checkWhiteAttack(board, piece, sx, sy, x, y)) {
            return true;        
        }                  
       
      
        return false;
    } 
    
    /**
     * Tarkistaa voiko nappula "syödä" kuninkaan seuravalla siirolla.
     * 
     * @param board tämän hetken shakki lauta
     * @param piece shakkinappulan numero, tämä määrää kumpi puoli on hyökkääjä
     */    
    public void findKing(int[][] board, int piece) {
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (piece % 2 == 0 && board[i][j] == 11) {
                    x = j;
                    y = i;
                } 
                
                if (piece % 2 != 0 && board[i][j] == 12) {
                    x = j;
                    y = i;
                }                
                
            }
        }               
    }
    
    /**
     * Tarkistaa voiko musta nappula siirtyä lähtöruudusta kohderuutuun.
     * 
     * @param board tämän hetken shakki lauta
     * @param piece siirtyneen nappulan numero
     * @param sx mustan nappulan lähtö x - koordinaatti
     * @param sy mustan nappulan lähtö y - koordinaatti
     * @param x mustan nappulan kohde x - koordinaatti
     * @param y mustan nappulan kohde y - koordinaatti
     * @return palauttaa arvoksi true jos siirto on mahdollinen
     */  
    public boolean checkBlackAttack(int[][] board, int piece, int sx, int sy, int x, int y) {
        
        if (piece == p.BLACKPAWN.getPiece() && moves.moveBlackPawn(blacks, whites, sx, sy, x, y)) {     
            return true;
        } 
        
        if (piece == p.BLACKROOK.getPiece() && moves.moveRook(board, blacks, sx, sy, x, y)) {         
            return true;
        } 
        
        if (piece == p.BLACKKNIGHT.getPiece() && moves.moveKnight(blacks, sx, sy, x, y)) {   
            return true;            
        } 
        
        if (piece == p.BLACKBISHOP.getPiece() && moves.moveBishop(board, blacks, sx, sy, x, y)) { 
            return true;            
        } 
        
        if (piece == p.BLACKQUEEN.getPiece() && moves.moveQueen(board, blacks, sx, sy, x, y)) {
            return true;            
        } 
        
        return false;
    } 
    
    /**
     * Tarkistaa voiko valkoinen nappula siirtyä lähtöruudusta kohderuutuun.
     * 
     * @param board tämän hetken shakki lauta
     * @param piece siirtyneen nappulan numero
     * @param sx valkoisen nappulan lähtö x - koordinaatti
     * @param sy valkoisen nappulan lähtö y - koordinaatti
     * @param x valkoisen nappulan kohde x - koordinaatti
     * @param y valkoisen nappulan kohde y - koordinaatti
     * @return palauttaa arvoksi true jos siirto on mahdollinen
     */     
    public boolean checkWhiteAttack(int[][] board, int piece, int sx, int sy, int x, int y) {       
        
        if (piece == p.WHITEPAWN.getPiece() && moves.moveWhitePawn(whites, blacks, sx, sy, x, y)) {  
            return true;
        }   
        
        if (piece == p.WHITEROOK.getPiece() && moves.moveRook(board, whites, sx, sy, x, y)) {  
            return true;
        }  
        
        if (piece == p.WHITEKNIGHT.getPiece() && moves.moveKnight(whites, sx, sy, x, y)) {  
            return true;
        }  
        
        if (piece == p.WHITEBISHOP.getPiece() && moves.moveBishop(board, whites, sx, sy, x, y)) {  
            return true;
        }
        
        if (piece == p.WHITEQUEEN.getPiece() && moves.moveQueen(board, whites, sx, sy, x, y)) { 
            return true;
        }  
        
        return false;
    } 
    
    /**
     * 4 getteria testauksen helpottamista varten
     */
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    } 
    
    public int getBlack(int i) {
        return blacks[i];
    }
 
    public int getWhite(int i) {
        return whites[i];
    }
   
    /**
     * Tämä metodi lisää kuninkaan ympärillä olevien ruutujen koordinaatit 
     * kahteen ArrayListiin, shakkimatti tilanteen tarkistamista varten.
     */    
    public void addSquares() {
        this.aroundKingX.clear();
        this.aroundKingY.clear();
        
        if (x - 1 > -1 && y - 1 > -1 && cb[y - 1][x - 1] == 0) {
            this.aroundKingX.add((x - 1));
            this.aroundKingY.add((y - 1));            
        }
        
        if (y - 1 > -1 && cb[y - 1][x] == 0) {
            this.aroundKingX.add((x));
            this.aroundKingY.add((y - 1));            
        } 
        
        if (x + 1 < 8 && y - 1 > -1 && cb[y - 1][x + 1] == 0) {
            this.aroundKingX.add((x + 1));
            this.aroundKingY.add((y - 1)); 
        }
        
        if (x + 1 < 8 && cb[y][x + 1] == 0) {
            this.aroundKingX.add((x + 1));
            this.aroundKingY.add((y)); 
        } 
        
        if (x + 1 < 8 && y + 1 < 8 && cb[y + 1][x + 1] == 0) {
            this.aroundKingX.add((x + 1));
            this.aroundKingY.add((y + 1)); 
        } 
        
        if (y + 1 < 8 && cb[y + 1][x] == 0) {
            this.aroundKingX.add((x));
            this.aroundKingY.add((y + 1)); 
        } 
        
        if (x - 1 > -1 && y + 1 < 8 && cb[y + 1][x - 1] == 0) {
            this.aroundKingX.add((x - 1));
            this.aroundKingY.add((y + 1)); 
        }
        
        if (x - 1 > -1 && cb[y][x - 1] == 0) {
            this.aroundKingX.add((x - 1));
            this.aroundKingY.add((y)); 
        }                
    }
     /**
     * Tarkistettaan pystyykö kuningas liikkumaan ympärillä oleviin ruutuihinsa.
     * 
     * @return palauttaa true jos kuningas pystyy liikkumaan
     */      
    public boolean canKingMove() {
        int checkX = 0;
        int checkY = 0;
        int piece = 0;
        int l = 0;
        
        for (int k = 0; k < aroundKingX.size(); k++) { 
            for (int i = 0; i < cb.length; i++) {
                for (int j = 0; j < cb.length; j++) {
                    checkX = aroundKingX.get(k);
                    checkY = aroundKingY.get(k);                 
                    if (cb[i][j] != 0 && cb[i][j] % 2 == 0) {
                        piece = cb[i][j];
                        if (checkBlackAttack(cb, piece, j, i, checkX, checkY)) {
                            l++;
                        }                        
                    }
                    if (cb[i][j] != 0 && cb[i][j] % 2 != 0) {
                        piece = cb[i][j];
                        if (checkWhiteAttack(cb, piece, j, i, checkX, checkY)) {
                            l++;
                        }                                               
                    }                     
                }
            }
            if (k > l) {
                return true;
            }            
            l = k;             
        }
        
        return false;
    }
 
     /**
     * Tarkistettaan pystytäänkö kuningasta vastaan hyökkävä nappula "syömään" tai
     * voidaanko sen uhka blockata jollain nappullalla, jos hyökkävä nappula on torni,
     * lähetti tai kuningatar.
     * 
     * @param x hyökkävän nappulan x - koordinaatti
     * @param y hyökkävän nappulan y - koordinaatti
     * @return palauttaa true jos kuningas voidaan pelastaa muiden nappuloiden toimesta
     */     
    public boolean canTakeDownAttacker(int x, int y) {
        if (canBlock(x, y)) {
            return true;
        }
        
        for (int i = 0; i < cb.length; i++) {
            for (int j = 0; j < cb.length; j++) {
                if (cb[y][x] % 2 == 0 && cb[i][j] != 0 && cb[i][j] % 2 != 0) {
                    if (checkWhiteAttack(cb, cb[i][j], j, i, x, y)) {
                        return true;
                    }
                } 
                
                if (cb[y][x] % 2 != 0 && cb[i][j] != 0 && cb[i][j] % 2 == 0) {
                    if (checkBlackAttack(cb, cb[i][j], j, i, x, y)) {
                        return true;
                    }
                }                 
            }
        }
        
        return false;
    }
       
     /**
     * Tarkistettaan pystytäänkö kuningasta vastaan hyökkävän nappulan hyökkäys
     * estämään jonkun oman nappulan toimesta.
     * 
     * @param ex hyökkävän nappulan x - koordinaatti
     * @param ey hyökkävän nappulan y - koordinaatti
     * @return palauttaa true jos kuningas voidaan pelastaa muiden nappuloiden toimesta
     */      
    public boolean canBlock(int ex, int ey) {
        this.blockX.clear();
        this.blockY.clear();   
        
        if (cb[ey][ex] == 3 || cb[ey][ex] == 4) {
            if (blockRook(ex, ey)) {
            return true;
            }
        }
        
        if (cb[ey][ex] == 7 || cb[ey][ex] == 8) {
            if (blockBishop(ex, ey)) {
            return true;
            }
        } 
        
        if (cb[ey][ex] == 9 || cb[ey][ex] == 10) {
            if (blockBishop(ex, ey) || blockRook(ex, ey)) {
            return true;
            }
        }        
               
        return false;
    }
    
     /**
     * Tarkistettaan pystytäänkö kuningasta vastaan hyökkävän tornin hyökkäys
     * estämään.
     * 
     * @param ex hyökkävän tornin x - koordinaatti
     * @param ey hyökkävän tornin y - koordinaatti
     * @return palauttaa true jos tornin hyökkäys torjutaan.
     */    
    public boolean blockRook(int ex, int ey) {
            if (ex == x) {
                rockVertical(ex, ey);
            } else { 
                rockHorizontal(ex, ey);
            }  
            
            if (blockCheck()) {
                return true;
            } 
            
            return false;
    }
     /**
     * Lisätään arraylistoihin tornin ja kuninkaan välissä olevat ruudut, jos
     * torni uhkaa kuningasta pystysuunnassa.
     * 
     * @param ex hyökkävän tornin x - koordinaatti
     * @param ey hyökkävän tornin y - koordinaatti
     */     
    public void rockVertical(int ex, int ey) {
        if (ey < y) {
            for (int i = ey + 1; i < y; i++) {
                blockX.add(ex);
                blockY.add(i);
            }  
        } else {
            for (int i = ey - 1; i > y; i--) {
                blockX.add(ex);
                blockY.add(i);
            }
        }         
    }

     /**
     * Lisätään arraylistoihin tornin ja kuninkaan välissä olevat ruudut, jos
     * torni uhkaa kuningasta vaakasuunnassa.
     * 
     * @param ex hyökkävän tornin x - koordinaatti
     * @param ey hyökkävän tornin y - koordinaatti
     */     
    public void rockHorizontal(int ex, int ey) {
        if (ex < x) {
            for (int i = ex + 1; i < x; i++) {
                blockX.add(i);
                blockY.add(ey);
            }       
        } else {
            for (int i = ex - 1; i > x; i--) {
                blockX.add(i);
                blockY.add(ey);
            }
        } 
    }
 
     /**
     * Tarkistettaan pystytäänkö kuningasta vastaan hyökkävän lähetin hyökkäys
     * estämään.
     * 
     * @param ex hyökkävän lähetin x - koordinaatti
     * @param ey hyökkävän lähetin y - koordinaatti
     * @return palauttaa true jos lähetin hyökkäys torjutaan.
     */     
    public boolean blockBishop(int ex, int ey) {
            if (ex > x && ey > y) {
                bishopNW(ex, ey);
            }
            
            if (ex > x && ey < y) {  
                bishopSW(ex, ey);
            }  
            
            if (ex < x && ey > y) {
                bishopNE(ex, ey);
            }
                        
            if (ex < x && ey < y) {
                bishopSE(ex, ey);                
            } 
            
            if (blockCheck()) {
                return true;
            } 
            
            return false;
    }

    /**
     * Lisätään arraylistoihin lähetin ja kuninkaan välissä olevat ruudut, jos
     * lähetti uhkaa kuningasta alaoikealta.
     * 
     * @param ex hyökkävän lähetin x - koordinaatti
     * @param ey hyökkävän lähetin y - koordinaatti
     */ 
    public void bishopNW(int ex, int ey) {
        int k = 0;
        
        for (int i = ex - 1; i > x; i--) {
            k++;
            blockX.add(i);
            blockY.add(ey - k);
        }        
    }
    
    /**
     * Lisätään arraylistoihin lähetin ja kuninkaan välissä olevat ruudut, jos
     * lähetti uhkaa kuningasta yläoikealta.
     * 
     * @param ex hyökkävän lähetin x - koordinaatti
     * @param ey hyökkävän lähetin y - koordinaatti
     */     
    public void bishopSW(int ex, int ey) {
        int k = 0;
        
        for (int i = ex - 1; i > x; i--) {
            k++;
            blockX.add(i);
            blockY.add(ey + k);            
        }        
    }
  
    /**
     * Lisätään arraylistoihin lähetin ja kuninkaan välissä olevat ruudut, jos
     * lähetti uhkaa kuningasta alavasemmalta.
     * 
     * @param ex hyökkävän lähetin x - koordinaatti
     * @param ey hyökkävän lähetin y - koordinaatti
     */     
    public void bishopNE(int ex, int ey) {
        int k = 0; 
        
        for (int i = ex + 1; i < x; i++) {
            k++; 
            blockX.add(i);
            blockY.add(ey - k);              
        }                
    }

    /**
     * Lisätään arraylistoihin lähetin ja kuninkaan välissä olevat ruudut, jos
     * lähetti uhkaa kuningasta ylävasemmalta.
     * 
     * @param ex hyökkävän lähetin x - koordinaatti
     * @param ey hyökkävän lähetin y - koordinaatti
     */     
    public void bishopSE(int ex, int ey) {
        int k = 0;
        
        for (int i = ex + 1; i < x; i++) {
            k++;
            blockX.add(i);
            blockY.add(ey + k);             
        }        
    }

    /**
     * Varsinainen hyökkäyksen estämisen, siirtämällä nappula kuninkaan
     * ja hyökkävän nappulan väliin, tarkistava metodi.
     * 
     * @return palauttaa true jos hyökkäys voidaan estää
     */    
    public boolean blockCheck() {   
        
        for (int k = 0; k < blockX.size(); k++) {
            for (int i = 0; i < cb.length; i++) {
                for (int j = 0; j < cb.length; j++) {
                    if (cb[i][j] % 2 != 0 && cb[i][j] != 0) {
                        if (checkBlackAttack(cb, cb[i][j], j, i, blockX.get(k), blockY.get(k))) {
                            return true;
                        }
                    } 
                    
                    if (cb[i][j] % 2 == 0 && cb[i][j] != 0) {
                        if (checkWhiteAttack(cb, cb[i][j], j, i,blockX.get(k), blockY.get(k))) {
                            return true;
                        }
                    }                 
                }
            }
        }
        
        return false;
    }
}
