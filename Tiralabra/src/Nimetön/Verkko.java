/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nimetön;

import java.util.Random;

/**
 *
 * @author Jaakko
 */
public class Verkko {
    
    public Solmu [][] taulukko;
    
    public int lähtöX;
    public int lähtöY;
    public int maaliX;
    public int maaliY;
    
    public Verkko(int x, int y){
        
        taulukko=new Solmu[y][x];
        
        lähtöX=1;
        lähtöY=1;
        maaliX=5;
        maaliY=5;
        
        Täytätaulukko();
        
    }
    
    
    public void Täytätaulukko(){
        
        for (int y = 0; y < taulukko.length; y++) {
            for (int x = 0; x < taulukko[0].length; x++) {
                taulukko[y][x]=new Solmu(x, y, HeuristiikkaArvo(y, x));
                if(y==0 || x==0 || y==taulukko.length-1 || x==taulukko[0].length-1){
                    taulukko[y][x].seinä=true;
                }
            }
            
        }
        
    }
    
    
//    public int ArvoLuku(){
//        
//        Random arvo = new Random();
//
//        int palautus = arvo.nextInt(10) + 0;
//        
//        return palautus;
//    }
    

    
    public int HeuristiikkaArvo(int y, int x){

        return Math.abs(x-maaliX)+Math.abs(y-maaliY);
    }
    
}
