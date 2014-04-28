/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juhor.tiralabra.ui;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author JuhoRim
 */
public class FileManager {

    private FileWriter writer;
    private Scanner fileReader;

    public FileManager() {}


    public double[][] readImagesFromDirectory(File dir, int startingIndex,  int endIndex) throws IOException {
        double[][] returnVal = new double[endIndex-startingIndex][27 * 27];
        FilenameFilter imgFilter = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                if (name.endsWith(".png")) {
                    return true;
                }
                return (false);
            }
        };

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files.length >= endIndex) {
                for (int i = startingIndex; i < endIndex; i++) {
                    returnVal[i] = readImage(files[i]);
                }
            }else{
                returnVal = new double[files.length-startingIndex+1][27*27];
                for(int i = startingIndex; i < files.length; i++){
                    returnVal[i] = readImage(files[i]);
                }
            }
            
            return returnVal;
        }
        return null;
    }

    public double[] readImage(File f) throws IOException {
        BufferedImage im = ImageIO.read(f);
        Raster r = im.getData();
        return r.getPixels(0, 0, r.getWidth(), r.getHeight(), (double[]) null);
    }

}
