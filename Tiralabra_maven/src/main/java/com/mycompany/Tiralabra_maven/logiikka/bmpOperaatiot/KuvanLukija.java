// KESKENERÄINEN
package com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot;

import com.mycompany.Tiralabra_maven.logiikka.Piste;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * KESKENERÄINEN
 */
public class KuvanLukija {

    private BufferedImage image;
    private Piste lahtoPiste;
    private Piste maaliPiste;
//    private Piste lahtoPiste = new Piste(1, 1);
//    private Piste maaliPiste = new Piste(22, 22);

    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * ALUSTAVA metodi avaa bmp-tiedoston ja muuttaa sen kokonaislukutaulukoksi,
     * joka tulostetaan. Sitten kuvan ensimmäinen pikseli muutetaan siniseksi ja
     * kuva tallennetaan uudella nimellä.
     */
    public int[][] seeBMPImage(String BMPFileName) {
//    public void seeBMPImage(String BMPFileName) {
//        BufferedImage image = ImageIO.read(getClass().getResource(BMPFileName));
        File BMPFile = new File(BMPFileName);


        try {
            this.image = ImageIO.read(BMPFile);
        } catch (IOException ioe) {
            System.out.println("Virhe kuvatiedoston lukemisessa.");
        }




        int[][] array2D = new int[image.getWidth()][image.getHeight()];


        int color;

//        int e = Integer.MAX_VALUE / 10;
        int este = 9;
        int vaikeaKulkuinen = 2;

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                color = image.getRGB(xPixel, yPixel);
                if (color == Color.WHITE.getRGB()) {
                    array2D[xPixel][yPixel] = 1;
                } else if (color == Color.BLACK.getRGB()) {
                    System.out.println("musta");
                    array2D[xPixel][yPixel] = este;
                } else if (color == Color.GRAY.getRGB()) {
                    array2D[xPixel][yPixel] = vaikeaKulkuinen;
                } else if (color == Color.RED.getRGB()) {
                    array2D[xPixel][yPixel] = 3;
                    this.lahtoPiste = new Piste(xPixel, yPixel);
                    System.out.println("punainen");
                } else if (color == Color.BLUE.getRGB()) {
                    System.out.println("sininen");
                    array2D[xPixel][yPixel] = 4;
                    this.maaliPiste = new Piste(xPixel, yPixel);
//                } else if (color == Color.GREEN.getRGB()) {
//                    array2D[xPixel][yPixel] = vaikeaKulkuinen;
//                } else {
//                    array2D[xPixel][yPixel] = 8; // ?
                }
            }
        }

//        Color.


        for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
            for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
                System.out.print(array2D[xPixel][yPixel]);
            }
            System.out.println("");
        }

        System.out.println("lahto: " + this.lahtoPiste.i + ", " + this.lahtoPiste.j);
        System.out.println("maali: " + this.maaliPiste.i + ", " + this.maaliPiste.j);


        return array2D;
    }

    public Piste getLahtoPiste() {
        return this.lahtoPiste;
    }

    public Piste getMaaliPiste() {
        return this.maaliPiste;
    }
}
