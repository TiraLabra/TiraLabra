

package Käyttöliittymä;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author Jaakko
 * 
 * Kuva luokka hakee kuvan joka muutetaan BufferedImageksi johon voi piirtää viivoja.
 */

public class Kuva extends JComponent implements MouseListener, MouseMotionListener{
    BufferedImage kuva;
    private Point p1, p2;
 
    public Kuva() throws IOException, URISyntaxException {
        this.kuva =  ImageIO.read(new File(getClass().getResource("untitled.jpg").toURI()));

        addMouseListener(this);
        addMouseMotionListener(this);        

    }
 
/**
 *
 * Maalaa BufferedImagen ja pisteiden p1 ja p2 välille viivan.
 * 
 * @param grafiikka grafiikka
 * 
 */  
    @Override
    protected void paintComponent(Graphics grafiikka) {
        grafiikka.drawImage(kuva, 0, 0, this);
        if (p1 != null && p2 != null){
            grafiikka.drawLine(p1.x, p1.y, p2.x, p2.y);
            
        }
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
 *Kutsuu metodia mouseReleased niin kauan kuin hiirtä pidetään pohjassa.
 * 
 * @param me hiirieventti
 */       
    @Override
    public void mouseDragged(MouseEvent me) {
        mouseReleased(me);
    }

/**
 *Asettaa p2:een hiiren klikkaaman pisteen ja repainttaa.
 * 
 * @param me hiirieventti
 */         
    @Override
    public void mouseReleased(MouseEvent me) {
        p2 = me.getPoint();
        repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent klik) {
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }


    @Override
    public void mouseMoved(MouseEvent me) {
    }
    
}
