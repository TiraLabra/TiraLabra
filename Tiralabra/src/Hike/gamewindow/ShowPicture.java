/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.gamewindow;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *  Class for showing pictures.
 * @author petri
 */
public class ShowPicture extends JPanel {

    private BufferedImage image;
    private int h;
    private int w;


    public ShowPicture(String file) {

        
        try {
            image = ImageIO.read(this.getClass().getResource(file));
            w = image.getWidth();
            h = image.getHeight();
        } catch (IOException ex) {
            Logger.getLogger(ShowPicture.class.getName()).log(Level.SEVERE, null, ex);
        }
        {
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);       
    }
    
    public BufferedImage getImage() {
        return image;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }
    
    
}
