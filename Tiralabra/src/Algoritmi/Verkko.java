/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;


/**

 * 
 * Luokka verkko sisältää solmuista koostuvan taulukon joka toimii verkkona.
 */
public class Verkko {
    
    public Solmu [][] taulukko;
    public BufferedImage kuva;
    
    public int maaliX;
    public int maaliY;

    /**
     * Konstruktori konstruktoroi ja kutsuu metodia Täytätaulukko.
     * 
     * @param kuva BufferedImage jonka pohjalta taulukko täytetään.
     * @param maali haettavan reitin maalipiste
    */ 
    
    public Verkko(BufferedImage kuva, Point maali){
        taulukko=new Solmu [kuva.getWidth()][kuva.getHeight()];
        this.kuva=kuva;

        maaliX=maali.x;
        maaliY=maali.y;




    }
    

    
    
    public void LuoSolmu(int x, int y){
        Color c = new Color(kuva.getRGB(x, y));
        
        taulukko[x][y]=new Solmu(x, y, HeuristiikkaArvo(x, y), c.getBlue());
        
    }
    
    
    
/**
 * Laskee solmun etäisyyden maalista.
 * 
 * 
 * @param x kyseisen solmun x koordinaatti.
 * @param y kyseisen solmun y koordinaatti.
 */       
    
    int HeuristiikkaArvo(int x, int y){
        

        int xPituus=Math.abs(x-maaliX);
        int yPituus=Math.abs(y-maaliY);
        
        if(xPituus > yPituus){
            return (xPituus-yPituus)*10+(yPituus*14);
        }else{
            return (yPituus-xPituus)*10+(xPituus*14);
        }

        
    }
    
    

}
