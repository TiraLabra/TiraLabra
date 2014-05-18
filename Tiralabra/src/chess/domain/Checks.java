
package chess.domain;

/**
 * Tässä luokassa on metodeja jotka tarkistavat lähinnä sitä onko tilaa
 * (ei muita nappuloita tiellä) siirtää nappulaa.
 * kohderuutuun.
 */

public class Checks {    
    /**
     * Final integer over on 8 joka on shakkilaudan ylärajana.
     */     
    private final int over = 8;    
    /**
     * Final integer over on 0 joka on shakkilaudan alarajana.
     */     
    private final int under = 0;     
    /**
     * Final integer s on 10 jota käytettään x - koordinaatin kertomiseen jotta
     * koordinaatit voitaisiin ilmoittaa yhdessä arrayssa.
     */      
    private final int s = 10;
       
     /**
     * Tarkistaa onko saman värisiä nappuloida kohderuudussa.
     * 
     * @param locations omien nappuloiden sijannit.
     * @param x siirettävän nappulan loppu x-koordinaatti.
     * @param y siirettävän nappulan loppu y-koordinaatti.
     * @return palauttaa true jos ruutu on tyhjä.
     */   
    public boolean empty(int[]locations, int x, int y) {
        for (int i = 0; i < locations.length; i++) {
            if(locations[i] == s * x + y) {
                return false;
            }
        }
        
        return true;
    }   
    
    /**
     * Tarkistaa onko koordinaatti shakkilaudalla.
     * 
     * @param x x - koordinaatti.
     * @param y y - koordinaatti.
     * @return palauttaa true jos koordinaatti on shakkilaudalla.
     */    
    public boolean onBoard(int x, int y) {
        if (x >= under && x < over && y >= under && y < over) {
            return true;
        }
        
        return false;
    } 

    /**
     * Tarkistaa voidaanko tornia siirtää alkuruudusta kohderuutuun 
     * kun ruudut ovat samalla pystysuoralla.
     * 
     * @param board shakkilauta
     * @param sx siirrettävän tornin alku x-koordinaatti.
     * @param sy siirrettävän tornin alku y-koordinaatti.
     * @param x siirettävän tornin loppu x-koordinaatti.
     * @param y siirettävän tornin loppu y-koordinaatti.
     * @return palauttaa true jos tornin siirto on mahdollinen.
     */     
    public boolean rookCheckVertical(int[][]board, int sx, int sy, int x, int y) {
        if (sy < y) {
            for (int i = sy + 1; i < y; i++) {
                if (board[i][sx] != 0) {
                    return false;
                }
            } 
            
            return true;
        } else {
            for (int i = sy - 1; i > y; i--) {
                if (board[i][sx] != 0) {
                    return false;
                }
            }
            
            return true;
        }        
    }

    /**
     * Tarkistaa voidaanko tornia siirtää alkuruudusta kohderuutuun 
     * kun ruudut ovat samalla vaakasuoralla.
     * 
     * @param board shakkilauta
     * @param sx siirrettävän tornin alku x-koordinaatti.
     * @param sy siirrettävän tornin alku y-koordinaatti.
     * @param x siirettävän tornin loppu x-koordinaatti.
     * @param y siirettävän tornin loppu y-koordinaatti.
     * @return palauttaa true jos tornin siirto on mahdollinen.
     */    
    public boolean rookCheckHorizontal(int[][]board, int sx, int sy, int x, int y) {
        if (sx < x) {
            for (int i = sx + 1; i < x; i++) {
                if (board[sy][i] != 0) {
                    return false;
                }
            }  
            
            return true;
        } else {
            for (int i = sx - 1; i > x; i--) {
                if (board[sy][i] != 0) {
                    return false;
                }
            }
            
            return true;
        }        
    }
    
    /**
     * Tarkistaa voidaanko lähettiä siirtää alkuruudusta kohderuutuun, kun kohde
     * ruutu on alkuruutua vasemalla ja ylempänä.
     * 
     * @param board shakkilauta
     * @param sx siirrettävän lähetin alku x-koordinaatti.
     * @param sy siirrettävän lähetin alku y-koordinaatti.
     * @param x siirettävän lähetin loppu x-koordinaatti.
     * @param y siirettävän lähetin loppu y-koordinaatti.
     * @return palauttaa true jos lähetin siirto on mahdollinen.
     */    
    public boolean bishopCheckNW(int[][]board, int sx, int sy, int x, int y) {
        int k = 0;
        
        if ((sx - x) != (sy - y)) {
            return false;
        }   
        
        for (int i = sx - 1; i > x; i--) {
            k++;
            if (board[sy - k][i] != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Tarkistaa voidaanko lähettiä siirtää alkuruudusta kohderuutuun, kun kohde
     * ruutu on alkuruutua vasemalla ja alempana.
     * 
     * @param board shakkilauta
     * @param sx siirrettävän lähetin alku x-koordinaatti.
     * @param sy siirrettävän lähetin alku y-koordinaatti.
     * @param x siirettävän lähetin loppu x-koordinaatti.
     * @param y siirettävän lähetin loppu y-koordinaatti.
     * @return palauttaa true jos lähetin siirto on mahdollinen.
     */     
    public boolean bishopCheckSW(int[][]board, int sx, int sy, int x, int y) {
        int k = 0;
        
        if ((sx - x) != (y - sy)) {
            return false;
        }  
        
        for (int i = sx - 1; i > x; i--) {
            k++;
            if (board[sy + k][i] != 0) {
                return false;
            }
        }
        
        return true; 
    }
    
    /**
     * Tarkistaa voidaanko lähettiä siirtää alkuruudusta kohderuutuun, kun kohde
     * ruutu on alkuruutua oikealla ja ylempänä.
     * 
     * @param board shakkilauta
     * @param sx siirrettävän lähetin alku x-koordinaatti.
     * @param sy siirrettävän lähetin alku y-koordinaatti.
     * @param x siirettävän lähetin loppu x-koordinaatti.
     * @param y siirettävän lähetin loppu y-koordinaatti.
     * @return palauttaa true jos lähetin siirto on mahdollinen.
     */     
    public boolean bishopCheckNE(int[][]board, int sx, int sy, int x, int y) {
        int k = 0;
        
        if ((x - sx) != (sy - y)) {
            return false;
        }
               
        for (int i = sx + 1; i < x; i++) {
            k++;            
            if (board[sy - k][i] != 0) {
                return false;
            }
        }
        
        return true;  
    }

    /**
     * Tarkistaa voidaanko lähettiä siirtää alkuruudusta kohderuutuun, kun kohde
     * ruutu on alkuruutua oikealla ja alempana.
     * 
     * @param board shakkilauta
     * @param sx siirrettävän lähetin alku x-koordinaatti.
     * @param sy siirrettävän lähetin alku y-koordinaatti.
     * @param x siirettävän lähetin loppu x-koordinaatti.
     * @param y siirettävän lähetin loppu y-koordinaatti.
     * @return palauttaa true jos lähetin siirto on mahdollinen.
     */     
    public boolean bishopCheckSE(int[][]board, int sx, int sy, int x, int y) {
        int k = 0;
        
        if ((x - sx) != (y - sy)) {
            return false;
        }        

        for (int i = sx + 1; i < x; i++) {
            k++;
            if (board[sy + k][i] != 0) {
                return false;
            }
        }
        
        return true; 
    }    
}
