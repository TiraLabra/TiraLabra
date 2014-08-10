/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaaja;

/**
 * Puu
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
     * etsii tietyn merkin rekursiivisesti käyttäen treeWalk-metodia ja palauttaa merkin pakkausmuodon
     * @param c etsittävä merkki
     * @param root puun juuri
     * @return pakkausmuoto (binäärikoodi) String-muodossa
     */
    public String find(char c) {
        String x = "";
        x = treeWalk(c, this.root, x);
        return x;
    }
    
    /**
     * käy puuta läpi rekursiivisesti ja lisää merkin pakkausmuotoon 0 jos se on vanhemman vasemmalta puolelta
     * ja 1 jos se on oikealta puolelta.
     * @param c merkki jota etsitään
     * @param root aluksi puun juuri
     * @param x pakkausmuoto
     * @return x
     */
    public String treeWalk(char c, Node root, String x) {
        if (root != null || root.getChar() != c) {
            treeWalk(c, root.getLeft(), x + "0");
            treeWalk(c, root.getRight(), x + "1");   
        } 
        return x;
    }
    
}
