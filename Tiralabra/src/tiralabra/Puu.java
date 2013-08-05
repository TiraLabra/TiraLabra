/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.util.PriorityQueue;

/**
 *
 * @author Joonas
 */
public class Puu {
    
    private PriorityQueue<Node> que;
    private Node root;
    private String reitit[];
    
    public Puu(PriorityQueue que){
         this.que = que;
         reitit = new String[256];
    }
    
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
    
    public Node getRoot(){
        return this.root;
    }
    
    public void muodostaReitit(Node node, String code){
        if (node != null) {
            if (node.getVasen() == null && node.getOikea() == null) {
                reitit[node.getMerkki()] = code;

            }
            muodostaReitit(node.getVasen(), code + "0");
            muodostaReitit(node.getOikea(), code + "1");
        }
    }
    
    public String[] getReitit(){
        return this.reitit;
    }
    
}
