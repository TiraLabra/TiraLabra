package com.mycompany.tiralabra_maven;

/**
 * 
 * @author Pia Kumpulainen 
 * Lyhyin reitti
 */

import java.util.LinkedList;
import java.util.Scanner;

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
    private LinkedList<String> kulkuneuvo;
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
        
        kulkuneuvo();
    }
    
    static public int matka(LinkedList kulkuneuvo, String lahto, String paatto) {
        int maara = 0;
        
    
        
        return maara;
    }
    
    /**
     *Toistaiseksi asemat lisätään yksitellen, tähän koitan keksiä
     * paremman ratkaisun
     */
    static public void kulkuneuvo() {
        LinkedList metro = new LinkedList();
        pysakki(metro, "Ruoholahti");
        pysakki(metro, "Kamppi");
        pysakki(metro, "Rautatieasema");   
        pysakki(metro, "Kaisaniemi");
        pysakki(metro, "Hakaniemi");
        pysakki(metro, "Sörnäinen");
        pysakki(metro, "Kalasatama");
        pysakki(metro, "Siilitie");

        int index= 0;
        while(index < metro.size()) {
            System.out.println(metro.get(index));
            index++;
        }
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

