/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaaja;

/**
 *
 * @author joonaskylliainen
 */

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Pakkaaja {
    
    
    public static void main( String[] args ) {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Anna pakattava: ");
        String lause = lukija.nextLine();
        ArrayList<Node> list = count(lause);
        PriorityQueue<Node> que = makeQueue(list);
        while (que.size() > 1) {
            Node a = que.poll();
            Node b = que.poll();
            que.add(makeNewNode(a,b));
        }    
        Node root = que.poll();
        
    }
    
//    public static HashMap<Character, Integer> count(String s) {
//        
//        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
//        
//        for(int i = 0; i < s.length(); i++) {            
//            char c = s.charAt(i);
//            Integer val = map.get(new Character(c));
//            if(val != null) {
//                map.put(c, new Integer(val + 1));
//            }
//            else {
//                map.put(c,1);
//            }    
//        }
//
//        return map;
//    }
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
    public static PriorityQueue<Node> makeQueue(ArrayList<Node> list) {
        PriorityQueue<Node> que = new PriorityQueue<Node>();
        for (int i = 0; i < list.size();i++) {
            que.add(list.get(i));           
        }
        return que;
    }
    public static Node makeNewNode(Node left, Node right) {
        char c = '*';
        Node nod = new Node(c, left.getFrequency() + right.getFrequency());
        nod.setLeft(left);
        nod.setRight(right);
        return nod;
    }
    
}
