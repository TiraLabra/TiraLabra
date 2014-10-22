

package Käyttöliittymä;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author Jaakko
 * 
 * Kuva luokka hakee kuvan joka muutetaan BufferedImageksi johon voi piirtää viivoja.
 */

public class Kuva extends JComponent implements MouseListener, MouseMotionListener{
    public BufferedImage kuva;
    private Point p1, p2;
    
    private Point LahtoPiste;

    private Point MaaliPiste;

 
    public Kuva(){
        

        haeKuva();

        addMouseListener(this);
        addMouseMotionListener(this);        

    }
    
    /**
     * 
     * Hakee kuvan "Untitled.jpg" ja asettaa sen BufferedImage kuvaksi.
     * 
     */    
    public void haeKuva() {
        
        
        try {
            this.kuva=ImageIO.read(new File(getClass().getResource("Untitled.jpg").toURI()));
        } catch (IOException ex) {
            Logger.getLogger(Aloitus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Aloitus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Point haeLahto(){
        return LahtoPiste;
        
    }
    
    public Point haeMaali(){
        return MaaliPiste;
        
    }    
 
/**
 *
 * Maalaa BufferedImagen.
 * 
 * @param grafiikka grafiikka
 * 
 */  
    @Override
    protected void paintComponent(Graphics grafiikka) {
        grafiikka.drawImage(this.kuva, 0, 0, this);

    }
    
/**
 *Asettaa p1:een hiiren klikkaaman pisteen.
 * 
 * @param me hiirieventti
 */   
    @Override
    public void mousePressed(MouseEvent me) {

        p1 = me.getPoint();
    }
    

/**
 * Asettaa haettavan reitin alku ja loppupisteen. 
 * Lisäksi piirtää viivoja.
 * 
 * @param me hiirieventti
 */         
    @Override
    public void mouseReleased(MouseEvent me) {
        if(me.isShiftDown()){
            LahtoPiste=p1;
            
            
        }else if(me.isControlDown()){
            MaaliPiste=p1;
            
            
        }else{
            
            p2 = me.getPoint();
            Graphics2D kuvagrafiikka = (Graphics2D)this.kuva.getGraphics();
            kuvagrafiikka.setStroke(new BasicStroke(10));
            kuvagrafiikka.setColor(Color.BLACK);

              if (p1 != null && p2 != null){
                kuvagrafiikka.drawLine(p1.x, p1.y, p2.x, p2.y);

            }
            repaint(); 
            
        }

    }
    

    

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }


    
}
