/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

import java.awt.Color;
import java.awt.Point;
import java.util.PriorityQueue;

/**
 *
 * @author Jaakko
 * 
 * Luokka reitinhaku etsii lyhyimmän reitin verkosta lähtö ja maali pisteiden välillä.
 * 
 */

public class Reitinhaku {
  
    public Verkko verkko;
    
    public PriorityQueue<Solmu> AvoinLista;
    public Keko keko;
    
    
/**
 * Konstruktori luo verkon sekä keon jossa on solmut joihin voidaan mennä. 
 * Konstruktori lisää tähän kekoon aloituspisteen ja laittaa sen 
 * edeltäväksi solmuksi itsensä.
 * 
 * @param syöte verkko jossa reitti etsitään.
 * @param Lähtö reitin lähtöpiste.
 * @param laajuus keon maksimikoko
 * 
 */          
    
    public Reitinhaku(Verkko syöte, Point Lähtö, int laajuus){
        verkko=syöte;
        keko=new Keko(laajuus);

        
        
        Solmu lähtösolmu=verkko.taulukko[Lähtö.x][Lähtö.y];
        LuoVierusSolmut(lähtösolmu);
        
        keko.lisää(lähtösolmu);
        lähtösolmu.Edeltävä=lähtösolmu;
        
        

    }
    
/**
 * Haku metodin looppi ottaa keosta solmun jolla on pienin heurestiikka-arvo, 
 * ja antaa sen parametrinä TarkistaViereiset metodille. Looppi loppuu kun maalipiste on
 * käsittelyssä. Metodi myös maalaa kaikki käydyt pisteet.
 * 
 * @return Palauttaa true jos reitti lyötyy false jos ei
 */        
    
    public boolean Haku(){

        
        Solmu käsittelyssä=new Solmu(6, 6, 6, 6);
        
        
        while(käsittelyssä.Heurestiikaarvo!=0){
            System.out.println("wut");
            käsittelyssä=keko.poista();
            
            if(käsittelyssä==null){
                
                return false;
            }
            
            verkko.kuva.setRGB(käsittelyssä.koordinaattiX, käsittelyssä.koordinaattiY, new Color(200,200,0).getRGB());
            
            LuoVierusSolmut(käsittelyssä);
            TarkistaViereiset(käsittelyssä);
            
            
        }
        
        PiirräReitti(käsittelyssä);
        return true;
         
    }
    
/**
 * Tarkistaa jokaisen viereisen solmun sen varalta että se on seinä, jos ei ole 
 * niin kutsuu metodia Lisää sille solmulle.
 * 
 * 
 *  @param Käsittelyssä Käsittelyssä oleva solmu
 */   
    
    public void TarkistaViereiset(Solmu Käsittelyssä){
                
        
        if(verkko.taulukko[Käsittelyssä.koordinaattiX+1][Käsittelyssä.koordinaattiY].haeSeina()==false){
            Lisää(Käsittelyssä.koordinaattiX+1, Käsittelyssä.koordinaattiY, Käsittelyssä, 1);

        }
        if(verkko.taulukko[Käsittelyssä.koordinaattiX][Käsittelyssä.koordinaattiY+1].haeSeina()==false){
            Lisää(Käsittelyssä.koordinaattiX, Käsittelyssä.koordinaattiY+1, Käsittelyssä, 2);

        }
        if(verkko.taulukko[Käsittelyssä.koordinaattiX-1][Käsittelyssä.koordinaattiY].haeSeina()==false){
            Lisää(Käsittelyssä.koordinaattiX-1, Käsittelyssä.koordinaattiY, Käsittelyssä, 3);

        }
        if(verkko.taulukko[Käsittelyssä.koordinaattiX][Käsittelyssä.koordinaattiY-1].haeSeina()==false){
            Lisää(Käsittelyssä.koordinaattiX, Käsittelyssä.koordinaattiY-1, Käsittelyssä, 4);

        }
        
        
    }
    
/**
 * Jos solmu ei ole ollut keossa aikaisemmin niin metodi laittaa 
 * sen sinne ja samalla asettaa sen Edeltavä solmuksi solmun "Käsittelyssä".
 * 
 * 
 * @param x Koordinaatti x
 * @param y Koordinaatti y
 * @param Käsittelyssä Käsittelyssä oleva solmu
 */   
    public void Lisää(int x, int y, Solmu Käsittelyssä, int asd){
        
        if(verkko.taulukko[x][y].Edeltävä==null){
            verkko.taulukko[x][y].Edeltävä=Käsittelyssä;
            verkko.taulukko[x][y].Reittipituus=Käsittelyssä.Reittipituus+2;
            verkko.taulukko[x][y].suunta=asd;
            
//            if(verkko.taulukko[x][y].suunta!=Käsittelyssä.suunta){
//                verkko.taulukko[x][y].Reittipituus++;
//            }
            keko.lisää(verkko.taulukko[x][y]);
        }
        
    }
    
/**
 * Looppi maalaa aina käsittelyssä olevan solmun/pisteen ja siirtyy sitten 
 * kyseisen solmun edeltävä solmuun, täten piirtäen reitin lopusta alkuun. 
 * Lähtöpisteeseen saavuttaessa looppi loppuu.
 * 
 * 
 * @param syöte Maalipiste josta reitin piirtäminen alkaa.
 */     
    
    
    
    public void PiirräReitti(Solmu syöte){
        
        Solmu käsittelyssä=syöte;
        
        while(true){
            
            verkko.kuva.setRGB(käsittelyssä.koordinaattiX, käsittelyssä.koordinaattiY, 255);
            
            if(käsittelyssä==käsittelyssä.Edeltävä){
                break;
            }else{
                käsittelyssä=käsittelyssä.Edeltävä;
            }
            
        }
        
        
    }
    
    
    public void LuoVierusSolmut(Solmu Käsittelyssä){
        System.out.println(""+verkko.taulukko[Käsittelyssä.koordinaattiX+1][Käsittelyssä.koordinaattiY]);
        System.out.println("asdasd");
        
        if(verkko.taulukko[Käsittelyssä.koordinaattiX+1][Käsittelyssä.koordinaattiY]==null){
            verkko.LuoSolmu(Käsittelyssä.koordinaattiX+1, Käsittelyssä.koordinaattiY, Käsittelyssä, 1);
            System.out.println("asd");
        }
        if(verkko.taulukko[Käsittelyssä.koordinaattiX][Käsittelyssä.koordinaattiY+1]==null){
            verkko.LuoSolmu(Käsittelyssä.koordinaattiX, Käsittelyssä.koordinaattiY+1, Käsittelyssä, 2);

        }
        if(verkko.taulukko[Käsittelyssä.koordinaattiX-1][Käsittelyssä.koordinaattiY]==null){
            verkko.LuoSolmu(Käsittelyssä.koordinaattiX-1, Käsittelyssä.koordinaattiY, Käsittelyssä, 3);

        }
        if(verkko.taulukko[Käsittelyssä.koordinaattiX][Käsittelyssä.koordinaattiY-1]==null){
            verkko.LuoSolmu(Käsittelyssä.koordinaattiX, Käsittelyssä.koordinaattiY-1, Käsittelyssä, 4);

        }        
        
    }
    
    
}