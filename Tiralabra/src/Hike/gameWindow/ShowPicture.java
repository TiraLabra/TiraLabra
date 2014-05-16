/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.gameWindow;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author petri
 */
public class ShowPicture extends JPanel {

    private BufferedImage image;


    public ShowPicture(String file) {

        
        try {
            image = ImageIO.read(this.getClass().getResource(file));
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
}
