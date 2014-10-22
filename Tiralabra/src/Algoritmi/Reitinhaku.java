/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algoritmi;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jaakko
 * 
 * Luokka reitinhaku etsii lyhyimmän reitin verkosta lähtö ja maali pisteiden välillä.
 * 
 */

public class Reitinhaku {
  
    public Verkko verkko;
    
    public Keko avoinlista;
    
    
/**
 * Konstruktori keon jossa on solmut joihin voidaan mennä (open set). 
 * Konstruktori lisää tähän kekoon aloituspisteen ja laittaa sen 
 * edeltäväksi solmuksi itsensä.
 * 
 * @param syöte verkko jossa reitti etsitään.
 * @param lähtö reitin lähtöpiste.
 * @param laajuus keon maksimikoko
 * 
 */          
    
    public Reitinhaku(Verkko syöte, Point lähtö, int laajuus){
        
        if(lähtö==null){
            JOptionPane.showMessageDialog(new JFrame(), "Anna alkupiste");
        }

        verkko=syöte;

        avoinlista=new Keko(laajuus);
        
        verkko.LuoSolmu(lähtö.x, lähtö.y);
        
        Solmu lähtösolmu=verkko.taulukko[lähtö.x][lähtö.y];

        avoinlista.lisää(lähtösolmu);

        lähtösolmu.edeltävä=lähtösolmu;
        
    }
    
/**
 * Haku metodin looppi ottaa keosta solmun jolla on pienin heurestiikan ja reittipituuden yhteisarvo, 
 * ja antaa sen parametrinä VierusSolmut metodille. Looppi loppuu kun maalipiste on
 * käsittelyssä. Metodi myös maalaa kaikki käydyt pisteet.
 * 
 * @return Palauttaa true jos reitti lyötyy false jos ei
 */        
    
    public boolean Haku(){
        
        Solmu käsittelyssä=new Solmu(6, 6, 6, 6);
        
        
        while(käsittelyssä.heurestiikaArvo!=0){
            käsittelyssä=avoinlista.Poista();
            
            if(käsittelyssä==null){
                
                return false;
            }
            
            verkko.kuva.setRGB(käsittelyssä.koordinaattiX, käsittelyssä.koordinaattiY, new Color(230,0,230).getRGB());
            
            VierusSolmut(käsittelyssä);
            käsittelyssä.käyty=true;
            
        }
        
        PiirräReitti(käsittelyssä);
        return true;
         
    }
    
    
    
    

/**
 * Tarkistaa jokaisen viereisen solmun. Jos Vierus solmua ei vielä ole luotu niin se luodaan.
 * Jokaisen vierussolmun reittipituutta verrataan käsittelyssä solmun kautta saatavaan reittipituuteen ja jos sen lyhyempi
 * niin vierussolmulle annetaan edeltävä arvoksi käsittelyssä oleva solmu ja reittiarvoksi kokeiluReittipituus.
 * Lisäksi naapuri solmut lisätää kekoon.
 * 
 * 
 *  @param Käsittelyssä Käsittelyssä oleva solmu
 */       
    
    public void VierusSolmut(Solmu Käsittelyssä){
        
        Solmu naapuri;
        
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                
                if(verkko.taulukko[Käsittelyssä.koordinaattiX+x][Käsittelyssä.koordinaattiY+y]==null){
                    verkko.LuoSolmu(Käsittelyssä.koordinaattiX+x, Käsittelyssä.koordinaattiY+y);
                }
                
                naapuri=verkko.taulukko[Käsittelyssä.koordinaattiX+x][Käsittelyssä.koordinaattiY+y];
                
                if(naapuri.haeSeina()){
                    continue;
                }
                
                int kokeiluReittipituus = Käsittelyssä.reittipituus + Kumpi(Käsittelyssä, naapuri);
                
                if(naapuri.käyty && kokeiluReittipituus>=naapuri.reittipituus){
                    continue;
                }
                
                if(naapuri.edeltävä==null || kokeiluReittipituus<naapuri.reittipituus){
                    naapuri.edeltävä=Käsittelyssä;
                    naapuri.reittipituus=kokeiluReittipituus;
                     
                    if(kokeiluReittipituus>=naapuri.reittipituus){
                        avoinlista.lisää(naapuri);
                    }
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
//    public void Lisää(Solmu naapuri, Solmu Käsittelyssä){
//        
//        if(naapuri.haeSeina()==false){
//            naapuri.edeltävä=Käsittelyssä;
//            
//            
//            naapuri.reittipituus=Käsittelyssä.reittipituus+Kumpi(Käsittelyssä, naapuri);
//            
//
//            avoinlista.lisää(naapuri);
//        }
//        
//    }
    
    /**
     * Selvittää ovatko kaksi solmua vierekkäin näin *X vai näin XX
     *                                               X*          **  
     * 
     * @param edeltävä verrattava solmu
     * @param nykyinen verrattava solmu
     * @return palauttaa 14 jos vinottain 10 jos suoraan
    */         
    
    public int Kumpi(Solmu edeltävä, Solmu nykyinen){
        
        if(edeltävä.koordinaattiX!=nykyinen.koordinaattiX && edeltävä.koordinaattiY!=nykyinen.koordinaattiY){
            return 14;
        }else{
            return 10;
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
            
            if(käsittelyssä==käsittelyssä.edeltävä){
                break;
            }else{
                käsittelyssä=käsittelyssä.edeltävä;
            }
            
        }
        
        
    }
    

}