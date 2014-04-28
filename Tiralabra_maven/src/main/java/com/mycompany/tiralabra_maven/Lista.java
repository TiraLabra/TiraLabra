/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Tähän luokkaan pitäisi tulla myös Nodelista kokonaisuudessaan.
 * 
 */
public class Lista {
    
    private ArrayList<Node> lista = new ArrayList();
    private Node apu, node;
    private Lista list;

    /**
     * Järjestää listan suuruusjärjestykseen.
     * @param lista
     */
    public void sorttaa(ArrayList<Node> lista){
        this.lista = lista;   
        for(int j = 0; j < lista.size(); j++){
            for(int i = lista.size()-1; i > j; i--){
                this.node = lista.get(i);
                //tulevaa arraylistin omaa toteutusta varten nodet saisi vaihdettua näin:
 //              if(node.getH() < node.getPrevNode().getH()){
                    //System.out.println(node.getH() + "lev: "  + node.lev + " korkeus " + node.kor + "prevnode geth " + node.getPrevNode().getH());
//                    this.apu = node;
//                    this.node = node.getPrevNode();
//                    this.node.setPrevNode(apu);

                if(lista.get(i).getH() < lista.get(i-1).getH()){       
                    //System.out.println(lista.toString());
                    apu = lista.get(i);
                    lista.remove(i);
                    lista.add(i, lista.get(i-1));
                    lista.remove(i-1);
                    lista.add(i-1, apu);         
                }
                }
            }
        }
     
    
}
    

