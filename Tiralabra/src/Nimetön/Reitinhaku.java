/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nimetön;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Jaakko
 */

public class Reitinhaku {
  
    public Verkko verkko;
    
    public PriorityQueue<Solmu> AvoinLista;
    
    public Reitinhaku(){
        
        verkko=new Verkko(6, 6);

        Comparator<Solmu> comparator = new LukuVertaaja();
        AvoinLista=new PriorityQueue<Solmu>(12, comparator);
        
        
        AvoinLista.add(verkko.taulukko[1][1]);
        verkko.taulukko[0][0].Edeltävä=verkko.taulukko[0][0];
        
        Haku();
    }
    
    
    
    
    public void Haku(){
        
        tulosta(verkko.taulukko);
        
        Solmu käsittelyssä=AvoinLista.poll();
        
        while(käsittelyssä.Heurestiikaarvo!=0){
            
            TarkistaViereiset(käsittelyssä);
            
            käsittelyssä=AvoinLista.poll();

        }
        
        
        
    }
    
    
    public void TarkistaViereiset(Solmu Käsittelyssä){
        
        System.out.println("muumimaailma  "+Käsittelyssä.koordinaattiX+"  "+Käsittelyssä.koordinaattiY);              
        
        if(verkko.taulukko[Käsittelyssä.koordinaattiY][Käsittelyssä.koordinaattiX+1].seinä==false){
            Lisää(Käsittelyssä.koordinaattiX+1, Käsittelyssä.koordinaattiY, Käsittelyssä);
        }
        if(verkko.taulukko[Käsittelyssä.koordinaattiY+1][Käsittelyssä.koordinaattiX].seinä==false){
            Lisää(Käsittelyssä.koordinaattiX, Käsittelyssä.koordinaattiY+1, Käsittelyssä);
        }
        if(verkko.taulukko[Käsittelyssä.koordinaattiY][Käsittelyssä.koordinaattiX-1].seinä==false){
            Lisää(Käsittelyssä.koordinaattiX-1, Käsittelyssä.koordinaattiY, Käsittelyssä);
        }
        if(verkko.taulukko[Käsittelyssä.koordinaattiY-1][Käsittelyssä.koordinaattiX].seinä==false){
            Lisää(Käsittelyssä.koordinaattiX, Käsittelyssä.koordinaattiY-1, Käsittelyssä);
        }
        

        
    }
    
   
    public void Lisää(int x, int y, Solmu Käsittelyssä){
        
        if(verkko.taulukko[x][y].Edeltävä==null){
            verkko.taulukko[x][y].Edeltävä=Käsittelyssä;
            AvoinLista.add(verkko.taulukko[x][y]);
        }
        
    }
    
    
    public void tulosta(Solmu[][] asd){
        
        for (int i = 0; i < asd.length; i++) {
            for (int j = 0; j < asd[0].length; j++) {
                System.out.print(" "+asd[i][j].Heurestiikaarvo);
            }
            System.out.println("");
        }
        
    }
    
}
