
package chess.gui;

import chess.game.Chess;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/** 
 * Piirtoalusta luokka, piirettään lauta ja laudalle reuna ja nappulat.
 */ 
public class DrawingPanel extends JPanel {
    /**
     * Chess chess, Chess olio jonka kautta päästään käsiksi shakkilautaan.
     */    
    private Chess chess;
    /**
     * ImageIcon whitePawn, valkoisen sotilaan imageiconi.
     */    
    private ImageIcon whitePawn;
    /**
     * ImageIcon blackPawn, mustan sotilaan imageiconi.
     */     
    private ImageIcon blackPawn;
    /**
     * ImageIcon whiteRook, valkoisen tornin imageiconi.
     */     
    private ImageIcon whiteRook;
    /**
     * ImageIcon blackRook, mustan tornin imageiconi.
     */      
    private ImageIcon blackRook;
    /**
     * ImageIcon whiteKnight, valkoisen ratsun imageiconi.
     */     
    private ImageIcon whiteKnight;
    /**
     * ImageIcon blackKnight, mustan ratsun imageiconi.
     */      
    private ImageIcon blackKnight;  
    /**
     * ImageIcon whiteBishop, valkoisen lähetin imageiconi.
     */     
    private ImageIcon whiteBishop;
    /**
     * ImageIcon blackBishop, mustan lähetin imageiconi.
     */      
    private ImageIcon blackBishop;
    /**
     * ImageIcon whiteQueen, valkoisen kuningattaren imageiconi.
     */     
    private ImageIcon whiteQueen;
    /**
     * ImageIcon blackQueen, mustan kuningattaren imageiconi.
     */      
    private ImageIcon blackQueen;
    /**
     * ImageIcon whiteKing, valkoisen kuninkaan imageiconi.
     */     
    private ImageIcon whiteKing;
    /**
     * ImageIcon blackKing, mustan kuninkaan imageiconi.
     */      
    private ImageIcon blackKing; 
    /**
     * ImageIcon numbers, shakkilaudan reunalle tulevan numeroiden imageiconi.
     */     
    private ImageIcon numbers;
    /**
     * ImageIcon letters, shakkilaudan reunalle tulevan kirjainten imageiconi.
     */     
    private ImageIcon letters;
 
    /** 
     * Luokan konstruktori,ladataan kaikkien nappuloiden ja shakkilaudan reunojen kuvat.
     * 
     * @param chess Chess olio, jonka avulla saadaan tämänhetkinen shakkilauta luokan käyttöön.
     */     
    public DrawingPanel(Chess chess) {
        super.setBackground(Color.GREEN);
        this.chess = chess; 
        ClassLoader cldr = this.getClass().getClassLoader();
        java.net.URL imageURL = cldr.getResource("images/whitepawn.png");
        this.whitePawn = new ImageIcon(imageURL);  
        imageURL = cldr.getResource("images/blackpawn.png"); 
        this.blackPawn = new ImageIcon(imageURL);  
        imageURL = cldr.getResource("images/whiterook.png"); 
        this.whiteRook = new ImageIcon(imageURL);   
        imageURL = cldr.getResource("images/blackrook.png"); 
        this.blackRook = new ImageIcon(imageURL);   
        imageURL = cldr.getResource("images/whiteknight.png"); 
        this.whiteKnight = new ImageIcon(imageURL);        
        imageURL = cldr.getResource("images/blackknight.png"); 
        this.blackKnight = new ImageIcon(imageURL); 
        imageURL = cldr.getResource("images/whitebishop.png"); 
        this.whiteBishop = new ImageIcon(imageURL);   
        imageURL = cldr.getResource("images/blackbishop.png"); 
        this.blackBishop = new ImageIcon(imageURL);   
        imageURL = cldr.getResource("images/whitequeen.png"); 
        this.whiteQueen = new ImageIcon(imageURL);        
        imageURL = cldr.getResource("images/blackqueen.png"); 
        this.blackQueen = new ImageIcon(imageURL);  
        imageURL = cldr.getResource("images/whiteking.png"); 
        this.whiteKing = new ImageIcon(imageURL);        
        imageURL = cldr.getResource("images/blackking.png"); 
        this.blackKing = new ImageIcon(imageURL);      
        imageURL = cldr.getResource("images/numbers.png"); 
        this.numbers = new ImageIcon(imageURL);  
        imageURL = cldr.getResource("images/letters.png"); 
        this.letters = new ImageIcon(imageURL);          
          
    }

    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paintBoard(graphics);
        paintPieces(graphics);
        paintNumbersLetters(graphics);
        
    }
    
    /** 
     * Metodi joka maalailee shakkilaudan.
     * 
     * @param graphics luokka.
     */     
    private void paintBoard(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(350, 100, 367, 367);
        graphics.setColor(Color.BLACK);  
        graphics.fillRect(396, 100, 1, 367);   
        graphics.fillRect(442, 100, 1, 367); 
        graphics.fillRect(488, 100, 1, 367); 
        graphics.fillRect(534, 100, 1, 367); 
        graphics.fillRect(580, 100, 1, 367);    
        graphics.fillRect(626, 100, 1, 367);   
        graphics.fillRect(672, 100, 1, 367);   
        graphics.fillRect(350, 146, 367, 1); 
        graphics.fillRect(350, 192, 367, 1);
        graphics.fillRect(350, 238, 367, 1);
        graphics.fillRect(350, 284, 367, 1);
        graphics.fillRect(350, 330, 367, 1);
        graphics.fillRect(350, 376, 367, 1);
        graphics.fillRect(350, 422, 367, 1);        
    }
    
    /** 
     * Metodi joka maalailee shakkilaudan reunat.
     * 
     * @param graphics luokka.
     */    
    private void paintNumbersLetters(Graphics graphics) {
        numbers.paintIcon(this, graphics, 310, 100);
        numbers.paintIcon(this, graphics, 719, 100); 
        letters.paintIcon(this, graphics, 350, 54);
        letters.paintIcon(this, graphics, 350, 468);        
    }
    
    /** 
     * Metodi joka maalailee nappulat shakkilaudalle
     * 
     * @param graphics luokka.
     */         
    private void paintPieces(Graphics graphics) {
        chess.copyBoard();
        int [][] cboard = chess.getBoard();
        
        if (cboard != null) {
        for (int i = 0; i < cboard.length; i++) {
            for (int j = 0; j < cboard.length; j++) {
                
                if (cboard[i][j] == 1) {
                    whitePawn.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                }
                
                if (cboard[i][j] == 2) {
                    blackPawn.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                }
                
                if (cboard[i][j] == 3) {
                    whiteRook.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                }
                
                if (cboard[i][j] == 4) {
                    blackRook.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                }
                
                if (cboard[i][j] == 5) {
                    whiteKnight.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                }
                
                if (cboard[i][j] == 6) {
                    blackKnight.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                }  
                
                if (cboard[i][j] == 7) {
                    whiteBishop.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                } 
                
                if (cboard[i][j] == 8) {
                    blackBishop.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                }
                
                if (cboard[i][j] == 9) {
                    whiteQueen.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                } 
                
                if (cboard[i][j] == 10) {
                    blackQueen.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                }
                
                if (cboard[i][j] == 11) {
                    whiteKing.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                } 
                
                if (cboard[i][j] == 12) {
                    blackKing.paintIcon(this, graphics,  350 + j * 46, 100 + i * 46);
                }                   
            }
        }        
        }        
    }                                 
}
