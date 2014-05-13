
package chess.gui;

import chess.game.Chess;
import java.awt.Container;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Graafisen käyttöliitymän pääluokka.
 */

public class Gui implements Runnable {
    /**
     * JFrame frame JFrame luokka.
     */     
    private JFrame frame;
    /**
     * Drawning paint, piirtoalusta olio.
     */     
    private DrawingPanel paint;
    /**
     * Jbutton newGame, aloitus nappula pelissä.
     */     
    private JButton newGame;
    /**
     * Jbutton exit, lopetus nappula pelissä.
     */     
    private JButton exit;
    /**
     * Jbutton giveUp, luovutus nappula pelissä.
     */    
    private JButton giveUp;
    /**
     * ModifyComponent mC, ModifyComponent olio jolla muokataan pelin komponenttejä.
     */     
    private ModifyComponent mC;
    /**
     * Chess chess, Chess olio joka liittää käyttöjärjestelmän pelilogiikkaan.
     */     
    private Chess chess;
    /**
     * JTextField startxField, tekstikenttä johon syötettään siirtojen alku x - koordinaatti.
     */    
    private JTextField startField;
    /**
     * JTextField startyField, tekstikenttä johon syötettään siirtojen alku y - koordinaatti.
     */     
    private JTextField endField;
    /**
     * JTextField endyField, tekstikenttä johon syötettään siirtojen loppu y - koordinaatti.
     */            
    private JLabel start;
    /**
     * JLabel endx, JLabel jotta pelaaja tietäisi mitä endxField tekee.
     */    
    private JLabel end;
    /**
     * JLabel turns, JLabel joka ilmoittaa kumman vuoro pelissä on menossa.
     */     
    private JLabel turns;
    /**
     * JButton move, JButton jolla suoritettaan siirrot, pelin tärkein nappi.
     */    
    private JButton moves;
    /**
     * JLabel info, JLabel joka ilmoittaa shakkimatti tilanteet ja pelin voiton.
     */    
    private JLabel info;
    /**
     * Integer sx, siirtojen lähtö x - koordinaatti.
     */     
    private int sx;
    /**
     * Integer sy, siirtojen lähtö y - koordinaatti.
     */      
    private int sy;
    /**
     * Integer ex, siirtojen kohde x - koordinaatti.
     */    
    private int ex;
    /**
     * Integer ey, siirtojen kohde y - koordinaatti.
     */     
    private int ey;


    /**
     * Luokan konstruktori. Luodaan Chess olio joka tulee pyörittämään pelilogiikkaa.
     */
    public Gui () {
        chess = new Chess();
    }

    /**
     * Tämä metodi käynnistää käyttöliittymän.
     */
    @Override
    public void run() {
        frame = new JFrame("Chess");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());       
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Tämä metodi luo pelin komponentit.
     */
    private void createComponents(Container container) {
        this.paint = new DrawingPanel(chess);
        this.mC = new ModifyComponent();
        
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        mC.modifyJMenu(file, 15, menu);
        
        JMenuItem load = new JMenuItem("Load game");
        mC.modifyJMenuItem(load, 15, file);        
        load.addActionListener(new MenuActions(this));
        
        JMenuItem save = new JMenuItem("Save game");
        mC.modifyJMenuItem(save, 15, file);         
        save.addActionListener(new MenuActions(this));
       
        
        newGame = new JButton("Start new game");
        mC.modifyJButton(newGame, 50, 50, 150, 50, 12, container);
        newGame.addActionListener(new ButtonActions(this));
        
        giveUp = new JButton("Give up");
        mC.modifyJButton(giveUp, 50, 150, 150, 50, 12, container);
        giveUp.addActionListener(new ButtonActions(this));
        
        exit = new JButton("Exit");
        mC.modifyJButton(exit, 50, 250, 150, 50, 12, container);
        exit.addActionListener(new ButtonActions(this));

        start = new JLabel("start square: ");
        mC.modifyJLabel(start, 25, 350, 70, 50, 12, container);

        startField = new JTextField("");
        mC.modifyJTextField(startField, 100, 350, 50, 40, 12, container);

        end = new JLabel("end square: ");
        mC.modifyJLabel(end, 25, 400, 70, 50, 12, container);

        endField = new JTextField("");
        mC.modifyJTextField(endField, 100, 400, 50, 40, 12, container);

        moves = new JButton("Move piece");
        mC.modifyJButton(moves, 120, 470, 140, 50, 12, container);


        turns = new JLabel("White turn ");
        mC.modifyJLabel(turns, 375, -10, 100, 100, 16, container);

        info = new JLabel("");
        mC.modifyJLabel(info, 500, -10, 200, 100, 16, container);
        
        frame.setJMenuBar(menu);
        container.add(paint);

    }

