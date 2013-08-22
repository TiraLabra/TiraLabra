/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_data_structures.HashMap;

import pr_data_structures.stack.Stack;

/**
 *
 * @author henrikorpela
 */
public class First_node {
    Stack<Node> list;
    public First_node()
    {
        this.list = new Stack<Node>();
    }
    
    public void add(Node node)
    {
        if(this.list.contains(node)) // Data already exsists in list.
        {
            Node node_in_list = this.list.find(node);
            node_in_list.set_data(node.get_data());
        }
        this.list.add(node); // Data doesn't exist in list.
    }
    
    public Object get(Object key)
    {
        Node node = this.list.find(key);
        if(node == null)
        {
            return null;
        }
        return node.get_data();
    }
    
    public void delete(Node node)
    {
        this.list.remove(node);
    }
}
