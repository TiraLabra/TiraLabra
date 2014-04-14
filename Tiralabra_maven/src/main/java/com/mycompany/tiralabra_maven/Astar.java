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
    ArrayList reitti = new ArrayList(); 
    
    private Wall tiili = new Wall();
    private Hiiri maus = new Hiiri(tiili);
    
    int[][] map = tiili.getMap();
    
    public void star(){
        //hiiren sijainti kartalla. eli algoritmin eteneminen
        Node current = new Node(maus.getXcoord(), maus.getYcoord());
        Node goal = new Node(tiili.getMap().length -1, tiili.getMap().length-1);

        int manhattanDist = (goal.lev - current.lev) + (goal.kor - current.kor);
        current.setH(manhattanDist);
        
    }
}
