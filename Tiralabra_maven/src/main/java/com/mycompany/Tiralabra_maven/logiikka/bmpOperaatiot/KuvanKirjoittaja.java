package com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import com.mycompany.Tiralabra_maven.logiikka.paikkaPino.OmaPinoAlkionaPaikka;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.util.Stack;
import javax.imageio.ImageIO;

/**
 * KESKENERÄINEN
 */
public class KuvanKirjoittaja {

    private BufferedImage image;

    public void writeImage(String BMPFileName, String outputFileEnding, OmaPinoAlkionaPaikka kaydytPaikat, OmaPinoAlkionaPaikka reittiPino) {

        File outputfile = new File(BMPFileName.substring(0, BMPFileName.length() - 4) + outputFileEnding + ".bmp");

        try {
            this.image = ImageIO.read(new File(BMPFileName));
        } catch (IOException e) {
            System.out.println("virhe");
        }

        int color;
        Paikka paikkaK;
        while (!kaydytPaikat.stackIsEmpty()) {
            paikkaK = kaydytPaikat.stackPop();
            color = image.getRGB(paikkaK.i, paikkaK.j);
            if (!(color == Color.BLACK.getRGB() || color == Color.GREEN.getRGB() || color == Color.RED.getRGB() || color == Color.GRAY.getRGB())) {
                image.setRGB(paikkaK.i, paikkaK.j, Color.YELLOW.getRGB());
            }
        }

        Paikka paikkaU;
        while (!reittiPino.stackIsEmpty()) {
            paikkaU = reittiPino.stackPop();
            image.setRGB(paikkaU.i, paikkaU.j, Color.BLUE.getRGB());

        }

//            image.setRGB(0, 4, Color.GRAY.getRGB());


        try {
            ImageIO.write(image, "bmp", outputfile);
        } catch (IOException e) {
        }
    }
}
