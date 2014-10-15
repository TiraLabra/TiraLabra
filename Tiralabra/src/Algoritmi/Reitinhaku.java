/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Jaakko
 * 
 * Luokka reitinhaku etsii lyhyimmän reitin verkosta lähtö ja maali pisteiden välillä.
 * 
 */

public class Reitinhaku {
  
    public Verkko verkko;
    
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

        
        Solmu ÄläVälitäTästäSolmusta=new Solmu(6, 6, 6, 6);
        verkko.LuoSolmu(Lähtö.x, Lähtö.y, ÄläVälitäTästäSolmusta);
        Solmu lähtösolmu=verkko.taulukko[Lähtö.x][Lähtö.y];


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
        
        
        while(käsittelyssä.heurestiikaArvo!=0){

            käsittelyssä=keko.poista();
            
            if(käsittelyssä==null){
                
                return false;
            }
            
            verkko.kuva.setRGB(käsittelyssä.koordinaattiX, käsittelyssä.koordinaattiY, new Color(200,0,200).getRGB());
            
            VierusSolmut(käsittelyssä);
            
            
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
    
    public void VierusSolmut(Solmu Käsittelyssä){
        
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {

                if(verkko.taulukko[Käsittelyssä.koordinaattiX+x][Käsittelyssä.koordinaattiY+y]==null){
                    verkko.LuoSolmu(Käsittelyssä.koordinaattiX+x, Käsittelyssä.koordinaattiY+y, Käsittelyssä);
                }                
                if(verkko.taulukko[Käsittelyssä.koordinaattiX+x][Käsittelyssä.koordinaattiY+y].haeSeina()==false){
                    Lisää(Käsittelyssä.koordinaattiX+x, Käsittelyssä.koordinaattiY+y, Käsittelyssä);
                    
                }                   
            }
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
    public void Lisää(int x, int y, Solmu Käsittelyssä){
        
        if(verkko.taulukko[x][y].Edeltävä==null){
            verkko.taulukko[x][y].Edeltävä=Käsittelyssä;
            verkko.taulukko[x][y].Reittipituus=Käsittelyssä.Reittipituus+1;
            

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
    

}