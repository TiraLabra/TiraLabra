/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Jaakko
 * 
 * Luokka verkko on väliaikainen ja sen voisi melkeimpä poistaa.
 */
public class Verkko {
    
    public Solmu [][] taulukko;
    public BufferedImage kuva;
    
    public int maaliX = 500;
    public int maaliY = 500;

    
    public Verkko(BufferedImage kuva){
        taulukko=new Solmu [kuva.getHeight()][kuva.getWidth()];
        this.kuva=kuva;
        
        Täytätaulukko();

        
    }
    
    
    public void Täytätaulukko(){
        
        for (int i = 0; i < kuva.getHeight(); i++) {
            for (int j = 0; j < kuva.getWidth(); j++) {
                Color c = new Color(kuva.getRGB(j, i));
                
                taulukko[i][j]=new Solmu(i, j, HeuristiikkaArvo(i, j), c.getBlue());
                
            }
        }
        
    }
    
    
    
    public int HeuristiikkaArvo(int y, int x){

        return Math.abs(x-maaliX)+Math.abs(y-maaliY);
    }
    
}
