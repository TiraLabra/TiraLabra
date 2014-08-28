// KESKENERÄINEN
package com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javax.imageio.ImageIO;

/**
 * KESKENERÄINEN
 */
public class KuvanKirjoittaja {

    private BufferedImage image;

    public void writeImage(String BMPFileName, int[][] kuvataulukko,Stack<Paikka> reittiPino) {

        try {
            this.image = ImageIO.read(new File(BMPFileName));
        } catch (IOException e) {
            System.out.println("virhe");
        }

        Color myBlue = new Color(0, 0, 255);
        int blue = myBlue.getRGB();

        Paikka paikkaU;
                while (!reittiPino.empty()) {
            paikkaU = reittiPino.pop();
//            System.out.println(paikkaU.i + ", " + paikkaU.j);
//            this.reittiKartta[paikkaU.i][paikkaU.j] = 0;
                    image.setRGB(paikkaU.i, paikkaU.j, blue);

        }



        try {



//            for (int i = 0; i < 100; i++) {
//                for (int j = 0; j < 100; j++) {

            File outputfile = new File(BMPFileName.substring(0, BMPFileName.length() - 4) + "tulos.bmp");
            ImageIO.write(image, "bmp", outputfile);

//                }
//            }

            // retrieve image
        } catch (IOException e) {
        }
    }
}
