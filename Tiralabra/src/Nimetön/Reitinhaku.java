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
    
    public ArrayList<Solmu> KäytyLista;
    public PriorityQueue<Solmu> AvoinLista;
    
    public Reitinhaku(){
        
        verkko=new Verkko(6, 6);
        
        KäytyLista=new ArrayList<Solmu>();
        
        Comparator<Solmu> comparator = new LukuVertaaja();
        AvoinLista=new PriorityQueue<Solmu>((PriorityQueue<? extends Solmu>) comparator);
        
        TarkistaViereiset(verkko.taulukko[0][0]);
        AvoinLista.add(verkko.taulukko[0][0]);
    }
    
    public void Haku(){
        
        while(true){
            Solmu käsittelyssä=AvoinLista.poll();
            
        }
        
    }
    
    public void TarkistaViereiset(Solmu Rusetti){
        
        if(Rusetti.koordinaattiX>=0){
            Lisää(Rusetti.koordinaattiX+1, Rusetti.koordinaattiY, Rusetti);
        }
        if(Rusetti.koordinaattiY>=0){
            Lisää(Rusetti.koordinaattiX, Rusetti.koordinaattiY+1, Rusetti);
        }
        if(Rusetti.koordinaattiX<=verkko.taulukko.length){
            Lisää(Rusetti.koordinaattiX-1, Rusetti.koordinaattiY, Rusetti);
        }
        if(Rusetti.koordinaattiY<=verkko.taulukko[0].length){
            Lisää(Rusetti.koordinaattiX, Rusetti.koordinaattiY-1, Rusetti);
        }
        
        
    }
   
    public void Lisää(int x, int y, Solmu Rusetti){
        
        if(verkko.taulukko[x][y].Edeltävä==null){
            verkko.taulukko[x][y].Edeltävä=Rusetti;
            AvoinLista.add(verkko.taulukko[x][y]);
        }
        
    }
    
}
