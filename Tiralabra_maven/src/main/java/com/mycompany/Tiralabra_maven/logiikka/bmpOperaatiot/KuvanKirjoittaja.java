// KESKENERÄINEN
package com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * KESKENERÄINEN
 */
public class KuvanKirjoittaja {

    private BufferedImage image;

    public void writeImage(String BMPFileName, int[][] kuvataulukko) {

        try {
            this.image = ImageIO.read(new File(BMPFileName));
        } catch (IOException e) {
            System.out.println("virhe");
        }

//        this.image=image;


        Color myBlue = new Color(0, 0, 255);
        int blue = myBlue.getRGB();

        try {



//            for (int i = 0; i < 100; i++) {
//                for (int j = 0; j < 100; j++) {

            File outputfile = new File("bitmaps/Kartta000Result.bmp");
            ImageIO.write(image, "bmp", outputfile);

            image.setRGB(0, 0, blue);
//                }
//            }

            // retrieve image
        } catch (IOException e) {
        }
    }
}
