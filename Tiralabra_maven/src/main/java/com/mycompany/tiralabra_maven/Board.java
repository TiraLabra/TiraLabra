package com.mycompany.tiralabra_maven;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Tiina
 */
public class Board extends JPanel implements ActionListener{

    /**
     *
     */
//    public final int B_WIDTH = 350;
//    private final int B_HEIGHT = 350;
//    private final int INITIAL_X = -40;
//    private final int INITIAL_Y = -40;
    private final int DELAY = 50;

    /**
     *
     */
    public static int x_map = 0;

    /**
     *
     */
    public static int y_map = 0;
    
    private Hiiri maus;
    private Timer timer;
    private Wall tiili;
    //private int x, y;

    /**
     *Luo uuden JPanelin, jota käytetään pelialustana. Luo myös hiiren ja seinät.
     */
        public Board() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setBackground(Color.green);
        maus = new Hiiri();
        timer = new Timer(DELAY, this);
        timer.start();
        tiili = new Wall();
    }

    /**
     *Piirtää hiiren ja seinät.
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(0, 0, 50, 50);
        g2d.setColor(Color.orange);
        g2d.fillRect(450, 450, 500, 500);

        
        g2d.drawImage(maus.getImage(), maus.getX(), maus.getY(), this);
            
            int [][] map = Wall.getMap();            
            
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if(map[i][j] == 1){
                        g2d.drawImage(tiili.getImage(), x_map, y_map, this);                         
                    }
                    x_map = x_map + 50;
                } 
                x_map = 0;
                y_map = y_map + 50;
            }
            
            y_map = 0;
            x_map = 0;

        g2d.drawString(Hiiri.getXcoord()+"", 20, 20);
        g2d.drawString(Hiiri.getYcoord()+"", 60, 60);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    /**
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        maus.move();
        repaint();  
    }

    private class TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            maus.keyReleased(e);
        }
        public void keyPressed(KeyEvent e) {
            maus.keyPressed(e);
        }
    }
    
    
    
    
    
    //Board itsekseen liikkuvaa hiirtä varten.
//    public Board() {
//                
//        loadImage();
//        initBoard();        
//    }
//    
//    private void loadImage() {
//        
//        ImageIcon ii = new ImageIcon("maus.png");
//        maus = ii.getImage();        
//    }
//    
//    private void initBoard() {
//        
//        setBackground(Color.green);
//        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
//
//        setDoubleBuffered(true);
//
//        x = INITIAL_X;
//        y = INITIAL_Y;
//        
//        timer = new Timer(DELAY, this);
//        timer.start();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        drawStar(g);
//    }
//    
//    private void drawStar(Graphics g) {
//        
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.drawImage(maus, x, y, this);
//        Toolkit.getDefaultToolkit().sync();
//        g.dispose();        
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
// 
//        x += 1;
//        y += 1;
//
//        if (y > B_HEIGHT) {
//            
//            y = INITIAL_Y;
//            x = INITIAL_X;
//        }
//        
//        repaint();  
//    }
}