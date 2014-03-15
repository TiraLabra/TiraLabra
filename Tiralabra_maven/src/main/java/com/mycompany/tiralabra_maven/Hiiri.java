/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

 

public class Hiiri {
    
    private String hii = "maus.png";

    private int dx;
    private int dy;
    private int x;
    private int y;
    private Image image;

    public Hiiri(){
        ImageIcon ii = new ImageIcon("maus.png");
        image = ii.getImage();
        x = 50;
        y = 50;

    }
    
   //nyt hiiri tekee hypyn tuntemattomaan jos menee yli reunan.
   public void move() {
        if (y > 700) {
            
            y = -40;
            x = -40;
        }
        x += dx;
        y += dy;
   }

    public int getX() {
  
      return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dy = 0;
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dx = 0;
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            
            dy = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    
}
 
 