package Hike.ImageTable;

import Hike.Values;
import Hike.gameWindow.ShowPicture;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class ImageTable {

    private ShowPicture picture;
    private int[][] table;
    private BufferedImage image;

    public ImageTable(ShowPicture pic) {
        picture = pic;
        image = picture.getImage();
        table = new int[Values.IMAGEHEIGHT][Values.IMAGEWIDTH];

        convertImage();



    }

    private void convertImage() {
        int pixelcount = 0;
        for (int y = 0; y < Values.IMAGEHEIGHT; y++) { // Y = height, X == width  
            for (int x = 0; x < Values.IMAGEWIDTH; x++) {

                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
//                System.out.print("Pixel " + x + "," + y + " ");
//                System.out.println(red + " " + green + " " + blue);
                if (red == 255 && green == 255 && blue == 255) { //white
                    table[y][x] = Values.NORMALGROUND;
                } else if (red == 0 && green == 255 && blue == 0) {
                    table[y][x] = Values.WOODS;

                } else if (red == 255 && green == 255 && blue == 0) {
                    table[y][x] = Values.DESERT;
                } else {
                    table[y][x] = 1000;
                }
            }
        }

    }

    public void printTable() {
        for (int y = 0; y < Values.IMAGEHEIGHT; y++) {
            System.out.println("");  
            for (int x = 0; x < Values.IMAGEWIDTH; x++) {
                System.out.print(table[y][x]);
            }

        }
    }
    
    public int[][] getTable() {
        return this.table;
    }
}
