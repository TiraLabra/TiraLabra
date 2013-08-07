/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.util.PriorityQueue;

/**
 * Luo Huffmanin puun
 * @author Joonas
 */
public class Puu {
    
    private PriorityQueue<Node> que;
    private Node root;
    private String reitit[];
    
    /**
     * Luo uuden puu luokan parametrina prioriteettijono.
     * @param que 
     */
    public Puu(PriorityQueue que){
         this.que = que;
         reitit = new String[256];
    }
    
    /**
     * Kokoaa jonosta puun. Aloitetaan kahdeesta pienimmästä ja muodostetaan niille
     * vanhempi jonka toistot on lapsien toistojen summa.
     */
    
    public void kokoa(){
        while(que.size() > 1) {
            Node pienin = que.poll();
            Node pienin2 = que.poll();
            Node newNode = new Node(-1, pienin.getToistot() + pienin2.getToistot());
            newNode.setVasen(pienin);
            newNode.setOikea(pienin2);
            que.add(newNode);
        }
        this.root = que.poll();
    }
    
    /**
     * Getteri root nodelle
     * @return 
     */
    
    public Node getRoot(){
        return this.root;
    }
    
    /**
     * Tallettaa taulukkoon jokaisen merkin "koodin" / sijainnin puussa
     * Node on puun root Node ja code on aluksi vain tyhä "" String, johon 
     * lisätään 0 tai 1 reitistä riippuen.
     * @param node
     * @param code 
     */
    public void muodostaReitit(Node node, String code){
        if (node != null) {
            if (node.getVasen() == null && node.getOikea() == null) {
                reitit[node.getMerkki()] = code;

            }
            muodostaReitit(node.getVasen(), code + "0");
            muodostaReitit(node.getOikea(), code + "1");
        }
    }
    
    /**
     * Palauttaa luodun reittitaulukon.
     * @return 
     */
    public String[] getReitit(){
        return this.reitit;
    }
    
}
