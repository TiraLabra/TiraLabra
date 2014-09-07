/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

import datastructures.Paketti;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Luokka lukemista varten
 * @author joonaskylliainen
 */
public class MyReader {
    
    /**
     * Lukee sille annetun byte-taulukon sisältävän tiedoston ja palauttaa paketti-olion
     * @param aInputFileName
     * @return
     */
    public Paketti read(String aInputFileName){
        System.out.println("Luetaan...");
        File file = new File(aInputFileName);
        System.out.println("Tiedoston koko: " + file.length());
        byte[] result = new byte[(int)file.length()];
        try {
          InputStream input = null;
          try {
            int totalBytesRead = 0;
            input = new BufferedInputStream(new FileInputStream(file));
            while(totalBytesRead < result.length){
              int bytesRemaining = result.length - totalBytesRead;

              int bytesRead = input.read(result, totalBytesRead, bytesRemaining); 
              if (bytesRead > 0){
                totalBytesRead = totalBytesRead + bytesRead;
              }
            }

            System.out.println("Luetut tavut: " + totalBytesRead);
          }
          finally {
            System.out.println("Suljetaan..");
            input.close();
          }
        }
        catch (FileNotFoundException ex) {
          System.out.println("Tiedostoa ei löydy.");
        }
        catch (IOException ex) {
          System.out.println(ex);
        }
        Paketti paketti = new Paketti(divideToTree(result), divideToText(result));
        return paketti;
    }
    
    /**
     * lukee käyttäjän antaman tekstitiedoston ja paluttaa stringin
     * @return teksti
     */
    public String readInput() {
        String lause = "";
        try {
            Scanner lukija = new Scanner(System.in);
            File file = new File(lukija.nextLine());
            Scanner in = new Scanner(file);

            while(in.hasNextLine()) {
                lause += in.nextLine();
            }           
            in.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return lause;
    }
    
    /**
     * lukee käyttäjän syötettä
     * @return
     */
    public String readStuff() {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Minkä haluat purkaa?");
        return lukija.nextLine();
    }

    /**
     * jakaa sille annetun byte taulukon alkuosan puuksi
     * @param b
     * @return
     */
    public byte[] divideToTree(byte[] b) {
        byte[] tree = new byte[b[0]];
        System.arraycopy(b, 1, tree, 0, b[0]);
        return tree;
    }
    /**
     * jakaa sille annteun byte taulukon loppuosan tekstiksi
     * @param b
     * @return
     */
    public byte[] divideToText(byte[] b) {
        byte[] text = new byte[b.length - b[0] - 1];
        System.arraycopy(b, b[0]+1, text, 0, b.length - b[0] -1);
        return text;
    }
    
}
