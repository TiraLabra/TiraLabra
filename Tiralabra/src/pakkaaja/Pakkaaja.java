/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaaja;

/**
 *
 * @author joonaskylliainen
 */

 // import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Pakkaaja {
    
    
    /**
     * Pakkaajan päämetodi, joka pakkaa sille annetun merkkijonon.
     * @param word syötetty merkkijono
     * @return pakkaus
     */
    public String pack( String word ) {
        
        ArrayList<Node> list = count(word);
        PriorityQueue<Node> que = makeQueue(list);
        Tree tree = makeTree(que);
        String pakkaus = packing(word, tree);
        
        return pakkaus;
        
    }
    

    /**
     * laskee eri merkkien määrän tekstissä ja luo listan, joka sisältää Node olioita.
     * Olioilla on merkki ja esiintymistaajuus.
     * @param s käyttäjän antama syöte
     * @return dynaaminen lista joissa jokaisen merkki esiintymistaajuiksineen.
     */
    public static ArrayList<Node> count(String s) {
        ArrayList<Node> list = new ArrayList<Node>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Node nod = new Node(c, 1);
            if (list.contains(nod)) {
                list.get(list.indexOf(nod)).increaseFrequencyByOne();
            }
            else {
                list.add(nod);
            }
         }
        return list;
    }
    /**
     * tekee keon listasta, jossa on solmut esiintymistaajuuksineen.
     * @param list
     * @return keko
     */
    public static PriorityQueue<Node> makeQueue(ArrayList<Node> list) {
        PriorityQueue<Node> que = new PriorityQueue<Node>(list.size()+1);
        for (int i = 0; i < list.size();i++) {
            que.add(list.get(i));           
        }
        return que;
    }
    /**
     * Tekee sille annetusta keosta Huffmann-puun
     * @param que keko
     * @return puu
     */
    public static Tree makeTree(PriorityQueue<Node> que) {
        while (que.size() > 1) {
            Node a = que.poll();
            Node b = que.poll();
            que.add(makeNewNode(a,b));
        }    
        Node root = que.poll();
        Tree tree = new Tree(root);
        return tree;
    }
    
    /**
     * tekee uuden solmun, eli Node-olion kekoa varten.
     * Solmun taajuus on lapsien taajuuksien summa
     * Käytetään silloin kun keosta tehdään puuta.
     * @param left solmun vasen lapsi
     * @param right solmun oikea lapsi
     * @return uusi solmu
     */
    public static Node makeNewNode(Node left, Node right) {
        char c = '*';
        Node nod = new Node(c, left.getFrequency() + right.getFrequency());
        nod.setLeft(left);
        nod.setRight(right);
        return nod;
    }
    /**
     * tekee puusta ja annetusta merkkijonosta pakatun version.
     * @param s merkkijono
     * @param tree Huffmann-puu
     * @return pakkaus
     */
    public static String packing(String s, Tree tree) {
        String pakkaus = "";
        for(int i = 0; i < s.length(); i++) {
            pakkaus = pakkaus + tree.find(s.charAt(i));
        }
        return pakkaus;
    }
    
}
