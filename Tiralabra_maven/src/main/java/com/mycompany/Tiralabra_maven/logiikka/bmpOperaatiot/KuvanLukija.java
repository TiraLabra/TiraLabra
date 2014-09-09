package com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot;

import com.mycompany.Tiralabra_maven.logiikka.Piste;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Luokka jonka avulla luetaan lähtotietokartta bmp-tiedostosta.
 */
public class KuvanLukija {

    private BufferedImage image;
    private Piste lahtoPiste;
    private Piste maaliPiste;

    /**
     * Metodi avaa bmp-tiedoston ja muuttaa sen värit kokonaislukutaulukoksi.
     *
     * @return Kokonaislukutaulukko, jossa kukin luku kertoo kyseiseen paikkaan
     * siirtymiseen kuluvan ajan.
     *
     * @param Luettavan bmp-tiedoston nimi.
     */
    public int[][] seeBMPImage(String BMPFileName) {
        File BMPFile = new File(BMPFileName);

        try {
            this.image = ImageIO.read(BMPFile);
        } catch (IOException ioe) {
            System.out.println("Virhe kuvatiedoston lukemisessa.");
        }

        int[][] array2D = new int[image.getWidth()][image.getHeight()];

        int color;

        int este = Integer.MAX_VALUE / 10;
        int vaikeaKulkuinen = 5;

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                color = image.getRGB(xPixel, yPixel);
                if (color == Color.WHITE.getRGB()) {
                    array2D[xPixel][yPixel] = 1;
                } else if (color == Color.BLACK.getRGB()) {
                    array2D[xPixel][yPixel] = este;
                } else if (color == Color.GRAY.getRGB()) {
                    array2D[xPixel][yPixel] = vaikeaKulkuinen;
                } else if (color == Color.GREEN.getRGB()) {
                    array2D[xPixel][yPixel] = 1;
                    this.lahtoPiste = new Piste(xPixel, yPixel);
                } else if (color == Color.RED.getRGB()) {
                    array2D[xPixel][yPixel] = 1;
                    this.maaliPiste = new Piste(xPixel, yPixel);
                }
            }
        }

        return array2D;
    }

    /**
     * Metodi palauttaa bmp-tiedostostosta löydetyn vihreän pikselin kohdalla
     * olevan pisteen.
     *
     * @return Piste-olio, jonka koordinaatit ovat lähtöpisteen.
     */
    public Piste getLahtoPiste() {
        return this.lahtoPiste;
    }

    /**
     * Metodi palauttaa bmp-tiedostostosta löydetyn punaisen pikselin kohdalla
     * olevan pisteen.
     *
     * @return Piste-olio, jonka koordinaatit ovat maalipisteen.
     */
    public Piste getMaaliPiste() {
        return this.maaliPiste;
    }
}
