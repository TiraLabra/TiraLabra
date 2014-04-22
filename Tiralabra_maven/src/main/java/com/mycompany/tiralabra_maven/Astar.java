/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 *
 * @author Tiina
 */
public class Astar {
    
    ArrayList openList = new ArrayList();
    ArrayList closedList = new ArrayList();
    ArrayList<Node> reitti = new ArrayList(); 
    
    private Wall tiili = new Wall();
    private Hiiri maus = new Hiiri(tiili);
    //maalin sijainti eli aina oikea alakulma.
    private Node goal = new Node(tiili.getMap().length -1, tiili.getMap().length-1);
    private Node start = new Node(0,0);
    private Node next;
    //matka lähdöstä maaliin 
    private int g=0;
    
    int[][] map = tiili.getMap();
    
    public Astar(){
        //hiiren sijainti kartalla. eli algoritmin eteneminen
        Node current = new Node(maus.getXcoord(), maus.getYcoord());
        //heuristiikka, laskee H:n arvon ja sijoittaa sen nodelle.
        int manhattanDist = Heuristics(current);
        current.setH(manhattanDist);
        current.setG(g);
        openList.add(current);
        reitti.add(current);
        seuraavaNode(current);
        reitin_lapikaynti(reitti);
    }
        
        //laskee naapurinodejen heuristiikat ja valitsee pienimmän arvon saaneen nextnodeksi.
    
    public void seuraavaNode(Node current){
        
        if(current.lev-1 >= 0){  
            Node vasen = new Node(current.lev - 1, current.kor);
            vasen.setH(Heuristics(vasen));
            if(vasen.getH() == 0) System.out.println("loppu");
            if(next == null || next.getH() >= vasen.getH() && next.getH() != 0)
            next = new Node(vasen.lev, vasen.kor, vasen.getH());
        }
        
        if(current.lev + 1 <= tiili.leveys-1){
            Node oikea = new Node(current.lev + 1, current.kor);
            oikea.setH(Heuristics(oikea));
            if(next == null || next.getH() >= oikea.getH() && next.getH() != 0){
                next = new Node(oikea.lev, oikea.kor, oikea.getH());
            }
        }
        
        if(current.kor-1 >= 0){  
            Node yla = new Node(current.lev, current.kor -1);
            yla.setH(Heuristics(yla));
            if(next.getH() >= yla.getH() && next.getH() != 0 || next == null){
                next = new Node(yla.lev, yla.kor, yla.getH());
            }
        }
        
        if(current.kor + 1 <= tiili.korkeus -1){
            Node ala = new Node(current.lev, current.kor +1);
            ala.setH(Heuristics(ala));
            if(next.getH() >= ala.getH() && next.getH() != 0 || next == null){
                next = new Node(ala.lev, ala.kor, ala.getH());
            }
        }
        
       
        //lisätään reittiin pienimmän heuristiikan omaava node.
        reitti.add(next);
        maus.setCoord(next.lev*50, next.kor*50);   

        
                
        while(next.getH()>0){            
           seuraavaNode(next);
        }
        
    }
    
    public void reitin_lapikaynti(ArrayList<Node> reitti){
        int i;
        for(int k = reitti.size()-1; k >= 0; k--){
            //maus.setCoord(reitti.get(k).lev+50, reitti.get(k).kor+50);   
        }
    }
    
        
    /**
     * Laskee heuristiikan nodelle.
     * 
     * Eli etäisyys nodesta maaliin + lähdöstä nodeen?
     * @param node
     * @return
     */
    public int Heuristics(Node node){
        //Manhattan-etäisyys nodesta maaliin.
        int m = (goal.lev - node.lev) + (goal.kor - node.kor);
        return m;
    }
}
