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
import java.util.Iterator;
import java.util.PriorityQueue;


public class Packer {
    
    
    /**
     * Pakkaajan päämetodi, joka pakkaa sille annetun merkkijonon.
     * @param word syötetty merkkijono
     * @return pakkaus
     */
    public Paketti pack( String word ) {
        
        int[] list = count(word);
        PriorityQueue<Node> que = makeQueue(list);
        Tree tree = makeTree(que);
        String pakkaus = packing2(word, tree);
        Paketti paketti = new Paketti(tree,pakkaus);
       
        return paketti;
        
    }
    

    /**
     * laskee eri merkkien määrän tekstissä ja luo listan, joka sisältää Node olioita.
     * Olioilla on merkki ja esiintymistaajuus.
     * @param s käyttäjän antama syöte
     * @return dynaaminen lista joissa jokaisen merkki esiintymistaajuiksineen.
     */
    public static int[] count(String s) {
//        ArrayList<Node> list = new ArrayList<Node>();
//        ArrayList<Character> apulist = new ArrayList<Character>();
//        for(int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            Node nod = new Node(c, 1);
//
//            if (apulist.contains(c)) {
//                list.get(apulist.indexOf(c)).increaseFrequencyByOne();
//            }
//            else {
//                list.add(nod);
//            }
//         }
        
        char[] input = s.toCharArray();
        int[] freq = new int[256];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }
        
//        for ( Node a : list) {
//            System.out.println(a.getChar());
//        }
        return freq;
    }
    /**
     * tekee keon listasta, jossa on solmut esiintymistaajuuksineen.
     * @param list
     * @return keko
     */
    public static PriorityQueue<Node> makeQueue(int[] list) {
        PriorityQueue<Node> que = new PriorityQueue<Node>(256);
        for (char i = 0; i < 256;i++) {
            if (list[i] > 0) {
                que.add(new Node(i, list[i]));
            }           
        }
//        for (Iterator<Node> it = que.iterator(); it.hasNext();) {
//            Node a = it.next();
//            System.out.println(a.getChar() + "  " + a.getFrequency());
//        }
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
    public static String packing2(String s, Tree tree) {
        String pakkaus = "";
        char[] ca = s.toCharArray();
        String[] list = tree.makeDirectory();       
        for (int i = 0; i < s.length(); i++) {
            pakkaus += list[ca[i]];
            System.out.println(list[ca[i]] + "  " + ca[i]);
            
        }    
        return pakkaus;
    }
    
}
