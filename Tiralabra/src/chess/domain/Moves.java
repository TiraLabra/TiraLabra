
package chess.domain;

/**
 * Tämä luokka määrittelee kaikkien nappuloiden siirtojen laillisuuden.
 */
public class Moves {    
    /**
     * Olio Checks siirtojen lailisuuden tarkistamista varten.
     */    
    private Checks checks;       
     /**
     * Luokan konstruktori. Luodaan uusi Checks olio.
     */   
    
    public Moves() {
        this.checks = new Checks();
    }
    
    /**
     * Tarkistaa voidaanko kuningasta siirtää alkuruudusta kohderuutuun.
     * 
     * @param locations omien nappuloiden sijannit.
     * @param sx siirrettävän nappulan alku x-koordinaatti.
     * @param sy siirrettävän nappulan alku y-koordinaatti.
     * @param x siirettävän nappulan loppu x-koordinaatti.
     * @param y siirettävän nappulan loppu y-koordinaatti.
     * @return palauttaa true jos siirton on mahdollinen.
     */
    public boolean moveKing(int[]locations, int sx, int sy, int x, int y) {
        if (checks.onBoard(x, y) && checks.empty(locations, x, y)) {
            
            if (sx - 1 == x && sy - 1 == y) {
                return true;
            }
            
            if (sx == x && sy - 1 == y) {
                return true;
            }
            
            if (sx + 1 == x && sy - 1 == y) {
                return true;
            }
            
            if (sx - 1 == x && sy == y) {
                return true;
            }
            
            if (sx + 1 == x && sy == y) {
                return true;
            }
            
            if (sx - 1 == x && sy + 1 == y) {
                return true;
            }
            
            if (sx == x && sy + 1 == y) {
                return true;
            }
            
            if (sx + 1 == x && sy + 1 == y) {
                return true;
            }            
        }
        
        return false;
        
    }
    
    /**
     * Tarkistaa voidaanko mustaa sotilasta siirtää alkuruudusta kohderuutuun.
     * 
     * @param blacks mustien nappuloiden sijannit.
     * @param whites valkoisten nappuloiden sijannit.
     * @param sx siirrettävän nappulan alku x-koordinaatti.
     * @param sy siirrettävän nappulan alku y-koordinaatti.
     * @param x siirettävän nappulan loppu x-koordinaatti.
     * @param y siirettävän nappulan loppu y-koordinaatti.
     * @return palauttaa true jos siirton on mahdollinen.
     */
    public boolean moveBlackPawn(int[]blacks, int[]whites, int sx, int sy, int x, int y) {
        if (x == sx && checks.empty(blacks, x, y) && checks.empty(whites, x, y)) {  
            
            if (sy == 1 && y == 3) {
                return true;
            }
            
            if (y < 8 && sy + 1 == y) {
                return true;
            }
        }
        
        if (!checks.empty(whites, x, y) && sy + 1 == y && (sx - 1 == x || sx + 1 == x)) {
            return true;
        }  
        
        return false;
    }
    
    /**
     * Tarkistaa voidaanko valkoista sotilasta siirtää alkuruudusta kohderuutuun.
     * 
     * @param whites valkoisten nappuloiden sijannit.
     * @param blacks mustien nappuloiden sijannit.
     * @param sx siirrettävän nappulan alku x-koordinaatti.
     * @param sy siirrettävän nappulan alku y-koordinaatti.
     * @param x siirettävän nappulan loppu x-koordinaatti.
     * @param y siirettävän nappulan loppu y-koordinaatti.
     * @return palauttaa true jos siirton on mahdollinen.
     */ 
    public boolean moveWhitePawn(int[]whites, int[]blacks, int sx, int sy, int x, int y) {
        if (x == sx && checks.empty(whites, x, y) && checks.empty(blacks, x, y)) {
            
            if (sy == 6 && y == 4) {
                return true;
            }
            
            if (y >= 0 && sy - 1 == y) {
                return true;
            }
        }
        
        if (!checks.empty(blacks, x, y) && sy - 1 == y && (sx - 1 == x || sx + 1 == x)) {
            return true;
        }  
        
        return false;
    }
    
