package fileio;

import com.mycompany.tiralabra_maven.gui.Ruutu;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Luokka, joka osaa lukea ruudukon kuvan perusteella.
 *
 * @author mikko
 */
public class KuvanLukija {

    /**
     * Lukee parametrina annetusta kuvatiedostosta ja palauttaa ruudukon
     * algoritmin ymmärtämässä muodossa.
     *
     * @param tiedosto luettava tiedosto
     * @return maailma
     */
    public Ruutu[][] lueMaailmaKuvasta(File tiedosto) {
        BufferedImage img;
        try {
            img = ImageIO.read(tiedosto);
        } catch (IOException e) {
            return null;
        }
        if (img == null) {
            return null;
        }

        Ruutu[][] palautus = new Ruutu[img.getHeight()][img.getWidth()];
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
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
