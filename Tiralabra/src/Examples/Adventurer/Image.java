/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Adventurer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Kalle
 */
public class Image {
    private BufferedImage image;
    private int x,y;
    public Image(String src, int x, int y){
        try{
            this.image = ImageIO.read(new File(src));
        }
        catch(IOException e){
        }
        this.x=x;
        this.y=y;
    }
    public void draw(Graphics g){
        g.drawImage(image, x, y, null);
    }
}
