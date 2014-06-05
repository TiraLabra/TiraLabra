package Hike.ImageTable;

import Hike.Graph.Node;
import Hike.Values;
import Hike.gameWindow.ShowPicture;
import java.awt.Color;
import java.awt.image.BufferedImage;


/**
 * Creates a 2d integer array based on the image.
 * @author petri
 */

public class ImageTable {

    private ShowPicture picture;
    private Node[][] tab;
    private Node[][] nodetable;
    private BufferedImage image;
    
    

    public ImageTable(ShowPicture pic) {
        picture = pic;
        image = picture.getImage();
        tab = new Node[Values.IMAGEHEIGHT][Values.IMAGEWIDTH];


        convertImage(Values.IMAGEHEIGHT, Values.IMAGEWIDTH);



    }
    
    /**
     * Creates a table based on the picture. Method checks the image pixel by pixel and creates a node with the same location and weight.
     */

    private void convertImage(int h, int w) {
        for (int y = 0; y < h; y++) { // Y = height, X == width  
            for (int x = 0; x < w; x++) {

                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

//                System.out.print("Pixel " + x + "," + y + " ");
//                System.out.println(red + " " + green + " " + blue);
                if (red == 255 && green == 255 && blue == 255) { //white
                    tab[y][x] = new Node(y, x, Values.NORMALGROUND);
                } else if (red == 0 && green == 255 && blue == 0) {
                    tab[y][x] = new Node(y, x, Values.GRASS);

                } else if (red == 255 && green == 255 && blue == 0) {
                    tab[y][x] = new Node(y, x, Values.DESERT);
                } else if (red == 0 && green == 0 && blue == 255) {
                    tab[y][x] = new Node(y, x, Values.WATER);
                } else if (red == 0 && green == 0 && blue == 0) {
                    tab[y][x] = new Node(y, x, Values.MOUNTAIN);
                } else {                    //If the user has used a color with wrong RGB values.                                                
                    System.out.println("Unknown color! R: " + red + " G: " + green + " B: " + blue);
                    tab[y][x] = new Node(y, x, 500);
                }
            }
        }
        tab[0][0].setNeighbours(tab, h, w);
        nodetable = tab[0][0].getTable();
        





    }

    public void printTable() {
        for (int y = 0; y < Values.IMAGEHEIGHT; y++) {
            System.out.println("");
            for (int x = 0; x < Values.IMAGEWIDTH; x++) {
                System.out.print("| " + tab[y][x].getY() + " " + tab[y][x].getX() + " ");
            }

        }
    }

    public Node[][] getTable() {
        return this.tab;
    }

    public Node[][] getNodeTable() {
        return this.nodetable;
    }
}
