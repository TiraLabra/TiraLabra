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

        maaliX=maali.y;
        maaliY=maali.x;

        Täytätaulukko();

    }
    
/**
 * Käy läpi kuvan ja muuttaa sen Solmuista koostuvaksi taulukoksi.
 * 
 */      
    private void Täytätaulukko(){
        
        for (int i = 0; i < kuva.getHeight(); i++) {
            for (int j = 0; j < kuva.getWidth(); j++) {
                Color c = new Color(kuva.getRGB(i, j));
                
                taulukko[i][j]=new Solmu(i, j, HeuristiikkaArvo(j, i), c.getBlue());
                
            }
        }
        
    }
    
/**
 * Laskee solmun etäisyyden maalista.
 * 
 * 
 * @param x kyseisen solmun x koordinaatti.
 * @param y kyseisen solmun y koordinaatti.
 */       
    
    int HeuristiikkaArvo(int x, int y){

        return (Math.abs(y-maaliY)+Math.abs(x-maaliX))*2;
    }
    
    

}
