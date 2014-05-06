/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Luokka Astar-algoritmin ratkaisua varten. Etsii lyhintä reittiä labyrintista
 * ulos.
 * 
 */
public class Astar {
    
    ArrayList<Node> openList = new ArrayList();
    ArrayList<Node> closedList = new ArrayList();
    ArrayList<Node> reitti = new ArrayList(); 
    
    private Wall tiili = new Wall();
    private Hiiri maus = new Hiiri(tiili);
    //maalin sijainti eli aina oikea alakulma.
    private Node goal = new Node(tiili.getMap().length -1, tiili.getMap().length-1);
    private Node start = new Node(0,0);
    private Node next;
    private int manh;
    private Lista lista = new Lista();
    //matka lähdöstä maaliin alussa
    private int matka=0;
    
    int[][] map = tiili.getMap();
    
    /**
     * Alustaa lähtökohtaan hiiren koordinaateiksi lähtökoordinaatit. Laskee ja
     * asettaa myös tarvittavat arvot ensimmäiselle Nodelle.
     */
    public Astar(){
        //hiiren sijainti kartalla aluksi.
        Node current = new Node(maus.getXcoord(), maus.getYcoord());
        //heuristiikka, laskee H:n arvon ja sijoittaa sen nodelle.
        int manhattanDist = Heuristics(current);
        current.setH(manhattanDist);
        current.setMatka(matka);
        openList.add(current);
        //reitti.add(current);
        seuraavaNode(current);
        //this.reitti = reitin_lapikaynti();
    }
        
    
    /**
     * Tämä luokka kutsuu rekursiivisesti itseään ja ratkaisee Astarin avulla 
     * lyhimmän reitin.
     * Laskee naapurinodejen heuristiikat ja valitsee pienimmän arvon saaneen
     * seuraavaksi nodeksi.
     * @param current
     */
        
    public void seuraavaNode(Node current){
        
        if(current.lev-1 >= 0){  
            Node vasen = new Node(current.lev - 1, current.kor);
            if(!closedList.contains(vasen)){
                if(!vasen.onkoSeina(vasen)){
                    for(Node n : openList){
                        if(Contains(n, vasen))
                    {
                        if(vasen.getMatka() > current.getMatka()+1){
                            vasen.setPrevNode(current);
                        }}}else{
                    vasen.setPrevNode(current);
                    vasen.setH(Heuristics(vasen));
                    vasen.setMatka();
                    openList.add(vasen);
                        } 
                }else vasen.setH(5000);
           }
        }
        
        if(current.lev + 1 <= tiili.leveys-1){
            Node oikea = new Node(current.lev + 1, current.kor);
            if(!closedList.contains(oikea)){            
                if(!oikea.onkoSeina(oikea)){
                   if(openList.contains(oikea)){
                        if(oikea.getMatka() > current.getMatka()+1){
                            oikea.setPrevNode(current);
                    }}else {
                    oikea.setPrevNode(current);
                    oikea.setH(Heuristics(oikea));
                    oikea.setMatka();
                    openList.add(oikea);
                        }
                }else oikea.setH(5000);
            }
        }
        
        if(current.kor-1 >= 0){  
            Node yla = new Node(current.lev, current.kor -1);
            if(!closedList.contains(yla)){
                if(!yla.onkoSeina(yla)){
                                        if(openList.contains(yla)){
                        if(yla.getMatka() > current.getMatka()+1){
                            yla.setPrevNode(current);
                        }}else{
                    yla.setPrevNode(current);
                    yla.setH(Heuristics(yla));
                    yla.setMatka();
                    openList.add(yla);
                        }
                }else yla.setH(5000);
            }
        }
        
        if(current.kor + 1 <= tiili.korkeus -1){
            Node ala = new Node(current.lev, current.kor +1);
            //System.out.println(tiili.korkeus);
            if(!closedList.contains(ala)){
                if(!ala.onkoSeina(ala)){
                    if(openList.contains(ala)){
                        if(ala.getMatka() > current.getMatka()+1){
                            ala.setPrevNode(current);
                    }}else{
                    ala.setPrevNode(current);
                    ala.setH(Heuristics(ala));
                    ala.setMatka();
                    openList.add(ala);
                        }
                }else {ala.setH(5000);
                       //closedList.add(ala);
                }
            }
        }
        
        if(openList.size() != 0){
            //if((openList.get(0).getH() >= 5000)){
            closedList.add(current);
            openList.remove(current);
            lista.sorttaa(openList);
            next = openList.get(0);
//            if(next.getPrevNode() != current){
//                int i = reitti.size()-1;
//                while(reitti.get(i) != next.getPrevNode()){
//                    reitti.remove(i);
//                    i = i-1;
//                }
//            }
            
                //next.setPrevNode(current);
                //reitti.add(next);
            
            
            if(next.getH() == 0){
                goal = next;
                while(goal.getPrevNode() != null){
                    reitti.add(goal);
                    
                    goal = goal.getPrevNode();
                }
            }
            
        while(next.getH()>0){ 
        //maus.setCoord(next.lev*50, next.kor*50);             
           seuraavaNode(next);
        }
        }
        
    }
    
    /**
     * 
     * @param reitti
     */
    public ArrayList<Node> reitin_lapikaynti(){
        int i;
        Node eka = goal;
        while (eka.getPrevNode() == start){
            reitti.add(eka);
            eka = eka.getPrevNode();
        }
        return reitti;
    }
    
        
    /**
     * Laskee heuristiikan nodelle.
     * 
     * Eli etäisyys nodesta maaliin + lähdöstä nodeen
     * @param node
     * @return
     */
    public int Heuristics(Node node){
        matka = node.getMatka();
        //Manhattan-etäisyys nodesta maaliin.
        manh = (goal.lev - node.lev) + (goal.kor - node.kor);
        return manh + matka;
    }
    
   
    public boolean Contains(Node node,Node seur)
    {
            if (node.kor == seur.kor && node.lev == seur.lev)
            return true;
        

            else return false;
    }
    }

    

