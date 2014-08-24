/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import com.mycompany.tiralabra_maven.gui.Ruutu;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author mikko
 */
public class KuvanLukija {
    

    public Ruutu[][] lueMaailmaKuvasta(File tiedosto) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(tiedosto);
        } catch (IOException e) {
            return null;
        }
        if (img == null) {
            return null;
        }
        
        Ruutu[][] palautus = new Ruutu[img.getHeight()][img.getWidth()];
        Ruutu ruutu;
        for (int y = 0; y<img.getHeight(); y++) {
            for (int x = 0; x<img.getWidth(); x++) {
                palautus[y][x] = variaVastaavaRuutu(img.getRGB(x, y));
            }
        }
        
        return palautus;
    }
    
    private Ruutu variaVastaavaRuutu(int vari) {
        if (vari == Color.BLACK.getRGB()) {
            return Ruutu.SEINA;
        }
        if (vari == Color.YELLOW.getRGB()) {
            return Ruutu.HIEKKA;
        }
        if (vari == Color.BLUE.getRGB()) {
            return Ruutu.VESI;
        }
        if (vari == Color.GREEN.getRGB()) {
            return Ruutu.RUOHO;
        }
        return Ruutu.LATTIA;
    }
}
