/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.verkko;

/**
 *Kuvaavat nimet ja kustannukset ruudulla liikkumiselle
 * @author sasumaki
 */
public enum Ruutu {

    LATTIA, VESI, METSÄ, SEINÄ;
    
    public int liikeHinta(){
        
        switch(this) {
            case LATTIA:
                return 1;
            case METSÄ:
                return 3;
            case VESI:
                return 10;
            case SEINÄ:
                return 10000;
            default:
                return 1;
        }
        
    }

}

