/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_data_structures.array_list;

/**
 *
 * @author henrikorpela
 * 
 * Array_lists node.
 */
public class Node {
    private Object data;
    
    public Node(Object data)
    {
        this.data = data;
    }
    
    public void setData(Object new_data)
    {
        this.data = new_data;
    }
    
    public Object getData()
    {
        return this.data;
    }
}
