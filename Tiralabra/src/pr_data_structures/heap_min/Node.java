/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_data_structures.heap_min;

/**
 *
 * @author henrikorpela
 */
public class Node {
    public int key;
    private Object data;
    
    public Node(int key,Object data)
    {
        this.key = key;
        this.data = data;
    }
    
    public void set_data(Object new_data)
    {
        this.data = new_data;
    }
    
    public Object get_data()
    {
        return this.data;
    }
}
