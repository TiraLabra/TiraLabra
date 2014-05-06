/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

/**
 * Node luokka. Nodeja käytetään apuna hiiren sijainnin muistamisessa ja 
 * Astar-algoritmin toteutuksessa.
 * 
 */
public class Node {
 
    
    int value, kor, lev;
    //astar muuttujat.
    private int h, matka;
    private Node prevNode;
    private Node node;
    private Wall tiili = new Wall(); 
    private int[][] map = Wall.getMap();

    /**
     * Luo uuden Noden antaen sille leveyden ja korkeuden. 
     * @param lev
     * @param kor
     */
    public Node(int lev, int kor){
        this.lev = lev;
        this.kor = kor;
        
    }
    
    /**
     *
     * @param lev
     * @param kor
     * @param h
     */
//    public Node(int lev, int kor, int h){
//        this.lev = lev;
//        this.kor = kor;
//        this.h = h;
//    }
 
 
    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Palauttaa noden heuristisen arvon.
     * @return
     */
    public int getH() {
        return h;
    }
 
    /**
     * Asettaa Nodelle heuristiikan.
     * @param h 
     */
    public void setH(int h) {
        this.h = h;
    }
    
    /**
     * Tarkistaa onko Node seinä, jolloin siitä ei voi mennä läpi.
     * @param node
     * @return
     */
    public boolean onkoSeina(Node node){
        this.node = node;
        if(map[node.lev][node.kor] == 1){
            return true;
        }else return false;
    }
 
    /**
     * Asettaa Nodelle edellisen Noden.
     * @param prevNode
     */
    public void setPrevNode(Node prevNode){
        this.prevNode = prevNode;
    }
    
    /**
     * Palauttaa arvonaan Nodea edeltävän Noden.
     * @return
     */
    public Node getPrevNode(){
        return this.prevNode;
    }
    
    
    /**
     * Palauttaa arvonaan tähän asti kuljetun matkan.
     * @return
     */
    public int getMatka() {
        return this.matka;
    }
 
    /**
     * Laskee matkaa aloitusNodesta alkaen. Jokainen asken lisää matkaa yhdellä.
     */
    public void setMatka() {
        this.matka = this.prevNode.getMatka() + 1;
    }   
    
    public int getXCoord(){
         return this.lev;
    }
    
    public int getYCoord(){
        return this.kor;
    }
    

    /**
     * Asettaa matkan. Tämä metodi on ekaa nodea varten, asetetaan nollaksi 
     * koska ei ole olemassa prevnodea.
     * @param matka
     */
        public void setMatka(int matka) {
        if(this.prevNode == null){
            this.matka = 1;
        }else
            this.matka = this.prevNode.getMatka() + 1;
    }
    

}