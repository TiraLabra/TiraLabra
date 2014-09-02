/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import datastructures.Node;

/**
 * Puu, joka sisältää solmu-olioita. Puu on pakkaamisen ja purkamisen ydin.
 * 
 * @author joonaskylliainen
 */
public class Tree {

    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Tree() {
        this(null);
    }

    /**
     * palauttaa juuren
     * @return juurisolmu
     */
    public Node getRoot() {
        return root;
    }

    /**
     * asettaa juuren
     * @param root juurisolmu
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * palauttaa puun merkkijonoesityksen
     * @return merkkiesitys
     */
    @Override
    public String toString() {
        return "Tree["+root+"]";
    }

    /**
     * Tekee listan jossa jokainen kohta vastaa ascii-merkkiä. Kohdan sisältö on merkin binääriluku.
     * @return
     */
    public String[] makeDirectory() {
        String[] lista = new String[256];
        treeWalk(lista,root,"");
        return lista;
    }
    

    /**
     * Käy puun läpi ja tallentaa x muuttujaan paikan mukaisen binäärikoodin.
     * Lehteen osuessaan tallentaa sen siältämän kirjaimen kohtaan x:n sisältämän binäärikoodin.
     * @param list merkkilista
     * @param n solmu
     * @param x binäärikoodi
     */
    public void treeWalk(String[] list, Node n, String x) {
        if (n.getLeft() != null && n.getRight() != null) {
            treeWalk(list,n.getLeft(),x+"0");
            treeWalk(list,n.getRight(),x+"1");
        }else{
            list[n.getChar()] = x;
        }
    }
    
    public String treeToBinary() {
        String s = "";
            Character.valueOf('s');
        return s;
    }
    
}
