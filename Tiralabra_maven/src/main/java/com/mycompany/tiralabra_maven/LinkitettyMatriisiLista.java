

package com.mycompany.tiralabra_maven;
import jama.*;


public class LinkitettyMatriisiLista {
    
    private Solmu ekaSolmu;
    
    public LinkitettyMatriisiLista() {
        ekaSolmu = null;
    }
    
    public void lisaa(String nimi, Matrix matriisi) {
        
        Solmu lisattava = new Solmu();
        lisattava.nimi = nimi;
        lisattava.matriisi = matriisi;
        Solmu tutkittava = new Solmu();
        tutkittava = ekaSolmu;
        
        if (tutkittava == null) {
            ekaSolmu = lisattava;
            return;
        }
        
        while (tutkittava.next != null) {
            tutkittava = tutkittava.next;
        }
        
        tutkittava.next = lisattava;
        
    }
    
    public Matrix hae(String nimi) throws Exception {
        
        Solmu tutkittava = new Solmu();
        tutkittava = ekaSolmu;
        
        if (tutkittava == null) {
            throw new Exception();
        }
        
        if (tutkittava.nimi.equals(nimi)) {
            return tutkittava.matriisi;
        }
        
        while(tutkittava.next != null) {
            tutkittava = tutkittava.next;
            if (tutkittava.nimi.equals(nimi)) {
                return tutkittava.matriisi;
            }            
        }
        
        throw new Exception();
        
        
    }
    
    public void tulostaNimet() {
        
        Solmu kasiteltava = new Solmu();
        kasiteltava = ekaSolmu;
        
        if (ekaSolmu == null) {
            return;
        }
        
        System.out.print(ekaSolmu.nimi);
        
        while (kasiteltava.next != null) {
            kasiteltava = kasiteltava.next;
            System.out.print(", " + kasiteltava.nimi);
            System.out.print("\n");
        }
    }
}
