/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaaja;

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
     * etsii tietyn merkin rekursiivisesti käyttäen treeWalk-metodia ja palauttaa merkin pakkausmuodon
     * @param c etsittävä merkki
     * @param root puun juuri
     * @return pakkausmuoto (binäärikoodi) String-muodossa
     */
    public String find(char c) {
        String x = "";
        treeWalk(c, this.root, x);
        return x;
    }
    /**
     * Tekee listan jossa jokainen kohta vastaa ascii-merkkiä. Kohdan sisältö on merkin binääriluku.
     * @return
     */
    public String[] makeDirectory() {
        String[] lista = new String[256];
        treeWalk2(lista,root,"");
        return lista;
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
        if (root == null) {
            return x;
        }
        if (root.getChar() == c) {
            return x;
        }
        if (root.getLeft() != null) {
            x = treeWalk(c, root.getLeft(), x + "0");
        }
        if (root.getRight() != null) {
            x = treeWalk(c, root.getRight(), x + "1");
        }
        return x;
    }
    /**
     * Käy puun läpi ja tallentaa x muuttujaan paikan mukaisen binäärikoodin.
     * Lehteen osuessaan tallentaa sen siältämän kirjaimen kohtaan x:n sisältämän binäärikoodin.
     * @param list merkkilista
     * @param n solmu
     * @param x binäärikoodi
     */
    public void treeWalk2(String[] list, Node n, String x) {
        if (n.getLeft() != null && n.getRight() != null) {
            treeWalk2(list,n.getLeft(),x+"0");
            treeWalk2(list,n.getRight(),x+"1");
        }else{
            list[n.getChar()] = x;
        }
    }
    
    
}