    /**
     * Tämä metodi on Move Piece nappulan käyttöä varten, valkoinen ja musta
     * käyttävät samaa nappulaa.
     */
    public void turns() {
        this.frame.repaint(); 
        update();        
        int turn = chess.getTurn();
        MovesListener listener = new MovesListener(this, startField, endField, turn);
        moves.addActionListener(listener);
    }

    /**
     * Tämä metodi aloittaa uuden pelin.
     */
    public void startGame() {
        chess.newGame();
        this.moves.setEnabled(true); 
        this.info.setText("New game");
        this.turns.setText("White turn");
        this.frame.repaint();
        turns();
    }

    /**
     * Tämä metodi ilmoittaa logiikka luokalle että peli pitäisi tallentaa.
     */
    public void saveGame() throws IOException {
        chess.saveGame();
    }
 
    /**
     * Tämä metodi suorittaa pelin latauksen tiedostosta gui:n osalta.
     */    
    public void loadGame() throws FileNotFoundException {
        chess.loadGame();
        this.moves.setEnabled(true); 
        this.frame.repaint();
        update();        
        turns();        
    }    

    /**
     * Tämä metodi lähettää logiikka luokalle viestin että valkoista pitäisi siirtää.
     */
    public void moveWhite() {
        chess.whiteTurn(sx, sy, ex, ey);
        turns();
    }

    /**
     * Tämä metodi lähettää logiikka luokalle viestin että mustaa pitäisi siirtää.
     */
    public void moveBlack() {
        chess.blackTurn(sx, sy, ex, ey);
        turns();
    }

    /**
     *
     * Tätä metodia käytettään siirtokoordinaattien luontia varten.
     *
     * @param x asettaa sx koordiaatin siirtoa varten.
     */
    public void setSX(int x) {
        this.sx = x;
    }

    /**
     * Tätä metodia käytettään siirtokoordinaattien luontia varten.
     *
     * @param y asettaa sy koordiaatin siirtoa varten.
     */
    public void setSY(int y) {
        this.sy = y;
    }

    /**
     * Tätä metodia käytettään siirtokoordinaattien luontia varten.
     *
     * @param x asettaa ex koordiaatin siirtoa varten.
     */
    public void setEX(int x) {
        this.ex = x;
    }

    /**
     * Tätä metodia käytettään siirtokoordinaattien luontia varten.
     *
     * @param y asettaa ey koordiaatin siirtoa varten.
     */
    public void setEY(int y) {
        this.ey = y;
    }

    /**
     * Tämä metodi luovuttaa pelin jos luovutusnappia painetaan.
     */
    public void endGame() {
        if (chess.getTurn() % 2 == 0) {
            chess.setInfo(4);
        } else {
            chess.setInfo(3);
        }
        
        this.frame.repaint();
        update();
        this.moves.setEnabled(false);      
    }

    public JFrame getFrame() {
        return frame;
    }
   
    /**
     * Tämä metodi päivittää nappuloiden tilanteen kun niitä painetaan.
     */    
    public void update() {                  
        if (chess.getTurn() % 2 == 0) {
            turns.setText("Black turn");
        } else {
            turns.setText("White turn");
        }
        
        if (chess.getInfo() == 0) {
            info.setText("");
        }
        
        if (chess.getInfo() == 1) {
            info.setText("White King under attack!");
        }
        
        if (chess.getInfo() == 2) {
            info.setText("Black King under attack!");
        }
        
        if (chess.getInfo() == 3) {
            info.setText("Black wins!");
            moves.setEnabled(false);
        }
        
        if (chess.getInfo() == 4) {
            info.setText("White wins!");
            moves.setEnabled(false);            
        }        
    } 
    
}
