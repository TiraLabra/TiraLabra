/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_launcher;

import pr_data_structures.stack.Stack;
import pr_pathfinding.Path_instructions;

/**
 *
 * @author henrikorpela
 */
public class Main {
    public static void main(String args[])
    {
        Stack<Integer> list = new Stack<Integer>();
         list.add(1);
         list.add(2);
         list.add(3);
         list.add(4);
         list.add(5);
         System.out.println(list);
         list.remove(3);
         System.out.print(list);
         
    }
}