    /**
     * Tarkistaa voidaanko ratsua siirtää alkuruudusta kohderuutuun.
     * 
     * @param locations omien nappuloiden sijannit.
     * @param sx siirrettävän nappulan alku x-koordinaatti.
     * @param sy siirrettävän nappulan alku y-koordinaatti.
     * @param x siirettävän nappulan loppu x-koordinaatti.
     * @param y siirettävän nappulan loppu y-koordinaatti.
     * @return palauttaa true jos siirton on mahdollinen.
     */     
    public boolean moveKnight(int[]locations, int sx, int sy, int x, int y) {
        if (checks.onBoard(x, y)  && checks.empty(locations, x, y)) {
            
            if (sx - 1 == x && sy - 2 == y) {
                return true;
            }
            
            if (sx - 2 == x && sy - 1 == y) {
                return true;
            } 
            
            if (sx + 1 == x && sy - 2 == y) {
                return true;
            }
            
            if (sx + 2 == x && sy - 1 == y) {
                return true;
            }
            
            if (sx - 1 == x && sy + 2 == y) {
                return true;
            }
            
            if (sx - 2 == x && sy + 1 == y) {
                return true;
            }
            
            if (sx + 1 == x && sy + 2 == y) {
                return true;
            }
            
            if (sx + 2 == x && sy + 1 == y) {
                return true;
            }
            
        }
        
        return false;
    }
    
    /**
     * Tarkistaa voidaanko tornia siirtää alkuruudusta kohderuutuun.
     * 
     * @param board shakkilauta
     * @param locations omien nappuloiden sijannit.
     * @param sx siirrettävän nappulan alku x-koordinaatti.
     * @param sy siirrettävän nappulan alku y-koordinaatti.
     * @param x siirettävän nappulan loppu x-koordinaatti.
     * @param y siirettävän nappulan loppu y-koordinaatti.
     * @return palauttaa true jos siirton on mahdollinen.
     */ 
    public boolean moveRook(int[][]board, int[]locations, int sx, int sy, int x, int y) {
        if (checks.onBoard(x, y) && checks.empty(locations, x, y)) {
            
            if (sx == x && checks.rookCheckVertical(board, sx, sy, x, y)) {
                return true;
            }
            
            if (sy == y && checks.rookCheckHorizontal(board, sx, sy, x, y)) {
                return true;
            }
        }
        
        return false;
    }    
    
    /**
     * Tarkistaa voidaanko lähettiä siirtää alkuruudusta kohderuutuun.
     * 
     * @param board shakkilauta
     * @param locations omien nappuloiden sijannit.
     * @param sx siirrettävän nappulan alku x-koordinaatti.
     * @param sy siirrettävän nappulan alku y-koordinaatti.
     * @param x siirettävän nappulan loppu x-koordinaatti.
     * @param y siirettävän nappulan loppu y-koordinaatti.
     * @return palauttaa true jos siirto on mahdollinen.
     */ 
    public boolean moveBishop(int[][]board, int[]locations, int sx, int sy, int x, int y) {
        if (checks.onBoard(x, y)  && checks.empty(locations, x, y)) {
            
            if (sx > x && sy > y && checks.bishopCheckNW(board, sx, sy, x, y)) {
                return true;
            }
            
            if (sx > x && sy < y && checks.bishopCheckSW(board, sx, sy, x, y)) {
                return true;                               
            }  
            
            if (sx < x && sy > y && checks.bishopCheckNE(board, sx, sy, x, y)) {
                return true;                                
            }
            if (sx < x && sy < y && checks.bishopCheckSE(board, sx, sy, x, y)) {
                return true;      
            }             
        }  
        
        return false;
    }    
     
    /**
     * Tarkistaa voidaanko kuningatarta siirtää alkuruudusta kohderuutuun, 
     * käytettään hyväksi moveRook ja moveBishop metodeja.
     * 
     * @param board shakkilauta
     * @param locations omien nappuloiden sijannit.
     * @param sx siirrettävän nappulan alku x-koordinaatti.
     * @param sy siirrettävän nappulan alku y-koordinaatti.
     * @param x siirettävän nappulan loppu x-koordinaatti.
     * @param y siirettävän nappulan loppu y-koordinaatti.
     * @return palauttaa true jos siirton on mahdollinen.
     */ 
    public boolean moveQueen(int[][]board, int[]locations, int sx, int sy, int x, int y) {
        if (moveRook(board, locations, sx, sy, x, y) || moveBishop(board, locations, sx, sy, x, y)) {
            return true;
        }
        
        return false;
    }    
        
}
