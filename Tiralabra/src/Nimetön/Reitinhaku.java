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
        AvoinLista=new PriorityQueue<Solmu>((PriorityQueue<? extends Solmu>) comparator);
        
        TarkistaViereiset(verkko.taulukko[0][0]);
        AvoinLista.add(verkko.taulukko[0][0]);
    }
    
    public void Haku(){
        
        Solmu käsittelyssä=AvoinLista.poll();
        
        while(käsittelyssä.Heurestiikaarvo!=0){
            
            TarkistaViereiset(käsittelyssä);
            
            käsittelyssä=AvoinLista.poll();

        }
        
    }
    
    public void Vertaile(Solmu Käsittelyssä){
        
        if(Käsittelyssä.pääsemisarvo>(Käsittelyssä.Edeltävä.pääsemisarvo+Käsittelyssä.Liikkumisarvo)){
            
        }
        
    }
    
    public void TarkistaViereiset(Solmu Käsittelyssä){
        
        if(Käsittelyssä.koordinaattiX>=0){
            Lisää(Käsittelyssä.koordinaattiX+1, Käsittelyssä.koordinaattiY, Käsittelyssä);
        }
        if(Käsittelyssä.koordinaattiY>=0){
            Lisää(Käsittelyssä.koordinaattiX, Käsittelyssä.koordinaattiY+1, Käsittelyssä);
        }
        if(Käsittelyssä.koordinaattiX<=verkko.taulukko.length){
            Lisää(Käsittelyssä.koordinaattiX-1, Käsittelyssä.koordinaattiY, Käsittelyssä);
        }
        if(Käsittelyssä.koordinaattiY<=verkko.taulukko[0].length){
            Lisää(Käsittelyssä.koordinaattiX, Käsittelyssä.koordinaattiY-1, Käsittelyssä);
        }
        
    }
    
   
    public void Lisää(int x, int y, Solmu Käsittelyssä){
        
        if(verkko.taulukko[x][y].Edeltävä==null){
            verkko.taulukko[x][y].Edeltävä=Käsittelyssä;
            AvoinLista.add(verkko.taulukko[x][y]);
        }
        
    }
    
}
