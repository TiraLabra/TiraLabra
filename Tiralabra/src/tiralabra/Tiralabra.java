/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.util.PriorityQueue;

/**
 * 
 * @author joonaslongi
 */
public class Tiralabra {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PriorityQueue<Node> que = new PriorityQueue<Node>();
        
        Laskija laskija = new Laskija();
        laskija.laske("src/Tiralabra/testitiedosto2.txt");
        
        int toistot[] = laskija.getToistot();
        
        for (int i = 0; i < 256; i ++){
            if(toistot[i] > 0){
                Node node = new Node(i,toistot[i]);
                que.add(node);
            }
        }
        
        Puu puu = new Puu(que);
        puu.kokoa();
        Node root = puu.getRoot(); 

        puu.muodostaReitit(root, "");
        String[] reitit = puu.getReitit();
      
        Lukija lukija = new Lukija ("src/Tiralabra/testitiedosto2.txt");
        
        Kirjoittaja kirjoittaja = new Kirjoittaja("src/Tiralabra/uusi");
        while (lukija.vapaana() > 0){
            String kirjoitettava = reitit[lukija.lue()];
            int kirjoita = Integer.parseInt(kirjoitettava);
            kirjoittaja.kirjoita(kirjoita);
        }
        lukija.sulje();
        kirjoittaja.sulje();
    }
    
    
}
