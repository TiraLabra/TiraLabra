package com.mycompany.tiralabra_maven;

/**
 * 
 * @author Pia Kumpulainen 
 * Lyhyin reitti
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author piakumpu
 */
public class App 
{
    /**
     *
     * @param args
     */
    private static LinkedList<String> kulkuneuvo;
    /**
     *
     */
    public static Scanner lukija = new Scanner(System.in);
    
    /**
     *
     * @param args
     */
    public static void main( String[] args )
    {
        String lahto, paatto;
        System.out.println("Anna aloitus pysäkki:");
        lahto = lukija.nextLine();
        System.out.println("Anna päätös pysäkki:");
        paatto = lukija.nextLine();
        
        System.out.println(matka(kulkuneuvo(), lahto,paatto));
    }
    
    /**
     *
     * @param kulkuneuvo
     * @param asema
     * @return
     */
    static public boolean onkoAsema(LinkedList kulkuneuvo, String asema) {
        
        if (kulkuneuvo.contains(asema)) {
            return true;
        }
        
        return false;
    }
    
    /**
     *
     * @param kulkuneuvo
     * @param lahto
     * @param paatto
     * @return
     */
    static public List matka(LinkedList kulkuneuvo, String lahto, String paatto) {
        int maara = 0;
        int alku = 0;
        int loppu =0;
        
        List<String> asema= new ArrayList<String>();
        asema.add("");
        
        
        
        if (onkoAsema(kulkuneuvo, lahto) == true) {
            alku = kulkuneuvo.indexOf(lahto)+1;
            if (onkoAsema(kulkuneuvo, paatto) == true) {
                loppu = kulkuneuvo.indexOf(paatto)+1;
                if (alku < loppu) {
                    asema = kulkuneuvo.subList(alku, loppu);
                }
                else {
                    asema = kulkuneuvo.subList(loppu, alku);
                }
            }
            else {
                System.out.println("Päättöasemaa ei löydy");
            }
        }
        else {
            System.out.println("Lähtöasemaa ei löydy");
        }
        
        return asema;
    }
    
    /**
     *Toistaiseksi asemat lisätään yksitellen, tähän koitan keksiä
     * paremman ratkaisun
     * @return 
     */
    static public LinkedList kulkuneuvo() {
        LinkedList metro = new LinkedList();
        pysakki(metro, "Ruoholahti");
        pysakki(metro, "Kamppi");
        pysakki(metro, "Rautatieasema");   
        pysakki(metro, "Kaisaniemi");
        pysakki(metro, "Hakaniemi");
        pysakki(metro, "Sörnäinen");
        pysakki(metro, "Kalasatama");
        pysakki(metro, "Siilitie");

       /* 
        * Testataan asemien tulostus
        * int index= 0;
        while(index < metro.size()) {
            System.out.println(metro.get(index));
            index++;
        }*/
        
        return metro;
        
    }
    
    /**
     *
     * @param kulkuneuvo
     * @param asema
     * 
     */
    static public void pysakki(LinkedList kulkuneuvo, String asema) {
        kulkuneuvo.add(asema);
    }
}

