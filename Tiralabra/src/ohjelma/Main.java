package ohjelma;

import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kkivikat
 */
public class Main {
//

    public static void main(String[] args) {
        
        int x = 5;
        int y = 6;
        int v = 7;
        Linkitettylista lista = new Linkitettylista();
        System.out.println("Pushataan 5");
        lista.push(x);
        System.out.println("Pushataan 6");
        lista.push(y);
        System.out.println("Pushataan 7");
        lista.push(v);
        
        System.out.println("Popataan 7");
        lista.pop();
        System.out.println("Popataan 6");
        lista.pop();
        System.out.println("Popataan 5");
        lista.pop();
        
//        Scanner lukija = new Scanner(System.in);
//        System.out.println("Tee ensin verkko: ");
//        Verkko verkko[] = new Verkko[10];
//        for (int i = 0; i < 10; i++) {
//            verkko[i]. = new Verkko();
//            System.out.println("["+i+"] : Anna lähtöpaikka");
//            verkko[i].lahde = lukija.nextInt();
//            
//            System.out.println("["+i+"] : Anna kohdepaikka");
//            verkko[i].kohde = lukija.nextInt();
//            
//            System.out.println("["+i+"] : Anna paino");
//            verkko[i].paino = lukija.nextInt();
//        }
//        
//        BellmanFord(verkko, 10, 5, 4);
//        System.out.println("Millä algoritmilla haluat suorittaa haun?" + "\n"
//                + "1. Bellman-Ford" + "\n"
//                + "2. Dijkstra");
//        String vastaus = reader.nextLine();
//        Suoritin.suorita(vastaus);
    }
}