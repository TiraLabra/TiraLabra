package com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Hannu
 */
public class XXXX {

    public void seeBMPImage(String BMPFileName) throws IOException {
//    public void seeBMPImage(String BMPFileName) {
//        BufferedImage image = ImageIO.read(getClass().getResource(BMPFileName));
        File BMPFile=new File(BMPFileName);
        BufferedImage image = ImageIO.read(BMPFile);

        int[][] array2D = new int[image.getWidth()][image.getHeight()];
        
        int color=9;

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                color = image.getRGB(xPixel, yPixel);
                if (color == Color.white.getRGB()) {
                    array2D[xPixel][yPixel] = 1;
                }
                else if (color == Color.black.getRGB()) {
                    array2D[xPixel][yPixel] = 2;
                }
                else if (color == Color.red.getRGB()) {
                    array2D[xPixel][yPixel] = 3;
                } else {
                    array2D[xPixel][yPixel] = 8; // ?
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

        Color myBlue = new Color(0, 0, 255);
        int rgb = myBlue.getRGB();

        try {
//            BufferedImage img = null;
//            try {
//                img = ImageIO.read(new File(BMPFileName));
//            } catch (IOException e) {
//            }



//            for (int i = 0; i < 100; i++) {
//                for (int j = 0; j < 100; j++) {

                    image.setRGB(0, 0, rgb);
//                }
//            }

            // retrieve image
            File outputfile = new File("bitmaps/Result.bmp");
//            File outputfile = new File("testikartta007-16-color-result.bmp");
            ImageIO.write(image, "bmp", outputfile);
        } catch (IOException e) {
        }


    }
}
