/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tietorakenteet;

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
     * Luo puun reiteistä lukiessa
     * @param reitit 
     */
    
    public Puu(String[] reitit){
        this.root = new Node(-1,-1);
        this.reitit = reitit;
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
    
    /**
     * Kokoaa Huffman puun reitiestä, jotka luetaan purettavan tiedoston alusta
     */
    
    public void kokoaReiteista(){
        for(int i = 0; i < reitit.length; i ++){
            if (reitit[i] != null){
                lisaaNode(i, reitit[i], this.root);
            }
        }
    }
    
    /**
     *  Lisää noden oikealle paikalle puuhun juuresta lähtien. Mikäli
     *  oikeaa / vasenta lasta ei ole, se luodaan kunnes päästään kyseisen
     *  merkin reitti loppuun.
     * @param merkki itse merkki ascii muodossa
     * @param reitti merkin reitti puussa
     * @param node puun juuri
     */
    
    public void lisaaNode(int merkki, String reitti, Node node){
        for(int i = 0; i < reitti.length(); i ++){
            if(reitti.charAt(i) == '0'){
                if(node.getVasen() == null){
                    node.setVasen(new Node(-1, -1));
                }
                node = node.getVasen();
            } else {
                if(node.getOikea() == null){
                    node.setOikea(new Node(-1,-1));
                }
                node = node.getOikea();
            }
        }
        node.setMerkki(merkki);
    }
    
}
