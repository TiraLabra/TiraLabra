

package com.mycompany.tiralabra_maven;
import java.util.Scanner;
import jama.*;
import java.util.ArrayList;

public class KomennonKasittelija {
    
    private Komento komento;
    private String[] kaskyjono;
    private Scanner scan;
    private Kayttoliittyma kali;
    private LinkitettyMatriisiLista matriisilista = new LinkitettyMatriisiLista();
    
    public KomennonKasittelija(Scanner scan, Kayttoliittyma kali) {
        this.scan = scan;
        this.kali = kali;
    }
    
    public void suoritaKomento(String kasky) throws Exception {
        
        kaskyjono = kasky.split(" ");
        
        
        try {
            matriisilista.hae(kaskyjono[0]).print(1, 2);
            return;
        }
        
        catch (Exception e) {
            
        }
        
        try {
            komento = maaritaKomento(kaskyjono[0]);          
        }
        
        catch (Exception e) {
            throw new Exception();
        }
       
        
        if (komento == Komento.KERRO) {
            suoritaKertolasku();
        }
        
        if (komento == Komento.DETERMINANTTI) {
            suoritaDeterminantti();
        }
        
        if (komento == Komento.MATRIISI) {
            suoritaMatriisi();           
        }
        
        if (komento == Komento.QUIT) {
            kali.quit();
        }
        
        if (komento == Komento.KOMENNOT) {
            kali.tulostaKomennot();
        }
        
        if (komento == Komento.KAANNA) {
            suoritaKaanto();
        }
        
        if (komento == Komento.TULOSTALISTA) {
            matriisilista.tulostaNimet();
        }
    }

    
    public void suoritaMatriisi() throws Exception {
        int m;
           int n;
           String[] ulottuvuudet;
           String matriisinNimi;
           String[] arvot;
           String syote;
           double[][] valimatriisi;
           
           try {
               ulottuvuudet = kaskyjono[1].split("x");
               m = Integer.parseInt(ulottuvuudet[0]);
               n = Integer.parseInt(ulottuvuudet[1]);
               matriisinNimi = kaskyjono[2];
               valimatriisi = new double[m][n];
           }
           
           catch (Exception e) {
               throw new Exception();
           }
           
           for (int i = 0; i < n; i++) {
               
               while (true) {
                   try {
                        System.out.println("Syötä sarakkeen " + i + " alkiot välimerkillä eroteltuna");
                        syote = scan.nextLine();
                        if (syote.equals("quit")) {
                            kali.quit();
                            return;
                        }
                        arvot = syote.split(" ");
               
               
                        for (int k = 0; k < m; k++) {
                            valimatriisi[k][i] = Double.parseDouble(arvot[k]);
                        }
                        break;
                    }
                   
                   catch (Exception e) {
                       System.out.println("Antamasi syöte ei ollut kelvollinen");
                   }
               }        
               

            }    
           Matrix matriisi = new Matrix(valimatriisi);
           matriisilista.lisaa(matriisinNimi, matriisi);
        }
    
    public void suoritaDeterminantti() {
        String matriisinnimi = kaskyjono[1];
        Matrix laskettava;
        double determinantti;
        
        try { 
            laskettava = matriisilista.hae(matriisinnimi);
        }
        
        catch (Exception e) {
            System.out.println("Virheellinen matriisin nimi");
            return;
        }
        
        determinantti = laskettava.det();
        System.out.println("Determinantti on " + determinantti);
        
        
    }
    
    public void suoritaKertolasku() {
        String matriisin1nimi = kaskyjono[1];
        String matriisin2nimi = kaskyjono[2];
        String tulosmatriisinnimi = kaskyjono[3];
        Matrix tulos;
        
        try {
         
            Matrix matrix1 = matriisilista.hae(matriisin1nimi);
            Matrix matrix2 = matriisilista.hae(matriisin2nimi);   
            tulos = matrix1.times(matrix2);
        }
        
        catch (Exception e) {
            System.out.println("Matriiseja näillä nimillä ei löydetty");
            return;
        }
        
        matriisilista.lisaa(tulosmatriisinnimi, tulos);
        
    }
    
    public void suoritaKaanto() {
        Matrix tulosmatriisi;
        String matriisinnimi = kaskyjono[1];
        String tulosmatriisinnimi = kaskyjono[2];
        try {
            Matrix kaannettava = matriisilista.hae(matriisinnimi);
            tulosmatriisi = kaannettava.inverse();
            matriisilista.lisaa(tulosmatriisinnimi, tulosmatriisi);
        }
        catch (Exception e) {
            System.out.println("Ei löytynyt sellaisia matriiseja");
        }
    }
  
    
    public Komento maaritaKomento(String kasky) throws Exception {
        if (kasky.equals("matriisi")) {
            return Komento.MATRIISI;
        }
        
        if (kasky.equals("quit")) {
            return Komento.QUIT;
        }
        
        if (kasky.equals("determinantti")) {
            return Komento.DETERMINANTTI;
        }
        
        if (kasky.equals("kerro")) {
            return Komento.KERRO;
        }
        
        if (kasky.equals("kaanna")) {
            return Komento.KAANNA;
        }
        
        if (kasky.equals("komennot")) {
            return Komento.KOMENNOT;
        }
        
        if (kasky.equals("lista")) {
            return Komento.TULOSTALISTA;
        }
        
        else throw new Exception();
    }
}
